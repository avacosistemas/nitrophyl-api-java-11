package ar.com.avaco.ws.rest.informe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPruebaCondicion;
import ar.com.avaco.nitrophyl.domain.entities.formula.RevisionParametros;
import ar.com.avaco.nitrophyl.domain.entities.lote.Ensayo;
import ar.com.avaco.nitrophyl.domain.entities.lote.EnsayoResultado;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteLoteConfiguracionCliente;
import ar.com.avaco.nitrophyl.service.reporte.ReporteLoteConfiguracionClienteService;
import ar.com.avaco.nitrophyl.ws.dto.ArchivoDTO;
import ar.com.avaco.utils.DateUtils;

public class InformeBuilder {

	private static final BaseColor COLOR_GRIS_BORDES = new BaseColor(238, 238, 238);

	private final static Font fontHeaderTable = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);
	private final static Font fontText = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

	public ArchivoDTO generarReporte(Lote lote, ReporteLoteConfiguracionClienteService serviceConfiguracion,
			Cliente cliente, String observacionesInforme) throws DocumentException, IOException, URISyntaxException, ErrorValidationException {

		// Obtengo la empresa para el logo
		String empresa = cliente.getEmpresa().name();

		Document document = new Document(PageSize.A4);

		try {
			ArchivoDTO adto = new ArchivoDTO();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

//			PdfWriter.getInstance(document, new FileOutputStream(
//					"c:\\nitrophyl\\nitrophyl-" + Calendar.getInstance().getTimeInMillis() + ".pdf"));

			PdfWriter.getInstance(document, baos);

//			writer.setPageEvent(new PDFEventHelper());

			document.open();
			document.setMargins(20, 20, 10, 10);

			// Obtengo la revisin de parametros para esa formula y obtengo el nro de
			// revision y la fecha
			RevisionParametros revision = lote.getRevisionParametros();
			Long revisionNro = revision != null ? revision.getRevision() : -1;
			String fecha = revision != null ? DateUtils.toStringFecha(revision.getFecha()) : "SINFECHA";

			// Armo el encabezado con el logo, revision y fecha
			Element encabezado = generarEncabezado(empresa, revisionNro, fecha);
			document.add(encabezado);

			// Dejo un espacio
			Paragraph element = new Paragraph(" ");
			document.add(element);

			// Armo la seccion de datos del lote
			Element datosLote = addDatosLotes(lote);
			generarSeccion(document, datosLote, null, null);

			// Obtengo todas las maquinas configuradas para la revision de ese lote
			List<ConfiguracionPrueba> configuracionesRevision = lote.getRevisionParametros().getConfiguraciones()
					.stream().sorted(Comparator.comparing(ConfiguracionPrueba::getPosicion))
					.collect(Collectors.toList());

			// Obtengo las configuraciones de reporte para la formula seleccionada y
			// cliente. Tambien obtengo las generales de esa formula.
			List<ReporteLoteConfiguracionCliente> configuracion = serviceConfiguracion
					.findConfiguracionesByClienteFormula(lote.getFormula(), cliente);

			Map<Long, Ensayo> ensayosPorMaquina = generarMapaEnsayos(lote.getEnsayos());

			for (ConfiguracionPrueba parametro : configuracionesRevision) {
				Long idMaquina = parametro.getMaquina().getId();

				Ensayo ensayo = ensayosPorMaquina.get(idMaquina);

				if (ensayo == null)
					ensayo = generarEnsayoVacio(parametro);

				ReporteLoteConfiguracionCliente reporteLoteConfiguracionCliente = serviceConfiguracion.buscarConfiguracion(cliente.getId(),
						configuracion, idMaquina);

				if (reporteLoteConfiguracionCliente != null) {
					Element addEnsayo = addEnsayo(ensayo, reporteLoteConfiguracionCliente);
					generarSeccion(document, addEnsayo, null, null);
				}

			}

			String string = "La parametrizacion entre los valores de reometria y las propiedades "
					+ "fsicas establecidas por Norma, fue realizada en nuestro laboratorio, "
					+ "en base a un estudio entre una curva patrn normalizada y la medicin directa de "
					+ "los ensayos fsicos descriptos por Norma bajo condiciones reguladas (I-LAB-018).";

			agregarObservacionesInforme(document, string);

			if (StringUtils.isNoneBlank(observacionesInforme)) {
				agregarObservacionesInforme(document, observacionesInforme);
			}
			
			generarFirma(document);
			document.close();
			adto.setArchivo(baos.toByteArray());
			adto.setNombre("Informe Calidad - " + cliente.getNombre().replace(".", "") + " - " + lote.getNroLote() + ".pdf");

			return adto;
		} catch (DocumentException e) {
			document.close();
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			document.close();
			e.printStackTrace();
			throw e;
		}

	}

	private void agregarObservacionesInforme(Document document, String string) throws DocumentException {
		PdfPCell cellBorder = new PdfPCell();
		cellBorder.setCellEvent(new RoundRectangle());
		cellBorder.setBorder(Rectangle.NO_BORDER);
		cellBorder.setPadding(10f);
		cellBorder.setPaddingTop(0);
		cellBorder.addElement(new Phrase(string, fontText));

		PdfPTable tableBorder = new PdfPTable(1);
		tableBorder.setWidthPercentage(100);
		tableBorder.addCell(cellBorder);

		document.add(tableBorder);
	}

	private Ensayo generarEnsayoVacio(ConfiguracionPrueba parametro) {
		Ensayo ensayo = new Ensayo();
		ensayo.setConfiguracionPrueba(parametro);
		parametro.getParametros().forEach(x -> {
			EnsayoResultado er = new EnsayoResultado();
			er.setConfiguracionPruebaParametro(x);
			ensayo.getResultados().add(er);
		});
		return ensayo;
	}

	private Map<Long, Ensayo> generarMapaEnsayos(Set<Ensayo> ensayos) {
		Map<Long, Ensayo> mapa = new HashMap<Long, Ensayo>();
		ensayos.forEach(x -> {
			mapa.put(x.getConfiguracionPrueba().getMaquina().getId(), x);
		});
		return mapa;
	}

	private Element generarEncabezado(String empresa, Long revision, String fecha)
			throws BadElementException, MalformedURLException, IOException, URISyntaxException, DocumentException {
		PdfPTable table = generateTable(3);
		PdfPCell cell = getPDFPCell();
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		URL resource = null;
		if (empresa.equals("NITROPHYL")) {
			resource = getClass().getClassLoader().getResource("nitro-logo.jpg");
		} else {
			resource = getClass().getClassLoader().getResource("elasint-logo.jpg");
		}
		cell.addElement(Image.getInstance(new File(resource.toURI()).getAbsolutePath()));
		table.addCell(cell);
		cell.setPhrase(
				new Phrase("INFORME DE CALIDAD", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK)));
		table.addCell(cell);

		PdfPTable tabladerecha = generateTable(2);
		tabladerecha.setWidths(new float[] { 35, 65 });

		PdfPCell fila = getPDFPCell();
		fila.setPhrase(new Phrase("Fecha: ", fontHeaderTable));
		fila.setBorder(0);
		tabladerecha.addCell(fila);

		fila = getPDFPCell();
		fila.setPhrase(new Phrase("05/12/2024", fontText));
		fila.setBorder(0);
		tabladerecha.addCell(fila);

		fila = getPDFPCell();
		fila.setPhrase(new Phrase("Cdigo:", fontHeaderTable));
		fila.setBorder(0);
		tabladerecha.addCell(fila);

		fila = getPDFPCell();
		fila.setPhrase(new Phrase("R-LAB-006", fontText));
		fila.setBorder(0);
		tabladerecha.addCell(fila);

		fila = getPDFPCell();
		fila.setPhrase(new Phrase("Rev.: ", fontHeaderTable));
		fila.setBorder(0);
		tabladerecha.addCell(fila);

		fila = getPDFPCell();
		fila.setPhrase(new Phrase(revision + " - " + fecha, fontText));
		fila.setBorder(0);
		tabladerecha.addCell(fila);

		PdfPCell celdatabladerecha = generarCeldaBordeRedondeado(null, null);
		celdatabladerecha.addElement(tabladerecha);
		celdatabladerecha.setBorder(0);

		table.addCell(celdatabladerecha);

		Paragraph p = new Paragraph();
		p.add(table);

		return p;
	}

	private void generarFirma(Document document)
			throws BadElementException, MalformedURLException, IOException, URISyntaxException, DocumentException {
		PdfPTable table = generateTable(2);
		PdfPCell cell = getPDFPCell();
		cell.setBorder(0);

		URL resource = getClass().getClassLoader().getResource("firmas.jpg");
		Image firmaRomina = Image.getInstance(new File(resource.toURI()).getAbsolutePath());
		firmaRomina.setWidthPercentage(100);
		cell.addElement(firmaRomina);
		table.addCell(cell);

		cell = getPDFPCell();
		cell.setBorder(0);
//		resource = getClass().getClassLoader().getResource("firma-graciela.jpg");
//		Image firmagraciela = Image.getInstance(new File(resource.toURI()).getAbsolutePath());
//		firmagraciela.setAlignment(Element.ALIGN_RIGHT);
//		firmagraciela.setWidthPercentage(50);
		table.addCell(cell);

		document.add(table);
	}

	private Element addDatosLotes(Lote lote) throws DocumentException {
		String fecha = DateUtils.toString(lote.getFecha(), DateUtils.dd_MM_yyyy);
		String material = lote.getFormula().getMaterial().getNombre();
		String formula = lote.getFormula().getNombre();
		String norma = lote.getFormula().getNorma();
		String loteNro = lote.getNroLote();

		PdfPTable table = new PdfPTable(new float[] { 20, 40, 20, 20 });
		table.setWidthPercentage(100);
		PdfPCell cell = getPDFPCell();

		cell.setPhrase(new Phrase("FECHA", fontHeaderTable));
		table.addCell(cell);
		cell.setPhrase(new Phrase(fecha, fontText));
		table.addCell(cell);
		cell.setPhrase(new Phrase("MATERIAL", fontHeaderTable));
		table.addCell(cell);
		cell.setPhrase(new Phrase(material, fontText));
		table.addCell(cell);
		cell.setPhrase(new Phrase("OBJETIVO", fontHeaderTable));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Aprobacin de Lote", fontText));
		table.addCell(cell);
		cell.setPhrase(new Phrase("GRADO", fontHeaderTable));
		table.addCell(cell);
		cell.setPhrase(new Phrase(formula, fontText));
		table.addCell(cell);

		cell.setPhrase(new Phrase("REF. NORMA", fontHeaderTable));
		cell.setBorder(0);
		table.addCell(cell);
		cell.setPhrase(new Phrase(norma, fontText));
		cell.setBorder(0);
		table.addCell(cell);
		cell.setPhrase(new Phrase("LOTE Nro.", fontHeaderTable));
		cell.setBorder(0);
		table.addCell(cell);
		cell.setPhrase(new Phrase(loteNro, fontText));
		cell.setBorder(0);
		table.addCell(cell);

		return table;

	}

	private Element addEnsayo(Ensayo ensayo, ReporteLoteConfiguracionCliente config) throws DocumentException {

		Paragraph p = new Paragraph();

		boolean mostrarParametros = false;
		boolean mostrarResultados = false;
		boolean mostrarCondiciones = false;
		boolean mostraObervacionesParametros = false;
		boolean mostrarTodasLasPruebas = false;

		if (config != null) {
			mostrarParametros = config.getMostrarParametros();
			mostrarResultados = config.getMostrarResultados();
			mostrarCondiciones = config.getMostrarCondiciones();
			mostraObervacionesParametros = config.getMostrarObservacionesParametro();
			mostrarTodasLasPruebas = config.getMaquina() == null;
		}

		// Principal: si no muestra parametros el resto no importa
		if (mostrarParametros) {

			int rowspanpruebas = ensayo.getResultados().size();
			int rowspancondiciones = ensayo.getConfiguracionPrueba().getCondiciones().isEmpty() ? 0 : 1; // ensayo.getConfiguracionPrueba().getCondiciones().size();

			// Armo la tabla de resultados
			// Contiene prueba, min, max, resultado y norma
			float[] colsConResultado = new float[] { 25, 30, 10, 10, 10, 20 };

			float[] cols = colsConResultado;

			PdfPTable tableResultados = new PdfPTable(cols);
			tableResultados.setWidthPercentage(100);
			tableResultados.setSpacingAfter(20);

			// Cabecera

			String observacionesMaquina = "";
			if (StringUtils.isNotBlank(ensayo.getConfiguracionPrueba().getMaquina().getObservacionesReporte())) {
				observacionesMaquina = ensayo.getConfiguracionPrueba().getMaquina().getObservacionesReporte();
			}
			String observacionesParametrizacion = mostraObervacionesParametros
					? ensayo.getConfiguracionPrueba().getObservacionesReporte()
					: "";
			boolean hayObservaciones = StringUtils.isNotEmpty(observacionesParametrizacion)
					|| StringUtils.isNotEmpty(observacionesMaquina);

			boolean hayCondiciones = mostrarCondiciones && !ensayo.getConfiguracionPrueba().getCondiciones().isEmpty();

			boolean first = true;

			int cantidadPruebas = ensayo.getResultados().size();
			int posPrueba = 1;

			PdfPCell cell;

			Set<EnsayoResultado> resultadossinorden = ensayo.getResultados();

			List<EnsayoResultado> resultados = resultadossinorden.stream()
					.sorted(Comparator.comparing(EnsayoResultado::getPosicion)).collect(Collectors.toList());

			Map<String, String> resultadosError = new HashMap<>();
			
			for (EnsayoResultado resultado : resultados) {

				boolean existePrueba = config.getPruebas().stream().filter(
						x -> x.getId() == resultado.getConfiguracionPruebaParametro().getMaquinaPrueba().getId())
						.findAny().isPresent();

				boolean mostrarPrueba = mostrarTodasLasPruebas || existePrueba;

				if (mostrarPrueba) {
					boolean ultimo = posPrueba == cantidadPruebas;
					posPrueba++;

					Double minimo = resultado.getConfiguracionPruebaParametro().getMinimo();
					Double maximo = resultado.getConfiguracionPruebaParametro().getMaximo();
					String nombre = resultado.getConfiguracionPruebaParametro().getMaquinaPrueba().getNombre();
					String norma = resultado.getConfiguracionPruebaParametro().getNorma();

					if (first) {

						cell = getPDFPCell();

						cell.setPhrase(new Phrase(resultado.getConfiguracionPruebaParametro().getMaquinaPrueba()
								.getMaquina().getNombre().toUpperCase(), fontHeaderTable));
						tableResultados.addCell(cell);

						cell = getPDFPCell();
						cell.setPhrase(new Phrase("Nombre", fontHeaderTable));
						tableResultados.addCell(cell);

						cell = getPDFPCell();
						cell.setPhrase(new Phrase("Min", fontHeaderTable));
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						tableResultados.addCell(cell);

						cell = getPDFPCell();
						cell.setPhrase(new Phrase("Max", fontHeaderTable));
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						tableResultados.addCell(cell);

						if (mostrarResultados) {
							cell = getPDFPCell();
							cell.setPhrase(new Phrase("Valor", fontHeaderTable));
							cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							tableResultados.addCell(cell);
						} else {
							cell = getPDFPCell();
							cell.setPhrase(new Phrase("", fontHeaderTable));
							cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							tableResultados.addCell(cell);
						}

						cell = getPDFPCell();
						cell.setPhrase(new Phrase("Norma", fontHeaderTable));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						tableResultados.addCell(cell);

						cell = getPDFPCell();
						cell.setRowspan(rowspanpruebas);
						cell.setPhrase(new Phrase("Ensayo", fontHeaderTable));
						cell.setBorder(0);
						cell.setBorderWidthRight(1);
						cell.setBorderWidthBottom(1);
						cell.setBorderColorRight(COLOR_GRIS_BORDES);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						if (!hayObservaciones && !hayCondiciones) {
							cell.setBorderWidthBottom(0);
						}
						tableResultados.addCell(cell);

						first = false;
					}

					cell = getPDFPCell();
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPhrase(new Phrase(nombre, fontText));
					if (!hayObservaciones && !hayCondiciones && ultimo) {
						cell.setBorderWidthBottom(0);
					}
					tableResultados.addCell(cell);

					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPhrase(new Phrase(minimo != null ? String.format("%.2f", minimo) : "", fontText));
					if (!hayObservaciones && !hayCondiciones && ultimo) {
						cell.setBorderWidthBottom(0);
					}
					tableResultados.addCell(cell);

					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPhrase(new Phrase(maximo != null ? String.format("%.2f", maximo) : "", fontText));
					if (!hayObservaciones && !hayCondiciones && ultimo) {
						cell.setBorderWidthBottom(0);
					}
					tableResultados.addCell(cell);

					if (mostrarResultados) {
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						
						if (resultado.getRedondeo() == null) {
							resultadosError.put(nombre, "No tiene resultado");
						} else {
							cell.setPhrase(new Phrase(String.format("%.2f", resultado.getRedondeo()), fontText));
							if (!hayObservaciones && !hayCondiciones && ultimo) {
								cell.setBorderWidthBottom(0);
							}
							tableResultados.addCell(cell);
						}
					} else {
						cell = getPDFPCell();
						cell.setPhrase(new Phrase("", fontHeaderTable));
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						tableResultados.addCell(cell);
					}

					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPhrase(new Phrase(norma, fontText));

					if (!hayObservaciones && !hayCondiciones && ultimo) {
						cell.setBorderWidthBottom(0);
					}

					tableResultados.addCell(cell);
				}
			}
			
			if (!resultadosError.isEmpty()) {
				throw new ErrorValidationException("Faltan cargar resultados en la mquina " + ensayo.getConfiguracionPrueba().getMaquina().getNombre(), resultadosError);
			}

			first = true;

			// Condiciones

			if (hayCondiciones) {

				cell = getPDFPCell();
				cell.setPhrase(new Phrase("Condiciones", fontHeaderTable));
				cell.setRowspan(rowspancondiciones);
				cell.setBorder(0);
				cell.setBorderWidthRight(1);
				cell.setBorderColorRight(COLOR_GRIS_BORDES);
				cell.setBorderWidthBottom(1);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				first = false;
				if (!hayObservaciones) {
					cell.setBorderWidthBottom(0);
				}
				tableResultados.addCell(cell);

				String condiciones = "";

				first = true;

				for (ConfiguracionPruebaCondicion condicion : ensayo.getConfiguracionPrueba().getCondiciones()) {

					if (!first) {
						condiciones += " - ";
					}
					first = false;

					condiciones += condicion.getNombre() + ": " + String.format("%.2f", condicion.getValor());

				}

				cell = getPDFPCell();
				cell.setColspan(cols.length - 1);
				cell.setPhrase(new Phrase(condiciones, fontText));

				if (!hayObservaciones) {
					cell.setBorderWidthBottom(0);
				}

				tableResultados.addCell(cell);

			}

			p.add(tableResultados);

			if (hayObservaciones) {

				cell = getPDFPCell();
				cell.setPhrase(new Phrase("Observaciones", fontHeaderTable));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderColorRight(COLOR_GRIS_BORDES);
				cell.setBorderWidthBottom(0);
				cell.setBorderWidthRight(1);
				tableResultados.addCell(cell);

				int colspan = cols.length;
				cell = getPDFPCell();
				String string = observacionesMaquina;
				if (StringUtils.isNotBlank(observacionesParametrizacion)
						&& StringUtils.isNotBlank(observacionesMaquina)) {
					string += System.lineSeparator();
				}
				string += observacionesParametrizacion;
				cell.setPhrase(new Phrase(string, fontText));
				cell.setColspan(colspan - 1);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidthBottom(0);
				tableResultados.addCell(cell);
			}

		}

		return p;

	}

	private PdfPTable generateTable(int columns) {
		PdfPTable table = new PdfPTable(columns);
		table.setWidthPercentage(100);
		return table;
	}

	private PdfPCell getPDFPCell() {
		PdfPCell cell = new PdfPCell();
		cell.setUseAscender(true);
		cell.setBorder(0);
		cell.setBorderWidthBottom(1);
		cell.setBorderColorBottom(COLOR_GRIS_BORDES);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5);
		return cell;
	}

	private void generarSeccion(Document document, Element data, String titulo, Float padding)
			throws DocumentException {

		PdfPCell cellBorder = generarCeldaBordeRedondeado(titulo, padding);
		cellBorder.addElement(data);

		PdfPTable tableBorder = new PdfPTable(1);
		tableBorder.setWidthPercentage(100);
		tableBorder.addCell(cellBorder);
		tableBorder.setSpacingAfter(10);

		document.add(tableBorder);
	}

	private PdfPCell generarCeldaBordeRedondeado(String titulo, Float padding) {
		PdfPCell cellBorder = new PdfPCell();
		cellBorder.setCellEvent(new RoundRectangle());
		cellBorder.setBorder(Rectangle.NO_BORDER);
		if (padding != null) {
			cellBorder.setPadding(padding);
		}
		return cellBorder;
	}

	public class RoundRectangle implements PdfPCellEvent {
		public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvas) {
			PdfContentByte cb = canvas[PdfPTable.BASECANVAS];
			cb.roundRectangle(rect.getLeft() + 1.5f, rect.getBottom() + 1.5f, rect.getWidth() - 3, rect.getHeight() - 3,
					4);
			cb.setColorFill(BaseColor.WHITE);
			cb.fill();
			PdfContentByte cb2 = canvas[PdfPTable.LINECANVAS];
			cb2.roundRectangle(rect.getLeft() + 1.5f, rect.getBottom() + 1.5f, rect.getWidth() - 3,
					rect.getHeight() - 3, 4);
			cb2.setColorStroke(new BaseColor(200, 200, 200));
			cb2.setLineWidth(1);
			cb2.stroke();
		}
	}

}

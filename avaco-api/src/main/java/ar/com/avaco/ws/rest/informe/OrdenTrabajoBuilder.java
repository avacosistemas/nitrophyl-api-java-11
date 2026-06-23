package ar.com.avaco.ws.rest.informe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;
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
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
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
import ar.com.avaco.ws.rest.informe.PDFUtils.RoundRectangle;

public class OrdenTrabajoBuilder {

	public void generarReporte() throws DocumentException, IOException {
		// Obtengo la empresa para el logo

		Document document = new Document(PageSize.A4);

		try {
			ArchivoDTO adto = new ArchivoDTO();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

//			" + Calendar.getInstance().getTimeInMillis() + 

			PdfWriter.getInstance(document, new FileOutputStream("c:\\nitrophyl\\nitrophyl-ot.pdf"));

			PdfWriter.getInstance(document, baos);

//			writer.setPageEvent(new PDFEventHelper());

			document.open();
			document.setMargins(20, 20, 10, 10);

			Element encabezado = generarEncabezado();
			document.add(encabezado);

			Element pieza = generarPieza();
			document.add(pieza);

			Element cuerpoOT = generarCuerpoOT();
			document.add(cuerpoOT);

			// Dejo un espacio
			Paragraph element = new Paragraph(" ");
			document.add(element);

			document.close();

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

	private PdfPTable generarCuerpoOT() throws DocumentException {

		// Tabla terminacion
		PdfPTable tableTerminacion = generarTablaTerminacion();

		// Tabla de controles
		PdfPTable tableControles = generarTablaControles();

		// Tabla de entregas
		PdfPTable tableEntregas = generarTablaEntregas();

		// Parte derecha
		PdfPCell cellCuerpoDerecha = PDFUtils.getPDFPCell();
		cellCuerpoDerecha.setVerticalAlignment(PdfPCell.ALIGN_TOP);
		// Agrego entregas
		cellCuerpoDerecha.addElement(tableEntregas);

		PdfPTable tableDetalle = generarTablaDetalle();

		PdfPCell cellCuerpoIzquierda = PDFUtils.getPDFPCell();
		cellCuerpoIzquierda.addElement(tableDetalle);
		cellCuerpoIzquierda.addElement(tableTerminacion);
		cellCuerpoIzquierda.addElement(tableControles);

		cellCuerpoIzquierda.setVerticalAlignment(PdfPCell.ALIGN_TOP);

		// Cuerpo principal
		PdfPTable tableCuerpoPrincipal = PDFUtils.generateTable(2);
		int[] widths = { 70, 30 };
		tableCuerpoPrincipal.setWidths(widths);

		// Agrego izquierda y derecha
		tableCuerpoPrincipal.addCell(cellCuerpoIzquierda);
		tableCuerpoPrincipal.addCell(cellCuerpoDerecha);

		return tableCuerpoPrincipal;
	}

	private PdfPTable generarTablaControles() {
		PdfPTable tableControles = PDFUtils.generateTable(2);

		PdfPCell cellControl2 = PDFUtils.getPDFPCell();
		cellControl2.setPhrase(new Phrase("[  ] Diametro 698-701", PDFUtils.fontHeaderTable));
		tableControles.addCell(cellControl2);

		PdfPCell cellControl3 = PDFUtils.getPDFPCell();
		cellControl3.setPhrase(new Phrase("[  ] Espesor 9.5-10.5", PDFUtils.fontHeaderTable));
		tableControles.addCell(cellControl3);

		PdfPCell cellControl4 = PDFUtils.getPDFPCell();
		cellControl4.setPhrase(new Phrase("[  ] Espesor Pepe 9.5-10.5", PDFUtils.fontHeaderTable));
		tableControles.addCell(cellControl4);

		PdfPCell cellControl1 = PDFUtils.getPDFPCell();
		cellControl1.setPhrase(new Phrase("[  ] Dureza 70-75", PDFUtils.fontHeaderTable));
		tableControles.addCell(cellControl1);

		return tableControles;
	}

	private PdfPTable generarTablaTerminacion() throws DocumentException {
		PdfPTable tableTerminacion = PDFUtils.generateTable(2);
		int[] widths = { 20, 80 };
		tableTerminacion.setWidths(widths);

		PdfPCell cellLabelIdent = PDFUtils.getPDFPCell();
		cellLabelIdent.setBorder(0);
		cellLabelIdent.setPhrase(new Phrase("IDENTIFICACION", PDFUtils.fontHeaderTable));
		tableTerminacion.addCell(cellLabelIdent);

		PdfPCell cellIdent = PDFUtils.getPDFPCell();
		cellIdent.setBorder(0);
		cellIdent.setPhrase(new Phrase(
				"Texto de ident Texto de ident Texto de ident Texto de ident Texto de ident Texto de ident Texto de ident",
				PDFUtils.fontText));
		tableTerminacion.addCell(cellIdent);

		PdfPCell cellLabelPostCura = PDFUtils.getPDFPCell();
		cellLabelPostCura.setBorder(0);
		cellLabelPostCura.setPhrase(new Phrase("POSTCURA", PDFUtils.fontHeaderTable));
		tableTerminacion.addCell(cellLabelPostCura);

		PdfPCell cellPostCura = PDFUtils.getPDFPCell();
		cellPostCura.setBorder(0);
		cellPostCura.setPhrase(new Phrase(
				"Texto de postcura Texto de postcura Texto de postcura Texto de postcura Texto de postcura Texto de postcura Texto de postcura Texto de postcura ",
				PDFUtils.fontText));
		tableTerminacion.addCell(cellPostCura);

		return tableTerminacion;

	}

	private PdfPTable generarTablaDetalle() {
		PdfPTable tableDetalle = PDFUtils.generateTable(4);

		PdfPCell cellLabelHojaProceso = PDFUtils.getPDFPCell();
		cellLabelHojaProceso.setBorder(0);
		cellLabelHojaProceso.setPhrase(new Phrase("HOJA PROCESO", PDFUtils.fontHeaderTable));
		cellLabelHojaProceso.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableDetalle.addCell(cellLabelHojaProceso);

		PdfPCell cellLabelPlanoRev = PDFUtils.getPDFPCell();
		cellLabelPlanoRev.setBorder(0);
		cellLabelPlanoRev.setPhrase(new Phrase("PLANO/REV", PDFUtils.fontHeaderTable));
		cellLabelPlanoRev.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableDetalle.addCell(cellLabelPlanoRev);

		PdfPCell cellLabelMatriz = PDFUtils.getPDFPCell();
		cellLabelMatriz.setBorder(0);
		cellLabelMatriz.setPhrase(new Phrase("MATRIZ", PDFUtils.fontHeaderTable));
		cellLabelMatriz.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableDetalle.addCell(cellLabelMatriz);

		PdfPCell cellLabelUbicacion = PDFUtils.getPDFPCell();
		cellLabelUbicacion.setBorder(0);
		cellLabelUbicacion.setPhrase(new Phrase("UBICACION", PDFUtils.fontHeaderTable));
		cellLabelUbicacion.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableDetalle.addCell(cellLabelUbicacion);

		PdfPCell cellHojaProceso = PDFUtils.getPDFPCell();
		cellHojaProceso.setBorder(0);
		cellHojaProceso.setPhrase(new Phrase("MOTO-SE-062", PDFUtils.fontText));
		cellHojaProceso.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableDetalle.addCell(cellHojaProceso);

		PdfPCell cellPlanoRev = PDFUtils.getPDFPCell();
		cellPlanoRev.setBorder(0);
		cellPlanoRev.setPhrase(new Phrase("25851/01", PDFUtils.fontText));
		cellPlanoRev.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableDetalle.addCell(cellPlanoRev);

		PdfPCell cellMatriz = PDFUtils.getPDFPCell();
		cellMatriz.setBorder(0);
		cellMatriz.setPhrase(new Phrase("29448/9 (PL 29448)", PDFUtils.fontText));
		cellMatriz.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableDetalle.addCell(cellMatriz);

		PdfPCell cellUbicacion = PDFUtils.getPDFPCell();
		cellUbicacion.setBorder(0);
		cellUbicacion.setPhrase(new Phrase("D-3", PDFUtils.fontText));
		cellUbicacion.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableDetalle.addCell(cellUbicacion);
		return tableDetalle;
	}

	private PdfPTable generarTablaEntregas() {
		PdfPTable tableEntregas = PDFUtils.generateTable(2);

		PdfPCell cellCabeceraEntregas = PDFUtils.getPDFPCell();
		cellCabeceraEntregas.setBorder(0);
		cellCabeceraEntregas.setColspan(2);
		cellCabeceraEntregas.setPhrase(new Phrase("ENTREGAS", PDFUtils.fontHeaderTable));
		cellCabeceraEntregas.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableEntregas.addCell(cellCabeceraEntregas);

		PdfPCell cellCabeceraEntregasFecha = PDFUtils.getPDFPCell();
		cellCabeceraEntregasFecha.setBorder(0);
		cellCabeceraEntregasFecha.setPhrase(new Phrase("FECHA", PDFUtils.fontHeaderTable));
		cellCabeceraEntregasFecha.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableEntregas.addCell(cellCabeceraEntregasFecha);

		PdfPCell cellCabeceraEntregaCantidad = PDFUtils.getPDFPCell();
		cellCabeceraEntregaCantidad.setBorder(0);
		cellCabeceraEntregaCantidad.setPhrase(new Phrase("CANTIDAD", PDFUtils.fontHeaderTable));
		cellCabeceraEntregaCantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableEntregas.addCell(cellCabeceraEntregaCantidad);

		for (int i = 0; i <= 10; i++) {

			PdfPCell cellBlanca = PDFUtils.getPDFPCell();
			cellBlanca.setFixedHeight(20);
			cellBlanca.setBorder(0);
			cellBlanca.setPhrase(new Phrase("   ", PDFUtils.fontHeaderTable));
			cellBlanca.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableEntregas.addCell(cellBlanca);

		}
		return tableEntregas;
	}

	private PdfPTable generarPieza() throws DocumentException {

		// Tabla de pieza
		PdfPTable tablePieza = PDFUtils.generateTable(1);

		// Tabla encabezado de pieza
		PdfPTable tableEncabezadoPieza = PDFUtils.generateTable(2);
		int[] widths = { 80, 20 };
		tableEncabezadoPieza.setWidths(widths);

		// Celda nombre pieza
		PdfPCell cellPiezaNombre = PDFUtils.getPDFPCell();
		cellPiezaNombre.setBorder(0);
		cellPiezaNombre.setPhrase(new Phrase("MOTOM 241534-1534", PDFUtils.fontHeaderTable));
		cellPiezaNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableEncabezadoPieza.addCell(cellPiezaNombre);

		// Celda cantidad de piezas
		PdfPCell cellPiezaCantidad = PDFUtils.getPDFPCell();
		cellPiezaCantidad.setBorder(0);
		cellPiezaCantidad.setPhrase(new Phrase("CANTIDAD: 60", PDFUtils.fontHeaderTable));
		cellPiezaCantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableEncabezadoPieza.addCell(cellPiezaCantidad);

		// Agrego el encabezado de piezas
		tablePieza.addCell(tableEncabezadoPieza);

		// Tabla de formula
		PdfPTable tableFormula = PDFUtils.generateTable(4);

		// Cabecera de Formula
		PdfPCell cellLabelMaterial = PDFUtils.getPDFPCell();
		cellLabelMaterial.setBorder(0);
		cellLabelMaterial.setPhrase(new Phrase("MATERIAL", PDFUtils.fontHeaderTable));
		cellLabelMaterial.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableFormula.addCell(cellLabelMaterial);

		PdfPCell cellLabelFormula = PDFUtils.getPDFPCell();
		cellLabelFormula.setBorder(0);
		cellLabelFormula.setPhrase(new Phrase("FORMULA", PDFUtils.fontHeaderTable));
		cellLabelFormula.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableFormula.addCell(cellLabelFormula);

		PdfPCell cellLabelLote = PDFUtils.getPDFPCell();
		cellLabelLote.setBorder(0);
		cellLabelLote.setPhrase(new Phrase("LOTE", PDFUtils.fontHeaderTable));
		cellLabelLote.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableFormula.addCell(cellLabelLote);

		PdfPCell cellLabelFabrico = PDFUtils.getPDFPCell();
		cellLabelFabrico.setBorder(0);
		cellLabelFabrico.setPhrase(new Phrase("FABRICÓ", PDFUtils.fontHeaderTable));
		cellLabelFabrico.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableFormula.addCell(cellLabelFabrico);

		// Datos de Formula
		PdfPCell cellMaterial = PDFUtils.getPDFPCell();
		cellMaterial.setBorder(0);
		cellMaterial.setPhrase(new Phrase("Nitrilo", PDFUtils.fontText));
		cellMaterial.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableFormula.addCell(cellMaterial);

		PdfPCell cellFormula = PDFUtils.getPDFPCell();
		cellFormula.setBorder(0);
		cellFormula.setPhrase(new Phrase("N70", PDFUtils.fontText));
		cellFormula.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableFormula.addCell(cellFormula);

		PdfPCell cellLote = PDFUtils.getPDFPCell();
		cellLote.setBorder(0);
		cellLote.setPhrase(new Phrase("", PDFUtils.fontText));
		cellLote.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableFormula.addCell(cellLote);

		PdfPCell cellFabrico = PDFUtils.getPDFPCell();
		cellFabrico.setBorder(0);
		cellFabrico.setPhrase(new Phrase("", PDFUtils.fontText));
		cellFabrico.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableFormula.addCell(cellFabrico);

		// Agrego la parte de formulas
		tablePieza.addCell(tableFormula);

		return tablePieza;
	}

	private PdfPTable generarEncabezado() throws DocumentException {

		PdfPTable tableEncabezado = PDFUtils.generateTable(2);
		int[] widths = { 75, 25 };
		tableEncabezado.setWidths(widths);

		PdfPCell cellIzquierda = PDFUtils.getPDFPCell();
		cellIzquierda.setBorder(0);
		cellIzquierda.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cellDerecha = PDFUtils.getPDFPCell();
		cellDerecha.setBorder(0);
		cellDerecha.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellDerecha.setVerticalAlignment(PdfPCell.ALIGN_TOP);

		PdfPTable tableIzquierda = generarEncabezadoIzquierdo();
		cellIzquierda.addElement(tableIzquierda);

		PdfPTable tableDerecha = generarEncabezadoDerecho();
		cellDerecha.addElement(tableDerecha);

		tableEncabezado.addCell(cellIzquierda);
		tableEncabezado.addCell(cellDerecha);

		return tableEncabezado;

	}

	private PdfPTable generarEncabezadoDerecho() {
		// Creo la tabla de la derecha
		PdfPTable tableDerecha = PDFUtils.generateTable(2);

		// Agrego el titulo Numero OF
		PdfPCell celdaLabelNroOT = PDFUtils.getPDFPCell();
		celdaLabelNroOT.setPhrase(new Phrase("Nº OF", PDFUtils.fontHeaderTable));
		celdaLabelNroOT.setBorder(0);
		celdaLabelNroOT.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaLabelNroOT.setColspan(2);
		tableDerecha.addCell(celdaLabelNroOT);

		// Agrego el numero OF
		PdfPCell celdaNroOT = PDFUtils.getPDFPCell();
		celdaNroOT.setPhrase(new Phrase("2024/004", PDFUtils.fontHeaderTable));
		celdaNroOT.setBorder(0);
		celdaNroOT.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaNroOT.setColspan(2);
		tableDerecha.addCell(celdaNroOT);

		// Agrego el label de fecha de emision
		PdfPCell celdaLabelFechaEmision = PDFUtils.getPDFPCell();
		celdaLabelFechaEmision.setPhrase(new Phrase("EMISION", PDFUtils.fontHeaderTable));
		celdaLabelFechaEmision.setBorder(0);
		celdaLabelFechaEmision.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableDerecha.addCell(celdaLabelFechaEmision);

		// Agrego el label de fecha de entrega
		PdfPCell celdaLabelFechaEntrega = PDFUtils.getPDFPCell();
		celdaLabelFechaEntrega.setPhrase(new Phrase("ENTREGA", PDFUtils.fontHeaderTable));
		celdaLabelFechaEntrega.setBorder(0);
		celdaLabelFechaEntrega.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableDerecha.addCell(celdaLabelFechaEntrega);

		// Agrego fecha de emision
		PdfPCell celdaFechaEmision = PDFUtils.getPDFPCell();
		celdaFechaEmision.setPhrase(new Phrase("10/02/2026", PDFUtils.fontText));
		celdaFechaEmision.setBorder(0);
		celdaFechaEmision.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableDerecha.addCell(celdaFechaEmision);

		// Agrego
		PdfPCell celdaFechaEntrega = PDFUtils.getPDFPCell();
		celdaFechaEntrega.setPhrase(new Phrase("15/03/2026", PDFUtils.fontText));
		celdaFechaEntrega.setBorder(0);
		celdaFechaEntrega.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableDerecha.addCell(celdaFechaEntrega);

		return tableDerecha;
	}

	private PdfPTable generarEncabezadoIzquierdo() {
		// Creo la tabla de la izquierda
		PdfPTable tableIzquierda = PDFUtils.generateTable(4);

		// Agrego el titulo
		PdfPCell celdaOT = PDFUtils.getPDFPCell();
		celdaOT.setPhrase(new Phrase("ORDEN DE TRABAJO", PDFUtils.fontHeaderTable));
		celdaOT.setBorder(0);
		celdaOT.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaOT.setColspan(4);
		tableIzquierda.addCell(celdaOT);

		// Agrego el label del cliente
		PdfPCell celdaLabelCliente = PDFUtils.getPDFPCell();
		celdaLabelCliente.setPhrase(new Phrase("CLIENTE", PDFUtils.fontHeaderTable));
		celdaLabelCliente.setBorder(0);
		tableIzquierda.addCell(celdaLabelCliente);

		// Agrego el label del sector
		PdfPCell celdaLabelSector = PDFUtils.getPDFPCell();
		celdaLabelSector.setPhrase(new Phrase("SECTOR", PDFUtils.fontHeaderTable));
		celdaLabelSector.setBorder(0);
		tableIzquierda.addCell(celdaLabelSector);

		// Agrego el label de la maquina
		PdfPCell celdaLabelMaquina = PDFUtils.getPDFPCell();
		celdaLabelMaquina.setPhrase(new Phrase("MAQUINA", PDFUtils.fontHeaderTable));
		celdaLabelMaquina.setBorder(0);
		tableIzquierda.addCell(celdaLabelMaquina);

		// Agrego el label del operario
		PdfPCell celdaLabelOperario = PDFUtils.getPDFPCell();
		celdaLabelOperario.setPhrase(new Phrase("OPERARIO", PDFUtils.fontHeaderTable));
		celdaLabelOperario.setBorder(0);
		tableIzquierda.addCell(celdaLabelOperario);

		// Agrego el nombre del cliente
		PdfPCell celdaNombreCliente = PDFUtils.getPDFPCell();
		celdaNombreCliente.setPhrase(new Phrase("MOTOMECANICA", PDFUtils.fontText));
		celdaNombreCliente.setBorder(0);
		tableIzquierda.addCell(celdaNombreCliente);

		// Agrego el nombre del sector
		PdfPCell celdaNombreSector = PDFUtils.getPDFPCell();
		celdaNombreSector.setPhrase(new Phrase("7G", PDFUtils.fontText));
		celdaNombreSector.setBorder(0);
		tableIzquierda.addCell(celdaNombreSector);

		// Agrego el nombre de la maquina
		PdfPCell celdaNombreMaquina = PDFUtils.getPDFPCell();
		celdaNombreMaquina.setPhrase(new Phrase("Prensa 4", PDFUtils.fontText));
		celdaNombreMaquina.setBorder(0);
		tableIzquierda.addCell(celdaNombreMaquina);

		// Agrego el nombre de la maquina
		PdfPCell celdaNombreOperario = PDFUtils.getPDFPCell();
		celdaNombreOperario.setPhrase(new Phrase("Martín Perez", PDFUtils.fontText));
		celdaNombreOperario.setBorder(0);
		tableIzquierda.addCell(celdaNombreOperario);

		// Agrego el titulo
		PdfPCell celdaObservaciones = PDFUtils.getPDFPCell();
		celdaObservaciones.setPhrase(new Phrase(
				"Observaciones: muchas observaciones para que la pieza salga bien prolija y así el cliente este contento y no se enoje.",
				PDFUtils.fontText));
		celdaObservaciones.setBorder(0);
		celdaObservaciones.setColspan(4);
		tableIzquierda.addCell(celdaObservaciones);

		return tableIzquierda;
	}

}

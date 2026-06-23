package ar.com.avaco.ws.rest.informe;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;


public class PDFUtils {

	public static final BaseColor COLOR_GRIS_BORDES = new BaseColor(238, 238, 238);

	public final static Font fontHeaderTable = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);
	public  final static Font fontText = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

	
	public static PdfPTable generateTable(int columns) {
		PdfPTable table = new PdfPTable(columns);
		table.setWidthPercentage(100);
		return table;
	}

	public static  PdfPCell getPDFPCell() {
		PdfPCell cell = new PdfPCell();
		cell.setUseAscender(true);
		cell.setBorder(0);
		cell.setBorderWidthBottom(1);
		cell.setBorderColorBottom(COLOR_GRIS_BORDES);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5);
		return cell;
	}
	
	public static void generarSeccion(Document document, Element data, String titulo, Float padding)
			throws DocumentException {

		PdfPCell cellBorder = PDFUtils.generarCeldaBordeRedondeado(titulo, padding);
		cellBorder.addElement(data);

		PdfPTable tableBorder = new PdfPTable(1);
		tableBorder.setWidthPercentage(100);
		tableBorder.addCell(cellBorder);
		tableBorder.setSpacingAfter(10);

		document.add(tableBorder);
	}
	
	public static PdfPCell generarCeldaBordeRedondeado(String titulo, Float padding) {
		PdfPCell cellBorder = new PdfPCell();
		cellBorder.setCellEvent(new RoundRectangle());
		cellBorder.setBorder(Rectangle.NO_BORDER);
		if (padding != null) {
			cellBorder.setPadding(padding);
		}
		return cellBorder;
	}

	public static class RoundRectangle implements PdfPCellEvent {
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

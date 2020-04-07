package pl.com.hom.printer;

import java.io.IOException;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Line;

public class Printer extends PdfCanvas{
	public Printer(PdfPage page){
		super(page);

		try {
			this.setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 11);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addLine(Line line) {
		this.setLineWidth(0.5f);
		this.setStrokeColorRgb(0f, 0f, 0f);
		this.moveTo(line.getBeginWidth(), 595.0f - line.getBeginHeight());
		this.lineTo(line.getEndWidth(),   595.0f - line.getEndHeight());

		this.stroke();
		this.closePathStroke();
		
	}
	public void addColumnRow(ColumnRow row) {
		this.beginText();
		this.moveText(row.getWidthPos() - 24, 595.0f - row.getHeightPos() - row.getHeight()/1.5);
		this.showText(row.getTechName());
		this.endText();
		this.addXObject(row.image(), new Rectangle(row.getWidthPos(), 595.0f - row.getHeightPos() - row.getHeight(), Measures.SCALE,Measures.SCALE));
	}

	public void addPoint(Point p) {
		this.addXObject(p.image(), new Rectangle(p.getWidthPos() - p.getWidth()/2, 595.0f - p.getHeightPos() -  p.getHeigh()/2, Measures.SCALE,Measures.SCALE));
	}
}

package pl.com.hom.printer;

import java.io.IOException;

import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Line;

import static pl.com.hom.configuration.Document.getPdfDocument;
public class Printer extends PdfCanvas{
	private Canvas canvas;

	public Printer(PdfPage page){
		super(page);
		canvas = new Canvas(this, getPdfDocument(), page.getPageSize());

		try {
			this.setFontAndSize(PdfFontFactory.createFont("src\\main\\resources\\fonts\\ShareTech-Regular.ttf"), 12);
			canvas.setFont(PdfFontFactory.createFont("src\\main\\resources\\fonts\\ShareTech-Regular.ttf"));
			canvas.setFontSize(10);
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
		if (row.techName() != null) {
			this.beginText();
			this.moveText(row.widthNamePos(), row.heightNamePos());
			this.showText(row.techName());
			this.endText();
		}

		if (row.visible())
			this.addXObject(row.image(), new Rectangle(row.widthPos(), 595.0f - row.heightPos() - row.height(), Measures.SCALE,Measures.SCALE));
	}

	public void addTerminal(Terminal t) {
		this.beginText();
		canvas.showTextAligned(t.fullName(), (int)(t.widthNamePos() - Measures.TERMINAL_DRAW_MARGIN), (int)(595.0f - t.heightNamePos()), TextAlignment.CENTER, VerticalAlignment.MIDDLE, 1.5707963268f);
		this.endText();
		this.addXObject(t.image(), new Rectangle(t.widthPos() - Measures.TERMINAL_DRAW_MARGIN, 595.0f - t.heightPos() - t.height(), Measures.SCALE,Measures.SCALE));
	}

	public void addPoint(Point p) {
		this.addXObject(p.image(), new Rectangle(p.widthPos() - p.width()/2, 595.0f - p.heightPos() -  p.height()/2, Measures.SCALE,Measures.SCALE));
	}
}
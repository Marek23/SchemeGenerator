package pl.com.hom.printer;

import java.io.IOException;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Line;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;
import pl.com.hom.element.pointer.Pointer;
import pl.com.hom.element.Element;

public class Printer extends PdfCanvas{
	private static final long serialVersionUID = 1L;
	private Canvas canvas;

	private int small  = 9;
	private int normal = 12;
	private int large  = 14;

	private PdfFont f;

	public Printer(PdfPage page){
		super(page);

		try {
			f = PdfFontFactory.createFont("src\\main\\resources\\fonts\\ShareTech-Regular.ttf");
		} catch (IOException e) {
		e.printStackTrace();
		}

		canvas = new Canvas(this, page.getDocument(), page.getPageSize());

		this.setFontAndSize(f, 12);
		canvas.setFont(f);
		canvas.setFontSize(normal);

	}

	public void addLine(Line line) {
		this.setLineWidth(0.8f);
		if (line.potential().name().contains("PE")) {
			this.setLineWidth(1f);
			this.setStrokeColorRgb(0.2f, 0.8f, 0.2f);
			this.setLineDash(8f, 3f);
		}
		else {
			this.setLineWidth(0.5f);
			this.setStrokeColorRgb(0f, 0f, 0f);
			this.setLineDash(1000f, 0f);
		}
		this.moveTo(line.getBeginWidth(), 595.0f - line.getBeginHeight());
		this.lineTo(line.getEndWidth(),   595.0f - line.getEndHeight());

		this.stroke();
		this.closePathStroke();
		
	}

	public void addElement(Element e) {
		if (e.symbol() != null) {
			this.setFontAndSize(this.f, normal);
			this.beginText();
			this.moveText(e.symbolWidthPos(), 595.0f - e.symbolHeightPos());
			this.showText(e.symbol());
			this.endText();
		}

		if (e.parentPageNr() > 0) {
			this.setFontAndSize(this.f, small);
			this.beginText();
			this.moveText(e.parentWidthPos(), 595.0f - e.parentHeightPos());
			this.showText("/" + String.valueOf(e.parentPageNr()));
			this.endText();
		}

		if (e.visible())
			this.addXObject(e.image(), new Rectangle(e.widthPos(), 595.0f - e.heightPos() - e.height(), Measures.SCALE,Measures.SCALE));
	}

	public void addPointer(Pointer p) {
		this.setFontAndSize(this.f, small);

		this.beginText();
		this.moveText(p.parentWidthPos(), 595.0f - p.parentHeightPos());
		this.showText("/" + String.valueOf(p.parentPage()));
		this.endText();

		this.addXObject(p.image(), new Rectangle(p.widthPos(), 595.0f - p.heightPos() - p.height(), Measures.SCALE,Measures.SCALE));
	}

	public void addTerminal(Terminal t) {
		canvas.setFontSize(normal);

		this.beginText();
		canvas.showTextAligned(t.fullName(), (int)(t.widthNamePos() - Measures.TERMINAL_DRAW_MARGIN), (int)(595.0f - t.heightNamePos()), TextAlignment.CENTER, VerticalAlignment.MIDDLE, 1.5707963268f);
		this.endText();
		this.addXObject(t.image(), new Rectangle(t.widthPos() - Measures.TERMINAL_DRAW_MARGIN, 595.0f - t.heightPos() - t.height(), Measures.SCALE,Measures.SCALE));
	}

	public void addPoint(Point p) {
		if (p.isVisibile())
			this.addXObject(p.image(), new Rectangle(p.widthPos() - p.width()/2, 595.0f - p.heightPos() -  p.height()/2, Measures.SCALE,Measures.SCALE));
	}

	public void addBeginPoint(Point p) {
		this.setFontAndSize(this.f, small);

		if (p.target() > 0) {
			this.beginText();
			this.moveText(p.targetWidth() - 35f, 595f - p.targetHeight());
			this.showText(p.potential().prettyName() + " /" + String.valueOf(p.target()) );
			this.endText();
		}
	}

	public void addEndPoint(Point p) {
		this.setFontAndSize(this.f, small);

		if (p.target() > 0) {
			this.beginText();
			this.moveText(p.targetWidth() + 5f, 595f - p.targetHeight());
			this.showText(p.potential().prettyName() + " /" + String.valueOf(p.target()) );
			this.endText();
		}
	}
}

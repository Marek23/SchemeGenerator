package pl.com.cs;

import java.io.IOException;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import pl.com.cs.schema.Point;
import pl.com.cs.schema.out.Terminal;
import pl.com.cs.schema.pointer.Pointer;
import pl.com.cs.schema.Line;
import pl.com.cs.schema.Drawable;

import static pl.com.cs.config.Measures.scale;
import static pl.com.cs.config.Measures.scaled;

public class Printer extends PdfCanvas{
	private static float scale = scale();
	private Canvas canvas;

	private float yRow = 80f;
	private float xRow = 80f;

	private int small  = 7;
	private int normal = 9;

	private boolean firstInput;
	private boolean firstOutput;

	private PdfFont  f;

	public Printer(PdfPage page){
		super(page);
		try {
			f = PdfFontFactory.createFont("src\\main\\resources\\fonts\\DejaVuSansCondensed.ttf", "Cp1250", true);
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

	public void addElement(Drawable d) {
		if (d.symbol() != null) {
			canvas.setFontSize(normal);
			canvas.showTextAligned(d.symbol(), d.symbolWidthPos(), 595.0f - d.symbolHeightPos(), TextAlignment.RIGHT);
		}

		if (d.mainPageNr() > 0) {
			canvas.setFontSize(small);
			canvas.showTextAligned("/" + String.valueOf(d.mainPageNr()), d.mainWidthPos(), 595.0f - d.mainHeightPos(), TextAlignment.RIGHT);
		}

		if (d.number() != null) {
			canvas.setFontSize(small);
			canvas.showTextAligned(d.number(), d.numberWidthPos(), 595.0f - d.numberHeightPos(), TextAlignment.RIGHT);
		}

		if (d.fuse() != null) {
			canvas.setFontSize(small);
			canvas.showTextAligned(d.fuse(), d.fuseWidthPos(), 595.0f - d.fuseHeightPos(), TextAlignment.RIGHT);
		}

		if (d.visible())
			this.addXObject(d.image(), new Rectangle(d.widthPos(), 595.0f - d.heightPos() - d.height(), scale,scale));
	}

	public void addPointer(Pointer p) {
		canvas.setFontSize(small);
		canvas.showTextAligned("/" + String.valueOf(p.mainPage()), p.mainWidthPos(), 595.0f - p.mainHeightPos(), TextAlignment.RIGHT);

		this.addXObject(p.image(), new Rectangle(p.widthPos(), 595.0f - p.heightPos() - p.height(), scale,scale));
	}

	public void addTerminal(Terminal t) {
		canvas.setFontSize(normal);
		canvas.showTextAligned(t.fullName(), t.widthNamePos() - scaled(200f), (595.0f - t.heightNamePos()), TextAlignment.RIGHT, VerticalAlignment.MIDDLE, 1.5707963268f);

		this.addXObject(t.image(), new Rectangle(t.widthPos() - scaled(100f), 595.0f - t.heightPos() - t.height(), scale,scale));
	}

	public void addTerminalWithGroup(Terminal t) {
		canvas.setFontSize(normal);
		canvas.showTextAligned(t.group(), t.widthNamePos() - scaled(270f), (595.0f - t.heightNamePos()), TextAlignment.CENTER);

		this.addXObject(t.image(), new Rectangle(t.widthPos() - scaled(100f), 595.0f - t.heightPos() - t.height(), scale,scale));

		addTerminal(t);
	}

	public void addPlcInput(String text) {
		if (!firstInput) {
			this.setFontAndSize(this.f, normal);
			this.beginText();
			this.moveText(270f, 495f - yRow);
			this.showText("Wejścia sterownika:");
			this.endText();
			
			yRow += 15f;
			firstInput = true;
		}

		this.setFontAndSize(this.f, small);
		this.beginText();
		this.moveText(270f, 495f - yRow);
		this.showText(text);
		this.endText();

		yRow += 10f;
	}

	public void addPlcOutput(String text) {
		if (!firstOutput) {
			this.setFontAndSize(this.f, normal);
			this.beginText();
			this.moveText(550f, 495f - xRow);
			this.showText("Wyjścia sterownika:");
			this.endText();
			
			xRow += 15f;
			firstOutput = true;
		}
		
		this.setFontAndSize(this.f, small);
		this.beginText();
		this.moveText(550f, 495f - xRow);
		this.showText(text);
		this.endText();

		xRow += 10f;
	}

	public void addSapInput(String text) {
		if (!firstInput) {
			this.setFontAndSize(this.f, normal);
			this.beginText();
			this.moveText(400f, 195f - yRow);
			this.showText("Opis sygnałów listwy zaciskowej:");
			this.endText();
			
			yRow += 15f;
			firstInput = true;
		}

		this.setFontAndSize(this.f, small);
		this.beginText();
		this.moveText(400f, 195f - yRow);
		this.showText(text);
		this.endText();

		yRow += 10f;
	}

	public void addSapOutput(String text) {
		if (!firstOutput) {
			this.setFontAndSize(this.f, normal);
			this.beginText();
			this.moveText(400f, 195f - xRow);
			this.showText("Opis sygnałów listwy zaciskowej:");
			this.endText();
			
			xRow += 15f;
			firstOutput = true;
		}
		
		this.setFontAndSize(this.f, small);
		this.beginText();
		this.moveText(400f, 195f - xRow);
		this.showText(text);
		this.endText();

		xRow += 10f;
	}

	public void addPoint(Point p) {
		if (p.isVisibile())
			this.addXObject(p.image(), new Rectangle(p.widthPos() - p.width()/2, 595.0f - p.heightPos() - p.height()/2, scale,scale));
	}

	public void addBeginPoint(Point p) {
		canvas.setFontSize(small);

		if (p.target() > 0) {
			this.beginText();
			canvas.showTextAligned(p.potential().prettyName() + " /" + String.valueOf(p.target()), p.targetWidth(), 595f - p.targetHeight(), TextAlignment.RIGHT);
			this.endText();
		}
	}

	public void addEndPoint(Point p) {
		canvas.setFontSize(small);

		if (p.target() > 0) {
			canvas.showTextAligned(p.potential().prettyName() + " /" + String.valueOf(p.target()), p.targetWidth(), 595f - p.targetHeight(), TextAlignment.LEFT);
		}
	}

	public void addMotorDescription(String text) {
		if (!firstOutput) {
			this.setFontAndSize(this.f, normal);
			this.beginText();
			this.moveText(550f, 495f - xRow);
			this.showText(text);
			this.endText();
			
			xRow += 15f;
			firstOutput = true;
		}
		else {
			this.setFontAndSize(this.f, small);
			this.beginText();
			this.moveText(550f, 495f - xRow);
			this.showText(text);
			this.endText();

			xRow += 10f;
		}
	}
}

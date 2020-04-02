package pl.com.hom.scheme;

import static pl.com.hom.configuration.Document.getPdfDocument;

import java.util.ArrayList;
import java.util.List;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import pl.com.hom.configuration.Measures;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.elements.bridges.AboveContactorBridge;
import pl.com.hom.elements.graphics.Contactor;

public class JetPage extends PdfPage{
	private static final long serialVersionUID = 7351148506505896070L;

	private List<Column> columns;
	private float x;

	PdfCanvas canvas;

	public JetPage() {
		super(getPdfDocument(), PageSize.A4.rotate());
		getPdfDocument().addPage(this);

		this.canvas  = new PdfCanvas(this);
		this.columns = new ArrayList<Column>();
		this.x       = 0f;

		Column    col1 = new Column(this, 120.0f);

		Contactor con1 = new Contactor(col1);

		Column    col2 = new Column(this, 120.0f);

		Contactor con2 = new Contactor(col2);

		col2.showElements();
		AboveContactorBridge acb = new AboveContactorBridge(col2);
	}

	public float getWidth() {
		float out = 0f;
		for (Column c : columns)
			out += c.getWidth();

		return out;
	}

	public float getWidthPos() {
		return x;
	}

	public void addColumn(Column c) {
		this.x = this.x + c.getWidth();
		columns.add(c);
	}

	public void draw() {
		for (Column column : columns) {
			for (ColumnLine line :column.getLines()) {
				canvas.setLineWidth(0.5f);
				canvas.setStrokeColorRgb(1f, 0f, 0f);
				canvas.moveTo(line.getBeginWidth(), 595.0f - line.getBeginHeight());
				canvas.lineTo(line.getEndWidth(),   595.0f - line.getEndHeight());
			}

			for (ColumnRow row : column.getColumnElements())
				if (row.visible())
				{
					System.out.println("Y: " + (595.0f - row.getHeightPos() - row.getHeight()));
					canvas.addXObject(row.image(), new Rectangle(row.getWidthPos(), 595.0f - row.getHeightPos() - row.getHeight(), Measures.SCALE,Measures.SCALE));
				}
		}

		canvas.stroke();
		canvas.closePathStroke();
	}
}

package pl.com.hom.scheme;

import static pl.com.hom.configuration.Document.getPdfDocument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfPage;

import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.elements.bridges.AboveContactor;
import pl.com.hom.elements.bridges.ToJetBridge;
import pl.com.hom.elements.bridges.ToMksBridge;
import pl.com.hom.elements.bridges.UpLeftPhases;
import pl.com.hom.elements.bridges.UpRightPhases;
import pl.com.hom.elements.graphics.CoilContactor;
import pl.com.hom.elements.graphics.Mks;
import pl.com.hom.elements.graphics.ThreePhaseFuse;
import pl.com.hom.elements.graphics.receiver.JetEngine;
import pl.com.hom.printer.Printer;

public class JetPage extends PdfPage{
	private static final long serialVersionUID = 7351148506505896070L;

	private ArrayList<Column> columns;
	private ArrayList<HorizontalLine> horizontalLines;
	private float x;
	private int nr;

	private HashMap<String, Integer> symbolNumbers;
	Printer printer;

	public JetPage(String firstGearPot, String secGearPot, int nr) {
		super(getPdfDocument(), PageSize.A4.rotate());
		getPdfDocument().addPage(this);

		this.nr  = nr;
		this.printer = new Printer(this);
		this.columns = new ArrayList<Column>();

		this.horizontalLines = new ArrayList<HorizontalLine>();
		this.symbolNumbers   = new HashMap<String, Integer>();

		this.x = 0f;

		Column jet1Col = new Column(this, 80.0f);
		Column jet2Col = new Column(this, 80.0f);
		Column engeCol = new Column(this, 80.0f);
		Column jet3Col = new Column(this, 80.0f);
		Column mksCol  = new Column(this, 90.0f);
		Column firstCoilCol = new Column(this, 90.0f);
		Column secCoilCol   = new Column(this, 90.0f);

		CoilContactor firstCoil = new CoilContactor(firstCoilCol, nr, getNumerForTechSymbol(CoilContactor.techSymbol), "L1____");
		CoilContactor secCoil   = new CoilContactor(secCoilCol,   nr, getNumerForTechSymbol(CoilContactor.techSymbol), "L1____");

		new ThreePhaseFuse(jet2Col, nr, getNumerForTechSymbol(ThreePhaseFuse.techSymbol));
		new ThreePhaseFuse(jet3Col, nr, getNumerForTechSymbol(ThreePhaseFuse.techSymbol));

		firstCoil.addFirstGearContactor(jet2Col);
		secCoil.addJetBridgeContactor(jet1Col);
		secCoil.addSecGearContactor(jet3Col);

		new AboveContactor(jet1Col);
		new UpRightPhases(jet1Col, "ToJetBridge");
		new ToJetBridge(jet2Col);
		new ToMksBridge(jet3Col);

		new Mks(mksCol, nr, getNumerForTechSymbol(Mks.techSymbol));

		new JetEngine(engeCol, nr);
		new UpRightPhases(jet2Col, "Receiver");
		new UpLeftPhases(jet3Col, "Receiver");
	}

	private void createLines() {
		for (Column c : columns) {
			c.createVerticalLines();
		}

		createHorizontalLines();
	}

	public float width() {
		float out = 0f;
		for (Column c : columns)
			out += c.width();

		return out;
	}

	public float widthPos() {
		return x;
	}

	public void addColumn(Column c) {
		this.x = this.x + c.width();
		columns.add(c);
	}

	//TEST
	public void showHorizontalLines() {
		System.out.println("showHorizontalLines");
		for (HorizontalLine cl : horizontalLines) {
			System.out.println(cl.toString());
		}
	}

	private void createHorizontalLines() {
		this.horizontalLines = new ArrayList<HorizontalLine>();

		for (Column c: columns)
			for (ColumnRow r: c.columnRows())
				for (Point p : r.points())
					p.unlinkHorizontalDirections();

		Column from;
		Column to;

		for (int r = Roles.rolesNumber(); r >= 0; r--) 
		{
			for (int i = columns.size()-1; i >= 1; i--)
			{
				to = columns.get(i);

				for (int j = i - 1; j >= 0 ; j--)
				{
					from = columns.get(j);

					ColumnRow rFrom = from.getColumnRowFromLevel(r);
					ColumnRow rTo   = to.getColumnRowFromLevel(r);

					if (rFrom != null && rTo != null)
						createLines(rFrom.points(), rTo.points());
				}
			}
		}
	}

	private void createLines(ArrayList<Point> from, ArrayList<Point> to) {
		ArrayList<Point> fPoints = new ArrayList<Point>();
		ArrayList<Point> tPoints = new ArrayList<Point>();

		for (Point p : from)
			if (p.hasUnlinkedDirection(Direction.Right))
				fPoints.add(p);

		for (Point p : to)
			if (p.hasUnlinkedDirection(Direction.Left))
				tPoints.add(p);

		for (Point f : fPoints) {
			Point t = null;

			Iterator<Point> i = tPoints.iterator();
			while (i.hasNext()) {
				t = i.next();
				if (f.potential().name().equals(t.potential().name())
				&& f.heightPos() == t.heightPos())
				{
					break;
				}
			}

			if (t != null)
				horizontalLines.add(new HorizontalLine(f, t));
		}
	}

	public void draw() {
		createLines();

		for (Column column : columns) {
			for (VerticalLine line :column.lines())
				printer.addLine(line);

			for(Point p: column.mainPoints())
				printer.addPoint(p);

			column.showSupplierPointsLines();
			for (ColumnRow row : column.getColumnElements())
			{
				printer.addColumnRow(row);

				for(Point p: row.points())
					if (p.isVisibile())
						printer.addPoint(p);

				if (row.terminals() != null)
					for(Terminal t: row.terminals())
						printer.addTerminal(t);
			}
		}

		for (HorizontalLine line : this.horizontalLines)
			printer.addLine(line);
	}

	private int getNumerForTechSymbol(String symbol){
		if (symbolNumbers.containsKey(symbol))
			symbolNumbers.put(symbol, symbolNumbers.get(symbol)+1);
		else
			symbolNumbers.put(symbol, 1);

		return symbolNumbers.get(symbol);
	}
}

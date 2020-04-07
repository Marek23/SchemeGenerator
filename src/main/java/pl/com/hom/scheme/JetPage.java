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
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.elements.bridges.AboveContactorBridge;
import pl.com.hom.elements.bridges.ToJetBridge;
import pl.com.hom.elements.bridges.UpRightPhasesBridge;
import pl.com.hom.elements.graphics.Contactor;
import pl.com.hom.elements.graphics.ThreePhaseEngine;
import pl.com.hom.elements.graphics.ThreePhaseFuse;
import pl.com.hom.printer.Printer;

public class JetPage extends PdfPage{
	private static final long serialVersionUID = 7351148506505896070L;

	private ArrayList<Column> columns;
	private ArrayList<HorizontalLine> horizontalLines;
	private float x;
	private int pageNr;

	private HashMap<String, Integer> symbolNumbers;
	Printer printer;

	public JetPage(String firstGear, String secondGear) {
		super(getPdfDocument(), PageSize.A4.rotate());
		getPdfDocument().addPage(this);

//		TODO
		this.pageNr  = 10;
		this.printer = new Printer(this);
		this.columns = new ArrayList<Column>();

		this.horizontalLines = new ArrayList<HorizontalLine>();
		this.symbolNumbers   = new HashMap<String, Integer>();

		this.x = 0f;

		Column bridgeCol = new Column(this, 120.0f);
		Contactor            cont1  = new Contactor(bridgeCol, pageNr, getNumerForTechSymbol(Contactor.techSymbol));
		AboveContactorBridge bridge = new AboveContactorBridge(bridgeCol);
		UpRightPhasesBridge  urpb   = new UpRightPhasesBridge(bridgeCol);

		Column mainCol = new Column(this, 120.0f);
		ThreePhaseFuse   tpf = new ThreePhaseFuse(mainCol, pageNr, getNumerForTechSymbol(ThreePhaseFuse.techSymbol));
		ThreePhaseEngine tpe = new ThreePhaseEngine(mainCol);
		ToJetBridge      tjb = new ToJetBridge(mainCol);
		Contactor        cont2 = new Contactor(mainCol, pageNr, getNumerForTechSymbol(Contactor.techSymbol));
	}

	private void createLines() {
		for (Column c : columns) {
			c.createVerticalLines();
		}

		createHorizontalLines();
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
			for (ColumnRow r: c.getColumnRows())
				for (Point p : r.getPoints())
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
						createLines(rFrom.getPoints(), rTo.getPoints());
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
			System.out.println("tytytyty");
			Point t = null;

			Iterator<Point> i = tPoints.iterator();
			while (i.hasNext()) {
				t = i.next();
				if (f.getPotential() == t.getPotential() && f.getHeightPos() == t.getHeightPos())
					break;
			}


			System.out.println(t);
			if (t != null)
				horizontalLines.add(new HorizontalLine(f, t));
		}
	}

	public void draw() {
		createLines();

		for (Column column : columns) {
			for (VerticalLine line :column.getLines())
				printer.addLine(line);

			for (ColumnRow row : column.getColumnElements())
			{
				if (row.visible())
					printer.addColumnRow(row);

				for(Point p: row.getPoints())
					if (p.isVisibile())
						printer.addPoint(p);
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

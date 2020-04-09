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
import pl.com.hom.elements.bridges.AboveContactorBridge;
import pl.com.hom.elements.bridges.ToJetBridge;
import pl.com.hom.elements.bridges.ToMksBridge;
import pl.com.hom.elements.bridges.UpLeftPhases;
import pl.com.hom.elements.bridges.UpRightPhasesBridge;
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
	private int pageNr;

	private HashMap<String, Integer> symbolNumbers;
	Printer printer;

	public JetPage(String firstGearPot, String secGearPot) {
		super(getPdfDocument(), PageSize.A4.rotate());
		getPdfDocument().addPage(this);

//		TODO
		this.pageNr  = 10;
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

		CoilContactor firstCoil = new CoilContactor(firstCoilCol, this.pageNr, getNumerForTechSymbol(CoilContactor.techSymbol), "L1____");
		CoilContactor secCoil   = new CoilContactor(secCoilCol,   this.pageNr, getNumerForTechSymbol(CoilContactor.techSymbol), "L1____");

		ThreePhaseFuse   tpf1 = new ThreePhaseFuse(jet2Col, pageNr, getNumerForTechSymbol(ThreePhaseFuse.techSymbol));
		ThreePhaseFuse   tpf2 = new ThreePhaseFuse(jet3Col, pageNr, getNumerForTechSymbol(ThreePhaseFuse.techSymbol));

		firstCoil.addFirstGearContactor(jet2Col);
		secCoil.addJetBridgeContactor(jet1Col);
		secCoil.addSecGearContactor(jet3Col);

		AboveContactorBridge bridge = new AboveContactorBridge(jet1Col);
		UpRightPhasesBridge  urpb   = new UpRightPhasesBridge(jet1Col, "ToJetBridge");
		ToJetBridge          tjb    = new ToJetBridge(jet2Col);
		ToMksBridge          tmb    = new ToMksBridge(jet3Col);

		Mks mks = new Mks(mksCol, this.pageNr, getNumerForTechSymbol(Mks.techSymbol));

		JetEngine           tpe  = new JetEngine(engeCol, this.pageNr);
		UpRightPhasesBridge urpj = new UpRightPhasesBridge(jet2Col, "Receiver");
		UpLeftPhases        ulpj = new UpLeftPhases(jet3Col, "Receiver");
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
			Point t = null;

			Iterator<Point> i = tPoints.iterator();
			while (i.hasNext()) {
				t = i.next();
				if (f.getPotential().getName().equals(t.getPotential().getName())
				&& f.getHeightPos() == t.getHeightPos())
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
			for (VerticalLine line :column.getLines())
				printer.addLine(line);

			for (ColumnRow row : column.getColumnElements())
			{
				if (row.visible())
					printer.addColumnRow(row);

				for(Point p: row.getPoints())
					if (p.isVisibile())
						printer.addPoint(p);

				if (row.getTerminals() != null)
					for(Terminal t: row.getTerminals())
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

package pl.com.hom.scheme;

import static pl.com.hom.configuration.Level.getRoleLevel;
import static pl.com.hom.configuration.Level.lastRowLevel;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Role;
import pl.com.hom.elements.PotentialsSuplier;
import pl.com.hom.elements.ColumnRow;

public class Column {
	private EnumSet<Role>     controller;
	private ArrayList<ColumnRow> columnRows;
	private ArrayList<ColumnLine>  lines;

	private int   index;
	private float x;
	private float y;

	private PotentialsSuplier suplier;

	//TODO Page parent
	public Column(int index) {
		this.index = index;
		this.x     = Measures.COL_WIDTH_MARGIN + Measures.COL_WIDTH * index;
		this.y     = Measures.PAGE_HEIGHT - Measures.COL_LEV_MARGIN;

		suplier = new PotentialsSuplier(this);

		controller = EnumSet.noneOf(Role.class);
		columnRows = new ArrayList<ColumnRow>();
		columnRows.add(suplier);

	}

	//TEST
	public void showLines() {
		for (ColumnLine cl : lines) {
			System.out.println(cl.toString());
		}
	}

	public ArrayList<ColumnLine> getLines() {
		return this.lines;
	}

	//TEST
	public void showSupplierPointsLines() {
		suplier.showPoints();
	}

	public void addElement(ColumnRow element)
	{
		if (controller.contains(element.getRole()))
			throw new RuntimeException("Column has doubled row");

		columnRows.add(element);

		suplier.fetchPointsToSupply(element);

		produceColumnLines();
	}

	public float getWidth() {
		return this.x;
	}

	public float getHeight() {
		return this.y;
	}

	private ColumnRow getColumnRowFromLevel(Integer i) {
		for (ColumnRow e : columnRows)
			if (getRoleLevel(e.getRole()).equals(i))
				return e;

		return null;
	}

	private void linkColumnRows(ColumnRow from, ColumnRow to) {
		ArrayList<Point> fPoints = from.getPointsTargetingDown();
		ArrayList<Point> tPoints = to.getPointsTargetingUp();

		for (Point f : fPoints) {
			Point t = null;

			Iterator<Point> i = tPoints.iterator();
			while (i.hasNext()) {
				t = i.next();
				if (f.getPotential() == t.getPotential())
					break;
			}

			lines.add(new ColumnLine(f, t));
		}
	}

	public void produceColumnLines() {
		for (ColumnRow elem : columnRows)
			for (Point p : elem.getPoints())
				p.unlinkColumnDirections();

		ColumnRow from;
		ColumnRow to;

		lines = new ArrayList<ColumnLine>();
		for (Integer i = 0; i <= lastRowLevel(); i++) {
			from = getColumnRowFromLevel(i);
			if (from == null) continue;

			for (Integer j = i + 1; j <= lastRowLevel(); j++) {
				to = getColumnRowFromLevel(j);
				if (to == null) continue;

				linkColumnRows(from, to);
			}
		}
	}
}	

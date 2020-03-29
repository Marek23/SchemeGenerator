package pl.com.hom.scheme;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import pl.com.hom.connections.Point;
import pl.com.hom.elements.PotentialsSuplier;
import pl.com.hom.utils.Measures;
import pl.com.hom.elements.ColumnRow;

import static pl.com.hom.utils.ColumnLevels.getRowLevel;
import static pl.com.hom.utils.ColumnLevels.lastRowLevel;

public class Column {
	private HashSet<Integer>     controller;
	private ArrayList<ColumnRow> columnRows;
	private ArrayList<ColumnLine>  lines;

	private int   index;
	private float x;

	private PotentialsSuplier suplier;

	public Column(int index) {
		suplier = new PotentialsSuplier();

		controller = new HashSet<Integer>();
		columnRows = new ArrayList<ColumnRow>();
		columnRows.add(suplier);

		this.index = index;
		this.x     = Measures.countColumnWidth(index);
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
		if (controller.contains(getRowLevel(element)))
			throw new RuntimeException("Column has doubled row");

		columnRows.add(element);
		suplier.fetchPointsToSupply(element);

		element.setColumnIndex(this.index);
		suplier.setColumnIndex(this.index);

		produceColumnLines();
	}

	private ColumnRow getColumnRowFromLevel(Integer i) {
		for (ColumnRow e : columnRows)
			if (getRowLevel(e).equals(i))
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

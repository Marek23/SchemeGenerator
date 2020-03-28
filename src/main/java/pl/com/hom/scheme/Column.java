package pl.com.hom.scheme;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import pl.com.hom.connections.Point;
import pl.com.hom.elements.PotentialsSuplier;
import pl.com.hom.elements.ColumnRow;

import static pl.com.hom.utils.ColumnRowRoles.getColumnRowLevel;
import static pl.com.hom.utils.ColumnRowRoles.lastLevel;

public class Column {
	private HashSet<Integer>     controller;
	private ArrayList<ColumnRow> columnRows;
	private HashSet<ColumnLine>  lines;

	private PotentialsSuplier suplier;

	public Column() {
		suplier = new PotentialsSuplier();
		columnRows.add(suplier);
	}

	public void addElement(ColumnRow element)
	{
		if (controller.contains(getColumnRowLevel(element)))
			throw new RuntimeException("Column has doubled row");

		suplier.fetchPointsToSupply(element);
		columnRows.add(element);
		produceColumnLines();
	}

	private ColumnRow getColumnRowFromLevel(Integer i) {
		for(ColumnRow e : columnRows)
			if(getColumnRowLevel(e) == i)
				return e;

		return null;
	}

	private void linkPoints(HashSet<Point> up, HashSet<Point> down) {
		for (Point f : up) {
			Point t = null;

			Iterator<Point> i = down.iterator();
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

		lines = new HashSet<ColumnLine>();
		for (Integer i = 0; i <= lastLevel(); i++) {
			from = getColumnRowFromLevel(i);
			if (from == null) continue;
			HashSet<Point> fPoints = from.getPointsTargetingDown();

			for (Integer j = i + 1; j <= lastLevel(); j++) {
				to = getColumnRowFromLevel(j);
				if (to == null) continue;
				HashSet<Point> tPoints = from.getPointsTargetingUp();

				linkPoints(fPoints, tPoints);
			}
		}
	}
}	

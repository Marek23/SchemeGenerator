package pl.com.hom.scheme;

import static pl.com.hom.configuration.Level.getRoleLevel;
import static pl.com.hom.configuration.Level.lastRowLevel;

import java.util.ArrayList;
import java.util.Iterator;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.elements.ColumnRow;

public class Column {
	private ArrayList<ColumnRow>   columnRows;
	private ArrayList<ColumnLine>  lines;

	private float x;
	private float width;

	private ArrayList<Point> supplyPoints;

	//TODO Page parent
	public Column(Page parent, float width) {
		this.width = width;
		this.x     = parent.getWidthPos() + Measures.COL_WIDTH_MARGIN + width;

		this.columnRows   = new ArrayList<ColumnRow>();
		this.supplyPoints = new ArrayList<Point>();

		System.out.println("Column width: " + x);
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
		for (Point p : supplyPoints)
			System.out.println(p);
	}

	public void addElement(ColumnRow element)
	{
		for (ColumnRow r : columnRows)
			if (r.getRole() == element.getRole())
				throw new RuntimeException("Column has doubled row");

		columnRows.add(element);
		fetchPointsToSupply(element);

		produceColumnLines();
	}

	public float getWidth() {
		return this.width;
	}

	public float getWidthPos() {
		return this.x;
	}

	private ColumnRow getColumnRowFromLevel(Integer i) {
		for (ColumnRow e : columnRows)
			if (getRoleLevel(e.getRole()).equals(i))
				return e;

		return null;
	}

	private void createLines(ArrayList<Point> from, ArrayList<Point> to) {
		ArrayList<Point> fPoints = new ArrayList<Point>();
		ArrayList<Point> tPoints = new ArrayList<Point>();

		for (Point p : from)
			if (p.hasDirection(Direction.Down))
				fPoints.add(p);

		for (Point p : to)
			if (p.hasDirection(Direction.Up))
				tPoints.add(p);

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

		for (Integer i = 0; i <= lastRowLevel(); i++)
		{
			to = getColumnRowFromLevel(i);
			if (to != null)
				createLines(supplyPoints, to.getPoints());
		}

		for (Integer i = 0; i <= lastRowLevel(); i++) {
			from = getColumnRowFromLevel(i);
			if (from == null) continue;

			for (Integer j = i + 1; j <= lastRowLevel(); j++) {
				to = getColumnRowFromLevel(j);
				if (to == null) continue;

				createLines(from.getPoints(), to.getPoints());
			}
		}
	}

	private void fetchPointsToSupply(ColumnRow element) {
		for (Point point : element.getPoints())
		{
			Potential pot = point.getPotential();

			if (!hasElementPotential(pot) && point.hasDirection(Direction.Up))
				this.supplyPoints.add(new Point(this,pot));
		}
	}

	private boolean hasElementPotential(Potential pot) {
		Iterator<Point> i = supplyPoints.iterator();
		while (i.hasNext())
			if (i.next().getPotential() == pot)
				return true;

		return false;
	}
}	

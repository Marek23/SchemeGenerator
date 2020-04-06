package pl.com.hom.scheme;

import java.util.ArrayList;
import java.util.Iterator;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Potential;
import pl.com.hom.elements.ColumnRow;

import static pl.com.hom.configuration.MainPotentialHeight.getHeight;

public class Column {
	private ArrayList<ColumnRow>   columnRows;
	private ArrayList<VerticalLine>  lines;

	private float x;
	private float width;

	private ArrayList<Point> supplyPoints;

	//TODO Page parent
	public Column(JetPage parent, float width) {
		this.width = width;
		this.x     = parent.getWidthPos() + Measures.COL_WIDTH_MARGIN + width;

		this.columnRows   = new ArrayList<ColumnRow>();
		this.supplyPoints = new ArrayList<Point>();

		parent.addColumn(this);
	}

	//TEST
	public void showLines() {
		for (VerticalLine cl : lines) {
			System.out.println(cl.toString());
		}
	}

	//TEST
	public void showElements() {
		for (ColumnRow r : columnRows) {
			System.out.println(r.toString());
		}
	}

	public ArrayList<ColumnRow> getColumnRows() {
		return this.columnRows;
	}

	public ArrayList<VerticalLine> getLines() {
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
			if (r.getRole().getLevel() == element.getRole().getLevel())
				throw new RuntimeException("Column has doubled row");

		columnRows.add(element);
		fetchPointsToSupply(element);
	}

	public float getWidth() {
		return this.width;
	}

	public float getWidthPos() {
		return this.x;
	}

	public ArrayList<ColumnRow> getColumnElements() {
		return this.columnRows;
	}

	public ColumnRow getColumnRowFromLevel(int i) {
		for (ColumnRow e : columnRows)
			if (e.getRole().getLevel() == i)
				return e;

		return null;
	}

	private void createVerticalLines(ArrayList<Point> from, ArrayList<Point> to) {
		ArrayList<Point> fPoints = new ArrayList<Point>();
		ArrayList<Point> tPoints = new ArrayList<Point>();

		for (Point p : from)
			if (p.hasUnlinkedDirection(Direction.Down))
				fPoints.add(p);

		for (Point p : to)
			if (p.hasUnlinkedDirection(Direction.Up))
				tPoints.add(p);

		for (Point f : fPoints) {
			Point t = null;

			Iterator<Point> i = tPoints.iterator();
			while (i.hasNext()) {
				t = i.next();
				if (f.getPotential() == t.getPotential() && f.getWidthPos() == t.getWidthPos())
					break;
			}

			if (t != null)
				lines.add(new VerticalLine(f, t));
		}
	}

	public void createVerticalLines() {
		this.lines = new ArrayList<VerticalLine>();

		for (ColumnRow elem : columnRows)
			for (Point p : elem.getPoints())
				p.unlinkVerticalDirections();

		ColumnRow from;
		ColumnRow to;

		for (Integer i = Roles.rolesNumber(); i >= 1; i--) {
			to = getColumnRowFromLevel(i);
			if (to == null) continue;

			for (Integer j = i - 1; j >= 0 ; j--) {
				from = getColumnRowFromLevel(j);
				if (from == null) continue;

				createVerticalLines(from.getPoints(), to.getPoints());
			}
		}

		for (Integer i = Roles.rolesNumber(); i >= 0; i--) {
			to = getColumnRowFromLevel(i);
			if (to == null) continue;

			createVerticalLines(supplyPoints, to.getPoints());
		}
		
	}

	private void fetchPointsToSupply(ColumnRow element) {
		this.supplyPoints = new ArrayList<Point>();

		for (Point point : element.getPoints())
		{
			Potential pointPot  = point.getPotential();
			float pointWidthPos = point.getWidthPos();

			if (point.hasDirection(Direction.Up))
				if (!hasPotentialInWidthPos(pointPot, pointWidthPos)) 
				{
					float mainHeight = getHeight(pointPot.getPotential());
					this.supplyPoints.add(
						new Point(this, pointPot.getPotential(), pointWidthPos, mainHeight)
					);
				}
		}
	}

	private boolean hasPotentialInWidthPos(Potential potential, float widthPos) {
		Iterator<Point> i = supplyPoints.iterator();
		while (i.hasNext())
		{
			Point supplyPoint = i.next();

			if (supplyPoint.getPotential() == potential && supplyPoint.getWidthPos() == widthPos)
				return true;
		}

		return false;
	}
}	

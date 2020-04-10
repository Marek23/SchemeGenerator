package pl.com.hom.scheme;

import java.util.ArrayList;
import java.util.Iterator;

import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Potential;
import pl.com.hom.elements.ColumnRow;

public class Column {
	private ArrayList<ColumnRow>   columnRows;
	private ArrayList<VerticalLine>  lines;

	private float x;
	private float width;

	private JetPage parent;

	private ArrayList<Point> mainPoints;

	public ArrayList<Point> mainPoints() {
		return mainPoints;
	}

	//TODO Page parent
	public Column(JetPage parent, float width) {
		this.width = width;
		this.x     = parent.widthPos() + width;

		this.columnRows = new ArrayList<ColumnRow>();
		this.mainPoints = new ArrayList<Point>();

		this.mainPoints = new ArrayList<Point>();

		this.parent = parent;
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

	public ArrayList<ColumnRow> columnRows() {
		return this.columnRows;
	}

	public ArrayList<VerticalLine> lines() {
		return this.lines;
	}

	//TEST
	public void showSupplierPointsLines() {
		for (Point p : mainPoints)
			System.out.println(p);
	}

	public void addElement(ColumnRow element)
	{
		for (ColumnRow r : columnRows)
			if (r.role().level() == element.role().level())
				throw new RuntimeException("Column has doubled row");

		columnRows.add(element);
		fetchPointsToSupply(element);
	}

	public float width() {
		return this.width;
	}

	public float widthPos() {
		return this.x;
	}

	public ArrayList<ColumnRow> getColumnElements() {
		return this.columnRows;
	}

	public ColumnRow getColumnRowFromLevel(int i) {
		for (ColumnRow e : columnRows)
			if (e.role().level() == i)
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
				if (f.potential().name().equals(t.potential().name())
				&& f.widthPos() == t.widthPos())
				{
					break;
				}
			}

			if (t != null)
				lines.add(new VerticalLine(f, t));
		}
	}

	public void createVerticalLines() {
		this.lines = new ArrayList<VerticalLine>();

		for (ColumnRow elem : columnRows)
			for (Point p : elem.points())
				p.unlinkVerticalDirections();

		ColumnRow from;
		ColumnRow to;

		for (Integer i = Roles.rolesNumber(); i >= 1; i--) {
			to = getColumnRowFromLevel(i);
			if (to == null) continue;

			for (Integer j = i - 1; j >= 0 ; j--) {
				from = getColumnRowFromLevel(j);
				if (from == null) continue;

				createVerticalLines(from.points(), to.points());
			}
		}

		for (Integer i = Roles.rolesNumber(); i >= 0; i--) {
			to = getColumnRowFromLevel(i);
			if (to == null) continue;

			createVerticalLines(mainPoints, to.points());
		}
		
	}

	private void fetchPointsToSupply(ColumnRow element) {
		for (Point point : element.points())
		{
			Potential potential  = point.potential();
			float widthPos  = point.widthPos();

			if (point.hasDirection(Direction.Up) && potential.name().startsWith("MAIN")) 
				if (!hasPotentialInWidthPos(potential, widthPos)) 
					this.mainPoints.add(Point.newMainPoint(point));
		}
	}

	private boolean hasPotentialInWidthPos(Potential potential, float widthPos) {
		Iterator<Point> i = mainPoints.iterator();
		while (i.hasNext())
		{
			Point supplyPoint = i.next();

			if (supplyPoint.potential().name().equals(potential.name())
			&& supplyPoint.widthPos() == widthPos)
			{
				return true;
			}
		}

		return false;
	}

	public JetPage getParent() {
		return this.parent;
	}
}	

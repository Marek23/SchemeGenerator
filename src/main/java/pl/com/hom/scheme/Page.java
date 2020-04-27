package pl.com.hom.scheme;

import java.util.ArrayList;
import java.util.Iterator;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfPage;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Potentials;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Potential;
import pl.com.hom.connections.Terminal;
import pl.com.hom.data.Board;
import pl.com.hom.elements.Element;
import pl.com.hom.printer.Printer;

import static pl.com.hom.configuration.Sequences.sequence;

public class Page extends PdfPage{
	protected static final long serialVersionUID = 7351148506505896070L;

	protected ArrayList<Point>    points;
	protected ArrayList<Line>     lines;
	protected ArrayList<Element>  elements;
	protected ArrayList<Terminal> terminals;

	protected int nr;

	Printer printer;

	public Page(Board parent) {
		super(parent, PageSize.A4.rotate());
		parent.addPage(this);

		this.nr  = sequence("page");
		this.printer = new Printer(this);

		this.points         = new ArrayList<Point>();
		this.lines          = new ArrayList<Line>();
		this.elements       = new ArrayList<Element>();
		this.terminals      = new ArrayList<Terminal>();

		this.printer = new Printer(this);
	}

	public void showPoints() {
		for (Point p : points)
			System.out.println(p);
	}

	public void showLines() {
		for (Line l : lines)
			System.out.println(l);
	}

	public void add(Element element) {
		this.elements.add(element);

		addMainPoints(element);
	}

	protected void createLines() {
		for (int i = 0; i < points.size(); i++) {
			Point from = points.get(i);

			if (from.existDisconnected(Direction.Right)) {
				Point nearest = null;

				for (int j = 0; j < points.size(); j++) {
					Point to = points.get(j);

					if (to.existDisconnected(Direction.Left) && from.heightPos() == to.heightPos()
					&& from.potential().shortName().equals(to.potential().shortName()))
					{
						float newDistance = to.widthPos() - from.widthPos();

						if (newDistance > 0)
						{
							if (nearest == null)
								nearest = to;
							else
							{
								float currDistance = nearest.widthPos() - from.widthPos();
	
								if (newDistance < currDistance)
									nearest = to; 
							}
						}
					}
				}

				if (nearest != null)
					lines.add(new HorizontalLine(from, nearest));
			}
		}

		for (int i = 0; i < points.size(); i++)
		{
			Point from = points.get(i);

			if (from.existDisconnected(Direction.Down)) {
				Point nearest = null;

				for (int j = 0; j < points.size(); j++) {
					Point to = points.get(j);

					if (to.existDisconnected(Direction.Up) && from.widthPos() == to.widthPos()
					&& from.potential().shortName().equals(to.potential().shortName()))
					{
						float newDistance = to.heightPos() - from.heightPos();

						if (newDistance > 0)
						{

							if (nearest == null)
								nearest = to;
							else
							{
								float currDistance = nearest.heightPos() - from.heightPos();

								if (newDistance < currDistance)
									nearest = to; 
							}
						}
					}
				}

				if (nearest != null)
					lines.add(new VerticalLine(from, nearest));
			}
		}
	}

	public void draw() {
		createLines();

		for (Line line : lines)
			printer.addLine(line);

		for(Point p: points)
			printer.addPoint(p);

		for (Element e : elements)
			printer.addElement(e);

		for (Terminal t : terminals)
			printer.addTerminal(t);
	}

	public void addAll(ArrayList<Point> points) {
		this.points.addAll(points);
	}

	public void add(Point point) {
		this.points.add(point);
	}

	private void addMainPoints(Element element) {
		for (Point point : element.points()) {
			String shortName = point.potential().shortName();
			String fullName  = point.potential().fullName();

			float width  = point.widthPos();
			float height = point.potential().height();
			if (point.has(Direction.Up) && shortName.startsWith("MAIN"))
			{
				if (!hasPoint(shortName, width, height)) {
					Point.supply(this, point, Direction.Down);

					if (!hasPoint(shortName, Measures.BEGIN_MAIN_POINT, point.potential().height()))
						Point.page(this, Measures.BEGIN_MAIN_POINT, point.potential().height(), shortName);

					if (!hasPoint(shortName, Measures.END_MAIN_POINT, point.potential().height()))
						Point.page(this, Measures.END_MAIN_POINT,     point.potential().height(), shortName);
				}
			}

			if (point.has(Direction.Down) && shortName.startsWith("GROUND"))
			{
				if (!hasPoint(shortName, width, height)) {
					Point.supply(this, point, Direction.Up);

					if (!hasPoint(shortName, Measures.BEGIN_MAIN_POINT, point.potential().height()))
						Point.page(this, Measures.BEGIN_MAIN_POINT, point.potential().height(), shortName);

					if (!hasPoint(shortName, Measures.END_MAIN_POINT, point.potential().height()))
						Point.page(this, Measures.END_MAIN_POINT,     point.potential().height(), shortName);
				}
			}

			if (point.has(Direction.Up) && shortName.startsWith("GROUND"))
			{
				if (!hasPoint(shortName, width, height))
				{
					Point.supply(this, point, Direction.Down);

					if (!hasPoint(shortName, Measures.BEGIN_MAIN_POINT, point.potential().height()))
						Point.page(this, Measures.BEGIN_MAIN_POINT, point.potential().height(), shortName);

					if (!hasPoint(shortName, Measures.END_MAIN_POINT, point.potential().height()))
						Point.page(this, Measures.END_MAIN_POINT,     point.potential().height(), shortName);
				}
			}

			if (point.has(Direction.Up) && (shortName.startsWith("1B") || shortName.startsWith("2B")))
			{
				if (!hasPoint(fullName, width, height))
				{
					Point.supply(this, point, Direction.Down);

					if (!hasPoint(fullName, Measures.BEGIN_STEER_POINT, point.potential().height()))
						Point.page(this, Measures.BEGIN_STEER_POINT, point.potential().height(), fullName);

					if (!hasPoint(fullName, Measures.END_STEER_POINT, point.potential().height()))
						Point.page(this, Measures.END_STEER_POINT,     point.potential().height(), fullName);
				}
			}
		}
	}

	private boolean hasPoint(String main, float width, float height) {
		Iterator<Point> i = points.iterator();

		Potential potential = Potentials.potential(main);
		
		while (i.hasNext()) {
			Point point = i.next();

			if (point.potential() == potential && point.widthPos()  == width && point.heightPos() == height)
				return true;
		}

		return false;
	}

	public int nr() {
		return nr;
	}

	public void add(Terminal t) {
		terminals.add(t);
	}
}

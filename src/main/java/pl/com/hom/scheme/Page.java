package pl.com.hom.scheme;

import java.util.ArrayList;
import java.util.Iterator;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfPage;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Potentials;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.HorizontalLine;
import pl.com.hom.connections.Line;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Potential;
import pl.com.hom.connections.Terminal;
import pl.com.hom.connections.VerticalLine;
import pl.com.hom.data.Board;
import pl.com.hom.element.pointer.Pointer;
import pl.com.hom.element.Element;
import pl.com.hom.element.main.Mks;
import pl.com.hom.printer.Printer;

import static pl.com.hom.configuration.Sequences.sequence;

public class Page extends PdfPage{
	protected static final long serialVersionUID = 7351148506505896070L;

	protected ArrayList<Point>    points;
	protected ArrayList<Point>    begins;
	protected ArrayList<Point>    ends;
	protected ArrayList<Line>     lines;
	protected ArrayList<Element>  elements;
	protected ArrayList<Pointer>  pointers;
	protected ArrayList<Terminal> terminals;

	protected int nr;

	Printer printer;

	public Page(Board board) {
		super(board, PageSize.A4.rotate());
		board.addPage(this);

		this.nr  = sequence("page");
		this.printer = new Printer(this);

		this.points         = new ArrayList<Point>();
		this.begins         = new ArrayList<Point>();
		this.ends           = new ArrayList<Point>();
		this.lines          = new ArrayList<Line>();
		this.elements       = new ArrayList<Element>();
		this.pointers       = new ArrayList<Pointer>();
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

	public void add(Pointer p) {
		this.pointers.add(p);
	}

	protected void createLines() {
		for (int i = 0; i < points.size(); i++) {
			Point from = points.get(i);

			if (from.existDisconnected(Direction.Right)) {
				Point nearest = null;

				for (int j = 0; j < points.size(); j++) {
					Point to = points.get(j);

					if (to.existDisconnected(Direction.Left) && from.heightPos() == to.heightPos()
					&& from.potential().name().equals(to.potential().name()))
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
					&& from.potential().name().equals(to.potential().name()))
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

		for(Point p: begins)
			printer.addBeginPoint(p);

		for(Point p: ends)
			printer.addEndPoint(p);

		for (Element e : elements)
			printer.addElement(e);

		for (Pointer p : pointers)
			printer.addPointer(p);

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
			String name = point.potential().name();

			float width  = point.widthPos();
			float height = point.potential().height();
			if (point.has(Direction.Up) && name.startsWith("MAIN"))
			{
				if (!hasPoint(name, width, height)) {
					Point.supply(this, point, Direction.Down);

					if (!hasPoint(name, Measures.BEGIN_MAIN_POINT, point.potential().height()))
						begins.add(Point.page(this, Measures.BEGIN_MAIN_POINT, point.potential().height(), name));

					if (!hasPoint(name, Measures.END_MAIN_POINT, point.potential().height()))
						ends.add(Point.page(this, Measures.END_MAIN_POINT, point.potential().height(), name));
				}
			}

			if (point.has(Direction.Down) && name.startsWith("GROUND"))
			{
				if (!hasPoint(name, width, height)) {
					Point.supply(this, point, Direction.Up);

					if (!hasPoint(name, Measures.BEGIN_MAIN_POINT, point.potential().height()))
						begins.add(Point.page(this, Measures.BEGIN_MAIN_POINT, point.potential().height(), name));

					if (!hasPoint(name, Measures.END_MAIN_POINT, point.potential().height()))
						ends.add(Point.page(this, Measures.END_MAIN_POINT, point.potential().height(), name));
				}
			}

			if (point.has(Direction.Up) && name.startsWith("GROUND"))
			{
				if (!hasPoint(name, width, height))
				{
					Point.supply(this, point, Direction.Down);

					if (!hasPoint(name, Measures.BEGIN_MAIN_POINT, point.potential().height()))
						begins.add(Point.page(this, Measures.BEGIN_MAIN_POINT, point.potential().height(), name));

					if (!hasPoint(name, Measures.END_MAIN_POINT, point.potential().height()))
						ends.add(Point.page(this, Measures.END_MAIN_POINT, point.potential().height(), name));
				}
			}

			if (point.has(Direction.Up) && (name.startsWith("1B") || name.startsWith("2B")))
			{
				if (!hasPoint(name, width, height))
				{
					Point.supply(this, point, Direction.Down);

					if (!hasPoint(name, Measures.BEGIN_STEER_POINT, point.potential().height()))
						begins.add(Point.page(this, Measures.BEGIN_STEER_POINT, point.potential().height(), name));

					if (!hasPoint(name, Measures.END_STEER_POINT, point.potential().height()))
						ends.add(Point.page(this, Measures.END_STEER_POINT, point.potential().height(), name));
				}
			}
		}
	}

	public ArrayList<Point> begins() {
		ArrayList<Point> out = new ArrayList<Point>();
		for (Point p: begins)
			if (p.target() == 0)
				out.add(p);

		return out;
	}

	public ArrayList<Point>ends() {
		ArrayList<Point> out = new ArrayList<Point>();
		for (Point p: ends)
			if (p.target() == 0)
				out.add(p);

		return out;
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

	public boolean pendingEnds() {
		for(Point p: ends)
			if (p.target() == 0)
				return true;

		return false;
	}

	public boolean pendingBegins() {
		for(Point p: begins)
			if (p.target() == 0)
				return true;

		return false;
	}

	public Mks mks() {
		for (Element e: elements)
			if (e instanceof Mks)
				return (Mks) e;

		return null;
	}
}

package pl.com.hom.scheme;

import java.util.ArrayList;
import java.util.Iterator;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfPage;

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

	private int nr;

	Printer printer;

	public Page(Board parent) {
		super(parent, PageSize.A4.rotate());
		parent.addPage(this);

		this.nr  = sequence("page");
		this.printer = new Printer(this);

		this.points    = new ArrayList<Point>();
		this.lines     = new ArrayList<Line>();
		this.elements  = new ArrayList<Element>();
		this.terminals = new ArrayList<Terminal>();

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

	public void addElement(Element element) {
		this.elements.add(element);

		this.points.addAll(element.points());

		addMainsPoints(element);
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

		for (Element e : elements)
			printer.addElement(e);

		for (Terminal t : terminals)
			printer.addTerminal(t);
	}

	public void addAll(ArrayList<Point> points) {
		this.points.addAll(points);
	}

	private void addMainsPoints(Element element) {
		for (Point point : element.points()) {
			String shortName = point.potential().name();

			if (shortName.startsWith("MAIN") || shortName.startsWith("GROUND") || shortName.startsWith("STEER"))
			{
				float width  = point.widthPos();

				if (!hasMainPoint(shortName, width))
					if (point.has(Direction.Up) || point.has(Direction.Down))
						this.points.add(Point.mainPoint(this, point));
			}
		}
	}

	private boolean hasMainPoint(String main, float width) {
		Iterator<Point> i = points.iterator();

		Potential potential = Potentials.potential(main);
		float height = potential.height();
		
		while (i.hasNext()) {
			Point point = i.next();

			if (point.potential() == potential && point.widthPos()  == width && point.heightPos() == height)
				return true;
		}

		return false;
	}

	public int getNr() {
		return nr;
	}

	public void terminal(Terminal t) {
		terminals.add(t);
	}
}
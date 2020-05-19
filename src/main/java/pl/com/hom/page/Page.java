package pl.com.hom.page;

import java.util.ArrayList;
import java.util.Iterator;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfPage;

import pl.com.hom.board.Board;
import pl.com.hom.configuration.Potentials;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.HorizontalLine;
import pl.com.hom.connections.Line;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Potential;
import pl.com.hom.connections.Terminal;
import pl.com.hom.connections.VerticalLine;
import pl.com.hom.element.pointer.Pointer;
import pl.com.hom.element.Element;
import pl.com.hom.element.main.Mks;
import pl.com.hom.printer.Printer;

import static pl.com.hom.configuration.Widths.x;
import static pl.com.hom.configuration.Sequences.sequence;
import static pl.com.hom.configuration.Sequences.sequence0;

public class Page extends PdfPage{
	protected static final long serialVersionUID = 7351148506505896070L;

	protected Board board;

	protected ArrayList<Point>    points;
	protected ArrayList<Point>    begins;
	protected ArrayList<Point>    ends;
	protected ArrayList<Line>     lines;
	protected ArrayList<Element>  elements;
	protected ArrayList<Pointer>  pointers;
	protected ArrayList<Terminal> terminals;
	protected ArrayList<String>   plcInputs;
	protected ArrayList<String>   plcOutputs;

	protected int nr;

	private Printer printer;

	public Page(Board board) {
		super(board, PageSize.A4.rotate());

		board.addPage(this);

		this.board = board;

		this.nr  = sequence(board.name() + "page");
		this.printer = new Printer(this);

		this.points    = new ArrayList<Point>();
		this.begins    = new ArrayList<Point>();
		this.ends      = new ArrayList<Point>();
		this.lines     = new ArrayList<Line>();
		this.elements  = new ArrayList<Element>();
		this.pointers  = new ArrayList<Pointer>();
		this.terminals = new ArrayList<Terminal>();

		this.plcInputs  = new ArrayList<String>();
		this.plcOutputs = new ArrayList<String>();

		this.printer = new Printer(this);
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

		for (String t : plcInputs)
			printer.addPlcInput(t);

		for (String t : plcOutputs)
			printer.addPlcOutput(t);
	}

	public void addAll(ArrayList<Point> points) {
		this.points.addAll(points);
	}

	public void add(Point point) {
		this.points.add(point);
	}

	public void addPlcX(String x) {
		this.plcInputs.add(x);
	}

	public void addPlcY(String y) {
		this.plcOutputs.add(y);
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

					if (!hasPoint(name, x("pageBegin"), point.potential().height()))
						begins.add(Point.page(this, x("pageBegin"), point.potential().height(), name));

					if (!hasPoint(name, x("pageEnd"), point.potential().height()))
						ends.add(Point.page(this, x("pageEnd"), point.potential().height(), name));
				}
			}

			if (point.has(Direction.Down) && name.startsWith("GROUND"))
			{
				if (!hasPoint(name, width, height)) {
					Point.supply(this, point, Direction.Up);

					if (!hasPoint(name, x("pageBegin"), point.potential().height()))
						begins.add(Point.page(this, x("pageBegin"), point.potential().height(), name));

					if (!hasPoint(name, x("pageEnd"), point.potential().height()))
						ends.add(Point.page(this, x("pageEnd"), point.potential().height(), name));
				}
			}

			if (point.has(Direction.Up) && name.startsWith("GROUND"))
			{
				if (!hasPoint(name, width, height))
				{
					Point.supply(this, point, Direction.Down);

					if (!hasPoint(name, x("pageBegin"), point.potential().height()))
						begins.add(Point.page(this, x("pageBegin"), point.potential().height(), name));

					if (!hasPoint(name, x("pageEnd"), point.potential().height()))
						ends.add(Point.page(this, x("pageEnd"), point.potential().height(), name));
				}
			}

			if (point.has(Direction.Up) && (name.startsWith("1B") || name.startsWith("2B") || name.startsWith("LEW") || name.startsWith("PRA")))
			{
				if (!hasPoint(name, width, height))
				{
					Point.supply(this, point, Direction.Down);

					if (!hasPoint(name, x("steeringBegin"), point.potential().height()))
						begins.add(Point.page(this, x("steeringBegin"), point.potential().height(), name));

					if (!hasPoint(name, x("steeringEnd"), point.potential().height()))
						ends.add(Point.page(this, x("steeringEnd"), point.potential().height(), name));
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

	public void clearPendingEdges() {
		ArrayList<Point> unmodified = new ArrayList<Point>(ends);
		for(Point p: unmodified)
			if (p.target() == 0) {
				ends.remove(p);
				points.remove(p);
			}

		unmodified = new ArrayList<Point>(begins);
		for(Point p: unmodified)
			if (p.target() == 0) {
				begins.remove(p);
				points.remove(p);
			}
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

	protected float coilX() {
		return x("coilsBegin") + x("coilSpace") * sequence0(board.name() + "PAGE" + String.valueOf(this.nr) + "COIL");
	}

	protected float plcModuleX() {
		return x("plcBegin") + x("plcModuleWidth") * (sequence0(board.name() + "PAGE" + String.valueOf(this.nr) + "PLC"));
	}

	protected float plcSignalX() {
		return x("plcSignalBegin") + x("plcSignalWidth") * (sequence0(board.name() + "PAGE" + String.valueOf(this.nr) + "PLCSIGNAL"));
	}

	public void end(Point p) {
		this.ends.add(p);
	}

	public Board board() {
		return board;
	}
}

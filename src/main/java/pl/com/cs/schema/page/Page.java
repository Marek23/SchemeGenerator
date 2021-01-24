package pl.com.cs.schema.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfPage;

import pl.com.cs.fps.Fps;
import pl.com.cs.schema.Potentials;
import pl.com.cs.schema.Direction;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.Potential;
import pl.com.cs.schema.out.Terminal;
import pl.com.cs.fps.SapInput;
import pl.com.cs.fps.SapOutput;
import pl.com.cs.schema.pointer.Pointer;
import pl.com.cs.schema.HorizontalLine;
import pl.com.cs.schema.main.MksMain;
import pl.com.cs.schema.Line;
import pl.com.cs.schema.VerticalLine;
import pl.com.cs.schema.Drawable;
import pl.com.cs.Printer;

import static pl.com.cs.config.Widths.x;

public class Page extends PdfPage{
	protected static final long serialVersionUID = 7351148506505896070L;

	protected Fps fps;

	protected ArrayList<Point>    points;
	protected ArrayList<Point>    begins;
	protected ArrayList<Point>    ends;
	protected ArrayList<Line>     lines;
	protected ArrayList<Drawable> drawables;
	protected ArrayList<Pointer>  pointers;
	protected ArrayList<Terminal> terminals;
	protected ArrayList<String>   plcInputs;
	protected ArrayList<String>   plcOutputs;
	protected ArrayList<String>   sapInputs;
	protected ArrayList<String>   sapOutputs;
	protected List<String>        motorDescription;

	protected int nr;

	private Printer printer;

	public Page(Fps fps) {
		super(fps, PageSize.A4.rotate());

		fps.addPage(this);

		this.fps = fps;

		this.nr  = fps.nextValueOf("page");
		this.printer = new Printer(this);

		this.points    = new ArrayList<Point>();
		this.begins    = new ArrayList<Point>();
		this.ends      = new ArrayList<Point>();
		this.lines     = new ArrayList<Line>();
		this.drawables = new ArrayList<Drawable>();
		this.pointers  = new ArrayList<Pointer>();
		this.terminals = new ArrayList<Terminal>();

		this.plcInputs  = new ArrayList<String>();
		this.plcOutputs = new ArrayList<String>();

		this.sapInputs  = new ArrayList<String>();
		this.sapOutputs = new ArrayList<String>();

		this.motorDescription = new ArrayList<String>();

		this.printer = new Printer(this);
	}

	public void add(Drawable d) {
		this.drawables.add(d);

		addMainPoints(d.points());
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

		for (Drawable d : drawables)
			printer.addElement(d);

		for (Pointer p : pointers)
			printer.addPointer(p);

		for (int i = 0; i < terminals.size(); i++) {
			Terminal t = terminals.get(i);
			if (i > 0) {
				printer.addTerminal(t);
			}
			else {
				printer.addTerminalWithGroup(t);
				printer.addTerminal(t);
			}
		}

		for (String t : plcInputs)
			printer.addPlcInput(t);

		for (String t : plcOutputs)
			printer.addPlcOutput(t);

		for (String si : sapInputs)
			printer.addSapInput(si);

		for (String so : sapOutputs)
			printer.addSapOutput(so);

		for (String md : motorDescription)
			printer.addMotorDescription(md);
	}

	public void addAll(ArrayList<Point> points) {
		this.points.addAll(points);
	}

	public void add(Point point) {
		this.points.add(point);
	}

	public void createMainForPoint(Point point) {
		var tmp = new ArrayList<Point>();
		tmp.add(point);
		addMainPoints(tmp);
	}

	public void addSapInputs(ArrayList<SapInput> inputs) {
		for (SapInput si: inputs)
			this.sapInputs.add("SAP IN: " + si.symbol() + " - " + si.function());
	}

	public void addMotorDescription(List<String> desc) {
		this.motorDescription = desc;
	}

	public void addSapOutput(SapOutput out) {
		this.sapInputs.add("SAP OUT: " + out.symbol() + " - " + out.function());
	}

	public void addPlcX(String x) {
		this.plcInputs.add(x);
	}

	public void addPlcY(String y) {
		this.plcOutputs.add(y);
	}

	private void addMainPoints(ArrayList<Point> points) {
		for (Point point : points) {
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

	public MksMain mks() {
		for (Drawable d: drawables)
			if (d instanceof MksMain)
				return (MksMain) d;

		return null;
	}

	protected float coilX() {
		return x("coilsBegin") + x("coilSpace") * (fps.nextValueOf("PAGE" + String.valueOf(this.nr) + "COIL") - 1);
	}

	protected float nextSapOutput() {
		return x("sapOutBegin") + x("sapOutSpace") * (fps.nextValueOf("PAGE" + String.valueOf(this.nr) + "SAPOUT") - 1);
	}
	
	
	protected float plcModuleX() {
		return x("plcBegin") + x("plcModuleWidth") * (fps.nextValueOf("PAGE" + String.valueOf(this.nr) + "PLC") - 1);
	}

	protected float plcSignalX() {
		return x("plcSignalBegin") + x("plcSignalWidth") * (fps.nextValueOf("PAGE" + String.valueOf(this.nr) + "PLCSIGNAL") - 1);
	}

	public void end(Point p) {
		this.ends.add(p);
	}

	public Fps fps() {
		return fps;
	}
}

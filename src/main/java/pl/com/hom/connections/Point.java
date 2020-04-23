package pl.com.hom.connections;

import java.util.EnumMap;
import java.util.Map.Entry;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Potentials;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;
public class Point {
	private float x;
	private float y;

	private float width;
	private float height;

	private String name;

	private boolean        visibility;
	private PdfFormXObject image;

	private EnumMap<Direction, Boolean> directions;
	private Potential potential;

	@Override
	public String toString() {
		return "Point [ x=" + x + ", y=" + y + ", visibility=" + visibility + ", image=" + image
				+ ", directions=" + directions + ", potential=" + potential + "]";
	}

	public static Point upOrDownPotential(Page page, Element parent, String potName, Direction direction) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(direction, false);

		Potential potential = Potentials.potential(potName);

		float x = potential.width();
		float y;

		if (direction == Direction.Up)
			y = 0f;
		else
			y = parent.height();

		return new Point(page, parent, potential, x, y, dirs, false);
	}

	public static Point upOrDownTerminal(Terminal parent, Direction direction) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(direction, false);

		return new Point(parent, direction);
	}

	public static Point pe(Page page, Element parent, float x, float y) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);

		Potential potential = Potentials.potential("GROUNDPE__");

		return new Point(page, parent, potential, x, y, dirs, false);
	}

	public static Point mainPoint(Page page, Point point) {
		if (point.has(Direction.Up))
			return new Point(page, point, Direction.Down);

		if (point.has(Direction.Down))
			return new Point(page, point, Direction.Up);

		throw new RuntimeException("Error while adding main point");
	}

	public static Point steerPoint(Page page, Point point) {
		return new Point(page, point);
	}

	public static Point upDownLeftBridge(Page page, Element parent, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Left, false);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Down, false);

		Potential potential = Potentials.potential(potName);

		float x = potential.width();
		float y = potential.height();

		return new Point(page, parent, potential, x, y, dirs, true);	
	}

	public static Point upDownRightBridge(Page page, Element parent, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Right, false);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Down, false);

		Potential potential = Potentials.potential(potName);

		float x = potential.width();
		float y = potential.height();

		return new Point(page, parent, potential, x, y, dirs, true);	
	}

	public static Point upLeftPoint(Page page, Element parent, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Left, false);

		Potential potential = Potentials.potential(potName);

		float x = potential.width();
		float y = potential.height();

		return new Point(page, parent, potential, x, y, dirs, false);	
	}

	public static Point upRightPoint(Page page, Element parent, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Right, false);

		Potential potential = Potentials.potential(potName);

		float x = potential.width();
		float y = potential.height();

		return new Point(page, parent, potential, x, y, dirs, false);	
	}

	public static Point leftPoint(Page page, Element parent, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Left, false);

		Potential potential = Potentials.potential(potName);

		float y = potential.height();
		float x = 0f;

		return new Point(page, parent, potential, x, y, dirs, false);	
	}

	public static Point rightPoint(Page page, Element parent, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Right, false);

		Potential potential = Potentials.potential(potName);

		float x = parent.width();
		float y = potential.height();

		return new Point(page, parent, potential, x, y, dirs, false);	
	}

	private Point(Page page, Element parent, Potential potential, float x, float y, EnumMap<Direction, Boolean> dirs, boolean visibility) {
		this.x = parent.widthPos()  + x;
		this.y = parent.heightPos() + y;

		this.visibility = visibility;
		if (visibility) {
			this.name       = "Point";
			this.visibility = true;
			this.image      = getImage(name, page.getDocument());

			this.width  = image.getWidth()  * Measures.SCALE;
			this.height = image.getHeight() * Measures.SCALE;
		}

		this.potential  = potential;
		this.directions = dirs;
	}

	private Point(Terminal parent, Direction direction) {
		this.x = parent.widthPos();

		if (direction == Direction.Up)
			this.y = parent.heightPos();
		else
			this.y = parent.heightPos() + parent.height();

		this.visibility = false;

		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(direction, false);

		this.potential  = parent.potential();
		this.directions = dirs;
	}

	private Point(Page page, Point point, Direction direction) {
		this.potential  = Potentials.potential(point.potential().shortName());

		this.x = point.widthPos();
		this.y = this.potential.height();

		this.name       = "Point";
		this.visibility = true;
		this.image      = getImage(name, page.getDocument());

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		this.directions = new EnumMap<Direction,Boolean>(Direction.class);
		this.directions.put(direction, false);

		this.directions.put(Direction.Left, false);
		this.directions.put(Direction.Right, false);
	}

	private Point(Page page, Point point) {
		this.potential  = Potentials.potential(point.potential().fullName());

		this.x = point.widthPos();
		this.y = this.potential.height();

		this.name       = "Point";
		this.visibility = true;
		this.image      = getImage(name, page.getDocument());

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		this.directions = new EnumMap<Direction,Boolean>(Direction.class);

		this.directions.put(Direction.Down, false);
		this.directions.put(Direction.Left, false);
		this.directions.put(Direction.Right, false);
	}

	public Potential potential() {
		return this.potential;
	}

	public boolean has(Direction d) {
		return directions.containsKey(d);
	}

	public EnumMap<Direction, Boolean> directions() {
		return this.directions;
	}
	
    public boolean isVisibile() {
		return visibility;
	}

    public void unlinkVertical() {
    	for (Entry<Direction, Boolean> e : directions.entrySet())
    		if (e.getKey() == Direction.Up || e.getKey() == Direction.Down)
    			directions.put(e.getKey(), false);
    }

    public void unlinkHorizontal() {
    	for (Entry<Direction, Boolean> e : directions.entrySet())
    		if (e.getKey() == Direction.Left || e.getKey() == Direction.Right)
    			directions.put(e.getKey(), false);
    }
    
    public float heightPos() {
    	return y;
    }

    public float widthPos() {
    	return x;
    }

    public float height() {
    	return height;
    }

    public float width() {
    	return width;
    }

	public boolean equals(Object o) { 
    	if (o == this) return true; 

    	if (!(o instanceof Element)) return false;  

    	Point p = (Point) o; 

    	boolean potEqual = this.potential == p.potential();
    	boolean dirEqual = true;

    	for(Entry<Direction,Boolean> d : this.directions.entrySet())
    		if (!p.directions().entrySet().contains(d))
    			dirEqual = false;

    	return potEqual && dirEqual;  
	}

	public PdfFormXObject image() {
		return this.image;
	}

	public boolean existDisconnected(Direction d) {
		if (directions.containsKey(d))
			return !directions.get(d);
		else
			return false;
	}

	public void connect(Direction d) {
		if (!directions.containsKey(d))
			throw new RuntimeException("Direction " + d + " does not exist in point.");

		directions.put(d, true);
	}
}

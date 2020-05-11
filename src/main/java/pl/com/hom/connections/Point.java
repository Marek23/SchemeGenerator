package pl.com.hom.connections;

import java.util.EnumMap;
import java.util.Map.Entry;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Potentials;
import pl.com.hom.element.Element;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Resource.getImage;
public class Point {
	private float x;
	private float y;

	private Page page;

	private float width;
	private float height;

	private String name;

	private boolean        visibility;
	private PdfFormXObject image;

	private EnumMap<Direction, Boolean> directions;
	private Potential potential;

	private int target;

	@Override
	public String toString() {
		return "Point [ x=" + x + ", y=" + y + ", visibility=" + visibility + ", image=" + image
				+ ", directions=" + directions + ", potential=" + potential + "]";
	}

	public static Point up(Page page, Element parent, float x, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = x * Measures.SCALE;
		float scaledY = 0;

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);
	}

	public static Point down(Page page, Element parent, float x,  boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Down, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = x * Measures.SCALE;
		float scaledY = parent.height();

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);
	}

	public static Point up(Page page, Terminal parent, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);

		Potential potential = Potentials.potential(potName);

		return new Point(page, parent, potential, 0f, 0f, dirs, visibility);
	}

	public static Point down(Page page, Terminal parent, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Down, false);

		Potential potential = Potentials.potential(potName);

		float scaledY = parent.height();

		return new Point(page, parent, potential, 0f, scaledY, dirs, visibility);
	}

	public static Point pe(Page page, Element parent, float x, float y, boolean visibility) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);

		Potential potential = Potentials.potential("GROUNDPE");

		float scaledX = x * Measures.SCALE;
		float scaledY = y * Measures.SCALE;

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);
	}

	public static Point upDownLeft(Page page, Element parent, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Left, false);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Down, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = x * Measures.SCALE;
		float scaledY = y * Measures.SCALE;

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}

	public static Point upDownRight(Page page, Element parent, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Right, false);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Down, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = x * Measures.SCALE;
		float scaledY = y * Measures.SCALE;

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}

	public static Point upDownRight(Page page, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Right, false);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Down, false);

		Potential potential = Potentials.potential(potName);

		return new Point(page, potential, x, y, dirs, visibility);	
	}

	public static Point upLeft(Page page, Element parent, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Left, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = x * Measures.SCALE;
		float scaledY = y * Measures.SCALE;

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}

	public static Point upLeft(Page page, float x, float y, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Left, false);

		Potential potential = Potentials.potential(potName);

		return new Point(page, potential, x, y, dirs, false);	
	}

	public static Point upRight(Page page, float x, float y, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Right, false);

		Potential potential = Potentials.potential(potName);

		return new Point(page, potential, x, y, dirs, false);	
	}

	public static Point downLeft(Page page, float x, float y, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Down, false);
		dirs.put(Direction.Left, false);

		Potential potential = Potentials.potential(potName);

		return new Point(page, potential, x, y, dirs, false);	
	}

	public static Point downRight(Page page, float x, float y, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Down, false);
		dirs.put(Direction.Right, false);

		Potential potential = Potentials.potential(potName);

		return new Point(page, potential, x, y, dirs, false);	
	}

	public static Point downLeft(Page page, Element parent, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Down, false);
		dirs.put(Direction.Left, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = x * Measures.SCALE;
		float scaledY = y * Measures.SCALE;

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}

	public static Point downLeft(Page page, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Down, false);
		dirs.put(Direction.Left, false);

		Potential potential = Potentials.potential(potName);

		return new Point(page, potential, x, y, dirs, visibility);	
	}

	public static Point downRight(Page page, Element parent, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Down, false);
		dirs.put(Direction.Right, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = x * Measures.SCALE;
		float scaledY = y * Measures.SCALE;

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}

	public static Point upRight(Page page, Element parent, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Right, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = x * Measures.SCALE;
		float scaledY = y * Measures.SCALE;

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}

	public static Point left(Page page, Element parent, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Left, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = 0f;
		float scaledY = y * Measures.SCALE;

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}

	public static Point right(Page page, Element parent, float y, boolean visibility,String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Right, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = parent.width();
		float scaledY = y * Measures.SCALE;

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}

	public static Point page(Page page, float x, float y, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Left, false);
		dirs.put(Direction.Right, false);
		dirs.put(Direction.Down, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = x;
		float scaledY = y;

		Element parent = null;
		return new Point(page, parent, potential, scaledX, scaledY, dirs, false);	
	}

	public static Point supply(Page page, Point point, Direction direction) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(direction, false);
		dirs.put(Direction.Left, false);
		dirs.put(Direction.Right, false);

		Potential potential = point.potential();

		float scaledX = point.widthPos();
		float scaledY = potential.height();

		return new Point(page, potential, scaledX, scaledY, dirs, true);	
	}

	private Point(Page page, Element parent, Potential potential, float x, float y, EnumMap<Direction, Boolean> dirs, boolean visibility) {
		this.x = x;
		this.y = y;

		this.page = page;

		if (parent != null)
		{
			this.x += parent.widthPos();
			this.y += parent.heightPos();
		}

		this.visibility = visibility;
		if (this.visibility) {
			this.name       = "Point";
			this.visibility = true;
			this.image      = getImage(name, page);

			this.width  = image.getWidth()  * Measures.SCALE;
			this.height = image.getHeight() * Measures.SCALE;
		}

		this.potential  = potential;
		this.directions = dirs;

		page.add(this);
	}

	private Point(Page page, Potential potential, float x, float y, EnumMap<Direction, Boolean> dirs, boolean visibility) {
		this.x = x;
		this.y = y;

		this.page = page;

		this.visibility = visibility;
		if (this.visibility) {
			this.name       = "Point";
			this.visibility = true;
			this.image      = getImage(name, page);

			this.width  = image.getWidth()  * Measures.SCALE;
			this.height = image.getHeight() * Measures.SCALE;
		}

		this.potential  = potential;
		this.directions = dirs;

		page.add(this);
	}

	private Point(Page page, Terminal parent, Potential potential, float x, float y, EnumMap<Direction, Boolean> dirs, boolean visibility) {
		this.x = parent.widthPos() + x;
		this.y = parent.heightPos() + y;

		this.page = page;

		this.visibility = visibility;
		if (visibility) {
			this.name       = "Point";
			this.visibility = true;
			this.image      = getImage(name, page);

			this.width  = image.getWidth()  * Measures.SCALE;
			this.height = image.getHeight() * Measures.SCALE;
		}

		this.potential  = potential;
		this.directions = dirs;

		page.add(this);
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

    public float targetWidth() {
    	return x;
    }

    public float targetHeight() {
    	return y + 3f;
    }

    public float height() {
    	return height;
    }

    public float width() {
    	return width;
    }

    public Page page() {
    	return page;
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

	public void target(Point target) {
		this.target = target.page.nr();
	}

	public Integer target() {
		return this.target;
	}
}

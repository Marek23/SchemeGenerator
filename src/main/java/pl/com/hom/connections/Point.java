package pl.com.hom.connections;

import java.util.EnumMap;
import java.util.Map.Entry;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Potentials;
import pl.com.hom.elements.ColumnRow;

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

	public static Point upOrDownPotential(ColumnRow parent, String potName, Direction direction) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(direction, false);

		Potential potential = Potentials.potential(potName);

		float x = potential.width();
		float y;

		if (direction == Direction.Up)
			y = 0f;
		else
			y = parent.height();

		return new Point(parent, potential, x, y, dirs, false);
	}

	public static Point mainPoint(Point point) {
		return new Point(point);
	}

	public static Point upDownLeftBridge(ColumnRow parent, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Left, false);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Down, false);

		Potential potential = Potentials.potential(potName);

		float x = potential.width();
		float y = potential.height();

		return new Point(parent, potential, x, y, dirs, true);	
	}

	public static Point upDownRightBridge(ColumnRow parent, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Right, false);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Down, false);

		Potential potential = Potentials.potential(potName);

		float x = potential.width();
		float y = potential.height();

		return new Point(parent, potential, x, y, dirs, true);	
	}

	public static Point upLeftPoint(ColumnRow parent, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Left, false);

		Potential potential = Potentials.potential(potName);

		float x = potential.width();
		float y = potential.height();

		return new Point(parent, potential, x, y, dirs, false);	
	}

	public static Point upRightPoint(ColumnRow parent, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Right, false);

		Potential potential = Potentials.potential(potName);

		float x = potential.width();
		float y = potential.height();

		return new Point(parent, potential, x, y, dirs, false);	
	}

	public static Point leftPoint(ColumnRow parent, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Left, false);

		Potential potential = Potentials.potential(potName);

		float y = potential.height();
		float x = 0f;

		return new Point(parent, potential, x, y, dirs, false);	
	}

	public static Point rightPoint(ColumnRow parent, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Right, false);

		Potential potential = Potentials.potential(potName);

		float x = parent.width();
		float y = potential.height();

		return new Point(parent, potential, x, y, dirs, false);	
	}

	private Point(ColumnRow parent, Potential potential, float x, float y, EnumMap<Direction, Boolean> dirs, boolean visibility) {
		this.x = parent.widthPos()  + x;
		this.y = parent.heightPos() + y;

		this.visibility = visibility;
		if (visibility) {
			this.name       = "Point";
			this.visibility = true;
			this.image      = getImage(name);

			this.width  = image.getWidth()  * Measures.SCALE;
			this.height = image.getHeight() * Measures.SCALE;
		}

		this.potential  = potential;
		this.directions = dirs;		
	}

	private Point(Point point) {
		this.potential  = Potentials.potential(point.potential().name());

		this.x = point.widthPos();
		this.y = this.potential.height();

		this.name       = "Point";
		this.visibility = true;
		this.image      = getImage(name);

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		this.directions = new EnumMap<Direction,Boolean>(Direction.class);
		this.directions.put(Direction.Down, false);
	}

	public Potential potential() {
		return this.potential;
	}

	public boolean has(Direction d) {
		return directions.containsKey(d);
	}

	public boolean hasUnlinked(Direction d) {
		if (directions.containsKey(d))
			return !directions.get(d);

		return false; 
	}

	public EnumMap<Direction, Boolean> directions() {
		return this.directions;
	}

	public void link(Direction direction) {
		if (!this.directions.containsKey(direction))
			throw new RuntimeException("Direction " + direction + " does not exist in point.");

		this.directions.put(direction, true);
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

    	if (!(o instanceof ColumnRow)) return false;  

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
}

package pl.com.hom.connections;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map.Entry;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Potentials;
import pl.com.hom.configuration.Roles;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.elements.bridges.ToMKSBridge;
import pl.com.hom.elements.graphics.Coil;
import pl.com.hom.scheme.Column;

import static pl.com.hom.configuration.Resource.getImage;
public class Point {
	private ColumnRow parent;

	private float x;
	private float y;

	private float width;
	private float height;

	private String name;

	private boolean        visibility;
	private PdfFormXObject image;

	@Override
	public String toString() {
		return "Point [parent=" + parent + ", x=" + x + ", y=" + y + ", visibility=" + visibility + ", image=" + image
				+ ", directions=" + directions + ", potential=" + potential + "]";
	}

	private EnumMap<Direction, Boolean> directions;
	private Potential potential;

	public Point(ColumnRow parent, String potential, Direction direction, float width)
	{
		this.parent    = parent;
		this.potential = Potentials.getPotential(potential);

		this.visibility = false;
		this.image      = null;

		this.x = parent.getWidthPos() + width;

		if (direction == Direction.Up)
			this.y = parent.getHeightPos();

		if (direction == Direction.Down)
			this.y = parent.getHeightPos() + parent.getHeight();

		this.directions = new EnumMap<Direction, Boolean>(Direction.class);

		this.directions.put(direction, false);
	}

	public Point(ToMKSBridge parent, String potential, Direction direction, float width)
	{
		this.parent    = parent;
		this.potential = Potentials.getPotential(potential);

		this.x = parent.getWidthPos()  + width;
		this.y = parent.getHeightPos() + parent.getHeight();

		this.visibility = false;
		this.image      = null;

		this.directions = new EnumMap<Direction, Boolean>(Direction.class);

		this.directions.put(direction, false);
	}

	public Point(Coil parent, String potential, Direction direction, float width)
	{
		this.parent    = parent;
		this.potential = Potentials.getPotential(potential);

		this.x = parent.getWidthPos() + width;

		if (direction == Direction.Up)
			this.y = parent.getHeightPos();

		if (direction == Direction.Down)
			this.y = parent.getHeightPos() + parent.getHeight();

		this.visibility = false;
		this.image      = null;

		this.directions = new EnumMap<Direction, Boolean>(Direction.class);

		this.directions.put(direction, false);
	}

	public static Point newToJetBridge(ColumnRow parent, String potential, float x, float y) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);

		dirs.put(Direction.Left, false);

		return new Point(parent, potential, x, y, dirs, true);	
	}

	public static Point newUpRightPoint(ColumnRow parent, String potential, float x, float y) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);

		dirs.put(Direction.Up, false);
		dirs.put(Direction.Right, false);

		return new Point(parent, potential, x, y, dirs, false);	
	}

	private Point(ColumnRow parent, String potential, float x, float y, EnumMap<Direction, Boolean> dirs, boolean visibility) {
		this.x = parent.getWidthPos()  + x;
		this.y = parent.getHeightPos() + y;

		this.visibility = visibility;
		if (visibility) {
			this.name       = "Point";
			this.visibility = true;
			this.image      = getImage(name);

			this.width  = image.getWidth()  * Measures.SCALE;
			this.height = image.getHeight() * Measures.SCALE;
		}

		this.potential  = Potentials.getPotential(potential);
		this.directions = dirs;		
	}

	public Point(Column column, String potential, float width, float height) {
		
		this.x = width;
		this.y = height;

		this.visibility = true;
		this.image      = getImage("Point");

		this.directions = new EnumMap<Direction, Boolean>(Direction.class);
		this.potential  = Potentials.getPotential(potential);

		this.directions.put(Direction.Down, false);
	}

	public Potential getPotential() {
		return this.potential;
	}

	public EnumSet<Direction> getOnlyDirections() {
		EnumSet<Direction> out = EnumSet.noneOf(Direction.class);

		for (Entry<Direction,Boolean> e : directions.entrySet())
			out.add(e.getKey());

		return out;
	}

	public boolean hasDirection(Direction d) {
		return directions.containsKey(d);
	}

	public boolean hasUnlinkedDirection(Direction d) {
		if (directions.containsKey(d))
			return !directions.get(d);

		return false; 
	}

	public EnumMap<Direction, Boolean> getDirections() {
		return this.directions;
	}

	public void addDirection(Direction direction) {
		this.directions.put(direction, false);
	}

	public void setDirectionLinked(Direction direction) {
		if (!this.directions.containsKey(direction))
			throw new RuntimeException("Direction " + direction + " does not exist in point.");

		this.directions.put(direction, true);
	}

	public void stickDirection(Direction direction) {
		this.directions.put(direction, true);
	}

	public void addDirections(EnumSet<Direction> directions) {
		for(Direction d: directions)
			this.directions.put(d, false);
	}

	public void setVisible() {
		this.visibility = true;
	}

	
    public boolean isVisibile() {
		return visibility;
	}

    public void unlinkVerticalDirections() {
    	for (Entry<Direction, Boolean> e : directions.entrySet())
    		if (e.getKey() == Direction.Up || e.getKey() == Direction.Down)
    			directions.put(e.getKey(), false);
    }

    public void unlinkHorizontalDirections() {
    	for (Entry<Direction, Boolean> e : directions.entrySet())
    		if (e.getKey() == Direction.Left || e.getKey() == Direction.Right)
    			directions.put(e.getKey(), false);
    }
    
    public float getHeightPos() {
    	return y;
    }

    public float getWidthPos() {
    	return x;
    }

    public float getHeigh() {
    	return height;
    }

    public float getWidth() {
    	return width;
    }

	public boolean equals(Object o) { 
    	if (o == this) return true; 

    	if (!(o instanceof ColumnRow)) return false;  

    	Point p = (Point) o; 

    	boolean potEqual = this.potential == p.getPotential();
    	boolean dirEqual = true;

    	for(Entry<Direction,Boolean> d : this.directions.entrySet())
    		if (!p.getDirections().entrySet().contains(d))
    			dirEqual = false;

    	return potEqual && dirEqual;  
	}

	public PdfFormXObject image() {
		return this.image;
	}
}

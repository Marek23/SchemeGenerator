package pl.com.hom.connections;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map.Entry;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Resource;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.elements.bridges.AboveContactorBridge;
import pl.com.hom.elements.bridges.ToMKSBridge;
import pl.com.hom.elements.graphics.Coil;
import pl.com.hom.elements.graphics.Contactor;
import pl.com.hom.scheme.Column;

public class Point {
	private ColumnRow parent;

	private float x;
	private float y;

	private boolean        visibility;
	private PdfFormXObject image;

	@Override
	public String toString() {
		return "Point [parent=" + parent + ", x=" + x + ", y=" + y + ", visibility=" + visibility + ", image=" + image
				+ ", directions=" + directions + ", potential=" + potential + "]";
	}

	private EnumMap<Direction, Boolean> directions;
	private Potential potential;

	public Point(Contactor parent, Potential potential, Direction direction)
	{
		this.parent    = parent;
		this.potential = potential;

		this.x = parent.getWidthPos() + potential.getWidth();

		if (direction == Direction.Up)
			this.y = parent.getHeightPos();

		if (direction == Direction.Down)
			this.y = parent.getHeightPos() + parent.image().getHeight() * Measures.SCALE;

		this.visibility = false;
		this.image      = null;

		this.directions = new EnumMap<Direction, Boolean>(Direction.class);
		this.potential  = potential;

		this.directions.put(direction, false);
	}

	public Point(AboveContactorBridge parent, Potential potential, Direction direction)
	{
		this.parent    = parent;
		this.potential = potential;

		this.x = parent.getWidthPos() + parent.getWidth() + potential.getWidth();
		this.y = parent.getHeightPos() + parent.image().getHeight() * Measures.SCALE;

		this.visibility = false;
		this.image      = null;

		this.x = parent.getWidthPos()  + potential.getWidth();
		this.y = parent.getHeightPos() + parent.image().getHeight() * Measures.SCALE;

		this.directions = new EnumMap<Direction, Boolean>(Direction.class);
		this.potential  = potential;

		this.directions.put(direction, false);
	}

	public Point(ToMKSBridge parent, Potential potential, Direction direction)
	{
		this.parent    = parent;
		this.potential = potential;

		this.x = parent.getWidthPos()  + potential.getWidth();
		this.y = parent.getHeightPos() + parent.getHeight() * Measures.SCALE;

		this.visibility = false;
		this.image      = null;

		this.directions = new EnumMap<Direction, Boolean>(Direction.class);
		this.potential  = potential;

		this.directions.put(direction, false);
	}

	public Point(Coil parent, Potential potential, Direction direction)
	{
		this.parent    = parent;
		this.potential = potential;

		this.x = parent.getWidthPos()  + potential.getWidth();

		if (direction == Direction.Up)
			this.y = parent.getHeight();

		if (direction == Direction.Down)
			this.y = parent.getHeight() + parent.image().getHeight() * Measures.SCALE;

		this.visibility = false;
		this.image      = null;

		this.directions = new EnumMap<Direction, Boolean>(Direction.class);
		this.potential  = potential;

		this.directions.put(direction, false);
	}

	public Point(Potential potential, Direction direction)
	{
		this.visibility = false;
		this.image      = null;

		this.directions = new EnumMap<Direction, Boolean>(Direction.class);
		this.potential  = potential;

		this.directions.put(direction, false);
	}

	public Point(Potential potential, EnumMap<Direction, Boolean> directions)
	{
		this.visibility = true;
		this.image      = getImage("Point");

		this.directions = new EnumMap<Direction, Boolean>(Direction.class);
		this.potential  = potential;

		for (Entry<Direction, Boolean> e : directions.entrySet())
		    this.directions.put(e.getKey(), false);
	}

	public Point(Column column, Potential potential) {
		this.visibility = true;
		this.image      = getImage("Point");

		this.directions = new EnumMap<Direction, Boolean>(Direction.class);
		this.potential  = potential;

		this.directions.put(Direction.Down, false);

		this.x = column.getWidthPos()  + potential.getWidth();
		this.y = potential.getMainLineHeight();
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

    public void unlinkColumnDirections() {
    	for (Entry<Direction, Boolean> e : directions.entrySet())
    		if (e.getKey() == Direction.Up || e.getKey() == Direction.Down)
    			directions.put(e.getKey(), false);
    }

    public float getHeightPos() {
    	return y;
    }

    public float getWidthPos() {
    	return x;
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
}

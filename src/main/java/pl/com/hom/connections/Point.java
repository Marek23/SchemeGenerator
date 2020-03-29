package pl.com.hom.connections;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map.Entry;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.electric.Potential;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.utils.Measures;
import pl.com.hom.utils.Resource;

import static pl.com.hom.utils.Resource.getImage;

public class Point {

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", visibility=" + visibility + ", image=" + image + ", directions="
				+ directions + ", potential=" + potential + "]";
	}

	private float x;
	private float y;

	private boolean      visibility;
	private PdfFormXObject image;

	private EnumMap<Direction, Boolean> directions;
	private Potential potential;

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
		this.image      = getImage(Resource.Point);

		this.directions = new EnumMap<Direction, Boolean>(Direction.class);
		this.potential  = potential;

		for (Entry<Direction, Boolean> e : directions.entrySet())
		    this.directions.put(e.getKey(), false);
	}

	public static Point newSupplierPoint(Point p) {
		return new Point(p);
	}

	private Point(Point p) {
		this.visibility = true;
		this.image      = getImage(Resource.Point);

		this.directions = new EnumMap<Direction, Boolean>(Direction.class);
		this.potential  = p.getPotential();

		for (Entry<Direction, Boolean> e : p.directions.entrySet())
			if (e.getKey() == Direction.Up)
				this.directions.put(Direction.getPair(e.getKey()), e.getValue());
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

	public boolean hasDirections(Direction d) {
		return directions.containsKey(d);
	}

	public EnumMap<Direction, Boolean> getDirections() {
		return this.directions;
	}

	public void addDirection(Direction direction) {
		this.directions.put(direction, false);
	}

	public void setDirectionLinked(Direction direction) {
		if (!this.directions.containsKey(direction))
			throw new RuntimeException("Direction " + direction +" does not exist in point.");

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

	public void setHeight(float height) {
    	this.y = height; 
    }

    public void setWidth(int colIndex) {
    	this.x =
    		Measures.countCommonWidth(this.getPotential()) +
    		Measures.countColumnWidth(colIndex);
    }

    public void unlinkColumnDirections() {
    	for (Entry<Direction, Boolean> e : directions.entrySet())
    		if (e.getKey() == Direction.Up || e.getKey() == Direction.Down)
    			directions.put(e.getKey(), false);
    }

    public float getHeight() {
    	return y;
    }

    public float getWidth() {
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

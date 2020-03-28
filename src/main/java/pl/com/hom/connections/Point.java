package pl.com.hom.connections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import com.itextpdf.kernel.pdf.canvas.wmf.WmfImageData;

import pl.com.hom.electric.Potential;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.utils.Resource;

import static pl.com.hom.utils.Resource.getImage;

public class Point {
	private boolean      visibility;
	private WmfImageData image;

	private HashMap<Direction, Boolean> directions;
	private Potential potential;

	public Point(Potential potential, Direction direction)
	{
		this.visibility = false;
		this.image      = null;

		this.directions = new HashMap<Direction, Boolean>();
		this.potential  = potential;

		this.directions.put(direction, false);
	}

	public Point(Potential potential, HashMap<Direction, Boolean> directions)
	{
		this.visibility = true;
		this.image      = getImage(Resource.Point);
		this.potential  = potential;
		this.image      = null;

		for (Entry<Direction, Boolean> e : directions.entrySet()) {
		    this.directions.put(e.getKey(), false);
		}
	}

	public static Point newSupplierPoint(Point p) {
		return new Point(p);
	}

	private Point(Point p) {
		this.visibility = true;
		this.image      = getImage(Resource.Point);  
		this.potential  = p.getPotential();

		for (Entry<Direction, Boolean> e : p.directions.entrySet()) {
			if (e.getKey() == Direction.Up)
				this.directions.put(Direction.getPair(e.getKey()), e.getValue());
		}
	}

	public Potential getPotential() {
		return this.potential;
	}

	public HashSet<Direction> getOnlyDirections() {
		HashSet<Direction> out = new HashSet<Direction>();

		for (Entry<Direction,Boolean> e : directions.entrySet()) {
			out.add(e.getKey());
		}
		return out;
	}

	public HashMap<Direction, Boolean> getDirections() {
		return directions;
	}

	public void addDirection(Direction direction) {
		this.directions.put(direction, false);
	}

	public void setDirectionLinked(Direction direction) {
		if (!directions.entrySet().contains(direction))
			throw new RuntimeException("Direction not existing in point.");

		this.directions.put(direction, true);
	}

	public void stickDirection(Direction direction) {
		this.directions.put(direction, true);
	}

	public void addDirections(HashSet<Direction> directions) {
		for(Direction d: directions) {
			this.directions.put(d, false);
		}
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

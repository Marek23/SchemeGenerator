package pl.com.cs.schema;

import java.util.EnumMap;
import java.util.Map.Entry;

import pl.com.cs.schema.out.Terminal;
import pl.com.cs.schema.page.Page;
import pl.com.cs.util.Image;

import static pl.com.cs.config.Measures.scaled;
import static pl.com.cs.config.Images.getImage;

public class Point {
	private float x;
	private float y;

	private Page page;

	private float width;
	private float height;

	private String name;

	private boolean visibility;
	private Image   image;

	private EnumMap<Direction, Boolean> directions;
	private Potential potential;

	private int target;

	public static Point up(Page page, Drawable parent, float x, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = scaled(x);
		float scaledY = 0f;

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);
	}

	public static Point down(Page page, Drawable parent, float x,  boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Down, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = scaled(x);
		float scaledY = parent.height();

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);
	}

	public static Point pe(Page page, Drawable parent, float x, float y, boolean visibility) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);

		Potential potential = Potentials.potential("GROUNDPE");

		float scaledX = scaled(x);
		float scaledY = scaled(y);

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);
	}

	public static Point upDownLeft(Page page, Drawable parent, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Left, false);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Down, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = scaled(x);
		float scaledY = scaled(y);

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}

	public static Point upDownRight(Page page, Drawable parent, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Right, false);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Down, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = scaled(x);
		float scaledY = scaled(y);

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}

	public static Point upRight(Page page, Drawable parent, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Right, false);
		dirs.put(Direction.Up, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = scaled(x);
		float scaledY = scaled(y);

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}

	public static Point upLeft(Page page, Drawable parent, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Left, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = scaled(x);
		float scaledY = scaled(y);

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}

	public static Point downLeft(Page page, Drawable parent, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Down, false);
		dirs.put(Direction.Left, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = scaled(x);
		float scaledY = scaled(y);

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}

	public static Point downRight(Page page, Drawable parent, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Down, false);
		dirs.put(Direction.Right, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = scaled(x);
		float scaledY = scaled(y);

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}

	public static Point left(Page page, Drawable parent, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Left, false);
	
		Potential potential = Potentials.potential(potName);
	
		float scaledX = 0f;
		float scaledY = scaled(y);
	
		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}

	public static Point right(Page page, Drawable parent, float y, boolean visibility,String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Right, false);
		
		Potential potential = Potentials.potential(potName);
		
		float scaledX = parent.width();
		float scaledY = scaled(y);
		
		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);	
	}



	public static Point up(Page page, Drawable parent, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = 0f;
		float scaledY = parent.height();

		return new Point(page, parent, potential, scaledX, scaledY, dirs, visibility);
	}

	public static Point down(Page page, Drawable parent, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Down, false);

		Potential potential = Potentials.potential(potName);

		float scaledX = 0f;
		float scaledY = parent.height();

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

	public static Point upLeft(Page page, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Left, false);

		Potential potential = Potentials.potential(potName);

		return new Point(page, potential, x, y, dirs, visibility);	
	}

	public static Point upRight(Page page, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Right, false);

		Potential potential = Potentials.potential(potName);

		return new Point(page, potential, x, y, dirs, visibility);	
	}

	public static Point downRight(Page page, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Down, false);
		dirs.put(Direction.Right, false);

		Potential potential = Potentials.potential(potName);

		return new Point(page, potential, x, y, dirs, visibility);	
	}

	public static Point downLeft(Page page, float x, float y, boolean visibility, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Down, false);
		dirs.put(Direction.Left, false);

		Potential potential = Potentials.potential(potName);

		return new Point(page, potential, x, y, dirs, visibility);	
	}

	public static Point left(Page page, float x, float y, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Left, false);

		Potential potential = Potentials.potential(potName);

		return new Point(page, potential, x, y, dirs, false);	
	}

	public static Point leftRight(Page page, float x, float y, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Left, false);
		dirs.put(Direction.Right, false);

		Potential potential = Potentials.potential(potName);

		return new Point(page, potential, x, y, dirs, false);	
	}



	public static Point page(Page page, float x, float y, String potName) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Left, false);
		dirs.put(Direction.Right, false);
		dirs.put(Direction.Down, false);

		Potential potential = Potentials.potential(potName);

		return new Point(page, potential, x, y, dirs, false);	
	}

	public static Point supply(Page page, Point point, Direction direction) {
		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(direction, false);
		dirs.put(Direction.Left, false);
		dirs.put(Direction.Right, false);

		Potential potential = point.potential();

		return new Point(page, potential, point.widthPos(), potential.height(), dirs, true);	
	}



	private Point(Page page, Drawable parent, Potential potential, float x, float y, EnumMap<Direction, Boolean> dirs, boolean visibility) {
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

			this.width  = image.getWidth();
			this.height = image.getHeight();
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

			this.width  = image.getWidth();
			this.height = image.getHeight();
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

			this.width  = image.getWidth();
			this.height = image.getHeight();
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

    	if (!(o instanceof Drawable)) return false;  

    	Point p = (Point) o; 

    	boolean potEqual = this.potential == p.potential();
    	boolean dirEqual = true;

    	for(Entry<Direction,Boolean> d : this.directions.entrySet())
    		if (!p.directions().entrySet().contains(d))
    			dirEqual = false;

    	return potEqual && dirEqual;  
	}

	public Image image() {
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

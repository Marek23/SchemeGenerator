package pl.com.hom.scheme;

import pl.com.hom.connections.Point;
import pl.com.hom.connections.Potential;

public abstract class Line {
	private Potential potential;

	private Point startPoint;
	private Point endPoint;

	Line (Point begin, Point end) {
		this.potential = begin.getPotential();

		this.startPoint = begin;
		this.endPoint   = end;
	}

	public Potential getPotential() {
		return this.potential;
	}

	public float getBeginHeight() {
		return startPoint.getHeightPos();
	}

	public float getBeginWidth() {
		return startPoint.getWidthPos();
	}

	public float getEndHeight() {
		return endPoint.getHeightPos();
	}

	public float getEndWidth() {
		return endPoint.getWidthPos();
	}

    public boolean equals(Object o) { 
    	if (o == this) return true; 

    	if (!(o instanceof Line)) return false;  

    	Line l = (Line) o;

    	boolean equalPot = this.potential == l.potential;

    	boolean equalBeginEnd = (
    		(
	    		this.startPoint.equals(l.startPoint) &&
	    		this.endPoint.equals(l.endPoint)
    		) ||
    		(
    	    	this.startPoint.equals(l.endPoint) &&
    	    	this.endPoint.equals(l.startPoint)
    		)
    	);

    	return equalPot && equalBeginEnd;  
	}
}

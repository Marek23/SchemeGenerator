package pl.com.hom.connections;

import java.util.HashSet;

import com.itextpdf.kernel.pdf.canvas.wmf.WmfImageData;

import pl.com.hom.electric.Potential;
import pl.com.hom.utils.Resource;

import static pl.com.hom.utils.Measures.getColumnRow;
import static pl.com.hom.utils.Resource.getImage;

public class Point {
	private int x;
	private int y;

	private boolean      visibility;
	private WmfImageData image;

	private HashSet<Direction> directions;
	private Potential potential;

	public Point(Potential potential, Direction direction)
	{
		this.visibility = false;
		this.image      = null;

		this.directions = new HashSet<Direction>();
		this.directions.add(direction);

		if (direction == Direction.Up)
		{
			this.x = 100; // TODO graphics size
		} else if (direction == Direction.Down)
		{
			this.x = 0; // TODO graphics root
		}
		this.y = getColumnRow(potential);


		this.potential  = potential;
	}

	public Point(Potential potential, HashSet<Direction> directions)
	{
		this.visibility = true;
		this.image      = getImage(Resource.Point); 
		this.y          = getColumnRow(potential);
		this.directions = directions; 
		this.potential  = potential;
		this.image      = null;
	}
}

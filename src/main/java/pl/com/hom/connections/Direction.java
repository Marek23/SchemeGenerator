package pl.com.hom.connections;

public enum Direction {
	Up,
	Down,
	Left,
	Right;

	public static Direction getPair(Direction d) {
		if (d == Up)
			return Down;
		else if (d == Down)
			return Up;
		else if (d == Left)
			return Right;
		else 
			return Left;
	}
}

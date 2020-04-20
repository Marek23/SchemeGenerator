package pl.com.hom.data;

public class SapOut extends Signal{
	public SapOut(Board parent, String function){
		super(function);

		parent.add(this);
	}
}

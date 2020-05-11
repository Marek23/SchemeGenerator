package pl.com.hom.data;

public class SapIn extends Signal{
	public SapIn(Board parent, String function){
		super(function);

		parent.addSapInput(this);
	}
}

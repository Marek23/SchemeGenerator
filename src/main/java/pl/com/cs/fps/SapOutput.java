package pl.com.cs.fps;

public class SapOutput extends Signal{
	public SapOutput(Fps parent, String function){
		super(parent, function);

		this.symbol = String.valueOf(parent.nextValueOf("SAPOUT"));

		parent.addSapOutput(this);
	}
}

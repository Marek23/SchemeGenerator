package pl.com.cs.fps;

public class SapOutput extends Signal{
	public SapOutput(Fps fps, String function){
		super(fps, function);

		this.symbol = fps.symbol("SAPOUT");

		fps.addSapOutput(this);
	}
}

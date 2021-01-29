package pl.com.cs.fps;

public class SapInput extends Signal {
	public SapInput(Fps fps, String function){
		super(fps, function);

		this.symbol = fps.symbol("SAPIN");

		fps.addSapInput(this);
	}
}

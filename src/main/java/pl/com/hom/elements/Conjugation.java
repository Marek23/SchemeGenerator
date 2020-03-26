package pl.com.hom.elements;

import java.util.ArrayList;
import java.util.List;

import pl.com.hom.electric.Potential;

public class Conjugation extends ElectricElement {
	List<Terminal> terminals;


	public List<Potential> getPotentials() {
		List<Potential> out = new ArrayList<Potential>();

		for (Terminal t : terminals)
		{
			if(!out.contains(t.getPotental()))
				out.add(t.getPotental());
		}

		return out;
	}
}

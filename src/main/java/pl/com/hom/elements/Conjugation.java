package pl.com.hom.elements;

import java.util.ArrayList;
import java.util.List;

import pl.com.hom.connections.Potential;
import pl.com.hom.elements.graphics.Terminal;

public class Conjugation extends ColumnRow {
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

package pl.com.hom.data;

import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellType;

public class Reader {
	private static HSSFWorkbook workbook;

	public static ArrayList<Jet> jets;

	public static ArrayList<String> titles;

	public static HashMap<String, Integer> indexes;

	public static void init(String name) throws IOException
	{
        File file = new File("src\\main\\resources\\data\\" + name);

        if (file.isFile() && file.exists())
            System.out.println(name + " open.");

        FileInputStream fip = new FileInputStream(file);

        workbook = new HSSFWorkbook(fip); 
        indexes  = new HashMap<String, Integer>();

        jets = new ArrayList<Jet>();

        titles = new ArrayList<String>(Arrays.asList(
        	"Nazwa",
        	"Zasilanie / sposób rozruchu",
        	"Napięcie [V]",
        	"Prąd I bieg [A]",
        	"Prąd II bieg [A]",
        	"Prąd startu [A]",
        	"Moc I bieg [kW]",
        	"Moc II bieg [kW]",
        	"Zabezpieczenie",
        	"Przekrój"
        ));

		for (Row r: workbook.getSheetAt(0))
			for(Cell c: r)
				if (c.getCellType() == CellType.STRING)
				{
					String value = c.getStringCellValue();
					if (titles.contains(value))
						indexes.put(value, c.getColumnIndex());
				}

		for (String t: titles)
			if(!indexes.containsKey(t))
				throw new RuntimeException("Brakująca kolumna " + t + " w bilansie mocy.");
	}

	public static void addEngines() {
		Iterator<Sheet> i = workbook.sheetIterator();
		while(i.hasNext())
		{
			Sheet s = i.next();
			for (Row r: s)
			{
				Cell fcell = r.getCell(r.getFirstCellNum());
				if (fcell != null)
				{
					boolean isNumber = fcell.getCellType() == CellType.NUMERIC;
					boolean isString = fcell.getCellType() == CellType.STRING;

					int lp = -1;
					try {
						if (isString)
							lp = Integer.valueOf(fcell.getStringCellValue());
					}catch (NumberFormatException ignore){}

					if(isNumber || lp > -1)
					{
						Cell nameCell = r.getCell(at("Nazwa"));
						if (nameCell != null && nameCell.getCellType() == CellType.STRING && !nameCell.getStringCellValue().equals("Sterowanie"))
						{
							String name   = nameCell.toString();
							Cell current1 = r.getCell(at("Prąd I bieg [A]"));
							Cell current2 = r.getCell(at("Prąd II bieg [A]"));
							Cell power1   = r.getCell(at("Moc I bieg [kW]"));
							Cell power2   = r.getCell(at("Moc II bieg [kW]"));
							Cell cable    = r.getCell(at("Przekrój"));
							System.out.println(name);
							if (current1.getNumericCellValue() > 0d && power1.getNumericCellValue() > 0d)
								jets.add(new Jet(
									name,
									String.valueOf(current1.getNumericCellValue()),
									String.valueOf(current2.getNumericCellValue()),
									String.valueOf(power1.getNumericCellValue()),
									String.valueOf(power2.getNumericCellValue()),
									cable.getStringCellValue(),
									s.getSheetName()
								));
						}
					}
					else {
					}
				}
			}
		}
	}

	private static int at(String name) {
		return indexes.get(name);
	}
//	TESTS
	public static void showJets() {
		for(Jet j: jets)
			System.out.println(j.name());
	}
}

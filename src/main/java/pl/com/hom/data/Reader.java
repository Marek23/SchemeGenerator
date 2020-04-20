package pl.com.hom.data;

import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellType;

public class Reader {
	private static HSSFWorkbook workbookB;
	private static HSSFWorkbook workbookM;
	private static HSSFWorkbook workbookS;

	public static HashMap<String, Integer> colB;
	public static HashMap<String, Integer> colM;
	public static HashMap<String, Integer> colS;

	public static HashMap<String, ArrayList<String>> steerings;

	public static HashMap<String, Board> boards;

	public static ArrayList<Receiver> receivers;
	public static ArrayList<String>   scenarios;

	public static void init() throws IOException
	{
		String balance = "BilansMocy.xls";
		String matrix  = "MatrycaSterowan.xls";
		String signals = "PunktyDanychDoInstalacjiSSP.xls";

        File fileB = new File("src\\main\\resources\\data\\" + balance);
        File fileM = new File("src\\main\\resources\\data\\" + matrix);
        File fileS = new File("src\\main\\resources\\data\\" + signals);

        if (fileB.isFile() && fileB.exists())
            System.out.println(balance + " open.");

        if (fileM.isFile() && fileM.exists())
            System.out.println(matrix + " open.");

        if (fileS.isFile() && fileM.exists())
            System.out.println(signals + " open.");

        FileInputStream fipB = new FileInputStream(fileB);
        FileInputStream fipM = new FileInputStream(fileM);
        FileInputStream fipS = new FileInputStream(fileS);

        workbookB = new HSSFWorkbook(fipB);
        workbookM = new HSSFWorkbook(fipM);
        workbookS = new HSSFWorkbook(fipS);

        colB  = new HashMap<String, Integer>();
        colM  = new HashMap<String, Integer>();
        colS  = new HashMap<String, Integer>();

        boards = new HashMap<String, Board>();

		Iterator<Sheet> i = workbookB.sheetIterator();
		while(i.hasNext()) {
			String name = i.next().getSheetName();

			boards.put(name, new Board(name));
		}

        ArrayList<String> balanceCols = new ArrayList<String>(Arrays.asList(
        	"Odbiornik",
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

        ArrayList<String> matrixCols = new ArrayList<String>(Arrays.asList(
        	"Odbiornik",
            "Detekcja",
            "Pożar"
        ));

        ArrayList<String> signalCols = new ArrayList<String>(Arrays.asList(
            "TYP",
            "Rozdzielnica",
            "Funkcja"
        ));

        scenarios = new ArrayList<String>();

		for (Row r: workbookB.getSheetAt(0))
			for(Cell c: r)
				if (c.getCellType() == CellType.STRING)
				{
					String value = c.getStringCellValue();
					if (balanceCols.contains(value))
						colB.put(value, c.getColumnIndex());
				}

		for (Row r: workbookM.getSheetAt(0))
			for(Cell c: r)
				if (c.getCellType() == CellType.STRING)
				{
					String value = c.getStringCellValue();
					for (String col: matrixCols)
						if(value.startsWith(col))
							if (!colM.containsKey(value)) {
								colM.put(value, c.getColumnIndex());
								scenarios.add(value);
							}
				}

		for (Row r: workbookS.getSheetAt(0))
			for(Cell c: r)
				if (c.getCellType() == CellType.STRING)
				{
					String value = c.getStringCellValue();
					if (signalCols.contains(value))
						colS.put(value, c.getColumnIndex());
				}

		for (String c: balanceCols)
			if(!colB.containsKey(c))
				throw new RuntimeException(balance + ": brakująca kolumna " + c);

		for (String c: signalCols)
			if(!colS.containsKey(c))
				throw new RuntimeException(signals + ": brakująca kolumna " + c);

		for (String c: colM.keySet())
			System.out.println("Matrix key: " + c);
	}

	public static void read() {
		readBalance();
		readMatrix();
		readSignals();
	}
	private static void readBalance() {
		Iterator<Sheet> i = workbookB.sheetIterator();
		while(i.hasNext())
		{
			Sheet s = i.next();

			String boardName = s.getSheetName();

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
					}catch (NumberFormatException canHapp){}

					if(isNumber || lp > -1)
					{
						Cell nameCell = r.getCell(atIn(colB, "Odbiornik"));
						if (nameCell != null && nameCell.getCellType() == CellType.STRING && !nameCell.getStringCellValue().equals("Sterowanie"))
						{
							String name    = nameCell.toString();
							Cell current1  = r.getCell(atIn(colB, "Prąd I bieg [A]"));
							Cell current2  = r.getCell(atIn(colB, "Prąd II bieg [A]"));
							Cell power1    = r.getCell(atIn(colB, "Moc I bieg [kW]"));
							Cell power2    = r.getCell(atIn(colB, "Moc II bieg [kW]"));
							Cell cable     = r.getCell(atIn(colB, "Przekrój"));
							Cell runMethod = r.getCell(atIn(colB, "Zasilanie / sposób rozruchu"));

							if (current1.getNumericCellValue() > 0d && power1.getNumericCellValue() > 0d)
								new Jet(
									boards.get(boardName),
									name,
									String.valueOf(current1.getNumericCellValue()),
									String.valueOf(current2.getNumericCellValue()),
									String.valueOf(power1.getNumericCellValue()),
									String.valueOf(power2.getNumericCellValue()),
									cable.getStringCellValue(),
									s.getSheetName()
								);
							else 
								new DolEngine(
									boards.get(boardName),
									name,
									String.valueOf(current2.getNumericCellValue()),
									String.valueOf(power2.getNumericCellValue()),
									cable.getStringCellValue(),
									s.getSheetName(),
									runMethod.getStringCellValue()
								);
							if (name.startsWith("TO")){}
							if (name.startsWith("DET")){}
								
						}
					}
				}
			}
		}
	}

	private static void readMatrix() {
		for (Row r: workbookM.getSheetAt(0))
		{
			Cell cell = r.getCell(atIn(colM, "Odbiornik"));
			if (cell != null)
			{
				boolean isString = cell.getCellType() == CellType.STRING;

				if (isString) {
					Cell lastScenario = r.getCell(atIn(colM, scenarios.get(scenarios.size() - 1)));

					if (lastScenario != null && lastScenario.getCellType() == CellType.STRING && !lastScenario.getStringCellValue().isEmpty()) {
						System.out.println(cell.getStringCellValue().replaceAll("\\.","").trim());
					}
				}
			}
		}
	}

	private static void readSignals() {
		for (Row r: workbookS.getSheetAt(0))
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
				}catch (NumberFormatException canHapp){}

				if (isNumber || lp > -1)
				{
					Cell typeCell = r.getCell(atIn(colS, "TYP"));
					if (typeCell != null && typeCell.getCellType() == CellType.STRING && typeCell.getStringCellValue().startsWith("D"))
					{
						Cell board    = r.getCell(atIn(colS, "Rozdzielnica"));
						Cell function = r.getCell(atIn(colS, "Funkcja"));

						String typeName  = typeCell.getStringCellValue();
						String boardName = board.getStringCellValue();
						String functName = function.getStringCellValue();

						if (!boards.containsKey(boardName))
							throw new RuntimeException("Tablica " + boardName + "nie istnieje w bilansie mocy.");

//						LOGIC SHIFTED FOR BOARD
						if (typeName.startsWith("DI"))
							new SapOut(boards.get(boardName), functName);
						else if (typeName.startsWith("DO"))
							new SapIn(boards.get(boardName), functName);
						else
							throw new RuntimeException("Nieznany TYP " + typeName + " w liście sygnałów SSP.");
					}
				}
			}
		}
	}

	public static void show() {
		for (Entry<String, Board> e: boards.entrySet())
			e.getValue().show();
	}

	private static int atIn(HashMap<String, Integer> col, String name) {
		return col.get(name);
	}
}

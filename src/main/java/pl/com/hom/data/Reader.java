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

import pl.com.hom.configuration.Potentials;
import pl.com.hom.connections.Potential;

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

	public static HashMap<String, ArrayList<Receiver>> steerings;

	public static ArrayList<Board> boards;

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

        FileInputStream fipB = new FileInputStream(fileB);
        FileInputStream fipM = new FileInputStream(fileM);
        FileInputStream fipS = new FileInputStream(fileS);

        workbookB = new HSSFWorkbook(fipB);
        workbookM = new HSSFWorkbook(fipM);
        workbookS = new HSSFWorkbook(fipS);

        colB  = new HashMap<String, Integer>();
        colM  = new HashMap<String, Integer>();
        colS  = new HashMap<String, Integer>();

        boards = new ArrayList<Board>();

		Iterator<Sheet> i = workbookB.sheetIterator();
		while(i.hasNext()) {
			String name = i.next().getSheetName();

			boards.add(new Board(name));
		}

        ArrayList<String> balanceCols = new ArrayList<String>(Arrays.asList(
        	"ODBIORNIK",
        	"ZASILANIE / SPOSÓB ROZRUCHU",
        	"NAPIĘCIE [V]",
        	"PRĄD I BIEG [A]",
        	"PRĄD II BIEG [A]",
        	"PRĄD STARTU [A]",
        	"MOC I BIEG [KW]",
        	"MOC II BIEG [KW]",
        	"ZABEZPIECZENIE",
        	"PRZEKRÓJ"
        ));

        ArrayList<String> matrixMainCols = new ArrayList<String>(Arrays.asList(
       		"ODBIORNIK"
        ));

        ArrayList<String> matrixCols = new ArrayList<String>(Arrays.asList(
           	"BRAK ALARMÓW",
           	"PRZEWIETRZANIE",        	
           	"DETEKCJA",
           	"POŻAR"
        ));

        ArrayList<String> signalCols = new ArrayList<String>(Arrays.asList(
            "TYP",
            "ROZDZIELNICA",
            "FUNKCJA"
        ));

        scenarios = new ArrayList<String>();
        receivers = new ArrayList<Receiver>();
        steerings = new HashMap<String, ArrayList<Receiver>>();

		for (Row r: workbookB.getSheetAt(0))
			for(Cell c: r)
				if (c.getCellType() == CellType.STRING)
				{
					String value = c.getStringCellValue().toUpperCase();
					if (balanceCols.contains(value))
						colB.put(value, c.getColumnIndex());
				}

		for (Row r: workbookM.getSheetAt(0))
			for(Cell c: r)
				if (c.getCellType() == CellType.STRING)
				{
					String value = c.getStringCellValue().toUpperCase();
					for (String col: matrixCols)
						if(value.startsWith(col))
							if (!colM.containsKey(value)) {
								colM.put(value, c.getColumnIndex());
								scenarios.add(value);
							}

					for (String col: matrixMainCols)
						if(value.startsWith(col))
							if (!colM.containsKey(value))
								colM.put(value, c.getColumnIndex());
				}

		for (Row r: workbookS.getSheetAt(0))
			for(Cell c: r)
				if (c.getCellType() == CellType.STRING)
				{
					String value = c.getStringCellValue().toUpperCase();
					if (signalCols.contains(value))
						colS.put(value, c.getColumnIndex());
				}

		for (String c: balanceCols)
			if(!colB.containsKey(c))
				throw new RuntimeException(balance + ": brakująca kolumna " + c);

		for (String c: signalCols)
			if(!colS.containsKey(c))
				throw new RuntimeException(signals + ": brakująca kolumna " + c);

//		for (String c: colM.keySet())
//			System.out.println("Matrix key: " + c);
	}

	public static void read() {
		readBalance();
		readMatrix();
		readSignals();
	}

	public static void generate() {
		for (Board b: boards) {
			b.draw();
		}
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
						Cell nameCell = r.getCell(atIn(colB, "ODBIORNIK"));
						if (nameCell != null && nameCell.getCellType() == CellType.STRING && !nameCell.getStringCellValue().equals("Sterowanie"))
						{
							String name    = nameCell.getStringCellValue();
							Cell current1  = r.getCell(atIn(colB, "PRĄD I BIEG [A]"));
							Cell current2  = r.getCell(atIn(colB, "PRĄD II BIEG [A]"));
							Cell power1    = r.getCell(atIn(colB, "MOC I BIEG [KW]"));
							Cell power2    = r.getCell(atIn(colB, "MOC II BIEG [KW]"));
							Cell cable     = r.getCell(atIn(colB, "PRZEKRÓJ"));

							String runMethod = r.getCell(atIn(colB, "ZASILANIE / SPOSÓB ROZRUCHU")).getStringCellValue();

							if (current1.getNumericCellValue() > 0d && power1.getNumericCellValue() > 0d) {
								receivers.add(new Jet(
									board(boardName),
									name,
									String.valueOf(current1.getNumericCellValue()),
									String.valueOf(current2.getNumericCellValue()),
									String.valueOf(power1.getNumericCellValue()),
									String.valueOf(power2.getNumericCellValue()),
									cable.getStringCellValue(),
									s.getSheetName()
								));
							}
							else if (current2.getNumericCellValue() > 0d && power2.getNumericCellValue() > 0d) {
								if (runMethod.equalsIgnoreCase("Rozruch bezpośredni")) {
									receivers.add(new DolEngine(
										board(boardName),
										name,
										String.valueOf(current2.getNumericCellValue()),
										String.valueOf(power2.getNumericCellValue()),
										cable.getStringCellValue(),
										s.getSheetName()
									));
								}
							}
						}
					}
				}
			}
		}
	}

	private static void readMatrix() {
		for (Row r: workbookM.getSheetAt(0))
		{
			Cell receiver = r.getCell(atIn(colM, "ODBIORNIK"));
			if (receiver != null)
			{
				boolean isString = receiver.getCellType() == CellType.STRING;

				if (isString) {
					String recName = receiver.getStringCellValue();
					Receiver rec   = receiver(recName);

					if (rec != null) {
						String key1B = "1B" + receiver(recName).board().name();
						String key2B = "2B" + receiver(recName).board().name();

						for (String s: scenarios) {
							Cell scenario = r.getCell(atIn(colM, s));

							boolean valid = scenario != null && scenario.getCellType() == CellType.STRING && !scenario.getStringCellValue().isEmpty();

							if (!valid) throw new RuntimeException("Błąd w wierszu: " + r.getRowNum() + " i kolumnie " + scenario.getColumnIndex() + " w matrycy sterowań.");

							String scenName = scenario.getStringCellValue();

							if (scenName.toUpperCase().trim().startsWith("1B")) key1B += s;
							if (scenName.toUpperCase().trim().startsWith("2B")) key2B += s;
						}
						addSteering(rec, key1B);
						addSteering(rec, key2B);
					}
				}
			}
		}

		for (Entry<String, ArrayList<Receiver>> e: steerings.entrySet()) {
			if (e.getKey().startsWith("1B"))
				for (Receiver r: e.getValue()) {
					Potentials.add(new Potential(e.getKey(), 100f, 1800f));
					r.ster1(e.getKey());
				}
			if (e.getKey().startsWith("2B"))
				for (Receiver r: e.getValue()) {
					Potentials.add(new Potential(e.getKey(), 100f, 1900f));
					r.ster2(e.getKey());
				}
		}

//		TESTS
		for (Entry<String, ArrayList<Receiver>> e: steerings.entrySet()) {
			System.out.println("Steering key: " + e.getKey());
			for (Receiver r: e.getValue())
				System.out.println(r);
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
						Cell board    = r.getCell(atIn(colS, "ROZDZIELNICA"));
						Cell function = r.getCell(atIn(colS, "FUNKCJA"));

						String typeName  = typeCell.getStringCellValue();
						String boardName = board.getStringCellValue();
						String functName = function.getStringCellValue();

						if (board(boardName) == null)
							throw new RuntimeException("Tablica " + boardName + " nie istnieje w bilansie mocy.");

//						LOGIC SHIFTED FOR BOARD
						if (typeName.startsWith("DI"))
							new SapOut(board(boardName), functName);
						else if (typeName.startsWith("DO"))
							new SapIn(board(boardName), functName);
						else
							throw new RuntimeException("Nieznany TYP " + typeName + " w liście sygnałów SSP.");
					}
				}
			}
		}
	}

	public static void show() {
//		for (Entry<String, Board> e: boards.entrySet())
//			e.getValue().show();
	}

	private static int atIn(HashMap<String, Integer> col, String name) {
		return col.get(name);
	}

	private static Receiver receiver(String name) {
		for (Receiver r: receivers)
			if (r.name.equalsIgnoreCase(name))
				return r;

		return null;
	}

	private static Board board(String name) {
		for (Board b: boards)
			if (b.name().equalsIgnoreCase(name))
				return b;

		return null;
	}

	private static void addSteering(Receiver receiver, String key) {
		if (steerings.containsKey(key))
			steerings.get(key).add(receiver);
		else
			steerings.put(key, new ArrayList<Receiver>(Arrays.asList(receiver)));
	}
}

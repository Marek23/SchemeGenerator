package pl.com.cs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import pl.com.cs.fps.Fps;
import pl.com.cs.fps.SapOutput;
import pl.com.cs.fps.SapInput;
import pl.com.cs.fps.MotorDol;
import pl.com.cs.fps.MotorJet;
import pl.com.cs.fps.Executable;
import pl.com.cs.fps.MotorSoftstart;
import pl.com.cs.fps.MotorStar;
import pl.com.cs.fps.MotorTwoGear;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellType;

public final class Reader {
	private static HSSFWorkbook workbookB;
	private static HSSFWorkbook workbookM;
	private static HSSFWorkbook workbookS;

	public static HashMap<String, Integer> colB;
	public static HashMap<String, Integer> colM;
	public static HashMap<String, Integer> colS;

	public static HashMap<String, Fps> fpss;

	public static ArrayList<Executable>  executables;
	public static ArrayList<String> scenarios;

	public static void readFiles(String balance, String matrix, String signals) throws IOException
	{
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

        fpss = new HashMap<String, Fps>();

		Iterator<Sheet> i = workbookB.sheetIterator();
		while(i.hasNext()) {
			String name = i.next().getSheetName();

			fpss.put(name, new Fps(name));
		}

        ArrayList<String> balanceCols = new ArrayList<String>(Arrays.asList(
        	"ODBIORNIK",
        	"FUNKCJA",
        	"ZASILANIE / SPOSÓB ROZRUCHU",
        	"NAPIĘCIE [V]",
        	"PRĄD I BIEG [A]",
        	"PRĄD II BIEG [A]",
        	"PRĄD STARTU [A]",
        	"MOC I BIEG [KW]",
        	"MOC II BIEG [KW]",
			"ZABEZPIECZENIE",
			"ZABEZPIECZENIE I BIEG",
        	"PRZEKRÓJ"
        ));

        ArrayList<String> matrixMainCols = new ArrayList<String>(Arrays.asList(
       		"ODBIORNIK",
       		"STEROWANIE"
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

        executables = new ArrayList<Executable>();
        scenarios   = new ArrayList<String>();

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
	}

	public static void read() {
		readBalance();
		readMatrix();
		readSignals();
	}

	public static void generate() {
		for (Fps f: fpss.values()) {
			System.out.println("drawing " + f.name());
			f.draw();
		}
	}

	private static void readBalance() {
		Iterator<Sheet> i = workbookB.sheetIterator();
		while(i.hasNext())
		{
			Sheet s = i.next();

			String fpsName = s.getSheetName();
			Fps fps        = fpss.get(fpsName);

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
						Cell fireCell = r.getCell(atIn(colB, "FUNKCJA"));
						if (nameCell != null && nameCell.getCellType() == CellType.STRING && !nameCell.getStringCellValue().equals("Sterowanie"))
						{

							String name     = nameCell.getStringCellValue();
							Cell current1  = r.getCell(atIn(colB, "PRĄD I BIEG [A]"));
							Cell current2  = r.getCell(atIn(colB, "PRĄD II BIEG [A]"));
							Cell power1    = r.getCell(atIn(colB, "MOC I BIEG [KW]"));
							Cell power2    = r.getCell(atIn(colB, "MOC II BIEG [KW]"));
							Cell fuse1     = r.getCell(atIn(colB, "ZABEZPIECZENIE I BIEG"));
							Cell fuse2     = r.getCell(atIn(colB, "ZABEZPIECZENIE"));
							Cell cable     = r.getCell(atIn(colB, "PRZEKRÓJ"));

							String fireMode  = fireCell.getStringCellValue();
							String runMethod = r.getCell(atIn(colB, "ZASILANIE / SPOSÓB ROZRUCHU")).getStringCellValue();

							Executable e = null;
							if (current1.getNumericCellValue() > 0d && power1.getNumericCellValue() > 0d) {
								if (power2.getNumericCellValue() > 2)
									e = new MotorTwoGear(
										fps,
										name,
										String.valueOf(current1.getNumericCellValue()),
										String.valueOf(current2.getNumericCellValue()),
										String.valueOf(power1.getNumericCellValue()),
										String.valueOf(power2.getNumericCellValue()),
										String.valueOf(fuse1.getStringCellValue()),
										String.valueOf(fuse2.getStringCellValue()),
										cable.getStringCellValue(),
										s.getSheetName()
									);
								else
									e = new MotorJet(
											fps,
											name,
											String.valueOf(current1.getNumericCellValue()),
											String.valueOf(current2.getNumericCellValue()),
											String.valueOf(power1.getNumericCellValue()),
											String.valueOf(power2.getNumericCellValue()),
											String.valueOf(fuse1.getStringCellValue()),
											String.valueOf(fuse2.getStringCellValue()),
											cable.getStringCellValue(),
											s.getSheetName()
										);
							}
							else if (current2.getNumericCellValue() > 0d && power2.getNumericCellValue() > 0d) {
								if (runMethod.equalsIgnoreCase("Rozruch bezpośredni")) {
									e = new MotorDol(
										fps,
										name,
										String.valueOf(current2.getNumericCellValue()),
										String.valueOf(power2.getNumericCellValue()),
										String.valueOf(fuse2.getStringCellValue()),
										cable.getStringCellValue(),
										s.getSheetName()
									);
								}

								if (runMethod.equalsIgnoreCase("Rozruch softstart")) {
									e = new MotorSoftstart(
										fps,
										name,
										String.valueOf(current2.getNumericCellValue()),
										String.valueOf(power2.getNumericCellValue()),
										String.valueOf(fuse2.getStringCellValue()),
										cable.getStringCellValue(),
										s.getSheetName()
									);
								}

								if (runMethod.equalsIgnoreCase("Rozruch gwiazda/trójkąt")) {
									e = new MotorStar(
										fps,
										name,
										String.valueOf(current2.getNumericCellValue()),
										String.valueOf(power2.getNumericCellValue()),
										String.valueOf(fuse2.getStringCellValue()),
										cable.getStringCellValue(),
										s.getSheetName()
									);
								}
							}
							if (e != null) {
								e.fireMode(fireMode.toUpperCase().contains("POŻAROWA"));
								executables.add(e);
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
			Cell executable = r.getCell(atIn(colM, "ODBIORNIK"));
			Cell steering   = r.getCell(atIn(colM, "STEROWANIE"));

			if (executable != null)
			{
				var isString         = executable.getCellType() == CellType.STRING;
				var steeringIsString = steering.getCellType() == CellType.STRING;
				var isSeparated      = false;

				if (isString) {
					if (steeringIsString) {
						String steeringType = steering.getStringCellValue();
						if (steeringType != null && steeringType.equalsIgnoreCase("S")) {
							isSeparated = true;
						}
					}

					var eName = executable.getStringCellValue();
					var e = executable(eName);

					if (e != null) {
						Fps fps = e.fps();

						String key1B   = "1B";
						String key2B   = "2B";
						String keyMain = "Z";

						int scenariosCounter  = 0;
						int directionsCounter = 0;

						boolean hasDirection  = false;  
						String keyLeft  = "LEW";
						String keyRight = "PRA";

//						begin validation
						for (String s: scenarios) {
							Cell scenario = r.getCell(atIn(colM, s));

							boolean valid = scenario != null && scenario.getCellType() == CellType.STRING && !scenario.getStringCellValue().isEmpty();

							if (!valid) throw new RuntimeException("Błąd dla urządzenia: " + eName + " w scenariuszu " + s + " w matrycy sterowań.");

							String scenName = scenario.getStringCellValue();

							if (scenName.toUpperCase().contains("L") || scenName.toUpperCase().contains("P")) {
								scenariosCounter++;
								directionsCounter++;
							}
							else
								if (!scenName.toUpperCase().trim().startsWith("WY"))
									scenariosCounter++;
							
						}
						if (directionsCounter > 0) {
							hasDirection = true;

							if (directionsCounter < scenariosCounter)
								for (String s: scenarios) {
									Cell scenario = r.getCell(atIn(colM, s));
	
									boolean valid = scenario != null && scenario.getCellType() == CellType.STRING && !scenario.getStringCellValue().isEmpty();
	
									if (!valid) throw new RuntimeException("Błąd dla urządzenia: " + eName + " w scenariuszu " + s + " w matrycy sterowań.");
	
									String scenName = scenario.getStringCellValue();
	
									if (!scenName.toUpperCase().trim().startsWith("WY") && (!scenName.toUpperCase().contains("L") && !scenName.toUpperCase().contains("P")))
										throw new RuntimeException("Dla urządzenia: " + eName + " brak definicji kierunku w scenariuszu " + s + " matrycy sterowań.");
								}
							
						}
//						end validation

						for (String s: scenarios) {
							Cell scenario = r.getCell(atIn(colM, s));

							String scenName = scenario.getStringCellValue();

							if (scenName.toUpperCase().trim().startsWith("1B")) key1B   += s;
							if (scenName.toUpperCase().trim().startsWith("2B")) key2B   += s;
							if (scenName.toUpperCase().trim().startsWith("Z"))  keyMain += s;

							if (scenName.toUpperCase().contains("LEW")) keyLeft  += s;
							if (scenName.toUpperCase().contains("PRA")) keyRight += s;
						}

						if (isSeparated) {
							key1B    += e.name();
							key2B    += e.name();
							keyMain  += e.name();
							keyLeft  += e.name();
							keyRight += e.name();
						}

						if (e.runMethod().equals("TWOGEAR")) {
							fps.addSteering1B(e, key1B);
							fps.addSteering2B(e, key2B);
						}
						else {
							fps.addMainSteering(e, keyMain);
						}
						if (hasDirection) {
							fps.addSteeringL(e, keyLeft);
							fps.addSteeringR(e, keyRight);
						}
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
						Cell fps      = r.getCell(atIn(colS, "ROZDZIELNICA"));
						Cell function = r.getCell(atIn(colS, "FUNKCJA"));

						String typeName  = typeCell.getStringCellValue();
						String fpsName   = fps.getStringCellValue();
						String functName = function.getStringCellValue();

						if (fpss.get(fpsName) == null)
							throw new RuntimeException("Tablica " + fpsName + " nie istnieje w bilansie mocy.");

//						LOGIC SHIFTED FOR BOARD
						if (typeName.startsWith("DI"))
							new SapOutput(fpss.get(fpsName), functName);
						else if (typeName.startsWith("DO"))
							new SapInput(fpss.get(fpsName), functName);
						else
							throw new RuntimeException("Nieznany TYP " + typeName + " w liście sygnałów SSP.");
					}
				}
			}
		}
	}

	private static int atIn(HashMap<String, Integer> col, String name) {
		return col.get(name);
	}

	private static Executable executable(String name) {
		for (Executable r: executables)
			if (r.name().equalsIgnoreCase(name))
				return r;

		return null;
	}
}

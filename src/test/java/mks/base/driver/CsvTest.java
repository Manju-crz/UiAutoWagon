package mks.base.driver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import mks.driver.base.DriverBase;
import mks.java.util.csv.CsvFile;
import mks.java.util.csv.CsvFile.CsvType;
import mks.uiautowagon.interactor.WagonerFacade;
import mks.java.util.csv.CsvReader;
import mks.java.util.csv.CsvWriter;

public class CsvTest extends DriverBase {

	String fs = File.separator;
	String testDataFolder = System.getProperty("user.dir") + fs + "TestData" + fs;
	String csvDataFile = testDataFolder + "UiAutoWagon - Sheet10.csv";
	String csvDataResultFile = testDataFolder + "Results" + fs + "Result_" + System.currentTimeMillis() + ".csv";

	String errorMsg = null;
	String filteredRowDataError = null;

	@Test
	public void csvDataTest() throws Exception {

		CsvFile csv = new CsvFile(CsvType.EXCEL_PREFERENCE, csvDataFile);
		CsvReader csvReader = new CsvReader(csv);
		int totalRows = csvReader.getTotalRecords();

		System.out.println("Resulting file : " + csvDataResultFile);
		CsvFile resultCsv = new CsvFile(csvDataResultFile).createFileForce();
		CsvWriter writer = new CsvWriter(resultCsv);
		writer.writeHeaders("URL", "Result", "ErrorMessage");
		writer.closeFileWriter();
		
		WagonerFacade wagoner = null;
		for (int i = 1; i <= totalRows; i++) {
			List<String> rowData = csvReader.getRowDataAt(i);
			List<String> filteredRowData = validateRowData(rowData);

			String URL = filteredRowData.get(0);
			if (filteredRowDataError != null) {
				writer = new CsvWriter(resultCsv);
				writer.writeRow(URL, "INVALID_ROW_DATA", filteredRowDataError);
				writer.closeFileWriter();
				continue;
			}
			driver.get(URL);
			wagoner = new WagonerFacade(driver);
			
			
			boolean rowPassed = true;
			
			for (int j = 1; j < filteredRowData.size(); j++) {
				String cellValue = filteredRowData.get(j);
				if (!cellValue.contains("=")) {
					writer = new CsvWriter(resultCsv);
					writer.writeRow(URL, "INVALID_CELL_VALUE", String.format(
							"INVALID CELL VALUE AT THE ROW POSITION %s AND THE CELL POSITION %s, and the cell value is %s",
							i, j, cellValue));
					rowPassed = false;
					writer.closeFileWriter();
					break;
				} else {

					System.out.println("working with cellValue : " + cellValue);
					if (cellValue.contains("COMMA")) {
						cellValue = cellValue.replace("COMMA", ",");
					}
					CsvTestPerformer performer = new CsvTestPerformer(wagoner, cellValue.split("=")[0].trim(),
							cellValue.split("=")[1].trim());
					performer.performAction();
					if (performer.foundException) {
						errorMsg = performer.exceptionMessage;
						writer = new CsvWriter(resultCsv);
						writer.writeRow(URL, "FAILED", errorMsg);
						writer.closeFileWriter();
						rowPassed = false;
						break;
					}
					
					if(performer.isButton() || performer.isLink())
						wagoner.reload();
					
				}
			}
			
			if(rowPassed) {
				writer = new CsvWriter(resultCsv);
				writer.writeRow(URL, "PASSED");
				writer.closeFileWriter();
			}
			
			
		}
	}

	private List<String> validateRowData(List<String> rowData) {
		List<String> filteredRowData = new ArrayList<String>();
		boolean havingRowData = false;
		if (!rowData.get(0).startsWith("http")) {
			filteredRowDataError = "URL seems to be invalid";
		} else {
			filteredRowData.add(rowData.get(0));
			for (int i = 1; i < rowData.size(); i++) {
				String row = cleanCellValue(rowData.get(i));
				if (row != null) {
					filteredRowData.add(row);
					havingRowData = true;
				}
			}
		}
		if (!havingRowData) {
			filteredRowDataError = "THERE IS NO DATA AT ALL IN THE COMPLETE ROW..";
		}
		return filteredRowData;
	}

	private String cleanCellValue(String cellValue) {

		if (cellValue != null) {
			cellValue = cellValue.trim();

			if (cellValue.startsWith("\n")) {
				cellValue = cellValue.substring(1);
			}
			if (cellValue.endsWith("\n")) {
				cellValue = cellValue.substring(0, cellValue.length() - 2);
			}
			if (cellValue.length() > 0) {
				while (cellValue.startsWith("\t")) {
					cellValue = cellValue.substring(1);
				}
				while (cellValue.endsWith("\t")) {
					cellValue = cellValue.substring(0, cellValue.length() - 2);
				}
			}
			if (cellValue.length() < 1) {
				cellValue = null;
			}
		}
		return cellValue;
	}

}

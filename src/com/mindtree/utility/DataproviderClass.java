package com.mindtree.utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataproviderClass {

	private static File file;
	private static FileInputStream fs;
	private static XSSFWorkbook wb;
	private static XSSFSheet sh;

	public static Object[][] getDataFromExcel(String filepath, String fbsheetName) throws Exception {
		System.out.println("Excel path is : " + filepath);
		System.out.println("The sheet name is: " + fbsheetName);
		file = new File(filepath);
		fs = new FileInputStream(file);
		wb = new XSSFWorkbook(fs);
		sh = wb.getSheet(fbsheetName);
		int rowCount = sh.getLastRowNum();
		int column = sh.getRow(0).getLastCellNum();

		System.out.println("Number of rows are : " + rowCount);
		System.out.println("Number of columns are : " + column);

		Object[][] data = new Object[rowCount][column];
		for (int i = 1; i <= rowCount; i++) {
			XSSFRow row = sh.getRow(i);
			for (int j = 0; j < column; j++) {
				if (row == null) {
					data[i - 1][j] = "";
				} else {
					XSSFCell cell = row.getCell(j);
					if (cell == null) {
						data[i - 1][j] = "";
					} else {
						DataFormatter formatter = new DataFormatter();
						String val = formatter.formatCellValue(cell);
						data[i - 1][j] = val;
					}
				}
			}
		}
		return data;
	}
}
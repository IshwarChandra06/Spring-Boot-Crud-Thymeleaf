package com.example.demo.Excel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.model.Employee;

public class ImportExcel 
{
	public static  List<Employee> parseExcelFile(InputStream is) {
		try {
		Workbook workbook = new XSSFWorkbook(is);

		Sheet sheet = (Sheet) workbook.getSheet("1");
		Iterator<Row> rows = sheet.iterator();

		List<Employee> listemployees = new ArrayList<Employee>();

		int rowNumber = 0;
		while (rows.hasNext()) {
		Row currentRow = rows.next();

		// skip header
		if (rowNumber == 0) {
		rowNumber++;
		continue;
		}

		Iterator<Cell> cellsInRow = currentRow.iterator();

		Employee emp = new Employee();

		int cellIndex = 0;
		while (cellsInRow.hasNext()) {
		Cell currentCell = cellsInRow.next();

		if (cellIndex == 0) {
		emp.setId((long) currentCell.getNumericCellValue());
		} else if (cellIndex == 1) {
		emp.setFirstName(currentCell.getStringCellValue());
		} else if (cellIndex == 2) {
		emp.setLastName(currentCell.getStringCellValue());
		} else if (cellIndex == 3) {
		emp.setEmail(currentCell.getStringCellValue());
		}

		cellIndex++;
		}

		listemployees.add(emp);
		}

		// Close WorkBook
		workbook.close();

		return listemployees;
		} catch (IOException e)
		{
		throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
		
		}

}


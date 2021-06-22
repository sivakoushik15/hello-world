package com.AutoRABITFrameWork;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.KWFW_BaseClass.OperationClass.BrowserLogin;
import com.KWFW_BaseClass.OperationClass.IsNull;
import com.KWFW_BaseClass.OperationClass.OperationsBaseClass;

public class AutoMainClass extends OperationsBaseClass {

	public static void main(String[] args) throws Throwable {
		PropertyAutoRABIT ProClass = new PropertyAutoRABIT();
		ProClass.FileLoad();
		Properties allProperty = ProClass.FileLoad();
		File file = new File(
				"C:\\Users\\Autorabit\\eclipse-workspace\\AutometionProject\\src\\com\\AutoRABITFrameWork\\AutoRabitProject.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int totalRowsInsheet1 = sheet.getLastRowNum() - sheet.getFirstRowNum();
		System.out.println("totalRowsInsheet1 : " + totalRowsInsheet1);
		for (int i = 1; i <= totalRowsInsheet1; i++) {
			XSSFRow row = sheet.getRow(i);
			String TestCase = row.getCell(0).toString();
			System.out.println("TestCase " + TestCase);
			String testcaseStatus = row.getCell(1).toString();
			if (testcaseStatus.equalsIgnoreCase("Y")) {
				System.out.println("The Testcase status is YES for " + TestCase);
				XSSFSheet sheet2 = workbook.getSheetAt(2);
				int rowsinsheet2 = sheet2.getLastRowNum() - sheet.getFirstRowNum();
				System.out.println("Rows in Sheet2 : " + rowsinsheet2);
				for (int j = 1; j <= rowsinsheet2; j++) {
					XSSFRow row2 = sheet2.getRow(j);
					String testcaseinSheet2 = row2.getCell(0).toString();
					System.out.println("testcaseinSheet2 " + testcaseinSheet2);
					if (testcaseinSheet2.equals(TestCase)) {
						System.out.println("testcase in seet2 is 'equals' in the test case in sheeet1");
						String statusInsheet2 = row2.getCell(1).toString();
						if (statusInsheet2.equalsIgnoreCase("Y")) {
							String Browser = row2.getCell(2).toString();
							String URl = row2.getCell(3).toString();
							String username = row2.getCell(4).toString();
							String password = row2.getCell(5).toString();
							System.out.println("TestCase : " + TestCase + " ; testcaseStatus : " + testcaseStatus
									+ " ; Browser : " + Browser + " ; URl : " + URl + " ; username : " + username
									+ " ; password : " + password);
							BrowserLogin.login(Browser, URl, username, password);
							XSSFSheet sheet1 = workbook.getSheetAt(1);
							int noOfRowsInSheet1 = sheet1.getLastRowNum() - sheet1.getFirstRowNum();
							for (int k = 1; k <= noOfRowsInSheet1; k++) {
								XSSFRow row1 = sheet1.getRow(k);
								String quit = "Quit";
								if (IsNull.IsNull(row1.getCell(1))) {
									System.out.println("Null is there and goes to next cell");
									String key = row1.getCell(2).toString();
									String Attribute = row1.getCell(3).toString();
									String Path = row1.getCell(4).toString();
									if (IsNull.IsNull(row1.getCell(5))) {
										String Values = null;
										String Values1 = null;
										System.out.println(
												"Keyword : " + key + " ; Attribute : " + Attribute + " ; Path : " + Path
														+ " ; Values : " + Values + " ; Values1 : " + Values1);
										OperationsBaseClass.KeyWordOpe(allProperty, key, Attribute, Path, Values,
												Values1);
									} else {
										String Values = row1.getCell(5).toString();
										String Values1 = null;
										System.out.println(
												"Keyword : " + key + " ; Attribute : " + Attribute + " ; Path : " + Path
														+ " ; Values : " + Values + " ; Values1 : " + Values1);
										OperationsBaseClass.KeyWordOpe(allProperty, key, Attribute, Path, Values,
												Values1);
									}

								} else {
									String valueinCell = row1.getCell(1).toString();
									System.out.println("String is there and goes to another loop");
									if (valueinCell.equals(quit)) {
										System.out.println("String is QUIT");
										// driver.quit();
									} else {
										System.out.println("New Test Case");
									}
								}
							}
						}
					} else {
						System.out.println("testcase in seet2 is 'not-equals' in the test case in sheeet1");
					}
				}
			} else {
				System.out.println("The Testcase-status is NO for " + TestCase);
			}
		}
	}
}

package excelData;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class dataDriven {
    // Identify testcases column by scanning the entire first row
    // Once column is identified, then scan entire testcase column to identify specific row.
    // after grab specific testcase row, pull all the data of that row and feed into.


    public ArrayList<String> getData(String testcase) throws IOException {

        //fileInputStream argument
        FileInputStream fis = new FileInputStream("/Users/yoodahun/Documents/Github/Java/Selenium WebDriver with Java/HandlingExcelData-using-POI/testing_excelData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        ArrayList<String> arraylist = new ArrayList<String>();

        int sheetsCount = workbook.getNumberOfSheets();

        for (int i = 0; i<sheetsCount; i++) {
            if(workbook.getSheetName(i).equals("testData")) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                Iterator<Row> rows = sheet.iterator(); //sheet is collection of rows
                Row firstRow = rows.next(); //access first row?

                Iterator<Cell> cells = firstRow.cellIterator(); //row is collection of cells
                int column=0;
                while(cells.hasNext()) {
                    Cell value = cells.next();
                    if(value.getStringCellValue().equals("TestCases")) {
                        System.out.println("TestCases");
                        System.out.println(value.getColumnIndex());
                        column = value.getColumnIndex();
                    }
                    System.out.println(value.getColumnIndex());
                }

                while(rows.hasNext()) {//read column.
                    Row row = rows.next();
                    if(row.getCell(column).getStringCellValue().equals(testcase)) {
                        Iterator<Cell> dataCell = row.cellIterator();
                        while(dataCell.hasNext()) {
                            Cell value = dataCell.next();
                            if(value.getCellType() == CellType.STRING) {
                                arraylist.add(value.getStringCellValue());
                            } else {
                                arraylist.add(NumberToTextConverter.toText(value.getNumericCellValue()));
                            }
                        }
                    }
                }

            }

        }

        return arraylist;

    }
}

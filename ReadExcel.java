package Week5.Day2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public String[][] readdata(String fileName) throws IOException {
	
		XSSFWorkbook wb= new XSSFWorkbook("./Data/"+fileName+".xlsx");
		XSSFSheet sheet=wb.getSheet("Sheet1");
		int rowcount =sheet.getLastRowNum();
		short columncount = sheet.getRow(0).getLastCellNum();
		String[][] data=new String[rowcount][columncount];
		for(int i=1;i<=rowcount;i++) {
			for(int j=0;j<columncount;j++) {
				String values = sheet.getRow(i).getCell(j).getStringCellValue();
				data[i-1][j]=values;
				
			}
		}
		return data;
		
	}
}

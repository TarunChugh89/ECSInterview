package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.aventstack.extentreports.ExtentTest;

public class ExcelReadOperation {
	
	private String filepath="";
	private Sheet sheetName;
	ExtentTest test;
	
	
	public ExcelReadOperation(String filepath, String sheetName, ExtentTest test)
	{
		this.test=test;
		this.filepath=filepath;
		File testFile= new File(filepath);
		try {
			Workbook wb= WorkbookFactory.create(testFile);
			this.sheetName=wb.getSheet(sheetName);
			if(sheetName==null)
			{
				throw new Exception("Sheet with sheetName"+sheetName + " not found.");
			}
		} catch (Exception e) {
			HandleException.handleException("ExcelRead", filepath, e, true, null, test);
		}
	}
	
	public Object[][] getExcelData()
	{
		if (sheetName == null) {
            return new Object[0][0];
        }
		
		int rowCount=sheetName.getPhysicalNumberOfRows();
		int columnCount=sheetName.getRow(0).getLastCellNum();
		Object[][] data= new Object[rowCount][columnCount];
		
		for(int i=1;i<=rowCount;i++)
		{
			for(int j=0;j<columnCount;j++)
			{
				if(sheetName.getRow(i).getCell(j)!=null)
				{
					data[i-1][j]=sheetName.getRow(i).getCell(j).toString();
				}
				else
				{
					data[i-1][j]="";
				}
			}
		}
		
		return data;
		
	}
	
	
	

}

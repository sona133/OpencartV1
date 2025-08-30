package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	@DataProvider(name = "LoginData")
	public String [][] getData() throws IOException 
	
	{
		String path = ".\\testData\\LoginData.xlsx"; //taking xml file from testdata folder
		ExcelUtility xutils = new ExcelUtility(path);
		int totalrows = xutils.getRowCount("Sheet1"); //getting row count from sheet1
		int totalcols = xutils.getCellCount("Sheet1", 1); //getting column count from sheet1
		
		String loginData[][] = new String[totalrows][totalcols]; //creating a 2D array to store data
		
		for (int i = 1; i <= totalrows; i++) //looping through rows starting from 1
		{ 
			
			for (int j = 0; j < totalcols; j++) //looping through columns
			{ 
				loginData[i - 1][j] = xutils.getCellData("Sheet1", i, j); //storing data in the array
			}
		}
		return loginData; //returning the 2D array with login data
	}

}

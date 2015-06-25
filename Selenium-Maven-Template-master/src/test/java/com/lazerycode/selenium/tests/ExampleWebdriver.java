package com.lazerycode.selenium.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.lazerycode.selenium.DriverFactory;

public class ExampleWebdriver  extends DriverFactory {

	@Test(enabled = false)
    public void testExample() throws Exception {
        WebDriver driver = getDriver();

        driver.get("http://www.google.com");
        
            //the below statement will throw an exception as the element is not found, Catch block will get executed and takes the screenshot.
		  driver.findElement(By.id("testing")).sendKeys("test");
             
               //if we remove the below comment, it will not return exception and screen shot method will not get executed.
		  driver.findElement(By.id("gbqfq")).sendKeys("test");
    }
    
    @Test(enabled = false)
    public void excelExample() throws Exception {
    	try {
	        // Open the Excel file
	        FileInputStream fis = new FileInputStream("c:\\working\\datasources\\testdata.xls");
	        // Access the required test data sheet
	        HSSFWorkbook wb = new HSSFWorkbook(fis);
	        HSSFSheet sheet = wb.getSheet("testdata");
	        // Loop through all rows in the sheet
	        // Start at row 1 as row 0 is our header row
	        for(int count = 1;count<=sheet.getLastRowNum();count++){
	            HSSFRow row = sheet.getRow(count);
	            System.out.println("Running test case " + row.getCell(0).toString());
	            // Run the test for the current test data row
	            runTest(row.getCell(1).toString(),row.getCell(2).toString());
	        }
	        fis.close();
	    } catch (IOException e) {
	        System.out.println("Test data file not found");
	    }
    }
    
    @Test(enabled = true)
    public void excelStepExample() throws Exception  {
    	String action = "";
		String value = "";
		String attribute = "";
		String attrval = "";
			// Open the Excel file for reading
			FileInputStream fis = new FileInputStream(
					"C:\\working\\datatest\\test.xls");
			// Open it for writing too
			FileOutputStream fos = new FileOutputStream(
					"C:\\working\\datatest\\test.xlstestResult.xls");
			// Access the required test data sheet
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheet("steps");
			// Loop through all rows in the sheet
			// Start at row 1 as row 0 is our header row
			for (int count = 1; count <= sheet.getLastRowNum(); count++) {
				HSSFRow row = sheet.getRow(count);
				System.out.println("Running test step "
						+ row.getCell(0).toString());

				// Run the test step for the current test data row
				if (!(row.getCell(1) == null || row.getCell(1).equals(
						Cell.CELL_TYPE_BLANK))) {
					action = row.getCell(1).toString();
				} else {
					action = "";
				}

				if (!(row.getCell(2) == null || row.getCell(2).equals(
						Cell.CELL_TYPE_BLANK))) {
					value = row.getCell(2).toString();
				} else {
					value = "";
				}

				if (!(row.getCell(3) == null || row.getCell(3).equals(
						Cell.CELL_TYPE_BLANK))) {
					attribute = row.getCell(3).toString();
				} else {
					attribute = "";
				}

				if (!(row.getCell(4) == null || row.getCell(4).equals(
						Cell.CELL_TYPE_BLANK))) {
					attrval = row.getCell(4).toString();
				} else {
					attrval = "";
				}

				System.out.println("Test action: " + action);
				System.out.println("Parameter value: " + value);
				System.out.println("Attribute: " + attribute);
				System.out.println("Attribute value: " + attrval);

				String result = runTestStep(action, value, attribute, attrval);

				// Write the result back to the Excel sheet
				row.createCell(5).setCellValue(result);

			}

			// Save the Excel sheet and close the file streams
			wb.write(fos);
			fis.close();
			fos.close();
    }
    
    public static String runTestStep(String action, String value,
			String attribute, String attrval) throws Exception {

    	WebDriver driver = getDriver();
	

		switch (action.toLowerCase()) {
		case "openbrowser":
			switch (value.toLowerCase()) {
			case "firefox":
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				return "OK";
			}
		case "navigate":
			driver.get(value);
			return "OK";
		case "type":
			try {
				WebElement element = findMyElement(attribute, attrval);
				element.sendKeys(value);
				return "OK";
			} catch (Exception e) {
				System.out.println(e.toString());
				return "NOK";
			}
		case "click":
			try {
				WebElement element = findMyElement(attribute, attrval);
				element.click();
				return "OK";
			} catch (Exception e) {
				System.out.println(e.toString());
				return "NOK";
			}
		case "validate":
			try {
				WebElement element = findMyElement(attribute, attrval);
				if (element.getText().equals(value)) {
					
					return "OK";
				} else {
					System.out.println("Actual value: " + element.getText()
							+ ", expected value: " + value);
					return "NOK";
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		case "closebrowser":
			driver.quit();
			return "OK";
		default:
			throw new Exception("Unknown keyword " + action);
		}
	}
    
    public static WebElement findMyElement(String attribute, String attrval)
			throws Exception {
    	WebDriver driver = getDriver();
		switch (attribute.toLowerCase()) {
		case "id":
			return driver.findElement(By.id(attrval));
		case "name":
			return driver.findElement(By.name(attrval));
		case "xpath":
			return driver.findElement(By.xpath(attrval));
		case "css-select":
			return driver.findElement(By.cssSelector(attrval));
		default:
			throw new Exception("Unknown selector type " + attribute);
		}
	}
    
    public void runTest(String strSearchString, String strPageTitle) {
    	WebDriver driver = getDriver();
    	driver.get("http://www.google.com");
    	
        // Enter the search string and send it
        WebElement element = driver.findElement(By.name("q"));
        element.clear();
        element.sendKeys(strSearchString);
        element.submit();
         
        // Check the title of the page
        if (driver.getTitle().equals(strPageTitle)) {
            System.out.println("Page title is " + strPageTitle + ", as expected");
        } else {
            System.out.println("Expected page title was " + strPageTitle + ", but was " + driver.getTitle() + " instead");
        }
		
	}
   

	public String gettimestamp() throws Exception
	  {
		  Calendar calendar = Calendar.getInstance();
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		  String currentTime = dateFormat.format(calendar.getTime());
		  //long startTime = System.currentTimeMillis();
		  //System.out.println("현재 시간 : " + dateFormat.format(calendar.getTime()));
		  //System.out.println(startTime);
		  return currentTime;

	  }
    
    public void Takescreenshot(WebDriver driver) throws Exception {
    	String time= gettimestamp();
    	   
        String screenshotDirectory = System.getProperty("screenshotDirectory");
        String screenshotAbsolutePath = screenshotDirectory + File.separator + System.currentTimeMillis() + "_"+ ".png";
        File screenshot = new File(screenshotAbsolutePath);       
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // now save the screenshto to a file some place
        FileUtils.copyFile(scrFile, new File(".\\target\\screenshots\\"+ time + "screenshot.png"));
	}
}

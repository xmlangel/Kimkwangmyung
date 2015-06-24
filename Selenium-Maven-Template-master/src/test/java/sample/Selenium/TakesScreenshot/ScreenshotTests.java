package sample.Selenium.TakesScreenshot;

import java.io.File;

public class ScreenshotTests {
	
	WebDriver driver;
	
	@Before
	public void setUp()
	{
		driver = new FirefoxDriver();
		driver.get("http://www.google.com");
	}
  
	@Test
	public void testTakesScreenshot()
	{
		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("c:\\tmp\\main_page.png"));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
  
	@Test
	public void testElementScreenshot(){
	    
		WebElement pmoabsdiv = driver.findElement(By.className("pmoabs"));
	    
	    try {
	    	FileUtils.copyFile(WebElementExtender.captureElementBitmap(pmoabsdiv), new File("c:\\tmp\\div.png"));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	  }
	
	@After
	public void teadDown()
	{
		driver.close();
		driver.quit();
	}
  
}
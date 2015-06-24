package sample.Selenium.Perfirmance;

public class TimerDemo {
	
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception { 
		driver = new FirefoxDriver();
	}

	@Test
	public void testBMICalculator_Perf() throws Exception {

		// Get the Start Time
		long startTime = System.currentTimeMillis();
		
		// Open the BMI Calculator Mobile Application
		driver.get("http://dl.dropbox.com/u/55228056/bmicalculator.html");
		
		// Wait for the Calculate Button
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("Calculate")));
		
		// Get the End Time
		long endTime = System.currentTimeMillis();
		
		// Measure total time
		long totalTime = endTime - startTime;
	    System.out.println("Total Page Load Time: " + totalTime + " milliseconds");
	    		
		WebElement height = driver.findElement(By.name("heightCMS"));
		height.sendKeys("181");
		
		WebElement weight = driver.findElement(By.name("weightKg"));
		weight.sendKeys("80");

		WebElement calculateButton = driver.findElement(By.id("Calculate"));
		calculateButton.click();
		
		try {
			
			WebElement bmi = driver.findElement(By.name("bmi"));
			assertEquals("24.4", bmi.getAttribute("value"));
			
			WebElement bmi_category = driver.findElement(By.name("bmi_category"));
			assertEquals("Normal",bmi_category.getAttribute("value"));
			
		} catch (Error e) {
			
			//Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
		}
	}
	
	@After
	public void tearDown() throws Exception {
		// Close the browser
		driver.quit();
		
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}

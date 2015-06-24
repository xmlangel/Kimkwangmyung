package sample.Selenium.Perfirmance;

public class DynaTraceDemo {
	
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception { 
		// Create a Profile Object and get Default Profile
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile ffprofile = profile.getProfile("default");
		
		// Create a new instance of the Firefox Driver, pass the default profile
		driver = new FirefoxDriver(ffprofile);
	}

	@Test
	public void testBMICalculator_Perf() throws Exception {
	
		// Open the BMI Calculator Mobile Application
		driver.get("http://dl.dropbox.com/u/55228056/bmicalculator.html");

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

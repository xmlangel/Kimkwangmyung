package sample.Selenium.Perfirmance;

import org.browsermob.core.har.Har;
import org.browsermob.proxy.ProxyServer;

import java.io.File;

public class BrowserMobDemo {
	
	public static void main(String[] args) throws Exception {
		//1. 먼저 BrowserMob 프록시를 시작한다.
		// Start the BrowserMob Proxy 
		// BrowserMob Proxy를 시작한다.
		ProxyServer server = new ProxyServer(4444);
		server.start();

		//2.BrowserMob 프록시는 SeleniumProxy와 DesiredCapabilities 객체를 사용한다.
		// Get the Selenium proxy object
		// 셀레늄 프록시 객체를 얻는다.
		Proxy proxy = server.seleniumProxy();
		
		// Configure Desired capability for using Proxy Server
		//DesiredCapabilities 객체가 프록시 서버를 사용하게 설정
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY, proxy);

		//3. 프록시를 사용해 브라우저 인스턴스를 만들고 실행한
		// Start the Browser up
		//브라우저를 실행한다.
		WebDriver driver = new FirefoxDriver(capabilities);
		
		//4. HAR 파일을 생성한다.
		// Create a new HAR with the label "bmiCalculator"
		//Har 파일에는 "bmicalculator"라벨을 붙인다.
		server.newHar("bmiCalculator");

		//5. 애플리케이션과상호작용하는 동작을 구현한다. 테스트를 수행한다는 개념
		// Open the BMI Calculator Application
		//BMI 계산기 애플리케이션을 실행한다.
		driver.get("http://www.google.com");

		/*driver.get("http://dl.dropbox.com/u/55228056/bmicalculator.html");

		WebElement height = driver.findElement(By.name("heightCMS"));
		height.sendKeys("181");
		
		WebElement weight = driver.findElement(By.name("weightKg"));
		weight.sendKeys("80");

		WebElement calculateButton = driver.findElement(By.id("Calculate"));
		calculateButton.click();*/
		
		Thread.sleep(5000);
		
		//6. 마지막은 BrowserMob 프록시 서버에서 측정한 성능 데이터를 수집하는 과정이다.
		// Get the HAR data
		//HAR 데이터를 가져온다.
		Har har = server.getHar();

		// Write the HAR Data in a File
		// 파일에 Har 데이터를 기록한다.
		File harFile = new File("C:\\bmiCalculator.har");
		har.writeTo(harFile);
		
		// Stop the BrowserMob Proxy Server
		//프록시 서버를 중지한다.
		server.stop();
		
		// Close the browser
		driver.quit();
				
	}
}

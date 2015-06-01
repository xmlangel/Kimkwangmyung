package appium.test.run;

import static appium.tutorial.android.util.Helpers.driver;
import io.appium.java_client.android.AndroidDriver;

import java.awt.RenderingHints.Key;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.touch.TouchActions;

import appium.test.run.UnicodeEncoder;


public class testone {

	public static void main(String[] args) throws MalformedURLException{
		
		
	//----------------Appium Setup
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("appium-version", "1.0");
		capabilities.setCapability("BROWSER_NAME", "Android");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "4.2");
        //capabilities.setCapability("deviceName", "HT117TH01325");
        capabilities.setCapability("deviceName", "cd1692bd");
         
        //capabilities.setCapability("unicodeKeyboard", "true");
        //capabilities.setCapability("resetkeyboard", "true");
        
        File appDir = new File("C:\\Temp");
		File app = new File(appDir, "yogiyo_debug.apk"); 
        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        
        //driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        
        implicitlyWait(driver, 50);
        
        By Button_Agree = By.xpath("//*[@text='동의하기']");
        By Input_Address = By.xpath("//*[@text='경기도 성남시 분당구 분당동']");
        By Input_Address_Text = By.xpath("//*[@class='android.widget.EditText']");
        By Button_All = By.xpath("//*[@resource-id='com.fineapp.yogiyo:id/btn_category_0']");
        By Button_Restarant = By.xpath("//*[@text='QA_Test_SMS_Relay']");
        By Button_TouchOrder= By.xpath("//*[@resource-id='com.fineapp.yogiyo:id/touchOrderTabBtn']");
        By Button_Phone= By.xpath("//*[@class='android.widget.LinearLayout']");
        By Button_Menu = By.xpath("//*[@text='자동화']");
        By Button_Justorder= By.xpath("//*[@resource-id='com.fineapp.yogiyo:id/justOrderBtn']");
        By Input_DetailAddress= By.xpath("//*[@resource-id='com.fineapp.yogiyo:id/input_address1]");
        By Input_PhoneAddress= By.xpath("//*[@resource-id='com.fineapp.yogiyo:id/input_phone]");
        By Checkbox_1= By.xpath("//*[@resource-id='com.fineapp.yogiyo:id/yogiyoTermChkBtn']");
        By Checkbox_2= By.xpath("//*[@resource-id='com.fineapp.yogiyo:id/collectItemsChkBtn']");
        By Button_OrderOk= By.xpath("//*[@resource-id='com.fineapp.yogiyo:id/btn_ok']");
               
        //UnicodeEncoder setText=  new UnicodeEncoder();
        
        
        
        
        Click(driver, Button_Agree);
		Click(driver, Input_Address);
		
		
		
		System.out.println(Input_Address_Text);
		driver.findElement(Input_Address_Text).clear();
        driver.findElement(Input_Address_Text).sendKeys("dyrldyehd\n");
		
/*        Click(driver, Button_All);
        
        Click(driver, Button_Phone);
        Click(driver, Button_TouchOrder);
        ClickScrole(driver, Button_Restarant);
        Click(driver, Button_Menu);
        Click(driver, Button_Justorder);
        Click(driver, Input_DetailAddress);
        Input_Text(driver, Input_DetailAddress, "wpszlstm wkehdghk xptmxm");
        Click(driver, Input_PhoneAddress);
        Input_Text(driver, Input_PhoneAddress, "01097704133");
        Click(driver, Checkbox_1);
        Click(driver, Checkbox_2);
        ClickScrole(driver, Button_OrderOk);
        
        System.out.println("OK");
        driver.quit();*/
}



	public static void implicitlyWait(WebDriver driver, int Time) {
		driver.manage().timeouts().implicitlyWait(Time, TimeUnit.SECONDS);
	}

	public static boolean isPresent(By by) {
        if (driver.findElements(by).size() > 0) return true;
        return false;
    }
	
	private static void ClickScrole(AndroidDriver driver, By locator) {
		
	
        for(int i=0; i<=10;i++)
        {
        	

        	

        	try{
        		driver.findElement(locator).click();
                break;
        	}catch(Exception e){
        		//swipe from the bottom of the screen to the top of the screen
        		//driver.swipe(470, 800, 470, 50, 400); 
        		//swipe the top element 
        		driver.swipe(470, 420, 470,180, 400); 
        		//swipe the last element swipe  
        		//driver.swipe(470, 800, 470, 600, 400); 
        		//swipe from middle of top element to the top of screen
        		//driver.swipe(470, 280, 470, 80, 400);


        		
        		//driver.swipe(600, 1000, 600, 200,1300);
        		
        		System.out.println("scrolling"+i);
        		continue;
        	}

        }
	}

	
	
	private static void Input_Text(AndroidDriver driver, By locator, String Text) {
		System.out.println(Text);
		driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(Text);
        Wait(2);
	}

	private static void Click(WebDriver driver, By locator) {
		System.out.println(locator);
		driver.findElement(locator).click();
		Wait(2);
	}

	private static void Wait(int Time) {
		int SleepTime = Time * 1000;
		
		try {
			Thread.sleep(SleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
/*	
	@Before
	public void setup() {

		File appDir = new File("C:\\Temp");
		System.out.println("1 OK");
		File app = new File(appDir, "yogiyo_debug.apk");
		System.out.println("2 OK");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		System.out.println("3 OK");
		capabilities.setCapability("BROWSER_NAME", "Android");
		System.out.println("4 OK");
		capabilities.setCapability("VERSION", "4.2.2");
		System.out.println("5 OK");
		capabilities.setCapability("platformName", "Android");
		System.out.println("6 OK");
		capabilities.setCapability("deviceName", "HT117TH01325");
		System.out.println("7 OK");
		capabilities.setCapability("app-package", "com.fineapp.yogiyo");
		System.out.println("8 OK");
		try {
			System.out.println("9 OK");
			WebDriver driver = new RemoteWebDriver(
					new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			System.out.println("OK");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	@Test
	public void loginTest() throws Exception {
		// System.out.println("OK");
	}
*/
	
}


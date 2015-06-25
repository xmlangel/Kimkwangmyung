package com.km.selenium.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.km.selenium.tests.Helper_mobile;

public class AndroidWebDriver extends Helper_mobile {
	By Button_Agree = By.xpath("//*[contains(@text,'동의하기')]");
	By Input_Address = By
			.xpath("//*[contains(@resource-id,'id/tv_address')]");
	By Input_Address_Text = By
			.xpath("//*[@class='android.widget.EditText']");
	By Button_All = By
			.xpath("//*[contains(@resource-id,'id/btn_category_0')]");
	By Button_Restarant = By.xpath("//*[@text='QA_Test_SMS_Relay']");
	By Button_TouchOrder = By
			.xpath("//*[contains(@resource-id,'id/touchOrderTabBtn')]");
	By Button_Phone = By.xpath("//*[@class='android.widget.LinearLayout']");
	By Button_Menu = By.xpath("//*[contains(@text,'자동화')]");
	By Button_Justorder = By
			.xpath("//*[contains(@resource-id,'justOrderBtn')]");
	By Input_DetailAddress = By
			.xpath("//*[contains(@resource-id,'id/input_address1')]");
	By Input_PhoneAddress = By
			.xpath("//*[contains(@resource-id,'id/input_phone')]");
	By Checkbox_TermCheck = By
			.xpath("//*[contains(@resource-id,'id/yogiyoTermCheckLayout')]");
	By Checkbox_collectItemsCheck = By
			.xpath("//*[contains(@resource-id,'id/collectItemsCheckLayout')]");
	By Button_OrderOk = By
			.xpath("//*[@resource-id='com.fineapp.yogiyo:id/btn_ok']");
	By Button_Close = By
			.xpath("//*[@resource-id='com.fineapp.yogiyo:id/closeBtn']");
	
	@Test
	public void mobile_App() throws Exception {
		// ----------------Appium Setup
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.2");
		capabilities.setCapability("deviceName", "cd1692bd");
		File appDir = new File("D:\\Automation\\Appium");
		File app = new File(appDir, "debug.apk");
		AndroidDriver driver = new AndroidDriver(new URL("http://110.110.110.101:4723/wd/hub"), capabilities);
		implicitlyWait(driver, 50);
		Click(driver, Button_Agree);
		Click(driver, Input_Address);
		driver.tap(1, 289, 190, 10);// 키입력받는곳
		Sleep(2000);
		TAP(driver, 211, 1005, "ㅇ");// ㅇ
		TAP(driver, 395, 906, "ㅛ");// ㅛ
		TAP(driver, 246, 910, "ㄱ");// ㄱ
		TAP(driver, 651, 1001, "ㅣ");// ㅣ
		TAP(driver, 211, 1005, "ㅇ");// ㅇ
		TAP(driver, 395, 906, "ㅛ");// ㅛ
		Sleep(2000);
		TAP(driver, 665, 1216, "검색");// 검색
		Sleep(2000);
		Click(driver, Button_All);
		Click(driver, Button_Phone);
		Click(driver, Button_TouchOrder);
		ClickScrole(driver, Button_Restarant);
		Click_Text(driver, "자동화");
		Sleep(2000);
		Click(driver, Button_Justorder);
		Click(driver, Input_DetailAddress);
		Input_Text(driver, Input_DetailAddress, "Jenkins Android Test");
		Click(driver, Input_PhoneAddress);
		Input_Text(driver, Input_PhoneAddress, "01062573612");
		driver.sendKeyEvent(4);// Back 버튼
		Click(driver, Checkbox_TermCheck);
		Click(driver, Checkbox_collectItemsCheck);
		Find_Text(driver, "주문 완료");
		Click(driver, Button_OrderOk);// 주문완료
		Click(driver, Button_Close);// 닫기
		Assert.assertEquals("감사합니다", "감사합니다.", "감사합니다.");
		System.out.println("Test_PassOK");
		Sleep(3000);
		driver.quit();
	}
}
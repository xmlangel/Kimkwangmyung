package com.km.selenium.tests;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import com.km.selenium.DriverFactory;

public class Helper extends DriverFactory {

	public void Set_URL(WebDriver driver, String URL) {
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public static void Sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void InputText(WebDriver driver, By Element, String Key) {
		ExpectedCondition(driver, Element);
		driver.findElement(Element).clear();
		driver.findElement(Element).sendKeys(Key);
	}

	public void WaitForText(WebDriver driver, String Text, final By Element) throws InterruptedException {
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (Text.equals(driver.findElement(
						Element).getText()))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}
	
	public void Click(WebDriver driver, final By Element) {
		ExpectedCondition(driver, Element);
		driver.findElement(Element).click();
		System.out.println(Element);
		Sleep(2000);
	}
	
	public void scrollAndClick(WebDriver driver, By by)
	{
	
	   WebElement element = driver.findElement(by);
	   int elementPosition = element.getLocation().getY();
	   String js = String.format("window.scroll(0, %s)", elementPosition);
	   ((JavascriptExecutor)driver).executeScript(js);
	   element.click();
	}
	
	public void TextClick(WebDriver driver, String text) {

		if (isTextExisting(driver, text)) {
			Sleep(1000);
			System.out.println("is_TextExist Click");
			driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]")).click();			
		}else{
			Sleep(1000);
			System.out.println("Scroll_Click");
			WebElement element = driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
			System.out.println(element);
			JavascriptExecutor jse = (JavascriptExecutor)driver;//scroll down
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			System.out.println("Click");
			driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]")).click();
			System.out.println("OK");
		}
		
		System.out.println(text);
	}
	
	
	private boolean isTextExisting(WebDriver driver, String text) {
		try {
			return driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]")).isDisplayed();

		} catch (NoSuchElementException e) {
			return false;
		}
	}

	
	private void While(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public static void ExpectedCondition(WebDriver driver, final By Element) {
	
		try {
			WebElement message = (new WebDriverWait(driver, 10))
					.until(new ExpectedCondition<WebElement>() {
						@Override
						public WebElement apply(WebDriver d) {
							return d.findElement(Element);
						}
					});
			//
			System.out.println(message);
			System.out.println(message.isDisplayed());
			System.out.println(message.getText());
			
		} catch (NoSuchElementException e) {
			fail("Element not found!!");
			e.printStackTrace();
		}
	}

	public static void FindWaitElement(WebDriver driver, By Byname) {
		Sleep(1000);
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(Byname);
	}

	public Helper() {
		super();
	}

	public String gettimestamp() throws Exception {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String currentTime = dateFormat.format(calendar.getTime());
		// long startTime = System.currentTimeMillis();
		// System.out.println("현재 시간 : " +
		// dateFormat.format(calendar.getTime()));
		// System.out.println(startTime);
		return currentTime;
	}
	
	public void MobileWeb_Order(WebDriver driver, String URL, String PhoneAddress) throws InterruptedException {
		By InputBox_Address = By.name("address_input");
		String Address = "요기요동";
		By Link_Adress_Input = By.linkText("요기요시 요기요구 요기요동");
		By Button_Total_View = By.cssSelector(".category-list>div.row>div>a");
		String Restaurant_Name = "QA_Test_SMS_Relay";
		String Menu_Category = "테스트메뉴";
		String Menu_Name = "자동화";
		By Button_order = By.linkText("주문하기");
		By InputBox_Detail_Address = By.name("address_detail");
		By InputBox_Phone_Address = By.name("phone");
		String Detail_Address = "Jenkins Automation Mobile Test";
		By Button_Phone_actavation = By.linkText("인증요청");
		By Button_Confirm = By.xpath("(//button[@type='button'])[6]");
		By CheckBox_aggrement = By
				.cssSelector("input[name=\"aggreement_yogiyo\"]");
		By CheckBox_aggrement_privacy = By
				.cssSelector("input[name=\"aggreement_privacy\"]");
		By Button_Chechout = By.xpath("//div[@id='content']/div/form/div[2]/div/button");
		By Order_Success = By.cssSelector("p.text-danger.msg-title");
		Set_URL(driver, URL);
		Click(driver, InputBox_Address); // 주소 입력
		InputText(driver, InputBox_Address, Address); // 요기요동입력
		Click(driver, Link_Adress_Input); // 요기요동클릭
		Click(driver, Button_Total_View);// 전체보기
		TextClick(driver, Restaurant_Name);// QA_TEST_SMS_Relay
		TextClick(driver, Menu_Category);// 테스트메뉴
		TextClick(driver, Menu_Name);// 자동화
		Click(driver, Button_order);// 주문하기
		InputText(driver, InputBox_Detail_Address, Detail_Address); // 상세주소
		InputText(driver, InputBox_Phone_Address, PhoneAddress); // 휴대전화번호
		Click(driver, Button_Phone_actavation); // 인증요청
		Click(driver, Button_Confirm); // 팝업 확인
		Click(driver, CheckBox_aggrement); // 이용약관
		Click(driver, CheckBox_aggrement_privacy); // 개인정보
		Click(driver, Button_Chechout);// 주문완료		
		WaitForText(driver, "주문 감사합니다", Order_Success);
		Sleep(3000);
	}

	public void DeskTopWeb_Order(WebDriver driver, String Restaurant, String Phone1, String Phone2, String Phone3) throws InterruptedException {
		// --- Search for restaurant
		// Input City Name
		By zipcode = By.xpath("//input[@id='zipcode_or_city']");
		By findRestaurant = By.xpath("//strong[text()='" + Restaurant + "']/..");
		By menulist = By.cssSelector("li[id*=menu_item-자동화]");
		By menuButton = By.cssSelector("div.large_red_button");
		By Button_Search_Summit = By.xpath("//input[@id='search_submit']");
		By inputAddress = By.xpath("//input[@id='customer_address_2']");
		By inputPhone_1 = By.xpath("//input[@id='phone_1']");
		By inputPhone_2 = By.xpath("//input[@id='phone_2']");
		By inputPhone_3 = By.xpath("//input[@id='phone_3']");
		By CheckBox_aggrement = By.xpath("//input[@id='tos']");
		By CheckBox_aggrement_privacy = By.xpath("//input[@id='tos1']");
		By Test1 = By.xpath("//input[@id='orderform_centralpayment_none']");
		By Test2 = By.xpath("//button[@id='payment_submit_button']");
		By Order_Success = By.xpath("//div[@id='order_complete_container']/p/b");
		
		Click(driver, zipcode);
		InputText(driver, zipcode, "요기요시 요기요구 요기요동");
		Click(driver, Button_Search_Summit);
		ExpectedCondition(driver, findRestaurant);
		Click(driver, findRestaurant);
		Click(driver, menulist);
		Click(driver, menuButton);
		// -- 주소입력창이 나올경우
		System.out.println("주소입력창검사");		
		List<WebElement> elements = driver.findElements(zipcode);
		System.out.println(elements.size());
		if (elements.size() == 1) {
			System.out.println("집코드없음");
			Click(driver, zipcode);
			InputText(driver, zipcode, "요기요시 요기요구 요기요동");
			Sleep(3000);
			driver.findElement(zipcode).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(zipcode).sendKeys(Keys.ENTER);
			Sleep(3000);
			Click(driver, menuButton);
		}		
		System.out.println("집코드있음");
		InputText(driver, inputAddress, "Jenkins Automation DeskTop Web Test");
		InputText(driver, inputPhone_1, Phone1);
		InputText(driver, inputPhone_2, Phone2);
		InputText(driver, inputPhone_3, Phone3);
		Click(driver, CheckBox_aggrement);
		Click(driver, CheckBox_aggrement_privacy);
		System.out.println("동의하기");
		Click(driver, Test1);
		Click(driver, Test2);
		System.out.println("주문완료");
		WaitForText(driver, "요기요를 이용해 주셔서 감사합니다.", Order_Success);
		Sleep(3000);
	}
	public void Change_ResultCode(WebDriver driver, String URL, String PhoneAddress) throws InterruptedException {
		By Button_login = By.cssSelector("input[value*=login]");
		By InputBox_id = By.id("id_username");
		By InputBox_Password = By.id("id_password");
		String User_ID = "qa_super@yogiyo.co.kr";
		String User_Password = "DNiWdwTwbMTgDmaLeOpavsQz";
		
		Set_URL(driver, URL);
		ExpectedCondition(driver, InputBox_id);
		
		// ---------------log in
		InputText(driver, InputBox_id, User_ID);
		InputText(driver, InputBox_Password, User_Password);
		Click(driver, Button_login);

		// --------------- Go to Orders Tab
		By Button_Orders = By.cssSelector("div[id*=page_orders]");

		Click(driver, Button_Orders);
		WaitForText(driver, "Orders",
				By.cssSelector("div.content_menu_headline"));
		// -------------- Check phone number
		InputText(driver, By.id("search"), PhoneAddress);
		Click(driver, By.cssSelector("input[value*=검색]"));
		WaitForText(driver, "Orders",
				By.cssSelector("div.content_menu_headline"));
		//Click(driver, By.linkText("DEFAULT: 기타(Not classified)"));// Update
		Click(driver, By.cssSelector("a.ticket_status"));
		InputText(driver, By.id("id_code_number"), "13");
		Click(driver, By.cssSelector("#update_button"));// Update Button
	}
}
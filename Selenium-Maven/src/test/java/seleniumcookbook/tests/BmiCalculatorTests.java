package seleniumcookbook.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import static org.junit.Assert.*;

import seleniumcookbook.tests.pageobjects.*;

public class BmiCalculatorTests{
	
	@Test
	public void testBmiCalculation()
	{
		// 크롬 브라우저를 열고BMI 계산기 페이지로 이동한다.
		WebDriver driver = new ChromeDriver();
		driver.get("http://dl.dropbox.com/u/55228056/bmicalculator.html");
		
		// 드라이버를 인자로 BmiCalcPage 인스턴스를 생성한다.
		BmiCalcPage bmiCalcPage = new BmiCalcPage(driver);
		
		//키와 몸무게를 입력한다.
		bmiCalcPage.heightCMS.sendKeys("181");
		bmiCalcPage.weightKg.sendKeys("80");
		
		//계산 버튼을 클릭한다.
		bmiCalcPage.Calculate.click();
		
		//계산결과로 나타난 Bmi 값과 법주를 확인한다.
		assertEquals("22.4",bmiCalcPage.bmi.getAttribute("value"));
		assertEquals("Normal",bmiCalcPage.bmi_category.getAttribute("value"));
		
		//브라우저를 닫는다.
		
	}
	
	
}

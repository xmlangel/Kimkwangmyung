package seleniumcookbook.tests;

import org.junit.Test;
import static org.junit.Assert.*;

import seleniumcookbook.tests.pageobjects.*;

public class BmiCalculatorTests{
	
	@Test
	public void testBmiCalculation()
	{
		//BmiCalcPage 인스턴스를 만들고 드라이버를 초기화한다.
		BmiCalcPage bmiCalcPage = new BmiCalcPage();
		
		//Bmi 계산기 페이지를 연다.
		bmiCalcPage.load();
		
		//키와 몸무게를 입력해 Bmi를 계산한다.
		bmiCalcPage.calculateBmi("181", "80");
		
		//계산결과로 나타난 Bmi 값과 법주를 확인한다.
		assertEquals("22.4",bmiCalcPage.getBmi());
		assertEquals("Normal",bmiCalcPage.getBmiCategory());
		
		//페이지를 닫는다.
		bmiCalcPage.close();
	}
}

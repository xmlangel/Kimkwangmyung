package com.km.selenium.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

public class ChangeResultCodeWebDriver extends Helper {
	
	String Phone_Address = "01062573612";
	// --- Change Result code
	String Backend_URL = "https://www.yogiyo.co.kr/backend/order/";
	String Backend_URL_Staging = "https://staging.yogiyo.co.kr/backend/order/";
			
	@Test
	public void ChangeResultCode_Staging() throws Exception {
		System.out.println("Start");
		WebDriver driver = getDriver();
		Change_ResultCode(driver, Backend_URL_Staging,Phone_Address);
	}
	
	@Test
	public void ChangeResultCode_Real() throws Exception {
		System.out.println("Start");
		WebDriver driver = getDriver();
		Change_ResultCode(driver, Backend_URL,Phone_Address);
	}
}

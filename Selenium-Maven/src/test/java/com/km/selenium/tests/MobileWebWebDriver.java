package com.km.selenium.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

public class MobileWebWebDriver extends Helper {
	
	String Phone_Address = "01062573612";
	String Mobile_Web_URL = "https://www.yogiyo.co.kr/mobile/";
	String Mobile_Web_URL_Staging = "https://staging.yogiyo.co.kr/mobile/";
			
	@Test
	public void mobileWeb_Real() throws Exception {
		System.out.println("Start");
		WebDriver driver = getDriver();
		MobileWeb_Order(driver, Mobile_Web_URL,Phone_Address);
	}
	
	@Test
	public void mobileWeb_Staging() throws Exception {
		System.out.println("Start");
		WebDriver driver = getDriver();
		MobileWeb_Order(driver, Mobile_Web_URL_Staging,Phone_Address);
	}
}

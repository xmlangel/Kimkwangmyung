package com.km.selenium.tests;

import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.km.selenium.DriverFactory;

public class Helper_mobile extends DriverFactory {
			
	public static void TAP(AndroidDriver driver, int x, int y, String Text) {
		driver.tap(1, x, y, 10);// 키입력
		System.out.println(Text);

	}

	public static void Click_Text(AndroidDriver driver, String Text) {
		System.out.println(Text);
		driver.scrollTo(Text).click();
		Sleep(2000);
	}

	public static void ClickScrole(AndroidDriver driver, By locator) {
		System.out.println(locator);
		for (int i = 0; i <= 10; i++) {
			try {
				driver.findElement(locator).click();
				break;
			} catch (Exception e) {
				// swipe from the bottom of the screen to the top of the screen
				driver.swipe(470, 420, 470, 180, 400);
				System.out.println("scrolling" + i);
				continue;
			}

		}
	}

	public static void Find_Text(AndroidDriver driver, String Text) {
		System.out.println(Text);
		driver.scrollTo(Text);
		Sleep(2000);
	}

	public static void implicitlyWait(AndroidDriver driver, int Time) {
		driver.manage().timeouts().implicitlyWait(Time, TimeUnit.SECONDS);
	}

	public static void Input_Text(AndroidDriver driver, By locator, String Text) {
		System.out.println(Text);
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(Text);
		Sleep(2000);
	}

	public static void Click(AndroidDriver driver, By locator) {
		System.out.println(locator);
		driver.findElement(locator).click();
		Sleep(2000);
	}

	public static void Sleep(int Time) {
		try {
			Thread.sleep(Time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

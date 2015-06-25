package com.km.selenium.tests;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.media.Format;
import org.monte.screenrecorder.ScreenRecorder;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;



public class DeskTopWebWebDriver extends Helper {

	String Desktop_Web_URL = "https://www.yogiyo.co.kr/";
	String Desktop_Web_URL_Staging = "https://staging.yogiyo.co.kr/";
	
	
	@Test
	public void Real_Terminal() throws Exception {
		WebDriver driver = getDriver();
		driver.manage().window().maximize();
		driver.get(Desktop_Web_URL);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	
		DeskTopWeb_Order(driver,"QA_Test_Terminal","010","6257","3612");
	}
	@Test
	public void Real_SMS_Relay() throws Exception {
		WebDriver driver = getDriver();
		driver.manage().window().maximize();
		driver.get(Desktop_Web_URL);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		DeskTopWeb_Order(driver,"QA_Test_SMS_Relay","010","6257","3612");
	}
	
	private ScreenRecorder screenRecorder;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Test
	public void Record() throws Exception {
		
	// Create an instance of GraphicsConfiguration to get the Graphics configuration
		// of the Screen. This is needed for ScreenRecorder class.
		startRecording();
		WebDriver driver = getDriver();
		System.out.println("getDriver");
		driver.manage().window().maximize();
		System.out.println("test");
		driver.get(Desktop_Web_URL);
		stopRecording();
	}
	private void stopRecording() throws IOException {
		this.screenRecorder.stop();
	}
	private void startRecording() throws IOException, AWTException {
		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDefaultConfiguration();
		System.out.println("start");
		// Create a instance of ScreenRecorder with the required configurations
		screenRecorder = new ScreenRecorder(gc,
			new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
			new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
				CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
				DepthKey, (int)24, FrameRateKey, Rational.valueOf(15),
				QualityKey, 1.0f,
				KeyFrameIntervalKey, (int) (15 * 60)),
			new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,"black",
				FrameRateKey, Rational.valueOf(30)),
			null);		
		
		this.screenRecorder.start();
		System.out.println("ok");
	}
}
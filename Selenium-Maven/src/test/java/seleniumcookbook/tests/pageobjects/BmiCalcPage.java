package seleniumcookbook.tests.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BmiCalcPage {
	private WebElement heightCMS;
	private WebElement weightKg;
	private WebElement Calculate;
	private WebElement bmi;
	private WebElement bmi_category;
	private WebDriver driver;
	private String url = "http://dl.dropbox.com/u/55228056/bmicalculator.html";
	
	public BmiCalcPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
}

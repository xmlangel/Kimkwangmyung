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
	
	public BmiCalcPage() {
		driver = new ChromeDriver();
		PageFactory.initElements(driver, this);
	}
	
	public void load() {
		this.driver.get(url);
	}
	
	public void calculateBmi(String height, String weight) {
		heightCMS.sendKeys(height);
		weightKg.sendKeys(weight);
		Calculate.click();
	}
	
	public String getBmi() {
		return bmi.getAttribute("value");
	}
	
	public String getBmiCategory() {
		return bmi_category.getAttribute("value");
	}
	
	public void close() {
		this.driver.close();
	}
	
}

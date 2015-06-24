package sample.Selenium.wait;

public class ExplicitWaitTest {
/*
	명시적 대기시간으로 테스트 동기화를 이룰수 있다. 명시적 대기시간은 묵시적 대기사간에 비해 더많은 제어권을 제공하는데 코드나 조건에 따라 테스트의 진행여부를 결정할 수있다.
	
	대기조건/
	엘리먼트가 보이고 활성화됨
	엘리먼트가 선택됨
	엘리먼트가 존재함
	명시한 텍스트가 엘리먼트에 존재함
	명시한 값이 엘리먼트 value 속성에 존재함
	명시한 제목을 포함하고 있음.
	메소드
	elementTobeClickable
	elementToBeSelected
	presenceOfElementLocated
	textToBePresentInElement
	textToBePresentInElementValue
	titleContains
	
	기타 자세한사항은 아래 참고
	- http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html
	- https://code.google.com/p/selenium/source/browse/java/client/src/org/openqa/selenium/support/ui/ExpectedConditions.java
	
*/
	
	
	@Test
 	public void testExplicitWait()
 	{
 		WebDriver driver = new FirefoxDriver();
        driver.get("http://dl.dropbox.com/u/55228056/AjaxDemo.html");
 		
        try {
 			WebElement page4button = driver.findElement(By.linkText("Page 4"));
 			page4button.click();
 		
 			WebElement message = (new WebDriverWait(driver, 5))
 					  .until(new ExpectedCondition<WebElement>(){
 						@Override
 						public WebElement apply(WebDriver d) {
 							return d.findElement(By.id("page4"));
 						}});
 			assertTrue(message.getText().contains("Nunc nibh tortor"));
 			
 		} catch (NoSuchElementException e) {
 			fail("Element not found!!");
 			e.printStackTrace();
 		} finally {
 			driver.close();
 		}
 	}
	
	@Test
	public void testExplicitWaitByTitle()
	{
		 WebDriver driver = new FirefoxDriver();
		 driver.get("http://www.google.com");
		 WebElement query = driver.findElement(By.name("q"));
		 query.sendKeys("selenium");
		 query.submit();
		 
		 (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			 public Boolean apply(WebDriver d) {
				 return d.getTitle().toLowerCase().startsWith("selenium");
		 }});

		 assertTrue(driver.getTitle().toLowerCase().startsWith("selenium"));

		 driver.quit();
	}
	
	@Test
	public void testExplicitWaitTitleContains()
	{
		//Go to the Google Home Page
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com");
	
		//Enter a term to search and submit
		WebElement query = driver.findElement(By.name("q"));
		query.sendKeys("selenium");
		query.submit();
		
		//Create Wait using WebDriverWait. 
		//This will wait for 10 seconds for timeout before title is updated with search term
		//If title is updated in specified time limit test will move to the text step 
		//instead of waiting for 10 seconds
		//페이지 제목이 입력한 검색어로 변경돼 나타날 때까지 10초간 대기한다. 설정한 시간안에 제목 표시줄이 변경되면 10초를 기다리지 않고 다음 테스트 코드가 동작한다.
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleContains("selenium"));

		//Verify Title
		assertTrue(driver.getTitle().toLowerCase().startsWith("selenium"));
		
		driver.quit();
	}
}

package sample.Selenium.TakesScreenshot;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class WebElementExtender {
	
   public static void setAttribute(WebElement element, String attributeName, String value)
   {
       WrapsDriver wrappedElement = (WrapsDriver) element;
       
       JavascriptExecutor driver = (JavascriptExecutor) wrappedElement.getWrappedDriver();
       driver.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attributeName, value);
   }
   
   public static void highlightElement(WebElement element) {
	    for (int i = 0; i < 5; i++) {
	    	WrapsDriver wrappedElement = (WrapsDriver) element;
	    	JavascriptExecutor driver = (JavascriptExecutor) wrappedElement.getWrappedDriver();
	    	driver.executeScript("arguments[0].setAttribute('style', arguments[1]);",
	                element, "color: green; border: 2px solid green;");
	    	driver.executeScript("arguments[0].setAttribute('style', arguments[1]);",
	                element, "");
	    }
	}
   
   public static File captureElementBitmap(WebElement element) throws Exception {   

	//Get the WrapsDriver of the WebElement    
	//WebElement에서 WrapsDriver 를 가져온다
   	WrapsDriver wrapsDriver = (WrapsDriver) element;
   	
   	//Get the entire Screenshot from the driver of passed WebElement
   	//드라이버에서 전체 스크린샷을 얻는다
   	File screen = ((TakesScreenshot)  wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
   	
   	//Create an instance of Buffered Image from captured screenshot
   	//스크린샷으로 버퍼 이미지 인스턴스를 생성한다
   	BufferedImage img = ImageIO.read(screen);
   	
   	// Get the Width and Height of the WebElement using getSize()
   	// 엘리먼트 크기르 구한다
   	int width = element.getSize().getWidth();
    int height = element.getSize().getHeight();

    //Create a rectangle using Width and Height
    //엘리먼트와 같은 크기의 직사각형을 생성한다
    Rectangle rect = new Rectangle(width, height);
    
    //Get the Location of WebElement in a Point. 
    //This will provide X & Y co-ordinates of the WebElement
    //엘리먼트가 위치한 x,y  좌표를  가져온다.
    Point p = element.getLocation();
    
    //Create image by for element using its location and size. 
    //This will give image data specific to the WebElement
    //좌표와 크기로 엘리먼ㅌ의 이미지를 생성한다. 엘리먼트만 떼어낸 이미지 데이터를 얻는다.
    BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width,         
                                          rect.height);
    
    //Write back the image data for element in File object
    //이미지 데이터를 파일 객체에 기록한다
    ImageIO.write(dest, "png", screen);
    
    //Return the File object containing image data
    return screen;
   }
}

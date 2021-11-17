package Week5.Day2;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;



public class ServiceNow1 {
	
	@Test(dataProvider="readData2")
	public void runServiceNow1(String u,String p) throws InterruptedException {
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	driver.get("https://dev107451.service-now.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	WebElement frame1 = driver.findElement(By.xpath("//div[@class='navpage-main-left ng-isolate-scope']/iframe"));
	driver.switchTo().frame(frame1);
	driver.findElement(By.id("user_name")).sendKeys(u);
	driver.findElement(By.id("user_password")).sendKeys(p);
	driver.findElement(By.id("sysverb_login")).click();
	driver.switchTo().defaultContent();
	driver.findElement(By.id("filter")).sendKeys("incident");
	driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
	WebElement frame2 = driver.findElement(By.xpath("//div[@class='navpage-main-left ng-isolate-scope']/iframe"));
	driver.switchTo().frame(frame2);
	driver.findElement(By.id("sysverb_new")).click();
	String attribute = driver.findElement(By.id("incident.number")).getAttribute("value");
	Thread.sleep(3000);
	driver.findElement(By.id("lookup.incident.caller_id")).click();
	Thread.sleep(2000);
	driver.switchTo().defaultContent();
	Set<String> win1 = driver.getWindowHandles();
	List<String> setwin1 = new ArrayList<String>(win1);
	driver.switchTo().window(setwin1.get(1));
	driver.findElement(By.xpath("//span[@class='table-btn-lg']/following::a[1]")).click();
	driver.switchTo().window(setwin1.get(0));
	driver.switchTo().frame(frame2);
	driver.findElement(By.id("incident.short_description")).sendKeys("Automation Testing");
	driver.findElement(By.id("sysverb_insert")).click();
	driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(attribute);
	driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(Keys.ENTER);
	String text = driver.findElement(By.xpath("(//td[@class='vt']/a)[1]")).getText();
	if(attribute.equals(text)) {
		System.out.println("The incident was created successfully");
	}else
	{
		System.out.println("The incident was not created successfully");
	}
	driver.close();
	}
	@DataProvider
	public String[][] readData2() throws IOException{
		ReadExcel exl2=new ReadExcel();
		String[][] readdata1=exl2.readdata("servicenow");
		return readdata1;
	}
}

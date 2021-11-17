package Week5.Day2;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Create {

	@Test(dataProvider="fetchdata")
public void runcreate(String u,String p,String comp,String name,String secname,String num) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("username")).sendKeys(u);
		driver.findElement(By.id("password")).sendKeys(p);
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(comp);
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(name);
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(secname);
		driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(num);
		driver.findElement(By.name("submitButton")).click();
		driver.close();
}
	
	@DataProvider(name="fetchdata")
	public String[][] readdata() throws IOException{
		ReadExcel exl=new ReadExcel();
		String[][] readdata=exl.readdata("leaftaps");
		return readdata;
	}

	
}

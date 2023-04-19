package Practice1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Property_file {
	
	WebDriver driver;
	Properties con;
	
	@BeforeTest
	public void setup() throws FileNotFoundException, IOException 
	{
		con = new Properties();
		con.load(new FileInputStream("or.properties"));
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(con.getProperty("url"));
	}
	
	@Test
	public void validateLogin() 
	{
		driver.findElement(By.xpath(con.getProperty("btnreset"))).click();
		driver.findElement(By.xpath(con.getProperty("inpuid"))).sendKeys(con.getProperty("entuid"));
		driver.findElement(By.xpath(con.getProperty("inpwd"))).sendKeys(con.getProperty("entpwd"));
		driver.findElement(By.xpath(con.getProperty("btnlogin"))).click();
		
		String Expected_Title = "Users « Stock Accounting";
		String Actual_Title = driver.getTitle();
		
		if (Expected_Title.equalsIgnoreCase(Actual_Title)) 
		{
			Reporter.log("Login Success::"+Expected_Title+"   "+Actual_Title,true);
		}else 
		{
			Reporter.log("Login Failed::"+Expected_Title+"   "+Actual_Title,true);
		}
		
	}
	@AfterTest
	public void closeApp() 
	{
		driver.close();
	}
}

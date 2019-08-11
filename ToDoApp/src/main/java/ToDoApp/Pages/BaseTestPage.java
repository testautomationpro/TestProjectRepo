package ToDoApp.Pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTestPage {
	public static WebDriver driver;
	public String baseurl;
	public String email;
	public String password;
	public BaseTestPage()   {

		if(driver==null) {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver 2");

			driver =new ChromeDriver();
			

		}
	}

}
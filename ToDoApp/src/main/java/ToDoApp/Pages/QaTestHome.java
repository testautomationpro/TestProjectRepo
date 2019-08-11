package ToDoApp.Pages;

import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class QaTestHome extends BaseTestPage{
		
		
		public QaTestHome()
		{
			
			PageFactory.initElements(driver, this);
			 
		}
		
		public void openPage(String url)
		{
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		
		@FindBy(xpath ="//h1[normalize-space()='ToDo App']")
		public WebElement headerToDoApp; 	
		
		// @FindBy(how = How.XPATH, using ="//a[text()='Sign In']") 
	 	@FindBy(xpath =".//a[text()=\"Sign In\"]") 
		public WebElement linkSignIn; 
			
	
}
	




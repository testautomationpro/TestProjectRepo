package ToDoApp.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ToDoAppSignIn extends BaseTestPage{
	
	public ToDoAppSignIn() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath ="//h1[normalize-space()='ToDo App']")
	public WebElement headerToDoApp; 	
	
	@FindBy(xpath="//input[@id=\"user_email\"]") 
	public WebElement textBoxEmail; 
	
	@FindBy(xpath ="//input[@id=\"user_password\"]")
	public WebElement textBoxpassword; 	
	
	@FindBy(xpath="//input[@type='submit']") 
	public WebElement buttonSignIn; 
	
	public void signin(String email, String Password) {
		textBoxEmail.clear();
		textBoxEmail.sendKeys("honeysebastian@gmail.com");
		textBoxpassword.clear();
		textBoxpassword.sendKeys("qatestavenuecode");
		buttonSignIn.click();
		
	}
}

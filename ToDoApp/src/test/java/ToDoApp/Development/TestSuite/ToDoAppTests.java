package ToDoApp.Development.TestSuite;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import ToDoApp.Pages.BaseTestPage;
import ToDoApp.Pages.QaTestHome;
import ToDoApp.Pages.ToDoAppSignIn;
import ToDoApp.Pages.ToDoAppUserHome;

public class ToDoAppTests extends BaseTestPage {
	

	Properties prop=new Properties();
	String baseurl;
	String email;
	String password;
	
public ToDoAppTests()  {
		super();
		// TODO Auto-generated constructor stub
	}
@BeforeSuite
public void beforeSuite() throws IOException{
		
	FileInputStream fis =new FileInputStream("src/test/resources/env.properties");
	prop.load(fis);
	

 baseurl=prop.getProperty("baseurl");
 email=prop.getProperty("email");
 password=prop.getProperty("password");
}
@AfterSuite
public void afterSuite() {
	driver.quit();
}
@AfterClass
public void afterClass(){
	//driver.quit();
}
@BeforeMethod
public void beforeMethod(){
	driver.manage().deleteAllCookies();
	
}
@AfterMethod
public void afterMethod(){
	
	//driver.close();
}

//Test case for Login Functionality
	@Test
	public void test_Signin()  {
		
	QaTestHome  qaTestHome = new QaTestHome();
	qaTestHome.openPage(baseurl);

	qaTestHome.linkSignIn.click();
	ToDoAppSignIn toDoAppSignIn =new ToDoAppSignIn();
	toDoAppSignIn.signin(email,password);
	ToDoAppUserHome toDoAppUserHome =new ToDoAppUserHome();
	assertEquals(toDoAppUserHome.textWelcomeUser.getText(),"Welcome, Honey Sebastian!");
	toDoAppUserHome.signout();
	
}
	//test case for adding task in the Todo list
	@Test
	public void test_addTask()  {
	
	//navigating to "http://qa-test.avenuecode.com/	" value stored in .properties file.
	QaTestHome  qaTestHome = new QaTestHome();
	qaTestHome.openPage(baseurl);
	//login to the application using credentials given in properties file
	qaTestHome.linkSignIn.click();
	ToDoAppSignIn toDoAppSignIn =new ToDoAppSignIn();
	toDoAppSignIn.signin(email,password);
	ToDoAppUserHome toDoAppUserHome =new ToDoAppUserHome();
	//creating a unique task name each run
	String taskname="task"+ToDoAppUserHome.generateCurrentDateTime();
	//TODO: if user names is provided , van be customized for $user name
	
	//adding task
	toDoAppUserHome.addTask(taskname);
	int n=toDoAppUserHome.getTask(taskname);
	System.out.println(n);
	String listname =toDoAppUserHome.linkToDo.get(n).getText();
	assertEquals(listname,taskname);
	toDoAppUserHome.signout();
	}
	
	//test case for adding subtask for a task
	@Test
	public void test_addSubTask()  {
		 
	QaTestHome  qaTestHome = new QaTestHome();
	qaTestHome.openPage(baseurl);

	qaTestHome.linkSignIn.click();
	ToDoAppSignIn toDoAppSignIn =new ToDoAppSignIn();
	toDoAppSignIn.signin(email,password);
	ToDoAppUserHome toDoAppUserHome =new ToDoAppUserHome();
	String taskname="task"+ToDoAppUserHome.generateCurrentDateTime();
	toDoAppUserHome.addTask(taskname);
	toDoAppUserHome.getTask(taskname);
	toDoAppUserHome.buttonManageTasks.get(toDoAppUserHome.taskindex).click();
	String subtaskname="Subtask"+ToDoAppUserHome.generateCurrentDateTime();
	toDoAppUserHome.addSubTask(subtaskname);
	assertTrue((subtaskname.length()>2) && (subtaskname.length()<251),"Sub Task Name should have minimum 3 characters or maximum 250 characters ");

	int i=toDoAppUserHome.getSubTask(subtaskname);
	
	try {
		
	String st_listname =toDoAppUserHome.linkSubTask.get(i).getText();
	assertEquals(st_listname,subtaskname);
	}
	catch(ArrayIndexOutOfBoundsException e) {
	
		System.out.println(e.getMessage());
	}
	
	toDoAppUserHome.ButtonClose.click();
	toDoAppUserHome.signout();
	
	
}
	//test case for verifying heading of Modal Dialog
	@Test
	public void test_addSubTask_HeaderValidation()  {
		 
	QaTestHome  qaTestHome = new QaTestHome();
	qaTestHome.openPage(baseurl);

	qaTestHome.linkSignIn.click();
	ToDoAppSignIn toDoAppSignIn =new ToDoAppSignIn();
	toDoAppSignIn.signin(email,password);
	ToDoAppUserHome toDoAppUserHome =new ToDoAppUserHome();
	String taskname="task"+ToDoAppUserHome.generateCurrentDateTime();
	toDoAppUserHome.addTask(taskname);
	toDoAppUserHome.getTask(taskname);
	toDoAppUserHome.buttonManageTasks.get(toDoAppUserHome.taskindex).click();
	String header=toDoAppUserHome.headerManageSubTask.getText();
	assertTrue(header.contains(taskname),"Incorrect Task header");
	
	}
	
	//Test case for validating fields while adding tasks
	@Test(dataProvider="testdata")
	public void test_addTask_FieldValidation(String taskname)  {
	
	//navigating to "http://qa-test.avenuecode.com/	" value stored in .properties file.
	QaTestHome  qaTestHome = new QaTestHome();
	qaTestHome.openPage(baseurl);
	//login to the application using credentials given in properties file
	qaTestHome.linkSignIn.click();
	ToDoAppSignIn toDoAppSignIn =new ToDoAppSignIn();
	toDoAppSignIn.signin(email,password);
	ToDoAppUserHome toDoAppUserHome =new ToDoAppUserHome();
	
	//adding task
	toDoAppUserHome.addTask(taskname);
	toDoAppUserHome.getTask(taskname);
//	System.out.println(toDoAppUserHome.taskindex);
	String listname =toDoAppUserHome.linkToDo.get(toDoAppUserHome.taskindex).getText();
	assertEquals(listname,taskname);
	assertTrue((taskname.length()>2) && (taskname.length()<251),"Task Name should have minimum 3 characters or maximum 250 characters ");
	//checking the the taskname is present by user re-login
	toDoAppUserHome.signout();
	qaTestHome.linkSignIn.click();
	toDoAppUserHome.linkMyTasks.click();
	assertEquals(listname,taskname);
	
	}
	//Note :using hard coded values, can be implemented using excel, as of noe I don't have MS-Excel in my system.
	@DataProvider(name="testdata")
	public Object[] TestDataFeed(){

	 
	Object [] tasklist=new Object[10];

	tasklist[0]="ab";
	tasklist[1]="12345";
	tasklist[2]="!@#$%";
	tasklist[3]="task1234";
	tasklist[4]="    ";
	tasklist[5]="";
	tasklist[6]="abc";
	//character count: 252
	tasklist[7]="testingmorethan250letters_for_task_name_aaaaaaabbbbbbbbbbbbbbbbbbbbbbccccccccccccccjjjjjjjffffffffllllllllllwwwwwuuuuuuuwwwbbbjjjklllsssssssssssssssssssssssrrrrwbbbjjjklllsssssssssssssssssssssssrrrrcccccjjjjjjjffffffffllllllllllwwwwwuuuuuuuwwwbbbjjjkeee";
	//character count: 256
	tasklist[8]="testingmorethan250letters_for_task_name_aaaaaaabbbbbbbbbbbbbbbbbbbbbbccccccccccccccjjjjjjjffffffffllllllllllwwwwwuuuuuuuwwwbbbjjjklllsssssssssssssssssssssssrrrrwbbbjjjklllsssssssssssssssssssssssrrrrcccccjjjjjjjffffffffllllllllllwwwwwuuuuuuuwwwbbbjjjkeeerrtr";
	
	tasklist[9]="123ABCD";
	
	
	// return arrayobject to testscript
	return tasklist;
	}
	 
}

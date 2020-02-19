package homeworks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Sinatra {
	
	static WebDriver driver;

	public static void main(String[] args) {
		
		setUp ("chrome", "https://songs-by-sinatra.herokuapp.com");
		
		validarHomePage (); 
		
		contactMessage ();
		
		ingresarLogin ();
		
		crearSongs ();
		
		SearchSongs ();
		
		closeBrowser();
		
	}

	private static void contactMessage() {
		
		WebElement contact = driver.findElement(By.xpath("//a[ @title='Contact']"));
		contact.click();
		WebElement contactnameField = driver.findElement(By.name("name"));
		WebElement contactemailField = driver.findElement(By.name("email"));
		WebElement contactmessagesField = driver.findElement(By.name("message"));
		WebElement sendButton = driver.findElement(By.xpath("//input [@value='Send Message']"));
		
		contactnameField.sendKeys("test contact");
		contactemailField.sendKeys("Test@test.com.co");
		contactmessagesField.sendKeys("Mensaje de prueba automatizada");
		sendButton.click();	
		
		WebElement sendMessage = driver.findElement(By.id("flash"));
		if (sendMessage.getText().equals("Thank you for your message. We'll be in touch soon."))
			System.out.println("Test Contact Passed");
		else
			System.out.println("Test Contact Failed");
		
	}

	private static void ingresarLogin() {
		
		WebElement loginLink = driver.findElement(By.xpath("//a[@href='/login']"));
		loginLink.click();
		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.xpath("//input[@value = 'Log In']"));

		usernameField.sendKeys("frank");
		passwordField.sendKeys("sinatra");
		loginButton.click();

		WebElement loggedInMessage = driver.findElement(By.id("flash"));
		if (loggedInMessage.getText().equals("You are now logged in as frank"))
			System.out.println("Test Login Passed");
		else
			System.out.println("Test Login Failed");
		
	}
	
	private static void crearSongs() {
		
		WebElement songs = driver.findElement(By.xpath("//a[ @title='Songs']"));
		songs.click();
		WebElement newSong = driver.findElement(By.xpath("//a[@href='/songs/new']"));
		newSong.click();
		WebElement songtitleField = driver.findElement(By.id("title"));
		WebElement songlengthnameField = driver.findElement(By.id("length"));
		WebElement songdateField = driver.findElement(By.id("released_on"));
		WebElement songnlyricsField = driver.findElement(By.id("lyrics"));
		WebElement savesongButton = driver.findElement(By.xpath("//input [@value='Save Song']"));
		
		songtitleField.sendKeys("Siguiendo la luna");
		songlengthnameField.sendKeys("24");
		songdateField.sendKeys("02/18/1992");
		songnlyricsField.sendKeys("Siguiendo la luna no llegare lejos\r\n" + 
				"Tan lejos como se pueda llegar");
		savesongButton.click();
		
		WebElement saveSongMessage = driver.findElement(By.id("flash"));
		if (saveSongMessage.getText().equals("Song successfully added"))
			System.out.println("Test Save Song Passed");
		else
			System.out.println("Test Save Song Failed");
		
	}

	private static void SearchSongs() {
		
		WebElement songs = driver.findElement(By.xpath("//a[ @title='Songs']"));
		songs.click();
		WebElement search = driver.findElement(By.xpath("//a [@href='/songs/603']"));
		search.click();
		WebElement like = driver.findElement(By.id("like"));
		
		if (like.isDisplayed())
			System.out.println("Test SearchSongs Page Passed");
		else
			System.exit(-1);
		
	}


	private static void closeBrowser() {
		
		//driver.close();
		//driver.quit();
		
	}

	private static void validarHomePage() {
		WebElement logobysinatra = driver.findElement(By.xpath ("//h1 [text()='Songs By Sinatra']"));
		WebElement home = driver.findElement(By.xpath("//a[ @title='Home']"));
		WebElement about = driver.findElement(By.xpath("//a[ @title='About']"));
		WebElement contact = driver.findElement(By.xpath("//a[ @title='Contact']"));
		WebElement songs = driver.findElement(By.xpath("//a[ @title='Songs']"));
		WebElement messageswelcome = driver.findElement(By.xpath("//p"));
		WebElement img = driver.findElement(By.xpath("//img [@src='/images/sinatra.jpg']"));
		
		if (logobysinatra.isDisplayed() && home.isDisplayed() && about.isDisplayed()
				&& contact.isDisplayed() && songs.isDisplayed() && messageswelcome.isDisplayed()
				&& img.isDisplayed())
			System.out.println("Test Home Page Passed");
		else
			System.exit(-1);
		
	}

	private static void setUp(String browser, String url) {
		switch(browser) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			
			driver = new FirefoxDriver();
			break;
		case "ie":
			driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("Ese browser no existe");
			System.exit(-1);
		}
		driver.manage().timeouts().implicitlyWait(30,  TimeUnit.SECONDS);
		driver.get(url);
		
	}

}

package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass
{
public static WebDriver driver;
public Logger logger;
public Properties p;
	
	@BeforeClass (groups = {"sanity","regression","master"})
	@Parameters({"os","browser"})
	public void setup(@Optional("windows") String os, @Optional("chrome") String br) throws IOException
	{
	
		FileReader file = new FileReader("./src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		
		// Set the WebDriver executable path based on the operating system
		logger = LogManager.getLogger(this.getClass());
		
		//access remote WebDriver executable path from properties file
	if(p.getProperty("execution_environment").equalsIgnoreCase("remote"));
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//OS
			if(os.equalsIgnoreCase("windows"))
			{
				
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("Linux"))
			{
				
				capabilities.setPlatform(Platform.LINUX);
			}
			
			else if(os.equalsIgnoreCase("No maching OS"))
			{
				return;
			}
			
			//Browser
			 switch (br.toLowerCase())
			 {
			 case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			 case "chrome": capabilities.setBrowserName("chrome"); break;
			 default: System.out.println("Browser is not supported, please use Chrome or Edge"); 
				 return;
			 }
			 
			 //passing url
			 driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		 }
		
		//access local WebDriver executable path from properties file
		
		if(p.getProperty("execution_environment").equalsIgnoreCase("local"));
		{
			switch (br) {
			case "edge": driver = new EdgeDriver(); break;
			case "chrome": driver  = new ChromeDriver(); break;
			default: System.out.println("Browser is not supported, please use Chrome or Edge"); return;
		} 
		// Initialize WebDriver and navigate to the home page
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL")); //reading the URL from properties file
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
}
	
	@AfterClass (groups = {"sanity","regression","master"})
	public void tearDown()
	{
		// Close the WebDriver instance
		driver.quit();
	}
	
	//This method generates a random lowercase alphabetic string of the specified length
		public String randomString() 
		{
		    int leftLimit = 'a';
		    int rightLimit = 'z';
		    Random random = new Random();
		    return random.ints(leftLimit, rightLimit + 1)
		                 .limit(5) // Specify the length of the string
		                 .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		                 .toString();
		}
		
		public String randomNumber() {
	    Random random = new Random();
	    int leftLimit = '0';
	    int rightLimit = '9';
	    return random.ints(leftLimit, rightLimit + 1)
	                 .limit(9)
	                 .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	                 .toString();
	}
		public String captureScreenShot(String name) 
		{
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
			
			TakesScreenshot ts =  (TakesScreenshot)driver;
			File sourcefile = ts.getScreenshotAs(OutputType.FILE);
			
			String destinationPath = System.getProperty("user.dir") + "/test-output/screenshots/" + name+"_" + timestamp + ".png";
			File destinationFile = new File(destinationPath);
			
			sourcefile.renameTo(destinationFile);
			
			return destinationPath;
		

		}
}

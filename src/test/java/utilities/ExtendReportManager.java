package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testBase.BaseClass;

public class ExtendReportManager implements ITestListener
{
	private static ITestContext textContext = null;
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	@Override
	public void onStart(ITestContext testContext) 
	{
		ExtendReportManager.textContext = testContext; // Assign the context before using it
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName = "Test-Report-" + timestamp + ".html";
		sparkreporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName);
		sparkreporter.config().setDocumentTitle("ParaBank Automation Report");
		sparkreporter.config().setReportName("Functional Testing");
		sparkreporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Application", "ParaBank");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customer");
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = textContext.getCurrentXmlTest().getParameter("os"); //for testNG parameterization
		extent.setSystemInfo("Operating System", os);
		
		String browser = textContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Operating System", browser);

		List<String> includeGroups = textContext.getCurrentXmlTest().getIncludedGroups(); //used for testNG grouping concet
		if (includeGroups != null && !includeGroups.isEmpty()) 
		{
			
			extent.setSystemInfo("Included Groups", includeGroups.toString()); 
		}
		
	}
		@Override
		public void onTestSuccess(ITestResult result) //pass
		{
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.PASS, result.getName()+ " is Passed");
		}
		@Override
		public void onTestFailure(ITestResult result) //failed
		{
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			
			test.log(Status.FAIL, result.getName() + " is Failed");
			test.log(Status.FAIL, result.getThrowable().getMessage());
			
			try
			{
				String imgpath = new BaseClass().captureScreenShot(result.getName());
				test.addScreenCaptureFromPath(imgpath);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		@Override
			public void onTestSkipped(ITestResult result) //skipped
			{
				test = extent.createTest(result.getTestClass().getName());
				test.assignCategory(result.getMethod().getGroups());
				test.log(Status.SKIP, result.getName() + " is Skipped");
				test.log(Status.INFO, result.getThrowable().getMessage());
				
			}
		
		@Override
			public void onFinish(ITestContext testContext) //end
			{
				extent.flush();
				
				String pathOfExtentReport = System.getProperty("user.dir") + "/test-output/" + repName;
				File extentReportFile = new File(pathOfExtentReport);
				
				try {
					Desktop.getDesktop().browse(extentReportFile.toURI());
					} catch (IOException e) {
						e.printStackTrace();
				}
			}
		
	
	
}


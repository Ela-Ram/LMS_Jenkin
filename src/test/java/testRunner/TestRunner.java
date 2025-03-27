package testRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
	    features = "src/test/resources/features", // Path to your feature files
	    tags =  "@login or @class",
	    glue = "stepDefinitions", // Package where your step definitions are located
	    		plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", //Allure Report
	    				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", //Extent Report
	    				"json:target/CucumberReports/LMS_API.json","html:target/CucumberReports/LMS.html", //Cucumber Report
	    				"com.aventstack.chaintest.plugins.ChainTestCucumberListener:", //ChainTest Report
	    				},
	    monochrome = true // Clean console output
	)
	public class TestRunner extends AbstractTestNGCucumberTests {
	    // This class doesn't need any code; it's just a configuration class.
	}

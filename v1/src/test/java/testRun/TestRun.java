package testRun;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {".//Features/AddMonitorToCart.feature"},
		glue= {"stepsDefinitions"},
		dryRun=false,
		plugin={"pretty",
				"html:target/cucumber-reports/cucumber.html",
				"json:target/cucumber-reports/cucumber.json"
		},
		monochrome=true
		
		
		
		)


public class TestRun extends AbstractTestNGCucumberTests{


}

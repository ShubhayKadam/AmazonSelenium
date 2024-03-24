package testRun;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class MainRunner {

	public static void main(String[] args) {
		TestNG ngt = new TestNG();
		
		List<String> suites= new ArrayList<String>();
		suites.add("C:\\Users\\Shubhay\\git\\AmazonSelenium1\\v1\\testng.xml");
		ngt.setTestSuites(suites);

		ngt.run();
	}

}

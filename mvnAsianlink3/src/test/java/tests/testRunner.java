package tests;

import org.testng.TestNG;
import utils.PdfReportGenerator;

public class testRunner {
	public static void main(String[] args) {
		TestNG testng = new TestNG();
		testng.setTestClasses(
				new Class[] { 
						AppStoreTest.class, 
						PlayStoreTest.class, 
						AboutPageTest.class, 
						JoinOourCommunityTest.class,
						HowtoGetInvolvedTest.class
						}
				);
		testng.run();

		PdfReportGenerator.generatePDFReport();
	}
}

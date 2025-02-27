package tests;

import org.testng.TestNG;
import java.util.Arrays;

public class testRunner {
	 public static void main(String[] args) {
	        TestNG testng = new TestNG();
	        testng.setTestClasses(new Class[] { appStoreTest.class, playStoreTest.class });
	        testng.run();
	    }
}

package tests;
import org.testng.TestNG;


public class testRunner {
	 public static void main(String[] args) {
	        TestNG testng = new TestNG();
	        testng.setTestClasses(new Class[] { 
	        		appStoreTest.class, 
	        		playStoreTest.class, 
	        		aboutPage.class
	        });
	        
	        testng.run();
	    }
}

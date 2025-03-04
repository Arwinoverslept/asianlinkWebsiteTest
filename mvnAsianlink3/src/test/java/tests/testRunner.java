package tests;

import org.testng.TestNG;
import utils.PdfReportGenerator; // Import the new class

public class testRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[]{
                appStoreTest.class,
                playStoreTest.class,
                aboutPage.class,
                joinOurCommunity.class
        });
        testng.run();

        PdfReportGenerator.generatePDFReport();
    }
}

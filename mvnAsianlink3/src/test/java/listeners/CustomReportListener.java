package listeners;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.Reporter;
import java.io.*;

public class CustomReportListener extends TestListenerAdapter {
    private PrintWriter writer;

    @Override
    public void onStart(ITestContext testContext) {
        try {
            writer = new PrintWriter(new FileWriter("test-output/command-line-report.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        writeLog(tr, "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        writeLog(tr, "FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        writeLog(tr, "SKIPPED");
    }

    private void writeLog(ITestResult tr, String status) {
        writer.println("TEST: " + tr.getName() + " - " + status);
        for (String log : Reporter.getOutput(tr)) {
            writer.println(log);
        }
        writer.println("-------------------------------------------------");
        writer.flush();
    }

    @Override
    public void onFinish(ITestContext testContext) {
        writer.close();
    }
    
}

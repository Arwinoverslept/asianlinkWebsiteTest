package utils;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PdfReportGenerator {
    public static void generatePDFReport() {
        try {
          
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String htmlFilePath = "test-output/Command line suite/Command line test.html";
            String outputFolder = "test-output/reports/";
            String outputFile = outputFolder + "TestReport_" + timestamp + ".pdf";

            
            new File(outputFolder).mkdirs();

            
            File htmlFile = new File(htmlFilePath);
            if (!htmlFile.exists()) {
                System.out.println("⚠️ HTML report not found: " + htmlFilePath);
                return;
            }

            
            org.jsoup.nodes.Document htmlDoc = Jsoup.parse(htmlFile, "UTF-8");

            
            Elements testRows = htmlDoc.select("tr");
            List<String> reportLogs = new ArrayList<>();

            for (Element row : testRows) {
                Elements columns = row.select("td");

                
                if (columns.size() >= 2 && isValidTestRow(columns)) {
                    String rawTestData = columns.get(0).text().trim();
                    String testStatus = columns.get(1).text().trim();

                    
                    String testName = extractTestName(rawTestData);

                    
                    List<String> logs = extractReporterLogs(row);

                    
                    if (testStatus.isEmpty()) {
                        testStatus = inferTestStatus(String.join(" ", logs));
                    }

                    
                    reportLogs.add("TEST: " + testName);
                    reportLogs.add("Status: " + testStatus.toUpperCase());
                    reportLogs.add("Logs:");

                    
                    for (String log : logs) {
                        reportLogs.add(log);
                    }

                    reportLogs.add("-------------------------------------------------");
                }
            }

            
            if (reportLogs.isEmpty()) {
                reportLogs.add("No valid test cases found.");
            }

            
            try (PdfWriter writer = new PdfWriter(outputFile);
                 PdfDocument pdfDoc = new PdfDocument(writer);
                 Document document = new Document(pdfDoc)) {

                
                for (String line : reportLogs) {
                    document.add(new Paragraph(line));
                }

                System.out.println("PDF Report Created: " + outputFile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private static boolean isValidTestRow(Elements columns) {
        String testName = columns.get(0).text().trim().toLowerCase();
        return !(testName.contains("tests passed") || testName.contains("started on") ||
                 testName.contains("total time") || testName.contains("included groups") ||
                 testName.contains("excluded groups") || testName.contains("test method"));
    }

    
    private static String inferTestStatus(String logs) {
        if (logs.toLowerCase().contains("error") || logs.toLowerCase().contains("failed") ||
            logs.toLowerCase().contains("exception") || logs.toLowerCase().contains("not found")) {
            return "FAILED";
        }
        return "PASSED";
    }

    
    private static String extractTestName(String rawData) {
        if (rawData.contains("Test class:")) {
            return rawData.substring(0, rawData.indexOf("Test class:")).trim();
        }
        return rawData;
    }

    
    private static List<String> extractReporterLogs(Element row) {
        List<String> logs = new ArrayList<>();

        
        Element logContainer = row.selectFirst("div.log");

        if (logContainer != null) {
            
            String logText = logContainer.html().trim();

            
            if (!logText.isEmpty()) {
                
                String[] logLines = logText.split("<br>");

                
                for (String logLine : logLines) {
                    if (!logLine.trim().isEmpty()) {
                        logs.add("- " + logLine.trim());
                    }
                }
            }
        } else {
            System.out.println("No log container found for this row.");
        }

       
        if (logs.isEmpty()) {
            logs.add("No logs available");
        }

        return logs;
    }
}

package utils;


import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

    private static ExtentReports extent;
    private ClassLoader classLoader;


    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            extent = new ExtentReports("Automation_Test_Results.html", true);
        }

        return extent;
    }
}

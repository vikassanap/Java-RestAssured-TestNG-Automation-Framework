package com.project.qa.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

/**
 * @author : Vikas S.
 * @since : 10-08-2019, Sat
 **/

public class CustomTestNGListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomTestNGListener.class);

    // This belongs to ISuiteListener and will execute before the Suite start
    public void onStart(ISuite arg0) {
        LOGGER.info("About to begin executing Suite " + arg0.getName(), true);
    }

    // This belongs to ISuiteListener and will execute, once the Suite is finished
    public void onFinish(ISuite arg0) {
        LOGGER.info("About to end executing Suite " + arg0.getName(), true);
    }

    // This belongs to ITestListener and will execute before starting of Test set/batch
    public void onStart(ITestContext arg0) {
        LOGGER.info("About to begin executing Test " + arg0.getName(), true);
    }

    // This belongs to ITestListener and will execute, once the Test set/batch is finished
    public void onFinish(ITestContext arg0) {
        LOGGER.info("Completed executing test " + arg0.getName(), true);
    }

    // This belongs to ITestListener and will execute only when the test is pass
    public void onTestSuccess(ITestResult arg0) {
        LOGGER.info("On test success {}", arg0.getMethod().toString());
    }

    // This belongs to ITestListener and will execute only on the event of fail test
    public void onTestFailure(ITestResult arg0) {
        LOGGER.error("On test failure {}", arg0.getMethod().toString());
    }

    // This belongs to ITestListener and will execute before the main test start (@Test)
    public void onTestStart(ITestResult arg0) {
        LOGGER.info("The execution of the main test starts now");
    }

    // This belongs to ITestListener and will execute only if any of the main test(@Test) get skipped
    public void onTestSkipped(ITestResult arg0) {
        LOGGER.warn("On test skipped {}", arg0.getMethod().toString());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

    }

    // This is the method which will be executed in case of test pass or fail
    // This will provide the information on the test
    private void printTestResults(ITestResult result) {
        LOGGER.info("Test Method resides in " + result.getTestClass().getName(), true);
        if (result.getParameters().length != 0) {
            String params = null;
            for (Object parameter : result.getParameters()) {
                params += parameter.toString() + ",";
            }
            LOGGER.info("Test Method had the following parameters : " + params, true);
        }

        String status = null;
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                status = "Pass";
                break;
            case ITestResult.FAILURE:
                status = "Failed";
                break;
            case ITestResult.SKIP:
                status = "Skipped";
        }
        LOGGER.info("Test Status: " + status, true);
    }

    // This belongs to IInvokedMethodListener and will execute before every method including @Before @After @Test
    public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
        String textMsg = "About to begin executing following method : " + returnMethodName(arg0.getTestMethod());
        LOGGER.info(textMsg, true);
    }

    // This belongs to IInvokedMethodListener and will execute after every method including @Before @After @Test
    public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
        String textMsg = "Completed executing following method : " + returnMethodName(arg0.getTestMethod());
        LOGGER.info(textMsg, true);
    }

    // This will return method names to the calling function
    private String returnMethodName(ITestNGMethod method) {
        return method.getRealClass().getSimpleName() + "." + method.getMethodName();
    }
}

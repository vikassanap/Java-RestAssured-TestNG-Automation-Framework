package com.project.qa.api.apis;
/**
 * @author : Vikas S.
 * @since : 10-08-2019, Sat
 **/

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.io.output.WriterOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * This class contains basic api operations
 */
public abstract class BaseAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAPI.class);
    public static StringWriter requestWriter;
    public static PrintStream requestCapture;
    public static StringWriter responseWriter;
    public static PrintStream responseCapture;
    protected String baseURI;
    protected RequestSpecBuilder requestSpecBuilder;
    protected RequestSpecification requestSpecification;
    protected ResponseSpecBuilder responseSpecBuilder;
    protected ResponseSpecification responseSpecification;
    protected Response apiResponse;
    protected int expectedStatusCode;

    public BaseAPI(String baseURI) {
        this.baseURI = baseURI;
        requestSpecBuilder = new RequestSpecBuilder();
        responseSpecBuilder = new ResponseSpecBuilder();
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true);
        responseWriter = new StringWriter();
        responseCapture = new PrintStream(new WriterOutputStream(responseWriter), true);
        requestSpecBuilder.addFilter(new RequestLoggingFilter(requestCapture));
        requestSpecBuilder.addFilter(new ResponseLoggingFilter(responseCapture));
    }

    /**
     * Method to return response object
     *
     * @return response object
     */
    protected Response getApiResponse() {
        return apiResponse;
    }

    /**
     * Method to return response as a string
     *
     * @return response in string format
     */
    public String getApiResponseAsString() {
        return apiResponse.asString();
    }

    /**
     * Method to return response in object
     *
     * @param type
     * @param <T>
     * @return object
     * @throws IOException
     */
    public <T> T getAPIResponseAsPOJO(Class<T> type) throws IOException {
        LOGGER.info("mapping api response to Pojo object");
        return new ObjectMapper().readValue(getApiResponseAsString(), type);
    }

    /**
     * Method to get expected status code
     *
     * @return status code
     */
    public int getExpectedStatusCode() {
        return expectedStatusCode;
    }

    /**
     * Method to set expected status code value
     *
     * @param expectedStatusCode
     */
    public void setExpectedStatusCode(int expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode;
    }

    /**
     * Method to get response time
     *
     * @return response time
     */
    public long getResponseTime() {
        return this.apiResponse.timeIn(MILLISECONDS);
    }

    /**
     * Method to create request
     *
     * @throws Exception
     */
    protected abstract void createRequest() throws Exception;

    /**
     * Method to execute request
     */
    protected abstract void executeRequest();

    /**
     * Method to validate response
     */
    protected abstract void validateResponse();

    /**
     * Method to perform create, execute and validate operations
     *
     * @throws Exception
     */
    public void perform() throws Exception {
        LOGGER.info("api call.....initiated");
        createRequest();
        executeRequest();
        Allure.addAttachment("Request", requestWriter.toString());
        Allure.addAttachment("Response", responseWriter.toString());
        LOGGER.info("Request:\n{}", requestWriter.toString());
        LOGGER.info("Response:\n{}", responseWriter.toString());
        LOGGER.info("api call.....completed");
        LOGGER.info("response validation.....initiated");
        validateResponse();
        LOGGER.info("response validation.....completed");
    }
}

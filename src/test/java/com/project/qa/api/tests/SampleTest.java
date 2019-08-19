package com.project.qa.api.tests;
/**
 * @author : Vikas S.
 * @since : 10-08-2019, Sat
 **/

import com.project.qa.api.utils.CustomTestNGListener;
import com.project.qa.api.readers.YAMLReader;
import com.project.qa.api.rest.RestClient;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/**
 * @author : Vikas S.
 * @since : 10-08-2019, Sat
 **/
@Listeners(CustomTestNGListener.class)
public class SampleTest extends RestClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleTest.class);
    private YAMLReader yamlReader;
    private Map<String, Object> testdata;

    @BeforeClass(description = "load test data from yaml file")
    public void setUp() {
        yamlReader = new YAMLReader();
        testdata = yamlReader.getYamlMaps();
    }

    @Test(enabled = true)
    public void getWeatherDetails() {
        List<Map<String, Object>> getWeatherDetails = getServiceData("GetWeatherDetails", testdata);
        String url = getURL(getWeatherDetails);
        Map<String, String> headers = getHeader(getWeatherDetails);
        int expectedStatusCode = getStatusCode(getWeatherDetails);
        Map<String, String> assertions = getAssertion(getWeatherDetails);

        Response response = get(url, headers);

        assertEquals(expectedStatusCode, response.getStatusCode(), "status code mismatch");
        assertTrue(responseEquals(assertions, response), "response assertions check failure");
    }
}

package com.project.qa.api.base;
/**
 * @author : Vikas S.
 * @since : 10-08-2019, Sat
 **/

import com.project.qa.api.apis.Login;
import com.project.qa.api.pojos.LoginRequest;
import com.project.qa.api.pojos.LoginResponse;
import com.project.qa.api.utils.HeaderConstants;
import com.project.qa.api.utils.StatusCodes;
import com.project.qa.api.readers.YAMLReader;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import java.util.Map;

public class BaseTest {
    protected String accessToken;
    protected Map<String, Object> testdata;
    protected String appBaseURI;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws Exception {
        YAMLReader yamlReader = new YAMLReader();
        testdata = yamlReader.getYamlMaps();
        appBaseURI = (String) testdata.get("app_uri");
        String email = (String) testdata.get("valid_user_email");
        String password = (String) testdata.get("valid_user_password");
        Login login = new Login(appBaseURI);
        login.setContentType(HeaderConstants.CONTENT_TYPE_JSON);
        login.setExpectedStatusCode(StatusCodes.OK);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        login.setLoginRequest(loginRequest);
        login.perform();

        LoginResponse loginResponse = login.getAPIResponseAsPOJO(LoginResponse.class);
        accessToken = loginResponse.getToken();

        Assert.assertTrue(accessToken.length() == 17, "access token length is less than expected");
    }
}

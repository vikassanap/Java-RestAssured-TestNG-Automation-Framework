package com.project.qa.api.apis;
/**
 * @author : Vikas S.
 * @since : 10-08-2019, Sat
 **/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.project.qa.api.pojos.LoginRequest;
import com.project.qa.api.utils.Constants;

import static io.restassured.RestAssured.given;

public class Login extends BaseAPI {
    private String apiPath = Constants.LOGIN_PATH;
    private String contentType;
    private String token;
    private ObjectMapper objectMapper;
    private LoginRequest loginRequest;

    public Login(String baseURI) {
        super(baseURI);
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    protected void createRequest() throws Exception {
        requestSpecBuilder.setBaseUri(baseURI);
        requestSpecBuilder.setBasePath(apiPath);
        requestSpecBuilder.setContentType(contentType);
        requestSpecBuilder.setBody(objectMapper.writeValueAsString(loginRequest));
        requestSpecification = requestSpecBuilder.build();
    }

    protected void executeRequest() {
        apiResponse = given().spec(requestSpecification).post();
    }

    protected void validateResponse() {
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecification = responseSpecBuilder.build();
        apiResponse.then().spec(responseSpecification);
    }
}

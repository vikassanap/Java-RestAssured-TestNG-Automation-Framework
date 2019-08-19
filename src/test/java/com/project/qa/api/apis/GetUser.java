package com.project.qa.api.apis;
/**
 * @author : Vikas S.
 * @since : 10-08-2019, Sat
 **/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.project.qa.api.utils.Constants;

import static io.restassured.RestAssured.given;

public class GetUser extends BaseAPI {
    private String apiPath = Constants.LIST_USERS_PATH + "/{userId}";
    private String contentType;
    private ObjectMapper objectMapper;
    private int userId;

    public GetUser(String baseURI) {
        super(baseURI);
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    protected void createRequest() throws Exception {
        requestSpecBuilder.setBaseUri(baseURI);
        requestSpecBuilder.setBasePath(apiPath);
        requestSpecBuilder.addPathParam("userId", userId);
        requestSpecBuilder.setContentType(contentType);
        requestSpecification = requestSpecBuilder.build();
    }

    protected void executeRequest() {
        apiResponse = given().spec(requestSpecification).get();
    }

    protected void validateResponse() {
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecification = responseSpecBuilder.build();
        apiResponse.then().spec(responseSpecification);
    }
}
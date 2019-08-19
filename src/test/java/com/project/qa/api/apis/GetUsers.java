package com.project.qa.api.apis;
/**
 * @author : Vikas S.
 * @since : 10-08-2019, Sat
 **/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.project.qa.api.utils.Constants;

import static io.restassured.RestAssured.given;

public class GetUsers extends BaseAPI {
    private String apiPath = Constants.LIST_USERS_PATH;
    private String contentType;
    private ObjectMapper objectMapper;
    private long pageNumber;
    private long delay;

    public GetUsers(String baseURI) {
        super(baseURI);
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
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
        if (pageNumber > 0L) requestSpecBuilder.addQueryParam("page", pageNumber);
        if (delay > 0L) requestSpecBuilder.addQueryParam("delay", delay);
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
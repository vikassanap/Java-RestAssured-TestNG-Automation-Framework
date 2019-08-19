package com.project.qa.api.pojos;
/**
 * @author : Vikas S.
 * @since : 10-08-2019, Sat
 **/

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "page",
        "per_page",
        "total",
        "total_pages",
        "data"
})
public class Users {

    @JsonProperty("page")
    private long page;
    @JsonProperty("per_page")
    private long perPage;
    @JsonProperty("total")
    private long total;
    @JsonProperty("total_pages")
    private long totalPages;
    @JsonProperty("data")
    private List<Data> data = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("page")
    public long getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(long page) {
        this.page = page;
    }

    @JsonProperty("per_page")
    public long getPerPage() {
        return perPage;
    }

    @JsonProperty("per_page")
    public void setPerPage(long perPage) {
        this.perPage = perPage;
    }

    @JsonProperty("total")
    public long getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(long total) {
        this.total = total;
    }

    @JsonProperty("total_pages")
    public long getTotalPages() {
        return totalPages;
    }

    @JsonProperty("total_pages")
    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    @JsonProperty("data")
    public List<Data> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Data> data) {
        this.data = data;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "page" + page + "perPage" + perPage + "total" + total + "totalPages" + totalPages +
                "data" + data + "additionalProperties" + additionalProperties;
    }

}
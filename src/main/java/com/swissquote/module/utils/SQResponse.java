package com.swissquote.module.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SQResponse {
    private String errorMesasge;
    private Object response;

    public SQResponse(Object response) {
        this.response = response;
    }

    public SQResponse(String errorMesasge) {
        this.errorMesasge = errorMesasge;
    }

    public String getErrorMesasge() {
        return errorMesasge;
    }

    public void setErrorMesasge(String errorMesasge) {
        this.errorMesasge = errorMesasge;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SQResponse)) return false;

        SQResponse that = (SQResponse) o;

        return new EqualsBuilder()
                .append(errorMesasge, that.errorMesasge)
                .append(response, that.response)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(errorMesasge)
                .append(response)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("errorMesasge", errorMesasge)
                .append("response", response)
                .toString();
    }
}

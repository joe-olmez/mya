package com.olmez.mya.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseTestRestController {

    @Autowired
    private MockMvc mockMvc;

    public void doMockRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    /**
     * 
     * @param path        example: "/api/v1/users"
     * @param requestType example HttpMethod.GET
     * @return MvcResult
     * @throws Exception
     */
    public MvcResult doMockRequest(String path, HttpMethod requestType) throws Exception {
        if (requestType == HttpMethod.GET) {
            return mockRequestGET(path);
        } else if (requestType == HttpMethod.POST) {
            return mockRequestPOST(path);
        } else if (requestType == HttpMethod.PUT) {
            return mockRequestPUT(path);
        } else if (requestType == HttpMethod.DELETE) {
            return mockRequestDELETE(path);
        }
        return null;
    }

    private MvcResult mockRequestGET(String path) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(path);
        return getResult(builder);
    }

    public MvcResult mockRequestPOST(String path) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(path);
        return getResult(builder);
    }

    public MvcResult mockRequestPUT(String path) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(path);
        return getResult(builder);
    }

    public MvcResult mockRequestDELETE(String path) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete(path);
        return getResult(builder);
    }

    private MvcResult getResult(MockHttpServletRequestBuilder builder) throws Exception {
        builder.contentType(MediaType.APPLICATION_JSON);
        return mockMvc.perform(builder).andReturn();
    }

    public int getResponseStatus(MvcResult result) {
        return result.getResponse().getStatus();
    }

    public String getResponseAsString(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

}

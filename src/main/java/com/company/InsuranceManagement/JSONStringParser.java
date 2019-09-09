package com.company.InsuranceManagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-12
 * Time: 15:38
 */

public class JSONStringParser {
    public static String asJsonString(final Object obj) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(obj);
        return jsonContent;
    }
}
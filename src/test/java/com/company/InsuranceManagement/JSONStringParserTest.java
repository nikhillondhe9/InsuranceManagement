package com.company.InsuranceManagement;

import com.company.InsuranceManagement.Models.Policy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.company.InsuranceManagement.JSONStringParser.asJsonString;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class JSONStringParserTest {

    private static final Policy policy = new Policy(1L, "Motor Insurance");

    @Test
    public void asJsonString_willReturnStringJSON() throws Exception {

        String result = asJsonString(policy);

        assertThat(result).isEqualTo("{\"policyNo\":1,\"policyName\":\"Motor Insurance\"}");
    }
}
package com.company.InsuranceManagement;

import com.company.InsuranceManagement.Models.Policy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InsuranceManagementApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getPolicy_willReturnPolicy() throws Exception {

	}

}

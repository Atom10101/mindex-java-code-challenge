package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String createCompensationUrl;
    private String readCompensationUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationService compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        createCompensationUrl = "http://localhost:" + port + "/compensation";
        readCompensationUrl = "http://localhost:" + port + "/compensation/{id}";
    }


    @Test
    public void testCreateReadUpdate() {
        Employee employeeExample = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        Compensation compensationExample = new Compensation();
        compensationExample.setEmployee(employeeExample);
        compensationExample.setSalary("100,000,000");
        compensationExample.setEffectiveDate(Instant.parse("2018-11-30T18:35:24.00Z"));

        // Create checks
        ResponseEntity createResponse = restTemplate.postForEntity(createCompensationUrl, compensationExample, Compensation.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        Compensation createCompensation = (Compensation)createResponse.getBody();
        assertNotNull(createCompensation);
        assertEquals(compensationExample, createCompensation);

        // Read checks
        ResponseEntity readResponse = restTemplate.getForEntity(readCompensationUrl, Compensation.class, createCompensation.getEmployee().getEmployeeId());
        assertEquals(HttpStatus.OK, readResponse.getStatusCode());
        Compensation readCompensation = (Compensation)readResponse.getBody();
        assertNotNull(readCompensation);
        assertEquals(readCompensation, createCompensation);
    }

}

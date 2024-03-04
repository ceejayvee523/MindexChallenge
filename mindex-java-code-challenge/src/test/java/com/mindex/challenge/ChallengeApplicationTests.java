package com.mindex.challenge;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.impl.CompensationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/*
The Spring Boot application needs to already be running for these test
I am unsure how to use TestRestTemplate, which is what I assume EmployeeServiceImplTest.java uses to simulate the Spring Boot application,
so please run gradlew bootRun before running these tests
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeApplicationTests {

	@Autowired
	private CompensationService compensationService;

	@Test
	public void contextLoads() throws IOException{
		//Reporting Structure Tests
		ReportingStructure report = new ReportingStructure("16a596ae-edd3-4847-99fe-c4518e82c86f"); //this is the id of John Lennon
		assertEquals(4, report.getNumberOfReports());

		//Compensation Tests
		Compensation c = compensationService.create("16a596ae-edd3-4847-99fe-c4518e82c86f", 90000, "8/22/2024");
		assertEquals(90000, c.getSalary());
		assertEquals("8/22/2024", c.getEffectiveDate());
	}
}



package org.rbernalop.pruebatecnicagft.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegrationTest {
  private static final String FILES_BASE_PATH = "src/test/resources/body_samples/";

  @Autowired
  protected ObjectMapper objectMapper;

  @Autowired
  protected CucumberContext context;

  @Autowired
  protected MockMvc mockMvc;

  protected String getContentByFileName(String fileName) throws IOException {
    return Files.readString(Paths.get(FILES_BASE_PATH + fileName));
  }

  @When("^a call is made to (.+) endpoint with (.+) method$")
  public void makeHttpRequest(String path, String method) throws Exception {
    HttpMethod httpMethod = HttpMethod.valueOf(method);
    MvcResult serverResponse = mockMvc.perform(request(httpMethod, path)).andReturn();
    context.setResponseEntity(serverResponse);
  }

  @Then("^the response status code is (\\d+)$")
  public void checkHttpStatus(int expectedStatusCode) {
    MvcResult serverResponse = context.getResponseEntity();
    final int actualStatusCode = serverResponse.getResponse().getStatus();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @And("^response contains data from file (.+)$")
  public void responseContainsData(String fileName) throws Exception {
    String expectedContent = getContentByFileName(fileName);
    String actualContent = context.getResponseEntity().getResponse().getContentAsString();

    JSONObject expectedJSON = new JSONObject(expectedContent);
    JSONObject actualJSON = new JSONObject(actualContent);

    JSONAssert.assertEquals(expectedJSON, actualJSON, false);
  }
}

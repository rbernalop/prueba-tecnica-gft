package org.rbernalop.pruebatecnicagft.it;

import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;

@ScenarioScope
@Component
@Data
public class CucumberContext {
  private MvcResult responseEntity;
}

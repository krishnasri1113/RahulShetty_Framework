package Chrome.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/Features",glue = {"StepDefinations"},plugin = "json:target/jsonReports/cucumber-report.json")
public class TestRunner {
	//tags = "@DeletePlace"
	//To run  particular tag through command propmp is mvn test -Dcucumber.options="--tags @AddPlace"
}

package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features ="src/test/java/features",
		glue ="stepDefinations",
		plugin = {"pretty", "summary","json:target/cucumber.json"}, strict = true, snippets = SnippetType.CAMELCASE,
		dryRun= false,
		monochrome=true
		
		
		//tags= {"@DeletePlace"}
		)
public class TestRunner {

}

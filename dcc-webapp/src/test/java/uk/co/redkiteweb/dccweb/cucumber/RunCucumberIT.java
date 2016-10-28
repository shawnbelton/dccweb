package uk.co.redkiteweb.dccweb.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by shawn on 15/06/15.
 */
@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber"}, monochrome = true)
public class RunCucumberIT {
}

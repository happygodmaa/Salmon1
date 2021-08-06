package com.staples.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;



@CucumberOptions(features = "src/test/resources/features", tags = {"@registration"},
    monochrome = true,
    // plugin = {"json:target/cucumber-report/runwebat/cucumber.json",
    // "rerun:target/cucumber-report/runwebat/rerun.txt"},
    glue = {"com.staples.test.step_definitions"}, dryRun = false, strict = true)
public class RunWebATSuite extends AbstractTestNGCucumberTests {
}

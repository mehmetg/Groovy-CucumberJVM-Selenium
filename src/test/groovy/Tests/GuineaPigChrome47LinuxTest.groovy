package Tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith;


/**
 * Created by mehmetgerceker on 9/21/15.
 */
@RunWith(Cucumber)
@CucumberOptions(
        //format = ["pretty", "html:build/reports/cucumber"],
        strict = true,
        features = ["src/test/groovy/cucumber/features/form_chrome_47_linux.feature"],
        glue = ["src/test/groovy/cucumber/steps", "src/test/groovy/cucumber/support"],
        tags = []
)
class GuineaPigChrome47LinuxTest {

}

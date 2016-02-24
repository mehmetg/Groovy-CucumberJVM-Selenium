import Utilities.WebDriverFactory;

import static cucumber.api.groovy.Hooks.*

Before(){
    //wd = WebDriverFactory.getSauceDriver(it.scenarioId);
    scenarioId = it.scenarioId;
}

After(){
    String sessionId = wd.getSessionId();
    ArrayList results = it.stepResults;
    WebDriverFactory.markPassFail(sessionId, results);
    wd.quit();
}
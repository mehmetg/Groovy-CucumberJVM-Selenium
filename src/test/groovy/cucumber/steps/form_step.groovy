package cucumber.steps;

import Pages.GuineaPigPage
import Utilities.WebDriverFactory;

/**
 * Created by mehmetgerceker on 9/21/15.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^browser is ([^"]*) ([^"]*) on ([^"]*)$/) { String browser , String version, String os->
    // Write code here that turns the phrase above into concrete actions
    wd = WebDriverFactory.getSauceDriver(browser, version, os, scenarioId);

}

Given(~/^page url is "([^"]*)"$/) { String url ->
    // Write code here that turns the phrase above into concrete actions
    gpp = GuineaPigPage.navigateTo(wd, url);
}

When(~/^unchecked checkbox is clicked$/) { ->
    // Write code here that turns the phrase above into concrete actions
    gpp.checkUncheckedCheckBox();
}

Then(~/^unchecked checkbox selected state should be "([^"]*)"$/) { String selectedState ->
    // Write code here that turns the phrase above into concrete actions
    assert(gpp.getUncheckedCheckBoxState().toString() == selectedState);
}

When(~/^checked checkbox is clicked$/) { ->
    // Write code here that turns the phrase above into concrete actions
    gpp.uncheckCheckedCheckBox();
}

Then(~/^checked checkbox selected state should be "([^"]*)"$/) { String selectedState ->
    // Write code here that turns the phrase above into concrete actions
    assert(gpp.getCheckedCheckBoxState().toString() == selectedState);
}

When(~/^email is entered as "([^"]*)"$/) { String email ->
    // Write code here that turns the phrase above into concrete actions
    gpp.enterEmailText(email);
}

Then(~/^email should show "([^"]*)"$/) { String email ->
    // Write code here that turns the phrase above into concrete actions
    emailText = gpp.getEmailText()
    assert(emailText == email);
}

Given(~/^comments are entered as "([^"]*)"$/) { String comments ->
    // Write code here that turns the phrase above into concrete actions
    gpp.setCommentText(comments);
}

When(~/^form is submitted$/) { ->
    // Write code here that turns the phrase above into concrete actions
    gpp.submitForm();
}

Then(~/^your comments section should end with "([^"]*)"$/) { String comments ->
    // Write code here that turns the phrase above into concrete actions
    assert(gpp.getSubmittedCommentText().endsWith(comments));
}

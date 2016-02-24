Feature: Form

  Scenario: Check unchecked box
    Given browser is firefox 44 on Windows 7
    Given page url is "https://saucelabs.com/test/guinea-pig"
    When unchecked checkbox is clicked
    Then unchecked checkbox selected state should be "true"

  Scenario: Uncheck checked box
    Given browser is firefox 44 on Windows 7
    Given page url is "https://saucelabs.com/test/guinea-pig"
    When checked checkbox is clicked
    Then checked checkbox selected state should be "false"

  Scenario: Enter email
    Given browser is firefox 44 on Windows 7
    Given page url is "https://saucelabs.com/test/guinea-pig"
    When email is entered as "foo@bar.com"
    Then email should show "foo@bar.com"

  Scenario: Enter and submit
    Given browser is firefox 44 on Windows 7
    Given page url is "https://saucelabs.com/test/guinea-pig"
    And comments are entered as "Hello, world!"
    When form is submitted
    Then your comments section should end with "Hello, world!"

  Scenario: Uncheck checked box and submit
    Given browser is firefox 44 on Windows 7
    Given page url is "https://saucelabs.com/test/guinea-pig"
    And checked checkbox is clicked
    When form is submitted
    Then checked checkbox selected state should be "true"

  Scenario: Check unchecked box and submit
    Given browser is firefox 44 on Windows 7
    Given page url is "https://saucelabs.com/test/guinea-pig"
    And unchecked checkbox is clicked
    When form is submitted
    Then unchecked checkbox selected state should be "false"
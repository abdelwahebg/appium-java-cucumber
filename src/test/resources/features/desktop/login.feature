Feature: Java-Cucumber Integration

  @Web
  Scenario: Login Normal Scenario
    Given Valid user credential
    When User do login

  @Web
  Scenario: Login Scenario
    When Try login with several accounts
      | username         | password |
      | aldotesting      | qwerty   |
      | zuraqia          | qwerty   |
      | arkadiusreymond2 | qwerty   |
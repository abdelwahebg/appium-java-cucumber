Feature: Multiple Scenario

  @PWA
  Scenario: Login
    Given Valid user credential
    When User do login

  @PWA
  Scenario: Register
    Given Valid user credential
    When User do login
@Some Tag
@Meta("Some tag with an argument")
Feature: Some Feature
  Some description.

  Background:
    Given some common condition
    And something else

  Scenario: Some Scenario
  Some scenario description.
  @This doesn't go here!!!
    When I do something
    Then there is some result

  Scenario: Some other scenario
  @NoTagAllowedHere
  | no  | table | allowed | here |
  | bad |  bad  |   bad   |  bad |
    When I do something else
    Then something else happens
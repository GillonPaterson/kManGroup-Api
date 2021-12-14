Feature: Smoke test suite for viewing job roles

  @smoke @smk1
  Scenario Outline: Smoke tests for viewing job roles webpage

    Given the client is on the job roles webpage
    Then the client should see employee <ID> and their <Job Role>

    Examples:
      | ID        | Job Role |
      | "1"       | "boss"   |



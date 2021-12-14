Feature: Smoke test suite for purchasing items from Amazon.

  @smoke @smk1
  Scenario Outline: Smoke tests for viewing

    Given the client is on the job roles webpage
    Then the client should see employee <ID> and their <Job Role>

    Examples:
      | ID        | Job Role |
      | "1"       | "boss"   |


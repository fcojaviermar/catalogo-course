Feature: Add course
  Scenario: addCourse
    Given un curso que no esta en el sistema
    When anado el curso al sistema
    Then el curso se anade correctamente
    
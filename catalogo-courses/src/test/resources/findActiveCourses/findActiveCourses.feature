Feature: Cursos disponibles
  Scenario: A
    Given un catalogo de cursos disponibles en el sistema
	  When accedo a los cursos activos
	  Then el sistema devuelve la lista de cursos activos

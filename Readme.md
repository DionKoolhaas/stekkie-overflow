
Run lokaal met het profiel "test".

Enable annotation processing voor lombok.

links:
- hal browser http://localhost:8080/data-rest-api/
- voorbeeld van een zoekquery http://localhost:8080/data-rest-api/explorer/index.html#uri=http://localhost:8080/data-rest-api/texts/search/findByText?text=OS
- h2 console http://localhost:8080/h2-console , verbind met (jdbc url=jdbc:h2:mem:mydatabase, user name=test, password=test)

TODO:
 - respecteer de robots txt & en andere filters voor het niet parsen van een document
 - documenten van internet halen ipv filesystem
 - maak mogelijk dat document vaker wordt opgehaald in de tijd en houd de geschiedenis bij
 - throttling
 - user mogelijkheid geven een volgende run te draaien
 - correcte foutafhandeling & logging
 - testen, testen & testen
 - cssQueries in aparte tabel en die meenmen bij het verwerken van het document
 - cssQueries toevoegen obv schema.org

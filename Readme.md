
## Requirements
 - java 21
 - maven

## Run local
 - use spring profile "test". 
 - Enable annotation processing for lombok in your IDE
 - run command: `mvn spring-boot:run -Dspring-boot.run.profiles=test`

## Links
- hal browser http://localhost:8080/data-rest-api/
- example of search query http://localhost:8080/data-rest-api/explorer/index.html#uri=http://localhost:8080/data-rest-api/texts/search/findByText?text=OS
- h2 console http://localhost:8080/h2-console , connect using (jdbc url=jdbc:h2:mem:mydatabase, user name=test, password=test)
- see http requests (can be run in intellij) in 'src/test/resources/requests/actuatorRequests.http'

## TODO
 - respecteer de robots txt & en andere filters voor het niet parsen van een document
 - maak mogelijk dat document vaker wordt opgehaald in de tijd en houd de geschiedenis bij
 - throttling
 - correcte foutafhandeling & logging
 - testen, testen & testen
 - cssQueries in aparte tabel en die meenmen bij het verwerken van het document
 - cssQueries toevoegen obv schema.org

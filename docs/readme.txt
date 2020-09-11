Requirements:

- Java runtime environment >= 13.0

Frameworks:
Evaluated:
    XML:
        https://github.com/jhannes/eaxy
        https://github.com/eclipse-ee4j/jaxb-ri
        https://github.com/FasterXML/jackson-dataformat-xml
        https://github.com/dom4j/dom4j
        http://jdom.org/

    JSON:

Choosen:
- GSON for JSON files
    com.google.code.gson:gson:2.8.5
- JUnit for testing
    org.junit.jupiter:junit-jupiter:5.6.2
- Unirest Java for REST requests
    com.konghq:unirest-java:3.10.00
- Apache Commons for FTP
    commons-net:commons-net:3.6
- OpenCSV for CSV files
    com.opencsv:opencsv:5.2
- Matomo tracker (Piwik)
    - matomo-java-tracker-1.6.jar
        - requires org.glassfish:jakarta.json:1.1.6
        - https://matomo.org/blog/2015/11/introducing-piwik-java-tracker/
- Sqlite
    - org.xerial:sqlite-jdbc:3.32.3
- JAXB as external library as it was removed in JDK 11
    javax.xml.bind:jaxb-api:2.3.1
        - needed for piwik

- start on command line
    java -jar D:\projects\Corazon\WatchDog\out\artifacts\WatchDog_jar\WatchDog.jar

- XML to JSON
    com.fasterxml.jackson.core:jackson-annotations:2.10.1

Removed:

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
- JAXB as external library as it was removed in JDK 11
    javax.xml.bind:jaxb-api:2.3.1
- JUnit for testing
    org.junit.jupiter:junit-jupiter:5.6.2
- Unirest Java for REST requests
    com.konghq:unirest-java:3.10.00
- Apache Commons for FTP
    commons-net:commons-net:3.6
- start on command line
    java -jar D:\projects\Corazon\WatchDog\out\artifacts\WatchDog_jar\WatchDog.jar


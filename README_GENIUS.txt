spring.datasource.url=jdbc:postgresql://10.110.106.182:5432/webphonebook
spring.datasource.username=developer
spring.datasource.password=qwe123
spring.jpa.generate-ddl=true

spring.freemarker.expose-request-attributes=true

server.port=9000

ru.omc.webphonebook.base-url=http://10.110.106.182:9000

java -jar -Dru.omc.webphonebook.base-url=http://10.110.80.12:9000
            -Dspring.datasource.url=jdbc:postgresql://10.110.106.182:5432/webphonebook
            -Dspring.datasource.username=developer
            -Dspring.datasource.password=qwe123
            -Dspring.jpa.generate-ddl=true
            -Dspring.freemarker.expose-request-attributes=true
            -Dserver.port=9000
            Phonebook-0.0.1-SNAPSHOT.jar

java -jar -Dru.omc.webphonebook.base-url=http://10.110.80.12:9000 -Dserver.port=9000 -Dspring.datasource.url=jdbc:postgresql://localhost:5433/Phonebook -Dspring.datasource.username=postgres -Dspring.datasource.password=123 -Dspring.jpa.generate-ddl=true -Dspring.freemarker.expose-request-attributes=true D:\Phonebook-0.0.1-SNAPSHOT.jar
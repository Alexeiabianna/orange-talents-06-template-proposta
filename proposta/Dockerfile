FROM maven
LABEL Alexei Abianna
COPY . /var/www
WORKDIR /var/www
RUN mvn clean install
ENTRYPOINT [ "mvn", "spring-boot:run" ]
EXPOSE 8080
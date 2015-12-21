FROM relateiq/oracle-java8

RUN mkdir -p /data

# App dependencies
ADD application/build/libs/pensieve-application.jar /data/pensieve-application.jar
ADD config/pensieve_docker.yml /data/pensieve.yml

CMD java -jar /data/pensieve-application.jar db migrate /data/pensieve.yml && \
    java -jar /data/pensieve-application.jar server /data/pensieve.yml

EXPOSE 8080

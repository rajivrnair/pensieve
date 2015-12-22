# For Shippable.io - we should merge this with the Dockerfile in web/
FROM relateiq/oracle-java8

RUN mkdir -p /data
RUN mkdir -p /var/logs/pensieve

# App dependencies
ADD ./buildoutput/* /data/

CMD java -jar /data/pensieve-application.jar db migrate /data/pensieve.yml && \
    java -jar /data/pensieve-application.jar server /data/pensieve.yml

EXPOSE 8080

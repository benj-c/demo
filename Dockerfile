FROM openjdk:8-jdk-alpine
RUN cd /usr/local
RUN mkdir spring

RUN wget https://dlcdn.apache.org/maven/maven-3/3.8.5/binaries/apache-maven-3.8.5-bin.tar.gz -O /tmp/apache-maven-3.8.5-bin.tar.gz
RUN tar -xvf /tmp/apache-maven-3.8.5-bin.tar.gz -C /usr/local/
RUN rm /tmp/apache-maven-3.8.5-bin.tar.gz

ENV PATH /usr/local/apache-maven-3.8.5/bin:$PATH
ENV M2_HOME /usr/local/apache-maven-3.8.5

COPY . /usr/local/spring
WORKDIR /usr/local/spring
RUN mvn clean package -DskipTests

CMD java -jar /usr/local/spring/demo-0.0.1-SNAPSHOT.jar
EXPOSE 8401

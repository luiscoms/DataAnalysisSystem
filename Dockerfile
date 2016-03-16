# FROM java:openjdk-8-jdk

# ENV MAVEN_VERSION 3.3.9

# RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar xzf - -C /usr/share \
#   && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
#   && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

# ENV MAVEN_HOME /usr/share/maven

# VOLUME /root/.m2

# docker run -it --rm -v ${PWD}:/dataanalysissystem luiscoms/dataanalisyssistem bash
FROM maven:3.3-jdk-8

MAINTAINER Luis Fernando Gomes <dev@luiscoms.com.br>

ADD ./ /dataanalisyssistem

WORKDIR /dataanalisyssistem

CMD ["mvn clean"]
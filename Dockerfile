# version 1.0.0
# date 2021-03-02
FROM sxjy/centos7.0-jdk
MAINTAINER gjg@sxw.cn

ENV APP_NAME="subsidize-server"
ENV JAVA_JVM_OPTS="-Xms512m -Xmx512m -Xmn256m"
COPY dist/$APP_NAME.jar $APP_NAME.jar
VOLUME /logs
EXPOSE 8889
ENTRYPOINT exec java -server $JAVA_JVM_OPTS -jar $APP_NAME.jar 2>&1

#ENTRYPOINT ["java","$JAVA_JVM_OPTS","$JAVA_MEM_OPTS","-jar","/base/candy-server.jar",">$JVM_LOG_HOME","2>&1","&"]

# version 1.0.0
# date 2021-03-02
FROM java:8
MAINTAINER zhangziqiang

ENV APP_NAME="sso-platform"
ENV JAVA_JVM_OPTS="-Xms256m -Xmx256m -Xmn128m"
ADD $APP_NAME.jar /base/$APP_NAME.jar
VOLUME /logs
EXPOSE 9500
ENTRYPOINT exec java -server $JAVA_JVM_OPTS -Duser.timezone=GMT+08 -jar /base/$APP_NAME.jar --spring.profiles.active=pro >/logs/$APP_NAME.log 2>&1

#ENTRYPOINT ["java","$JAVA_JVM_OPTS","$JAVA_MEM_OPTS","-jar","/base/candy-server.jar",">$JVM_LOG_HOME","2>&1","&"]

FROM tomcat:latest

RUN rm -rf /usr/local/tomcat/webapps/ROOT

ADD root.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]
# 拉取基础镜像
FROM java

# 将JDK复制到镜像中，如果镜像已经有了JDK环境则无需配置此项和下面的运行环境
# COPY jdk1.7.0_79 /usr/local/jdk1.7.0_79
# 配置运行环境
# ENV JAVA_HOME=/usr/local/jdk1.7.0_79
# ENV PATH=$JAVA_HOME/bin:$PATH
# ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

# 添加springboot项目到镜像中的home目录，并重命名为app.jar
ADD demo-0.0.1-SNAPSHOT.jar ~/app.jar

# 容器启动后执行的命令
ENTRYPOINT ["java","-jar","~/app.jar", "--server.port=9090"]
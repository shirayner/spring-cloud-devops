# 基于哪个镜像
FROM openjdk:jre-alpine
# 修改时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# 将本地文件夹挂载到当前容器
VOLUME /tmp

# 拷贝文件到容器，
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""

# 开放8761端口
EXPOSE 8761

# 配置容器启动后执行的命令
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar " ]
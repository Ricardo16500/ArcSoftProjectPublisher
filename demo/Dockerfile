FROM azul/zulu-openjdk:11.0.10 as test
RUN apt update

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENV URL_DATABASE "jdbc:postgresql://192.168.0.159:5432/school_db"
ENV RABBITMQ_HOST "localhost"
ENV RABBITMQ_USERNAME "guest"
ENV RABBITMQ_PASSWORD "guest"
ENV RABBITMQ_PORT "5672"

ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.demo.MsSchoolApplication"]
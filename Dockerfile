ARG JDK_IMAGE=openjdk:8
FROM $JDK_IMAGE

RUN useradd fibber \
    && mkdir -p /home/fibber/bin \
    && mkdir -p /home/fibber/lib \
    && chown -R fibber:fibber /home/fibber

ARG fib_jar

USER fibber
COPY --chown=fibber:fibber $fib_jar /home/fibber/lib/travis-scala-sbt.jar
COPY --chown=fibber:fibber bin/fibber /home/fibber/bin

WORKDIR /home/fibber
ENV PATH="${PATH}:/home/fibber/bin"

ENTRYPOINT ["fibber"]

#!/usr/bin/env bash

set -eu
set -o pipefail

: "${TRAVIS_SCALA_VERSION?TRAVIS_SCALA_VERSION must be set}"
: "${COMPOSE_FILE:=docker-compose.test.yml}"
: "${TRAVIS_JDK_VERSION?TRAVIS_JDK_VERSION must be set}"
: "${TRAVIS_COMMIT:=1234}"

export COMPOSE_FILE="${COMPOSE_FILE}"

JAVA_TAG=$(echo "$TRAVIS_JDK_VERSION" | sed -e 's/.*\(jdk\)\(.*\)/\1\2/')
if [ ! -z "$TRAVIS_TAG" ]
then
  export DOCKER_BUILD_TAG="${JAVA_TAG}-${TRAVIS_TAG}"
else
  export DOCKER_BUILD_TAG="${JAVA_TAG}-${TRAVIS_COMMIT::8}"
fi

export JDK_IMAGE=$(echo "$TRAVIS_JDK_VERSION" | sed -e 's/\(.*jdk\)\(.*\)/\1:\2-alpine/')

export FIB_JAR=$(sbt ++$TRAVIS_SCALA_VERSION 'show Compile / packageBin /artifactPath ' | grep -E '\.jar$' | awk '{print $NF}' | sed -e 's@.*/target@target@')

docker-compose build \
  && docker-compose run sut \
  && docker image ls \
  && docker-compose rm -s -f


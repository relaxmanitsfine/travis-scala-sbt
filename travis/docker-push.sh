#!/usr/bin/env bash

set -eu
set -o pipefail

: "${DOCKER_EMAIL?DOCKER_EMAIL must be set}"
: "${DOCKER_USER?DOCKER_USER must be set}"
: "${DOCKER_PASS?DOCKER_PADD must be set}"
: "${TRAVIS_JDK_VERSION?TRAVIS_JDK_VERSION must be set}"

JAVA_TAG=$(echo "$TRAVIS_JDK_VERSION" | sed -e 's/.*\(jdk\)\(.*\)/\1\2/')
if [ ! -z "$TRAVIS_TAG" ]
then
  export DOCKER_BUILD_TAG="${JAVA_TAG}-${TRAVIS_TAG}"
else
  export DOCKER_BUILD_TAG="${JAVA_TAG}-${TRAVIS_COMMIT::8}"
fi

docker login -e "$DOCKER_EMAIL" -u "$DOCKER_USER" -p "$DOCKER_PASS" \
  && docker tag "fibber:${DOCKER_BUILD_TAG}" \
     "relaxmanitsfine/fibber:${DOCKER_BUILD_TAG}" \
  && docker push "relaxmanitsfine/fibber:${DOCKER_BUILD_TAG}"

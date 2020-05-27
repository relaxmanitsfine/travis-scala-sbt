#!/usr/bin/env bash

set -eu
set -o pipefail

: "${WORKING_DIR:=/ci/tests}"

FIXTURES="${WORKING_DIR}/fixtures"

run_fib() {
  NUM="$1"

  # PATH should be set in the container build
  echo "running fibber ${NUM}:"
  echo ""
  fibber "$NUM" | tee "/tmp/fibber.$$.${NUM}.out"

  echo ""
  echo "diff with expected:"
  echo ""
  diff -w "${FIXTURES}/expected_${NUM}.txt" "/tmp/fibber.$$.${NUM}.out"
}

run_fib 20
run_fib 50
run_fib 200


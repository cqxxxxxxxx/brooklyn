#!/bin/sh

set -e
APP=gitlab-runner
TAG=latest
docker build -t cqx/$APP:$TAG -f ./Dockerfile .

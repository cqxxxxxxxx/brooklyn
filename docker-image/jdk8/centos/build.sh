#!/bin/sh

set -e
APP=jdk8-centos
TAG=latest
docker build -t cqx/$APP:$TAG -f ./Dockerfile ../

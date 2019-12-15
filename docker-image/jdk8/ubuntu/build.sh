#!/bin/sh

set -e
APP=jdk8-ubuntu
TAG=latest
docker build -t cqx/$APP:$TAG -f ./Dockerfile ../

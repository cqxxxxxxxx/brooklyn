#!/bin/sh


function build_image() {
    echo "========================================================"
    echo "=          构建镜像 param($1)                            "
    echo "========================================================"
    docker build -t $1 -f ./Dockerfile .
}


function push_image() {
    echo "========================================================"
    echo "=           推送到仓库 param($1 $2)                      "
    echo "========================================================"
    docker tag $1 registry.cn-hangzhou.aliyuncs.com/cqxxxxxxxx/prometheus:$2
    docker push registry.cn-hangzhou.aliyuncs.com/cqxxxxxxxx/prometheus:$2
}


set -e
OWNER=cqx
APP=alertmanager
TAG=latest
IMAGE_NAME="$OWNER/$APP:$TAG"
build_image ${IMAGE_NAME}

IMAGE_ID=$(docker image ls | grep ${OWNER}/${APP} | grep ${TAG} | awk '{print $3}')
IMAGE_PUSH_TAG="${APP}_${TAG}"
push_image ${IMAGE_ID} ${IMAGE_PUSH_TAG}


#!/bin/sh
set -e

function maven() {
    read -p "是否跳过maven构建?(y/n)" SKIP_BUILD
    if [ "$SKIP_BUILD" != "y" ]; then
        cd $1
        mvn clean package -Dmaven.test.skip=true && mvn docker:build
    else
        echo "跳过maven build"
    fi
    return
}

function deploy() {
    echo "========================================================"
    echo "=                    启动eureka服务                     ="
    echo "========================================================"
    cd $1
    docker-compose -f docker-compose.yml up -d
}

DEPLOY_ROOT=$(pwd)
cd ..
MAVEN_ROOT=$(pwd)
maven "$MAVEN_ROOT"
deploy "$DEPLOY_ROOT"
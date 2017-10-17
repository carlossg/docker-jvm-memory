#!/bin/sh -x
docker run -it -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app -w /usr/src/app \
  maven:3-alpine mvn package

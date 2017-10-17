#!/bin/sh -x
docker run -it -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app -w /usr/src/app \
  -m 210m \
  maven:3-alpine mvn -o package

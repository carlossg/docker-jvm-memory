#!/bin/sh -x
docker run --rm -it -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app -w /usr/src/app \
  -m 210m -e _JAVA_OPTIONS=-Xmx210m \
  maven:3-alpine mvn -f pom-fork.xml -o package

# Understanding Docker and JVM memory

Create a shared Maven repository

    docker volume create --name maven-repo

Run with no memory limits, no problem

    docker run -it -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app -w /usr/src/app maven:3-alpine mvn package
    docker rm mvn-test

Limit memory to 210m

    docker run -it -m 210m -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app -w /usr/src/app maven:3-alpine mvn -o package
    docker inspect mvn-test | head -n 25
    docker rm mvn-test

Limit memory to 210m and set Xmx

    docker run --rm -it -m 210m -e MAVEN_OPTS=-Xmx210m -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app -w /usr/src/app maven:3-alpine mvn -o package
    docker rm mvn-test

Use JDK 8u131

    docker run --rm -it -m 210m \
      -e MAVEN_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1" \
      -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app -w /usr/src/app maven:3-alpine mvn -o package

Limit memory to 210m and set Xmx, with surefire running in the default forked mode

    docker run --rm -it -m 210m -e MAVEN_OPTS=-Xmx210m -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app -w /usr/src/app maven:3-alpine mvn -f pom-fork.xml -o package
    docker rm mvn-test

Limit memory to 210m and set Xmx in `_JAVA_OPTIONS`, with surefire running in the default forked mode

    docker run --rm -it -m 210m -e _JAVA_OPTIONS=-Xmx210m -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app -w /usr/src/app maven:3-alpine mvn -f pom-fork.xml -o package
    docker rm mvn-test

# Understanding Docker and JVM memory

Create a shared Maven repository

    docker volume create --name maven-repo

Run with no memory limits, no problem

    docker run -it -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app maven:3-onbuild mvn package
    docker rm mvn-test

Limit memory to 210m

    docker run -it -m 220m -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app maven:3-onbuild mvn -o package
    docker inspect mvn-test | head -n 25
    docker rm mvn-test

Limit memory to 210m and set Xmx

    docker run --rm -it -m 220m -e MAVEN_OPTS=-Xmx210m -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app maven:3-onbuild mvn -o package
    docker rm mvn-test

Limit memory to 210m and set Xmx, with surefire running in the default forked mode

    docker run --rm -it -m 220m -e MAVEN_OPTS=-Xmx210m -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app maven:3-onbuild mvn -f pom-fork.xml -o package
    docker rm mvn-test

Limit memory to 210m and set Xmx in `_JAVA_OPTIONS`, with surefire running in the default forked mode

    docker run --rm -it -m 220m -e _JAVA_OPTIONS=-Xmx210m -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app maven:3-onbuild mvn -f pom-fork.xml -o package
    docker rm mvn-test

# Understanding Docker and JVM memory

Create a shared Maven repository

    docker volume create --name maven-repo

Run with no memory limits, no problem

    docker run -it -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app maven:3-onbuild mvn package
    docker rm mvn-test

Limit memory to 192m

    docker run -it -m 192m -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app maven:3-onbuild mvn -o package
    docker inspect mvn-test | head -n 25
    docker rm mvn-test

Limit memory to 192m and set Xmx

    docker run --rm -it -m 192m -e MAVEN_OPTS=-Xmx192m -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app maven:3-onbuild mvn -o package
    docker rm mvn-test

Limit memory to 192m and set Xmx, with surefire running in the default forked mode

    docker run --rm -it -m 192m -e MAVEN_OPTS=-Xmx192m -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app maven:3-onbuild mvn -f pom-fork.xml -o package
    docker rm mvn-test

Limit memory to 192m and set Xmx in `_JAVA_OPTIONS`, with surefire running in the default forked mode

    docker run --rm -it -m 192m -e _JAVA_OPTIONS=-Xmx192m -v maven-repo:/root/.m2 --name mvn-test -v `pwd`:/usr/src/app maven:3-onbuild mvn -f pom-fork.xml -o package
    docker rm mvn-test

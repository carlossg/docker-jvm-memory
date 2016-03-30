# Understanding Docker and JVM memory

Create a shared Maven repository

    docker create -v /root/.m2 --name maven-repo busybox /bin/true

Run with no memory limits, no problem

    docker run -it --volumes-from maven-repo --name mvn-test -v `pwd`:/usr/src/app maven:3-onbuild mvn -o package
    docker rm mvn-test

Limit memory to 256m

    docker run -it -m 256m --volumes-from maven-repo --name mvn-test -v `pwd`:/usr/src/app maven:3-onbuild mvn -o package
    docker inspect mvn-test | head -n 25
    docker rm mvn-test

Limit memory to 256m and set Xmx

    docker run --rm -it -m 256m -e MAVEN_OPTS=-Xmx256m --volumes-from maven-repo --name mvn-test -v `pwd`:/usr/src/app maven:3-onbuild mvn -o package
    docker rm mvn-test

Limit memory to 256m and set Xmx, with surefire running in the default forked mode

    docker run --rm -it -m 256m -e MAVEN_OPTS=-Xmx256m --volumes-from maven-repo --name mvn-test -v `pwd`:/usr/src/app maven:3-onbuild mvn -f pom-fork.xml -o package
    docker rm mvn-test

Limit memory to 256m and set Xmx in `_JAVA_OPTIONS`, with surefire running in the default forked mode

    docker run --rm -it -m 256m -e _JAVA_OPTIONS=-Xmx256m --volumes-from maven-repo --name mvn-test -v `pwd`:/usr/src/app maven:3-onbuild mvn -f pom-fork.xml -o package
    docker rm mvn-test

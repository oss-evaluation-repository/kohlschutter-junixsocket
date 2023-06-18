![junixsocket logo](https://user-images.githubusercontent.com/822690/246675372-d1775152-5f5e-4576-8f3d-8445779ea584.png)

# junixsocket

junixsocket is a Java/JNI library that allows the use of
[Unix Domain Sockets](https://en.wikipedia.org/wiki/Unix_domain_socket) (AF_UNIX sockets), and
other address/protocol families (such as [AF_TIPC](http://tipc.io/) and AF_VSOCK), from Java.

## Unix sockets API, in Java, AF.

* *junixsocket* is the most complete implementation of AF_UNIX sockets for the Java ecosystem.
* Supports other socket types, such as TIPC (on Linux) and VSOCK (on Linux, and certain macOS VMs), as well!
* Comes with pre-built native libraries for most operating systems and platforms, including
  macOS, Linux, Windows, Solaris, FreeBSD, NetBSD, OpenBSD, DragonFlyBSD, AIX, IBM i.
* Additionally, you can build and run junixsocket natively on IBM z/OS (experimental).
* Supports all Java versions since Java 8*
* Supports both the Java Socket API and NIO (`java.net.Socket`, `java.net.SocketChannel`, etc.)
* Supports streams and datagrams.
* Supports Remote Method Invocation (RMI) over AF_UNIX.
* Supports JDBC database connectors (connect to a local database server via Unix sockets).
    * Generic *AFUNIXSocketFactory* for databases like PostgreSQL
    * Custom socket factory for MySQL Connector/J, as [recommended by Oracle](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-unix-socket.html)
* Supports [peer credentials](https://kohlschutter.github.io/junixsocket/peercreds.html).
* Supports sending and receiving [file descriptors](https://kohlschutter.github.io/junixsocket/filedescriptors.html).
* Supports the abstract namespace on Linux.
* Supports socketpair, and instantiating socket classes from file descriptors.
* Supports [HTTP over UNIX sockets](https://kohlschutter.github.io/junixsocket/http.html) (using [NanoHTTPD](https://github.com/NanoHttpd/nanohttpd), [OkHttp](https://github.com/square/okhttp), and [jetty](https://github.com/eclipse/jetty.project/)).
* Supports JPMS/Jigsaw modules. The project is modularized so you can install only what you need.
* Supports GraalVM native-image AOT/ahead-of-time compilation (since 2.6.0)
* Provides a selftest package with 100+ tests to ensure compatibility with any target platform.
* Apache 2.0 licensed.

`*` (Tested up to Java 20; support for Java 7 was dropped in version 2.5.0).

## Quick links

 * [Project website](https://kohlschutter.github.io/junixsocket/) and [Github project](https://github.com/kohlschutter/junixsocket/)
 * [Changelog](https://kohlschutter.github.io/junixsocket/changelog.html)
 * [Getting started](https://kohlschutter.github.io/junixsocket/quickstart.html)
 * [Demo code](https://kohlschutter.github.io/junixsocket/demo.html) ([Java source](https://kohlschutter.github.io/junixsocket/junixsocket-demo/xref/index.html))
    - Sockets (`org.newsclub.net.unix.demo`)
    - RMI over Unix Sockets (`org.newsclub.net.unix.demo.rmi` and `org.newsclub.net.unix.demo.rmi.services`)
    - MySQL over Unix Sockets  (`org.newsclub.net.mysql.demo`)
  * [API Javadocs](https://kohlschutter.github.io/junixsocket/apidocs/)
  * [Unix Domain Socket Reference](https://kohlschutter.github.io/junixsocket/unixsockets.html)
  * [TIPC documentation](https://kohlschutter.github.io/junixsocket/junixsocket-tipc/index.html)
  * [VSOCK documentation](https://kohlschutter.github.io/junixsocket/junixsocket-vsock/index.html)

## Licensing

junixsocket is released under the Apache 2.0 License.

Commercial support is available through [Kohlschütter Search Intelligence](http://www.kohlschutter.com/).

## Self-test

To verify that the software works as expected on your platform, you can run the
[junixsocket-selftest](https://kohlschutter.github.io/junixsocket/selftest.html) program, which is
located in the "junixsocket-dist" distribution package, and also released on GitHub.

```
java -jar junixsocket-selftest-VERSION-jar-with-dependencies.jar
```

(with VERSION being the corresponding junixsocket version).

## Maven dependency

To include the core junixsocket functionality in your project, add the following Maven dependency

> **NOTE** Since version 2.4.0, `junixsocket-core` is POM-only (that's why you need to specify `<type>pom</type>`)

```
<dependency>
  <groupId>com.kohlschutter.junixsocket</groupId>
  <artifactId>junixsocket-core</artifactId>
  <version>2.6.2</version>
  <type>pom</type>
</dependency>
```

While you should definitely pin your dependency to a specific version, you are very much encouraged to always update to the most recent version. Check back frequently.

For more, optional packages (RMI, MySQL, Jetty, TIPC, VSOCK, server, GraalVM, etc.) and Gradle instructions see
[here](https://kohlschutter.github.io/junixsocket/dependency.html)

If you're testing a `-SNAPSHOT` version, make sure that the Sonatype snapshot repository is enabled in your POM:

```
<repositories>
    <repository>
        <id>sonatype.snapshots</id>
        <name>Sonatype snapshot repository</name>
        <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
        <layout>default</layout>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```

> **NOTE** Never rely on -SNAPSHOT builds. They can break any time.

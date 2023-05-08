Alibaba Socket GraalVM native-image with Spring Boot 3
===========================================================

Alibaba RSocket GraalVM native-image demo with Spring Boot 3

# Requirements

* GraalVM 22.3.0 with native-image installed  https://www.graalvm.org/downloads/

# How to run

```
$ # switch to GraalVM 22.3.0
$ mvn -Pnative -DskipTests clean package native:compile
$ ./target/ali-rsocket-graal-boot3-demo
$ # open a new tab
$ curl http://localhost:8183/user/1
```

# Native assist

For details, please
refer: https://www.graalvm.org/reference-manual/native-image/BuildConfiguration/#assisted-configuration-of-native-image-builds

### How to run app with native-image-agent mode?

```
$ mkdir -p target/native-image
$ java -agentlib:native-image-agent=config-output-dir=./target/native-image/ -jar target/ali-rsocket-graal-boot3-demo.jar
```

Then check assisted configurations under `target/native-image/`

# Compress executable binary with upx

Compress GraalVM native image binary with [UPX](https://github.com/upx/upx):

```
$ upx -7 -k target/ali-rsocket-graal-demo  
```

# GraalVM Native Image guide

* If you want to access remote RSocket Service, please add interface full name to `proxy-config.json`, then
  add `.nativeImage()`  to create service call stub.

```
    @Bean
    public UserService userService(UpstreamManager upstreamManager) {
        return RSocketRemoteServiceBuilder
                .client(UserService.class)
                .upstreamManager(upstreamManager)
                .nativeImage()
                .build();
    }
```

* If you want to publish RSocket Service, please add interface & implementation to `reflection-config.json`

# References

* RSocket: https://rsocket.io/
* GraalVM: https://www.graalvm.org/
* Alibaba RSocket Broker: https://github.com/alibaba/alibaba-rsocket-broker
* upx: the Ultimate Packer for eXecutables https://github.com/upx/upx
* Spring Boot GraalVM Native Image
  Support: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#native-image

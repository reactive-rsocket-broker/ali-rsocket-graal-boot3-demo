package com.alibaba.graal;

import com.alibaba.rsocket.invocation.RSocketRemoteServiceBuilder;
import com.alibaba.rsocket.upstream.UpstreamManager;
import com.alibaba.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication(proxyBeanMethods = false)
@ImportRuntimeHints(AppRuntimeHintsRegistrar.class)
public class AliRSocketGraalApp {

    public static void main(String[] args) {
        SpringApplication.run(AliRSocketGraalApp.class, args);
    }

    @Bean
    public UserService userService(UpstreamManager upstreamManager) {
        return RSocketRemoteServiceBuilder
                .client(UserService.class)
                .upstreamManager(upstreamManager)
                .nativeImage()
                .build();
    }
}

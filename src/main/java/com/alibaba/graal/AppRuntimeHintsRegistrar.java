package com.alibaba.graal;

import com.alibaba.user.User;
import com.alibaba.user.UserService;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

class AppRuntimeHintsRegistrar implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        hints.reflection()
                .registerType(User.class)
                .registerType(GraalDemoService.class)
                .registerType(GraalDemoServiceImpl.class);
        hints.proxies().registerJdkProxy(UserService.class);
    }

}

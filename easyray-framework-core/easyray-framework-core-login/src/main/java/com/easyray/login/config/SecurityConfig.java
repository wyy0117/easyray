package com.easyray.login.config;

import com.easyray.auth.annotation.EasyrayNoAuth;
import com.easyray.login.service.impl.UserDetailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Date: 20-1-30
 * @Author: wyy
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private UserDetailServiceImpl userDetailsServiceImpl;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsServiceImpl)
//                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors();
//        http.csrf().disable();
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry requests = http.authorizeRequests();
//        requests.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()// 跨域预检请求
//                // 登录URL
//                .antMatchers("/login").permitAll()
//                // swagger
//                .antMatchers("/swagger**/**").permitAll()
//                .antMatchers("/webjars/**").permitAll()
//                .antMatchers("/v2/**").permitAll();
//        Set<String> allNoAuthMethod = findAllNoAuthMethod();
//        log.debug("these url need no auth : {}", allNoAuthMethod);
//        requests.antMatchers(allNoAuthMethod.toArray(new String[0])).permitAll();
//        AuthenticationManager authenticationManager = authenticationManagerBean();
////        http.addFilter(new JwtLoginFilter(authenticationManager));
//        http.addFilter(new JWTTokenFilter(authenticationManager));
//        // 其他所有请求需要身份认证
//        requests.anyRequest().authenticated();

        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    private Set<String> findAllNoAuthMethod() throws ClassNotFoundException {
        Map<String, Object> beanName_bean_map = applicationContext.getBeansWithAnnotation(Controller.class);
        Set<String> ignoreAuthUriList = new HashSet<>();
        for (Object bean : beanName_bean_map.values()) {
            String className = bean.getClass().getName();

            Class<?> controller = Class.forName(className);
            RequestMapping annotation = controller.getAnnotation(RequestMapping.class);
            String urlPrefix = "";
            if (annotation != null) {
                String[] value = annotation.value();
                urlPrefix = value[0];
            }
            Method[] methods = controller.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(EasyrayNoAuth.class)) {
                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                    if (requestMapping != null) {
                        String[] value = requestMapping.value();
                        for (String path : value) {
                            path = urlPrefix + (path.startsWith("/") ? path : "/" + path);
                            ignoreAuthUriList.add(path);
                        }
                        continue;
                    }
                    GetMapping getMapping = method.getAnnotation(GetMapping.class);
                    if (getMapping != null) {
                        String[] value = getMapping.value();
                        for (String path : value) {
                            path = urlPrefix + (path.startsWith("/") ? path : "/" + path);
                            if (path.contains("{")) {
                                ignoreAuthUriList.add(path.substring(0, path.indexOf("{")));
                            } else {
                                ignoreAuthUriList.add(path);
                            }
                        }
                        continue;
                    }
                    PostMapping postMapping = method.getAnnotation(PostMapping.class);
                    if (postMapping != null) {
                        String[] value = postMapping.value();
                        for (String path : value) {
                            path = urlPrefix + (path.startsWith("/") ? path : "/" + path);
                            ignoreAuthUriList.add(path);
                        }
                        continue;
                    }
                    PutMapping putMapping = method.getAnnotation(PutMapping.class);
                    if (putMapping != null) {
                        String[] value = putMapping.value();
                        for (String path : value) {
                            path = urlPrefix + (path.startsWith("/") ? path : "/" + path);
                            ignoreAuthUriList.add(path);
                        }
                        continue;
                    }
                    DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
                    if (deleteMapping != null) {
                        String[] value = deleteMapping.value();
                        for (String path : value) {
                            path = urlPrefix + (path.startsWith("/") ? path : "/" + path);
                            if (path.contains("{")) {
                                ignoreAuthUriList.add(path.substring(0, path.indexOf("{")));
                            } else {
                                ignoreAuthUriList.add(path);
                            }
                        }
                    }

                }
            }
        }
        return ignoreAuthUriList;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}

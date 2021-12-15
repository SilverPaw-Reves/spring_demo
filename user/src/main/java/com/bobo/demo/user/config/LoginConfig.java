package com.bobo.demo.user.config;

import com.bobo.demo.user.client.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author bo
 */
@Configuration
public class LoginConfig extends WebMvcConfigurationSupport {
  @Autowired
  private AuthClient authClient;
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //注册TestInterceptor拦截器
    InterceptorRegistration registration = registry.addInterceptor(loginInterceptor());
    //所有路径都被拦截
    registration.addPathPatterns("/**");
    //添加不拦截路径
    registration.excludePathPatterns("favicon.ico", "/user-info/auth");
  }
  
  @Bean
  public UserLoginInterceptor loginInterceptor() {
    return new UserLoginInterceptor(authClient);
  }
}

package com.example.vhr.config;

import com.example.vhr.bean.Hr;
import com.example.vhr.bean.RespBean;
import com.example.vhr.service.HrService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    HrService hrService;
    @Autowired
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;
    @Autowired
    CustomUrlDecisionManager customUrlDecisionManager;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//      登录页不通过springSecurity,放行
        web.ignoring().antMatchers("/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(customUrlDecisionManager);
                        o.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        return o;
                    }
                })
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    //登录成功后的回调
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest Req, HttpServletResponse Resp, Authentication authentication) throws IOException, ServletException {
                        Resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = Resp.getWriter();
                        Hr hr = (Hr) authentication.getPrincipal();
                        //通过Authentication.getPrincipal()可以获取到代表当前用户的信息，这个对象通常是UserDetails的实例。
                        hr.setPassword(null); //登录成功返回到前端的信息里面，常理应该是不显示密码的
                        RespBean success = RespBean.success("登录成功", hr);
                        String s = new ObjectMapper().writeValueAsString(hr);
                        //ObjectMapper()中writeValue是直接将传入的对象序列化为json，并且返回给客户端
                        //而writeValueAsString则是将传入的对象序列化为json，返回给调用者
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    //登录失败后的回调
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest Req, HttpServletResponse Resp, AuthenticationException exception) throws IOException, ServletException {
                        Resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = Resp.getWriter();
                        RespBean respBean = RespBean.error("登录失败！");
                        if (exception instanceof LockedException) {
                            respBean.setMsg("用户被锁定，请联系管理员！");
                        } else if (exception instanceof DisabledException) {
                            respBean.setMsg("用户不可用，请联系管理员！");
                        } else if (exception instanceof CredentialsExpiredException) {
                            respBean.setMsg("密码已过期，请重新登录！");
                        } else if (exception instanceof AccountExpiredException) {
                            respBean.setMsg("账户已过期，请联系管理员！");
                        } else if (exception instanceof BadCredentialsException) {
                            respBean.setMsg("账户或密码不正确，请重新输入");
                        }
                        out.write(new ObjectMapper().writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    //退出登录
                    //注销的接口SpringSecurity已经自动配置好了，默认是（"/logout"）
                    @Override
                    public void onLogoutSuccess(HttpServletRequest Req, HttpServletResponse Resp, Authentication authentication) throws IOException, ServletException {
                        Resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = Resp.getWriter();
                        RespBean respBean = RespBean.success("注销成功！");
                        out.write(new ObjectMapper().writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .csrf().disable().
                //没有认证（登录）时，在这里处理结果，告诉系统不需要重定向
                exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest httpServletRequest, HttpServletResponse Resp, AuthenticationException exception) throws IOException, ServletException {
                Resp.setContentType("application/json;charset=utf-8");
                Resp.setStatus(401);
                PrintWriter out = Resp.getWriter();
                RespBean respBean = RespBean.error("访问失败！");
                if (exception instanceof InsufficientAuthenticationException) {
                    respBean.setMsg("请求失败，请联系管理员！");
                }
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        });
    }
}

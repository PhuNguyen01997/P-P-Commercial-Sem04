package com.apt.p2p.config;

import com.apt.p2p.common.FacebookSignInAdapter;
import com.apt.p2p.common.RegexString;
import com.apt.p2p.model.form.Custom0Auth2User;
import com.apt.p2p.service.CustomOAuth2UserServiceImpl;
import com.apt.p2p.service.FacebookConnectionSignup;
import com.apt.p2p.service.UsersDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomOAuth2UserServiceImpl oAuth2UserService;

    @Autowired
    private UsersDetailServiceImpl userService;

    @Bean
    public BCryptPasswordEncoder pwdEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UsersDetailServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(pwdEncoder());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/", "/signin", "/signup").permitAll();
        http.authorizeRequests().antMatchers("/card", "/address", "/order", "/cart", "/portal/**").authenticated();
//        http.authorizeRequests().antMatchers("/portal").access("hasRole('ROLE_SELLER')");
        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/signin")
                .defaultSuccessUrl("/")
                .failureUrl("/signin?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .oauth2Login()                  // thiết lập cơ chế login cho social login
                .loginPage("/login")            // dùng chung trang login
                .userInfoEndpoint()
                .userService(oAuth2UserService)
                .and()
                // xử lý cho login thành công
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        // nhận thông tin người dùng thông qua Principle
                        Custom0Auth2User user = (Custom0Auth2User)authentication.getPrincipal();
                        String from = RegexString.replaceUrl(request.getRequestURI());
                        userService.processOAuthPostLogin(user ,  from);
                        response.sendRedirect("/");
                    }
                })
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/").permitAll()
                .and()
                .authorizeRequests().and().rememberMe()
                .tokenRepository(persistenceTokenRepository())
                .tokenValiditySeconds(60)
                .and()
                .exceptionHandling().accessDeniedPage("/403");

    }

    @Bean
    public PersistentTokenRepository persistenceTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }
}

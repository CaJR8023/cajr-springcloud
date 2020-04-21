package com.cajr.config;

import com.cajr.exception.AuthExceptionEntryPoint;
import com.cajr.exception.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author CAJR
 * @date 2019/11/25 4:50 下午
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourcesServerConfig extends ResourceServerConfigurerAdapter {

    @Resource
    DataSource dataSource;

    @Resource
    CustomAccessDeniedHandler accessDeniedHandler;

    @Override
    public void configure(final HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/login/**").permitAll()
                .antMatchers("/sms/**").permitAll()
                .antMatchers("/oauth/token/**").permitAll()
                .antMatchers("/code/**").permitAll()
                .antMatchers("/oauth/check_token/**").permitAll()
                .antMatchers("/tokens/**").permitAll()
                .antMatchers(HttpMethod.POST,"/user/**").permitAll()
                .antMatchers(HttpMethod.GET,"/news/**").permitAll()
                .antMatchers(HttpMethod.GET,"/tag/**").permitAll()
                .antMatchers(HttpMethod.GET,"/column/**").permitAll()
                .antMatchers(HttpMethod.GET,"/review/**").permitAll()
                .antMatchers(HttpMethod.GET,"/reply/**").permitAll()
                .antMatchers(HttpMethod.POST,"/admin/").permitAll()
                .antMatchers("/visitor/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/user/forget_password").permitAll()
                //swagger
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll();

        http.authorizeRequests().anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(tokenServices());
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint())
                .accessDeniedHandler(accessDeniedHandler);

    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        converter.setSigningKey("123");
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices(){
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

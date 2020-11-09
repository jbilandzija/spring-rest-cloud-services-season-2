package com.bilandzija.restcloudservices.userservicedemo.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.http.HttpServletRequest


// TODO: Does not permit correctly at the moment. Use only locally!
@Configuration
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    public override fun configure(http: HttpSecurity) {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestMatcher(BasicRequestMatcher())
                .authorizeRequests()
                .antMatchers("/h2").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
    }

    internal class BasicRequestMatcher : RequestMatcher {
        override fun matches(request: HttpServletRequest): Boolean {
            val auth = request.getHeader(HttpHeaders.AUTHORIZATION)
            return auth != null && auth.startsWith("Basic")
        }
    }
}

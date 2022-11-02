package com.example.P50519.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {return new BCryptPasswordEncoder(8);}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/login", "/registration", "/fav", "/profile").permitAll().
                antMatchers("/car", "/carSaloon", "/carType", "/color", "/driveType", "/fuelType", "/saloon", "/transmission", "/carTablesEdit/**")
                .hasAnyAuthority("ADMIN", "REDACTOR").
                antMatchers("/userTableEdit/**").hasAnyAuthority("ADMIN").anyRequest().
                authenticated().and().
                formLogin().loginPage("/login").permitAll().and().
                logout().permitAll().and().
                csrf().disable().cors().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).
                passwordEncoder(getPasswordEncoder()).
                usersByUsernameQuery("SELECT login, password, active FROM user WHERE login = ?").
                authoritiesByUsernameQuery("SELECT u.login, ur.roles FROM user u INNER JOIN user_role ur on u.iduser = ur.user_id WHERE login = ?");
    }
}


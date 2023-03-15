package com.example.PnrTicket2.config;


import com.example.PnrTicket2.enums.Permission;
import com.example.PnrTicket2.enums.Roles;
import com.example.PnrTicket2.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    UserDetailsServiceImpl userDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        super.configure(auth);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin")
//                .password("admin")
//                .roles(Roles.ADMIN.name())
//                .and()
//                .withUser("user")
//                .password("user")
//                .roles(Roles.USER.name());
//    }

    @Bean
    protected PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.GET,"/pnr/*","/user/*","/arrivalcity/*","/departurecity/*","/aviacompany/*","/email/*").hasAuthority(Permission.ADMIN_READ.getPermission())
                .antMatchers(HttpMethod.POST,"/pnr/*","/user/*","/arrivalcity/*","/departurecity/*","/aviacompany/*").hasAuthority(Permission.ADMIN_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE,"/pnr/*","/user/*","/arrivalcity/delete/*","/departurecity/delete/*","/aviacompany/delete/*").hasAuthority(Permission.ADMIN_UPDATE.getPermission())
                .antMatchers(HttpMethod.PUT,"/pnr/*","/user/delete/*","/user/update/*","/arrivalcity/*","/departurecity/*","/aviacompany/*").hasAuthority(Permission.ADMIN_UPDATE.getPermission())
                .anyRequest().authenticated()

                .and().httpBasic();
//                .and().formLogin();
    }


}

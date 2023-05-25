package id.co.mii.serverapp.config;

import id.co.mii.serverapp.services.AppUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private AppUserDetailService appUserDetailService;
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
          .userDetailsService(appUserDetailService)
          .passwordEncoder(passwordEncoder)
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()

                // digantikan dengan privilege atau GlobalMethodSecurity
                //.antMatchers("/region/**")
                //.hasRole("USER")
                //.antMatchers("/country/**")
                //.hasRole("ADMIN")

                .antMatchers(HttpMethod.POST,"/register")
                .permitAll() // mengizinkan request 'register' tuk diakses semua
                .antMatchers(HttpMethod.POST, "/login")
                .permitAll()
                .anyRequest().authenticated() // hanya mengizinkan user/admin yg sudah login
                // .anyRequest().permitAll() // mengizinkan akses untuk semua
                .and()
                .httpBasic() // untuk menggunakan backend dgn JSON
                //.formLogin(); // untuk menggunakan front end dgn HTML template spring
        ;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

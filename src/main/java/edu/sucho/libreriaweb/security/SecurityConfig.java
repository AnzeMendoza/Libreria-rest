package edu.sucho.libreriaweb.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true) // Habilitamos la securización de nuestra API con @Secured
@EnableGlobalMethodSecurity(prePostEnabled = true) // Habilitamos la securización de nuestra API con @Secured
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilterBean() {
        return new JwtAuthorizationFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configuración de la clase que recupera los usuarios y algorito para procesar las passwords
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().and()
                .csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/auth/").permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(new JwtAuthenticationFilter(authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthorizationFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // @formatter:off
//        http
//                .authorizeRequests()
//                .antMatchers("/signup", "/registro", "/css/*", "/assets/*", "/img/*","/").permitAll()
//                .antMatchers("/**").permitAll()//.authenticated()
//                .and()
///*                .formLogin()
//                .loginPage("/login")//página de login
//                .loginProcessingUrl("/logincheck")
//                .usernameParameter("email")
//                .passwordParameter("clave")
//                .defaultSuccessUrl("/tienda", true)//pagina que se rendiriza una vez logueado
//                .failureUrl("/login?error=true")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logout=true")
//                .permitAll()
//                .deleteCookies("JSESSIONID")
//                .and()*/
//                .csrf()
//                .disable();
//    }


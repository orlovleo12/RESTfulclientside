package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@ComponentScan("java")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Qualifier("restServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

//     измените настройку секьюрности с inMemory на userDetailService.
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // указываем страницу с формой логина
              // .loginPage("/login")
                //указываем логику обработки при логине
                .successHandler(new LoginSuccessHandler())
                // даем доступ к форме логина всем
                .permitAll();


        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //выключаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();

//        4) Все CRUD операции и страницы для них должны быть доступны только для админа
//        5) Юзер должен иметь доступ только к своей домашней странице, где выводятся все его данные
        http
                .authorizeRequests() // делаем страницу регистрации недоступной для авторизированных пользователей
                .antMatchers("/login").anonymous() //страницы аутентификации доступна всем
                // защищенные URL
                .antMatchers("/","/users/list", "/users/update", "/users/update/*", "/users/create").access("hasAuthority('ADMIN')")
               .antMatchers("/users/read").access("hasAnyAuthority('ADMIN','USER')")
                ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


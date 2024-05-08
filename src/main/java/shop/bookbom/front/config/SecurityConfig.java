package shop.bookbom.front.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.bookbom.front.security.config.JwtConfig;
import shop.bookbom.front.security.filter.JwtAuthenticationFilter;
import shop.bookbom.front.security.provider.UserEmailPasswordAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    // security에서 css와 같은 정적인 파일들에는 security를 적용하지 않도록 설정
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserEmailPasswordAuthenticationProvider userEmailPasswordAuthenticationProvider() {
        return new UserEmailPasswordAuthenticationProvider();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(userEmailPasswordAuthenticationProvider());
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        jwtAuthenticationFilter.setFilterProcessesUrl("/dosignin");
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager());
        jwtAuthenticationFilter.afterPropertiesSet();
        return jwtAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(jwtAuthenticationFilter());

        /**
         * .permitAll() -> 로그인 안한 사용자도 접근 가능하게 설정
         * .authenticated() -> 로그인 된 사용자만 접근 가능
         * .hasRole("ADMIN") -> 해당 ROLE을 가진 사용자만 접근 가능
         * 주문내역 확인 등 로그인이 필요한 패턴들을 .anyRequest()위 공백부분에 등록하기
         */
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/users/**").authenticated()
                .anyRequest().permitAll();
    }
}

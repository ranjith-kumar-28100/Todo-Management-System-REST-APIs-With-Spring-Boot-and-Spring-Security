package inc.codeman.springboot.todomanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
@NoArgsConstructor
public class SpringSecurityConfiguration {
  private UserDetailsService userDetailsService;

  // @Autowired
  // public SpringSecurityConfiguration(UserDetailsService userDetailsService) {
  // this.userDetailsService = userDetailsService;
  // }

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests(authorize -> {
      // authorize.requestMatchers(HttpMethod.POST,
      // "/api/v1/todos/*").hasRole("ADMIN");
      // authorize.requestMatchers(HttpMethod.PUT,
      // "/api/v1/todos/*").hasRole("ADMIN");
      // authorize.requestMatchers(HttpMethod.DELETE,
      // "/api/v1/todos/*").hasRole("ADMIN");
      // authorize.requestMatchers(HttpMethod.PATCH,
      // "/api/v1/todos/*").hasAnyRole("ADMIN", "USER");
      authorize.requestMatchers(HttpMethod.GET, "/api/v1/todos/*").permitAll();
      authorize.anyRequest().authenticated();
    }).httpBasic(Customizer.withDefaults());
    return http.build();

  }

  // @Bean
  // public UserDetailsService userDetailsService() {
  // UserDetails user1 =
  // User.builder().username("user1").password(passwordEncoder().encode("pass")).roles("USER")
  // .build();
  // UserDetails user2 =
  // User.builder().username("user2").password(passwordEncoder().encode("ssap")).roles("USER")
  // .build();
  // UserDetails admin =
  // User.builder().username("admin").password(passwordEncoder().encode("1234")).roles("ADMIN")
  // .build();
  // return new InMemoryUserDetailsManager(user1, user2, admin);
  // }
}

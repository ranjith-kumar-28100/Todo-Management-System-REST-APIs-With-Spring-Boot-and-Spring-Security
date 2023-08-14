package inc.codeman.springboot.todomanagement.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import inc.codeman.springboot.todomanagement.entity.User;
import inc.codeman.springboot.todomanagement.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    User user = this.userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail)
        .orElseThrow(() -> new UsernameNotFoundException("Username or Email not exist"));
    Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
        .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
    return new org.springframework.security.core.userdetails.User(usernameOrEmail, user.getPassword(),
        grantedAuthorities);
  }

}

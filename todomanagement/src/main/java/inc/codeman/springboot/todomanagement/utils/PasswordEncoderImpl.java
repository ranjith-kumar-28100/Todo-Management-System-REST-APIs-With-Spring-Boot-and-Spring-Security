package inc.codeman.springboot.todomanagement.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl {
  public static void main(String[] args) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    System.out.println(passwordEncoder.encode("pass"));
    System.out.println(passwordEncoder.encode("ssap"));
    System.out.println(passwordEncoder.encode("1234"));
  }
}

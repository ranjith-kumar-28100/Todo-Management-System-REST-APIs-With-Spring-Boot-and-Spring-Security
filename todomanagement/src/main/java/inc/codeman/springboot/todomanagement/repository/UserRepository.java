package inc.codeman.springboot.todomanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import inc.codeman.springboot.todomanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserName(String userName);

  Boolean existsByEmail(String email);

  Optional<User> findByUserNameOrEmail(String userName, String email);
}

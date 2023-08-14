package inc.codeman.springboot.todomanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import inc.codeman.springboot.todomanagement.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}

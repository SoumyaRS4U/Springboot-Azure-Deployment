package com.springbootProject.Main.repository;

import com.springbootProject.Main.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}

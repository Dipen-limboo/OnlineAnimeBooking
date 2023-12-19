package com.springbootAnmte.animte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootAnmte.animte.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByRole(String role);
}

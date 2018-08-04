package org.ares.app.demo.jpa.service;

import org.ares.app.demo.jpa.entity.DUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<DUser, String> {

	DUser findByUserid(String userid);
	
}

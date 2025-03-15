package com.sec.app.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sec.app.entity.UserClass;

@Repository
public interface UserRepository extends JpaRepository<UserClass, UUID> {
	Optional<UserClass> findByEmail(String email);

}

//SELECT 
//	CONCAT(
//			SUBSTR(HEX(role_id),1,8),'-',
//			SUBSTR(HEX(role_id),9,4),'-',
//			SUBSTR(HEX(role_id),13,4),'-',
//			SUBSTR(HEX(role_id),17,4),'-',     Convert BLOB UUID into hexadecimal value
//			SUBSTR(HEX(role_id),21),
//			) AS role_id_formatted,
//			role_id,
//			role_name
//	FROM capstone_project.role;
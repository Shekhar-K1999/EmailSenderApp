package com.shekhar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shekhar.model.Email;


@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
	
}


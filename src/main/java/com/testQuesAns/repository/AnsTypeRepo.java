package com.testQuesAns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testQuesAns.domain.AnsType;

@Repository
public interface AnsTypeRepo extends JpaRepository<AnsType, Integer> {
	
}

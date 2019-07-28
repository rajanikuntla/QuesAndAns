package com.testQuesAns.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testQuesAns.domain.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

}

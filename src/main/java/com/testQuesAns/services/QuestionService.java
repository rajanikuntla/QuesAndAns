package com.testQuesAns.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testQuesAns.domain.AnsType;
import com.testQuesAns.domain.Question;
import com.testQuesAns.repository.AnsTypeRepo;
import com.testQuesAns.repository.QuestionRepo;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepo questionRepo;
	
	@Autowired
	private AnsTypeRepo ansTypeRepo;
	
	public void save(List<Question> ques) {
		questionRepo.saveAll(ques);
	}
	
	public List<AnsType> getTypesOfAns(){
		return ansTypeRepo.findAll();
	}

}

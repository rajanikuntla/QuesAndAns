package com.testQuesAns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.testQuesAns.domain.AnsType;
import com.testQuesAns.domain.Question;
import com.testQuesAns.services.QuestionService;


@RestController
public class QuestionController {
	
	@Autowired
	public QuestionService quesService;

	/*
	 * Method takes a list of questions and answers
	 * and stores it to DB
	 * 
	 */
	@RequestMapping(method= RequestMethod.POST, value="/dashboard", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveQuestions(@RequestBody List<Question> ques){
		System.out.println("entered");
		quesService.save(ques);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	/*
	 * Method returns the types of answers available
	 */
	@RequestMapping(method= RequestMethod.GET, value="/dashboard")
	public List<AnsType> getTypesOfAns(){
		return quesService.getTypesOfAns();
	}
}

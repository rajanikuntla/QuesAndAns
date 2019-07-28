package com.testQuesAns.domain;

import java.util.List;
import java.util.ListIterator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.testQuesAns.repository.AnsTypeRepo;

@Entity
@Table(name="question")
public class Question {
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column(name="ques_id")
	private Long quesId;
	
	@Column(name="name")
	private String ques;
	
	@Column(name="ans_type_id")
	private Integer ansTypeId;
	
	@JsonManagedReference
	@OneToMany(mappedBy="ques", cascade = CascadeType.ALL )
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<Answer> ans;

	@Transient
	private AnsType ansType;
	
	@Transient
	@Autowired
	private AnsTypeRepo ansTypeRepo;

	public Question() {
		
	}
	
	public Question(Long quesId, String ques, Integer ansTypeId, List<Answer> ans) {
		this.quesId = quesId;
		this.ques = ques;
		this.ansTypeId = ansTypeId;
		this.ansType = ansTypeRepo.getOne(ansTypeId);
		ListIterator<Answer> ansIterator = ans.listIterator();
		while(ansIterator.hasNext()) {
			Answer temp = (Answer)ansIterator.next();
			temp.setQues(this);
			ansIterator.set(temp);
		}
		this.ans = ans;
	}


	public Long getQuesId() {
		return quesId;
	}
	public void setQuesId(Long quesId) {
		this.quesId = quesId;
	}
	public String getQues() {
		return ques;
	}
	public void setQues(String ques) {
		this.ques = ques;
	}
	public Integer getAnsTypeId() {
		return ansTypeId;
	}
	public void setAnsTypeId(Integer ansTypeId) {
		this.ansTypeId = ansTypeId;
	}
	public List<Answer> getAns() {
		return ans;
	}
	public void setAns(List<Answer> ans) {
		this.ans = ans;
	}

	public AnsType getAnsType() {
		return ansType;
	}

	public void setAnsType(AnsType ansType) {
		this.ansType = ansType;
	}
	
	

}

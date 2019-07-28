app.controller('dashboardController', dashboardController);

function dashboardController($http, $scope, $compile, $rootScope){
	$rootScope.QuestionNo  = 0;
	
	// Initializes the controller
	
	$scope.init = function(){
		$http.get('/dashboard').then(function (response) { 
			$rootScope.ansTypes = response.data;
			
		});
	};
	
	// Sends the list of question and answers to the controller
	
	$scope.save = function(){
		var ques = $scope.getQuestions();
		if(ques.length == 0){
			$scope.errmsg ="Please add a new question"
		} else {
				$scope.questions =  ques;
				$http.post('/dashboard', ques).then(function (response) {
					$scope.cancel();
					$scope.msg ="Successfully added the questions."
				});
			}
	}
	
	// Clears the screen
	
	$scope.cancel = function(){
		$scope.questions =  [];
		var questions = angular.element(document.querySelectorAll("question"));
		for (var i = 0; i <questions.length; i++)
		      angular.element(questions[i]).remove();
		$scope.msg = "";
		$scope.errmsg = "";
	};
	
	// Adds a new question element to the screen
	
	$scope.addQues = function() {
		$rootScope.QuestionNo += 1;
		$scope.msg = "";
		$scope.errmsg = "";
	    var divElement = angular.element(document.querySelector('#quesDiv'));
	    var appendHtml = $compile('<question></question>')($scope);
	    divElement.append(appendHtml);
	 };
	 
	 // Returns the questions and answers 
	 
	 $scope.getQuestions = function(){
		 var currentNode = $scope.$$childHead;
		 var questions = [];
		 while(currentNode != null){
			 questions.push(getQuesAndSubQues(currentNode));
			 currentNode = currentNode.$$nextSibling;
		 }
		 return questions;
	 }
	 
	 // Retrieves the question and answers from $scope
	 
	 function getQuesAndSubQues(currentNode){
		 var question = currentNode.question;
		 if(question.ansType === "1"){
			 return {
				 "ques" : question.ques,
				 "ansTypeId" : question.ansType,
				 "ans" : [{
					 "ans": question.ans[0].ans 
				 }]
			 } 
		 } else if(question.ansType === "2"){
			 var tempSubQuesAns;
			 if(currentNode.$$childTail)
				 tempSubQuesAns = getQuesAndSubQues(currentNode.$$childTail);
			 return {
				 "ques" : question.ques,
				 "ansTypeId" : question.ansType,
				 "ans" :[{
					 "ans" : question.ans[0].ans,
					 "subQues" : tempSubQuesAns
				 }] 
			 }
			 
		 } else if(question.ansType === "3"){
			 var answers = [];
			 var tempAns = [];
			 var tempSubQuesAns;
			 
			 angular.forEach(question.ans, function(temp){
				 tempAns.push(temp);
			 })
			 currentNode = currentNode.$$childTail;
			 while(tempAns.length != 0){
				 var tempAnswer = tempAns.pop();
				 if(tempAnswer.ansId !== undefined){
					 if(currentNode){
						 tempSubQuesAns = getQuesAndSubQues(currentNode);
						 currentNode = currentNode.$$prevSibling;
					 }
					 answers.push({
						 "ans": tempAnswer.ans,
						 "subQues" : tempSubQuesAns
					 })
				 } else {
					 answers.push({
						 "ans": tempAnswer.ans
					 })
				 }
				 
			 }
			 while(answers.length != 0){
				 tempAns.push(answers.pop());
			 }
			 return {
				 "ques" : question.ques,
				 "ansTypeId": question.ansType,
				 "ans": tempAns
			 };
		 }
	 }
	
	$scope.init();
};

//Custom directive for each question
app.directive('question', function() {
	  return {
	    restrict: "E",
	    scope: {},
	    templateUrl:'/views/questionTemplate.html',
	    controller: function($rootScope,$compile, $scope, $element){
	    	 $scope.ansTypes = $rootScope.ansTypes;
	    	 $scope.question ={};
	    	 $scope.question.questionNo = $rootScope.QuestionNo;
	    	
	    	 // Adds the new sub question
	    	 
	    	 $scope.addSubQues = function(n) {
	    		 var id;
	    		 if(n != 0){
	    			 id = '#multipleChoiceSubQuesDiv' + $scope.question.questionNo + n;
	    			 $scope.question.ans[n-1].ansId = n-1;
	    		 } else {
	    			 id = '#singleChoiceSubQuesDiv' + $scope.question.questionNo;
	    		 }
	    		 
	    		 var divElement = angular.element(document.querySelector(id));
	    		 var appendHtml = $compile('<question></question>')($scope);
	    		 divElement.append(appendHtml);
	    	};
	    }
	  }
	});
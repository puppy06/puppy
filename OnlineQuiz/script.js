const questions = [
	{
		question: "What university am I studying at?",
		answers: [
			{ text: "University of Toronto", correct: false},
			{ text: "University of Waterloo", correct: true},
			{ text: "Harvard University", correct: false},
			{ text: "Ontario Tech University", correct: false},
		]
	},
	{
		question: "What instrument do I play?",
		answers: [
			{ text: "Violin", correct: true},
			{ text: "Piano", correct: false},
			{ text: "Viola", correct: false},
			{ text: "Triangle", correct: false},
		]
	},
	{
		question: "Which of the following languages do I know?",
		answers: [
			{ text: "Java", correct: false},
			{ text: "Python", correct: false},
			{ text: "HTML", correct: false},
			{ text: "All of the above", correct: true},
		]
	},
	{
		question: "What high school did I go to?",
		answers: [
			{ text: "Bur Oak S.S", correct: false},
			{ text: "Pierre Elliott Trudeau H.S", correct: true},
			{ text: "Unionville H.S", correct: false},
			{ text: "Milliken Mills H.S", correct: false},
		]
	},
	{
		question: "Which other project do I have listed on my website?",
		answers: [
			{ text: "Dr. House AI Diagnoses you", correct: false},
			{ text: "Music School Project", correct: false},
			{ text: "Weather Tracking Application", correct: true},
			{ text: "Java 2.0", correct: false},
		]
	}
];

const questionElement = document.getElementById("question");
const answerButtons = document.getElementById("answer-buttons");
const nextButton = document.getElementById("next-btn");
const backToMainMenuButton = document.getElementById("back-to-main-menu");


let currentQuestionIndex = 0;
let score = 0;

function startQuiz(){
	currentQuestionIndex = 0;
	score = 0;
	nextButton.innerHTML = "Next";
	showQuestion();
}

function showQuestion(){
	resetState();
	let currentQuestion = questions[currentQuestionIndex];
	let questionNo = currentQuestionIndex+1;
	questionElement.innerHTML = questionNo + ". " + currentQuestion.question; 
	
	currentQuestion.answers.forEach(answer => {
		const button = document.createElement("button");
		button.innerHTML = answer.text;
		button.classList.add("btn");
		answerButtons.appendChild(button);
		if(answer.correct){
			button.dataset.correct = answer.correct;
		}
		button.addEventListener("click", selectAnswer);
	});
}

function resetState(){
	nextButton.style.display = "none";
	while(answerButtons.firstChild){
		answerButtons.removeChild(answerButtons.firstChild);
	}
}

function selectAnswer(e){
	const selectedBtn = e.target;
	const isCorrect = selectedBtn.dataset.correct === "true";
	if(isCorrect){
		selectedBtn.classList.add("correct");
		score++;	
	}else{
		selectedBtn.classList.add("incorrect");
	}
	Array.from(answerButtons.children).forEach(button => {
		if(button.dataset.correct === "true"){
			button.classList.add("correct");
		}
		button.disabled = true;
	});
	nextButton.style.display = "block";
}

function showScore(){
	resetState();
	questionElement.innerHTML = `You scored ${score} out of ${questions.length}!`;
	nextButton.innerHTML = "Play Again";
	nextButton.style.display = "block";
	backToMainMenuButton.style.display = "block";
}


function handleNextButton(){
	currentQuestionIndex++;
	if(currentQuestionIndex<questions.length){
		showQuestion();
	}else{
		showScore();
	}
}

function generateQuestions(){
  window.location.href = "questions.html";
} 
function goToMainMenu() {
    window.location.href = "index.html"; // Redirects to index.html
}

nextButton.addEventListener("click", ()=>{
	if(currentQuestionIndex < questions.length){
		handleNextButton();
	}else{
		startQuiz();
	}
});



startQuiz();



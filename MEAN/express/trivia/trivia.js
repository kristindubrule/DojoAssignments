class Game {
	constructor() {
		this.questions = [
			new Question("What year was Seattle founded?", "1851"),
			new Question("Who is the largest employer in the Seattle area?","Boeing"),
			new Question("What is one of the most common products purchased by residents of Seattle?","Sunglasses")
		];
		this.question = this.questions[(Math.floor(Math.random()*this.questions.length))];
	}

	checkAnswer(answer) {
		return this.question.isCorrect(answer);
	}
};

class Question {
	constructor (text,answer) {
		this.text = text;
		this.answer = answer;
	}

	isCorrect(answer) {
		console.log("checking ", this.answer, answer);
		return this.answer == answer;
	}
};

module.exports = Game;
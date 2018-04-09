class Player {
	constructor(name,id) {
		this.name = name;
		this.correct = 0;
		this.incorrect = 0;
		this.score = 0;
		this.id = id;
	}
}

module.exports = Player;
import random 

class Deck(object):
	def __init__(self):
		self.cards = []
		self.suits = ["heart","spade","clove","diamond"]
		self.reset()
	def shuffle(self):
		shuffled = []
		while (len(self.cards) > 0):
			idx = random.randint(0,len(self.cards)-1)
			shuffled.append(self.cards.pop(idx))
		self.cards = shuffled
		return self

	def display_deck(self):
		print "Deck contains {} cards: ".format(len(self.cards))
		for card in self.cards:
			card.show_card()

	def draw_card(self):
		print "Drew card"
		return self.cards.pop(0)

	def deal_cards(self,numplayers,numcards):
		player_cards = []
		for player in range(0,numplayers):
			player_cards.append([])
			for card in range(0,numcards):
				player_cards[player].append(self.draw_card())
		return player_cards

	def reset(self):
		for suit in self.suits:
			for j in range(0,13):
				self.cards.append(Card(suit,j+1))
		self.shuffle()
		return self

class Card(object):
	def __init__(self,suit,value):
		self.suit = suit;
		self.value = value;

	def show_card(self):
		print "Suit: {} Value: {}".format(self.suit,self.value)

deck1 = Deck()

player_card_list = deck1.deal_cards(3, 5)
for player, card_list in enumerate(player_card_list):
	print player
	for card in card_list:
		card.show_card()
deck1.display_deck()


import random

def coin_toss():
	coins = ["head","tail"]
	tosses = {"head":0, "tail":0}
	print "Starting the program..."
	for i in range(0,5001):
		random_num = random.randint(0,1)
		coin = coins[random_num]
		#print coins[random_num], tosses{coin}
		tosses[coins[random_num]] += 1
		print "Attempt #1: Throwing a coin... It's a {}! ... Got {} head(s) so far and {} tail(s) so far".format(coins[random_num], tosses["head"], tosses["tail"])
	print "Ending the program, thank you!"

coin_toss()
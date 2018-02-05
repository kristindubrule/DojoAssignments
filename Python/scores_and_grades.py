import random

def gen_scores():
	score_table = {60:"D", 70:"C", 80:"B", 90:"A", 100:"A"}
	for i in range(0,11):
		random_num = random.randint(60,100)
		print "Score: {}; Your grade is {}".format(random_num,score_table[(random_num/10)*10])

gen_scores()


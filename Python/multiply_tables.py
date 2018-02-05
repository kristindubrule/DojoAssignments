def multTable():
	printStr = "x\t"
	for i in range(0,13):
		if i > 0:
			printStr += str(i) + "\t"
		for j in range(1,13):
			if i == 0:
				printStr += str(j) + "\t"
			else:
				printStr += str(i*j) + "\t"			
		print printStr
		printStr = ""

multTable()
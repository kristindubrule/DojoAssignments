def checkerboard():
	red = "*"
	black = " "
	for row in range(0,6):
		printStr = ""
		for col in range(0,6):
			# if row & col are both odd or both even, print red
			if row%2 == col%2:
				printStr += red
			else:
				printStr += black
		print printStr

checkerboard()
def testType(val):
	if isinstance(val,int):
		if val >= 100:
			print "That's a big number!"
		else:
			print "That's a small number"
	elif isinstance(val,str):
		if len(val) >= 50:
			print "Long sentence"
		else:
			print "Short sentence"
	elif isinstance(val,list):
		if len(val) >= 10:
			print "Big list"
		else:
			print "Short list"
	else:
		print "Unknown"

testArr = [];
testArr.append(45)
testArr.append(100)
testArr.append(455)
testArr.append(0)
testArr.append(-23)
testArr.append("Rubber baby buggy bumpers")
testArr.append("Experience is simply the name we give our mistakes")
testArr.append("Tell me and I forget. Teach me and I remember. Involve me and I learn.")
testArr.append("")
testArr.append([1,7,4,21])
testArr.append([3,5,7,34,3,2,113,65,8,89])
testArr.append([4,34,22,68,9,13,3,5,7,9,2,12,45,923])
testArr.append([])
testArr.append(['name','address','phone number','social security number'])

for val in testArr:
	print val
	testType(val)




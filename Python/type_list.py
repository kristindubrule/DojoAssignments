def printList(arr):
	newStr = ""
	sum = 0
	hasNumbers = 0
	hasStrings = 0
	listType = ""

	for val in arr:
		if isinstance(val,str):
			newStr += val + " "
			hasStrings = 1
		elif isinstance(val,int) or isinstance(val,float):
			sum += val
			hasNumbers = 1
	if hasNumbers:
		print "Number", sum
		listType = "numbers"
	if hasStrings:
		newStr = newStr[:-1]
		print "String", newStr
		listType = "strings"
	if hasNumbers + hasStrings == 2:
		listType = "mixed"
	print "The list you entered is of {} type.".format(listType)

#input
l = ['magical unicorns',19,'hello',98.98,'world']
#output
printList(l)

# input
l = [2,3,1,7,4,12]
#output
printList(l)

# input
l = ['magical','unicorns']
#output
printList(l)


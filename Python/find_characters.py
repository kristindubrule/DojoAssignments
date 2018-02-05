def containingSubstr(strList,char):
	foundList = []
	for str in strList:
		if str.count(char) > 0:
			foundList.append(str)
	print foundList

# input
word_list = ['hello','world','my','name','is','Anna']
containingSubstr(word_list, 'o')

nowords = []
containingSubstr(nowords,'o')

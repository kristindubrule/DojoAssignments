def compareLists(list1, list2):
	if len(list1) != len(list2):
		print "The lists are not the same"
		return
	for idx, val in enumerate(list1):
		if val != list2[idx]:
			print "The lists are not the same"
			return
	print "The lists are the same"
	return

list_one = [1,2,5,6,2]
list_two = [1,2,5,6,2]
compareLists(list_one, list_two)

list_one = [1,2,5,6,5]
list_two = [1,2,5,6,5,3]
compareLists(list_one, list_two)

list_one = [1,2,5,6,5,16]
list_two = [1,2,5,6,5]
compareLists(list_one, list_two)

list_one = ['celery','carrots','bread','milk']
list_two = ['celery','carrots','bread','cream']
compareLists(list_one, list_two)

def odd_even():
	odd_even = "odd";
	for i in range(1,2001):
		if i%2 == 0:
			odd_even = "odd"
		else:
			odd_even = "even"
		print "Number is {}. This is an {} number.".format(i, odd_even)

def multiply(list,num):
	new_list = []
	for val in list:
		new_list.append(val*num)
	return new_list

def layered_multiples(arr):
	new_list = []
	idx = 0
	for val in arr:
		new_list.append([])
		for i in range(0,val):
			new_list[idx].append(1)
		idx += 1
	return new_list

print "Odd Even ", odd_even()
print "Multiply ", [1,3,4,9,15], multiply([1,3,4,9,15],5)
print "Multiply ", [2,4,10,16], multiply([2,4,10,16],5)
print "Layered ", multiply([2,4,10,16],5), layered_multiples(multiply([2,4,10,16],5))

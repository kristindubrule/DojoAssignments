def make_tuples(dict1):
	tuple_list = []
	for key,value in dict1.iteritems():
		tuple_list.append((key,value))
	return tuple_list

my_dict = {
  "Speros": "(555) 555-5555",
  "Michael": "(999) 999-9999",
  "Jay": "(777) 777-7777"
}

print make_tuples(my_dict)
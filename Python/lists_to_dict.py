def listsToDict(list1,list2):
	new_dict = {}

	if len(list1) >= len(list2):
		keylist = list1
		vallist = list2
	else:
		keylist = list2
		vallist = list1

	for idx, val in enumerate(keylist):
		if idx >= len(vallist):
			new_dict[val] = "None"
		else:
			new_dict[val] = vallist[idx]
	return new_dict

name = ["Anna", "Eli", "Pariece", "Brendan", "Amy", "Shane", "Oscar"]
favorite_animal = ["horse", "cat", "spider", "giraffe", "ticks", "dolphins", "llamas"]

print listsToDict(name, favorite_animal)

cities = ["NY","DC","Boston","Seattle"]
hockey = ["Rangers","Capitals","Bruins"]

print listsToDict(cities,hockey)
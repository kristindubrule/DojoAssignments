questions = { "name": "My name is", "age": "My age is", "country":"My country of birth is", "language":"My favorite language is"}
demodata = {"name":"Kristin","age":"37","country":"US","language":"Python"}

def printDemographics(demodata):
	for key,data in demodata.iteritems():
		print questions[key], data
	print demodata.keys();

printDemographics(demodata)	
class Hospital(object):
	def __init__(self,name,max_patients,location):
		self.max_patients = max_patients
		self.location = location
		self.patients = []
		self.name = name

	def discharge_patient(self,patient_name):

		for idx, p in enumerate(self.patients):
			if p.name == patient_name:
				self.patients.pop(idx)
				p.bednumber = "None"
				break
		return self

	def admit_patient(self,patient1):
		if len(self.patients) == self.max_patients:
			print "This hospital is full; {} can't be admitted".format(patient1.name)
		else:
			self.patients.append(patient1)
			patient1.bednumber = len(self.patients)*2
		return self

	def displayInfo(self):
		print "Current hospital patients:"
		for p in self.patients:
			p.displayInfo()

class Patient(object):
	def __init__(self,uid,name,age,condition,allergies):
		self.uid = uid
		self.name = name
		self.age = age
		self.condition = condition
		self.procedures = []
		self.allergies = allergies
		self.bednumber = "None"

	def procedure(self,procedureInfo):
		procedures.append(procedureInfo)
		return self

	def displayInfo(self):
		print "ID: {}\nName: {}\nAge: {}\nCondition: {}\nAllergies: {}\nBednumber: {}".format(self.uid,self.name,self.age,self.condition,self.allergies,self.bednumber)
		return self

patient1 = Patient(1,"Kristin",37,"too much coding","sulfa")
patient2 = Patient(2,"Ed",37,"too many movies","fun")
patient3 = Patient(3,"Cordie",15,"thinks she's a human","dairy")
patient4 = Patient(4,"Lorne",10,"dry skin","cats")

hospital1 = Hospital("UW",3,"Seattle")

hospital1.admit_patient(patient1)

hospital1.displayInfo()

hospital1.admit_patient(patient2)

hospital1.admit_patient(patient3)

hospital1.admit_patient(patient4)

hospital1.displayInfo()

hospital1.discharge_patient(patient2.name).displayInfo()
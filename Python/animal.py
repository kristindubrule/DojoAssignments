class Animal(object):
	def __init__(self,name,health):
		self.name = name
		self.health = health
	def walk(self):
		self.health -= 1
		return self
	def run(self):
		self.health -= 5
		return self
	def display_health(self):
		print "Animal {} has {} health remaining.".format(self.name,self.health)

class Dog(Animal):
	def __init__(self,name):
		super(Dog,self).__init__(name,150)
	def pet(self):
		self.health += 5
		return self

class Dragon(Animal):
	def __init__(self,name):
		super(Dragon,self).__init__(name,170)
	def fly(self):
		self.health -= 10
		return self
	def display_health(self):
		super(Dragon,self).display_health()
		print "I am a dragon!"
		return self

animal1 = Animal("Yogi", 100)
for i in range(3):
	animal1.walk()
for i in range(2):
	animal1.run()
animal1.display_health()

dog1 = Dog("Spot")
# Have the Dog walk() three times, run() twice, pet() once, and have it displayHealth().
for i in range(3):
	dog1.walk()
for i in range(2):
	dog1.run()
dog1.pet().display_health()

dragon1 = Dragon("Fireball")
dragon1.fly().display_health()

animal1.display_health()



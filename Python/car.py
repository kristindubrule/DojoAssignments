class Car(object):
	def __init__(self,price,speed,fuel,mileage):
		self.price = price
		self.speed = speed
		self.fuel = fuel
		self.mileage = mileage

		if price > 10000: self.tax = 0.15
		else: self.tax = 0.12

	def displayAll(self):
		return "Price: {}\nspeed: {}mpg\nfuel: {}\nmileage: {}mpg\ntax: {}%".format(self.price, self.speed, self.fuel, self.mileage, self.tax*100)

car1 = Car(2000,35,"Full",15)
print car1.displayAll()

car2 = Car(2000,5,"Not Full",105)
print car2.displayAll()

car3 = Car(2000,15,"Kind of Full",95)
print car3.displayAll()

car4 = Car(2000,25,"Full",25)
print car4.displayAll()

car5 = Car(2000,45,"Empty",25)
print car5.displayAll()

car6 = Car(20000000,35,"Empty",15)
print car6.displayAll()

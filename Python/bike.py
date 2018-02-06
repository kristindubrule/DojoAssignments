class Bike(object):
    def __init__(self, price, max_speed):
        self.price = price
        self.max_speed = max_speed
        self.miles = 0
    def displayInfo(self):
        print "Bike price ${}, maximum speed: {}, current miles {}.".format(self.price, self.max_speed, self.miles)
        return self
    def ride(self):
        print "Riding"
        self.miles += 10
        return self
    def reverse(self):
        if self.miles > 5:
            print "Reversing"
            self.miles -= 5
        else:
            print "Can't reverse; at 0 miles"
        return self

bike1 = Bike(500, 20)
for i in range(3):
    bike1.ride()
bike1.reverse().displayInfo()

bike2 = Bike(2000, 30)
for i in range(2):
    bike2.ride()
    bike2.reverse()
bike2.displayInfo()

bike3 = Bike(10000,50)
for i in range(3):
    bike3.reverse()
bike3.displayInfo()
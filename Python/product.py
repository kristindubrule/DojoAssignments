# Price

# Item Name

# Weight

# Brand

# Status: default "for sale"

# Methods:

# Sell: changes status to "sold"

# Add tax: takes tax as a decimal amount as a parameter and returns the price of the item including sales tax

# Return: takes reason for return as a parameter and changes status accordingly. If the item is 
# being returned because it is defective, change status to "defective" and change price to 0. If it is 
# being returned in the box, like new, mark it "for sale". If the box has been, opened, set the status 
# to "used" and apply a 20% discount.

# Display Info: show all product details.

class Product(object):
	def __init__(self,price,name,weight,brand):
		self.price = price*1.0
		self.name = name
		self.weight = weight
		self.brand = brand
		self.status = "for sale"
	def sell(self):
		self.status = "sold"
		return self
	def add_tax(self, tax):
		return self.price + self.price*tax
	def return_product(self,reason):
		if reason == "defective":
			self.status = "defective"
			self.price = 0
		elif reason == "in the box, like new":
			self.status = "for sale"
		elif reason == "opened":
			self.status = "used"
			self.price -= self.price * 0.20
		return self
	def display_info(self):
		print "Name: {}\nPrice: ${}\nWeight: {}lbs\nBrand: {}\nStatus: {}".format(self.name,self.price,self.weight,self.brand,self.status)
		return self

product1 = Product(1505,"Fancy Bike",10,"Trek")
print product1.add_tax(0.09)
product1.display_info().return_product("defective").display_info()

product2 = Product(3,"Paperclip",0.25,"Clippy")
product2.return_product("opened").display_info()

product3 = Product(10,"Water bottle",1,"REI")
product3.return_product("in the box, like new").display_info()


class Product(object):
	def __init__(self,price,name,weight,brand):
		self.price = price*1.0
		self.name = name
		self.weight = weight
		self.brand = brand
		self.status = "for sale"
	def __eq__(self,other_product):
		return self.name == other_product
	def sell(self):
		self.status = "sold"
		return self
	def add_tax(self, tax):
		return self.price*(tax+1)
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

if __name__ == "__main__":
	print "Product"
	product1 = Product(1505,"Fancy Bike",10,"Trek")
	product1.return_product("defective").display_info()

	product2 = Product(3,"Paperclip",0.25,"Clippy")
	product2.return_product("opened").display_info()

	product3 = Product(10,"Water bottle",1,"REI")
	product3.return_product("in the box, like new").display_info()
	print "\n"

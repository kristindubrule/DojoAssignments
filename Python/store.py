# Store class:
# Attributes:

# products: an array of products objects

# location: store address

# owner: store owner's name

# Methods:

# add_product: add a product to the store's product list

# remove_product: should remove a product according to the product name

# inventory: print relevant information about each product in the store

# You should be able to test your classes by instantiating new objects of each class 
# and using the outlined methods to demonstrate that they work.

class Store(object):
	def __init__(self,products,location,owner):
		self.products = products
		self.location = location
		self.owner = owner
	def add_product(self,product):
		self.products.append(product)
		return self
	def remove_product(self,other_product):
		for idx, value in enumerate(self.products):
			if value.__eq__(other_product):
				self.products.pop(idx)
				break
		return self
	def inventory(self):
		for product in self.products:
			product.display_info()

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

product1 = Product(1505,"Fancy Bike",10,"Trek")
product1.return_product("defective")

product2 = Product(3,"Paperclip",0.25,"Clippy")
product2.return_product("opened")

product3 = Product(10,"Water bottle",1,"REI")
product3.return_product("in the box, like new")

store = Store([product1,product2,product3],"Seattle","Kristin")
store.inventory()
print "\n"

store.remove_product("Fancy Bike")
store.inventory()
print "\n"

store.add_product(Product(3500,"Laptop",3,"Apple"))
store.inventory()
print "\n"

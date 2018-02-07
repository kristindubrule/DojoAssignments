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

from product import Product

class Store(object):
	def __init__(self,location,owner):
		self.products = []
		self.location = location
		self.owner = owner
	def add_product(self,price,name,weight,brand):
		self.products.append(Product(price,name,weight,brand))
		return self.products[len(self.products)-1]
	def remove_product(self,other_product):
		for idx, value in enumerate(self.products):
			if value.__eq__(other_product):
				self.products.pop(idx)
				break
		return self
	def inventory(self):
		map(Product.display_info, self.products)
		return self
	def __repr__(self):
		product_string = "\n------------\n".join(map(Product.__repr__, self.products))
		return "Store: "+self.location+", Owner: "+self.owner+"\nProducts:\n------------\n"+product_string

if __name__ == "__main__":
	print "Store"
	store = Store("Seattle","Kristin").inventory()
	product1 = store.add_product(1505,"Fancy Bike",10,"Trek")
	product2 = store.add_product(3,"Paperclip",0.25,"Clippy")
	product3 = store.add_product(10,"Water bottle",1,"REI")
	store.inventory()
	print "\n"

	print "Before remove"
	store.remove_product("Fancy Bike")
	store.inventory()
	print "\n"

	print "Before add"
	store.add_product(3500,"Laptop",3,"Apple")
	store.inventory()
	print "\n"

	print store

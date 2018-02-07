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
		return self

if __name__ == "__main__":
	print "Store"
	product1 = Product(1505,"Fancy Bike",10,"Trek")
	product2 = Product(3,"Paperclip",0.25,"Clippy")
	product3 = Product(10,"Water bottle",1,"REI")

	store = Store([product1,product2,product3],"Seattle","Kristin")
	store.inventory()
	print "\n"

	store.remove_product("Fancy Bike")
	store.inventory()
	print "\n"

	store.add_product(Product(3500,"Laptop",3,"Apple")).inventory()
	print "\n"

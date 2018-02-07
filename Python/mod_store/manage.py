from store import Store
from product import Product

product1 = Product(2300,"Fast Fancy Bike",10,"Trek")
product1.return_product("defective")

product2 = Product(300,"Gold Paperclip",2,"Clippy")
product2.return_product("opened")

product3 = Product(1,"Broken Water bottle",1,"REI")
product3.return_product("in the box, like new")

store = Store([product1,product2,product3],"Seattle","Kristin").inventory()
print "\n"

store.remove_product("Fast Fancy Bike").inventory()
print "\n"

store.add_product(Product(3000,"MacBook",3,"Apple")).inventory()
print "\n"

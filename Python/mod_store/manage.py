from store import Store

store = Store("Seattle","Kristin")

product1 = store.add_product(2300,"Fast Fancy Bike",10,"Trek")
product2 = store.add_product(300,"Gold Paperclip",2,"Clippy")
product3 = store.add_product(1,"Broken Water bottle",1,"REI")
store.inventory()
print "\n"
product1.return_product("defective")
product2.return_product("opened")
product3.return_product("in the box, like new")
store.inventory()
print "\n"
store.remove_product("Fast Fancy Bike")
store.inventory()
print "\n"
store.add_product(3000,"MacBook",3,"Apple")
store.inventory()
print "\n"

print store
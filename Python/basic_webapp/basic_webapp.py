class User(object):
    def __init__(self, name, email):
        self.name = name
        self.email = email
        self.logged = True
    def login(self):
        self.logged = True
        print self.name + " is logged in."
        return self
    def logout(self):
        self.logged = False
        print self.name + " is not logged in"
        return self
    def show(self):
        print "My name is {}. You can email me at {}".format(self.name, self.email)
        return self

anna = User("Anna","anna@anna.net")
print "anna's name: ", anna.name
anna.name = "NotAnna"
print "anna's name: ", anna.name

#  try concatenating a string with an integer using each method.


#  string interpolation
first_name = "Zen"
last_name = "Coder"
print "My name is {} {}".format(first_name, last_name)

hw = "hello %s" % 'world'
print hw
# the output would be:
# hello world
class Underscore(object):
    def map(self,arg_list,f):
        # your code here
        ret_list = []
        for idx, val in enumerate(arg_list):
            ret_list.append(f(val))
        return ret_list
    def reduce(self,arg_list,f):
        # your code here
        temp = arg_list[0]
        for i in range(1,len(arg_list)):
            temp = f(temp,arg_list[i])
        return temp    
    def find(self,arg_list,f):
        for element in arg_list:
            if f(element):
                return element
        return None
        # your code here
    def filter(self,arg_list,f):
        returnarr = []
        for element in arg_list:
            if f(element):
                returnarr.append(element)
        return returnarr
        # your code
    def reject(self,arg_list,f):
        # your code
        temp = arg_list[0]
        for i in range(1,len(arg_list)-1):
            if f(arg_list[i],arg_list[i+1]):
                return 1
        return 0

# you just created a library with 5 methods!
# let's create an instance of our class

_ = Underscore() # yes we are setting our instance to a variable that is an underscore
evens = _.filter([1, 2, 3, 4, 5, 6], lambda x: x % 2 == 0)
print evens
# should return [2, 4, 6] after you finish implementing the code above

print _.reduce([47,2,9], lambda x,y: x+y)

print _.map([5,3,9], lambda x: x*x)

print _.find([6,1,0,2], lambda x: x%2 == 0)
print _.find([3,1,11,9], lambda x: x%2 == 0)

print _.reject([5,10,-2,1], lambda x,y: x > y) # reject if not sorted
print _.reject([-2,1,5,10], lambda x,y: x > y) # reject if not sorted





import itertools
import datetime

class Call(object):
	def __init__(self,uid,name,phone,time,reason):
		self.uid = uid
		self.name = name
		self.phone = phone
		self.time = time
		self.reason = reason

	def displayInfo(self):
		print "ID: {}\nName: {}\nPhone: {}\nTime: {}\nReason: {}".format(self.uid,self.name,self.phone,self.time,self.reason)
		return self

class CallCenter(object):
	def __init__(self,name):
		self.calls = []
		self.queue = 0

	def add_call(self,newcall):
		self.calls.append(newcall)
		return self

	def remove_call(self, *phone):
		if len(phone) == 0:
			self.calls.pop(0)
		else:
			for idx, c in enumerate(self.calls):
				if c.phone == phone[0]:
					self.calls.pop(idx)
		return self
	def displayInfo(self):
		print "Current queue length is {}:".format(len(self.calls))
		for c in self.calls:
			c.displayInfo()
		return self
	def sort_queue(self):
		# Insertion sort implementation
   		for idx, c in enumerate(self.calls):
			j = idx-1
 
	       # /* Move elements of arr[0..i-1], that are
	       #    greater than key, to one position ahead
	       #    of their current position */
			while j >= 0 and self.calls[j].time > c.time:
				self.calls[j+1] = self.calls[j]
				j = j-1
				self.calls[j+1] = c
		return self

call1 = Call(1,"Wallingford fire","310-999-9999",datetime.datetime(2018,2,6,10,00),"fire in house")
call1.displayInfo()
print "\n"

call3 = Call(3,"Noise CH","718-999-9999",datetime.datetime(2018,2,6,23,00),"loud party")
call3.displayInfo()
print "\n"

call2 = Call(2,"Belltown cat","206-999-9999",datetime.datetime(2017,2,6,23,30),"cat in tree")
call2.displayInfo()
print "\n"

call_center = CallCenter("Seattle")
call_center.add_call(call1)
call_center.add_call(call2)
call_center.add_call(call3)
call_center.displayInfo()

print "Sorted"	
call_center.sort_queue().displayInfo()

call_center.remove_call("310-999-9999").displayInfo()

call_center.remove_call().displayInfo()



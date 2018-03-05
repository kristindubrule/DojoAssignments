class MathDojo(object):
	def __init__(self):
		self.result = 0
	def nested_sum(self,sum_me, cur_sum):
		print sum_me
		if isinstance(sum_me,int):
			return ret
		else:
			for x in sum_me:
				cur_sum += self.nested_sum(x)
		return ret
	def add(self, arg1, *args):
		params = []
		params.append(arg1)		
		params.extend(args)
		self.result += self.nested_sum(params)
		return self

	def subtract(self, arg1, *args):
		self.result -= arg1
		for num in args:
			self.result -= num
		return self

md = MathDojo()
print md.add(2).add(2,5).add(2,5).subtract(3,2).result


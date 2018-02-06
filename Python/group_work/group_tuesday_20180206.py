# 1. Define a small program that accepts strings as integers
# 2. Have your program create a blank string
# 3. Starting at the back of the input string and walking backwards
# 3a. Push each character into the blank string
# 3b. Repeat for all characters in input string
# 4. Print the reverse string

def reverseString(str1):
	revStr = ""
	for cidx in range(0,len(str1)):
		revStr += str1[len(str1)-cidx-1]
	print revStr

reverseString("hello")


# x equals 10
# x equals x times 7
# y equals 30
# z equals y plus x
# z equals z times 3
# z equals z minus y
# z equals z divided by 27
# x equals z plus y
# y equals 3
# x equals x plus y
# return true if x modulus 10 equals 0
# otherwise return false 

def mathStuff():
	x = 10
	x *= 7 # x = 70
	y = 30 
	z = y + x # z = 100
	z *= 3 # z = 300
	z -= y # z = 270
	z /= 27 # z = 10
	x = z + y # x = 40
	y = 3 
	x += y # x = 43

	return (x%10 == 0)

print mathStuff()

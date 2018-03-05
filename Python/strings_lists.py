words = "It's thanksgiving day. It's my birthday,too!"
print words.find("day");

new_words = words.replace("day","month")
print new_words

x = [2,54,-2,7,12,98]
print "Max", max(x)
print "Min", min(x)

y = ["hello",2,54,-2,7,12,98,"world"]
print y[0], y[-1]
newy = [y[0], y[-1]]

z = [19,2,54,-2,7,12,98,32,10,-3,6,11]
z.sort()
print z

weirdz = [z[0:len(z)/2]]
weirdz += z[len(z)/2:len(z)]
print len(z), len(z)/2
print weirdz
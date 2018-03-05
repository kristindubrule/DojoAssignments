def multiply(arr,num):
    for i,x in enumerate(arr):
        arr[i] *= num
    return arr

a = [2,4,10,16]
b = multiply(a,5)
print b

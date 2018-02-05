def draw_stars(col_arr):
	for cols in col_arr:
		for i in range (0,cols):
			print "*",
		print

draw_stars([4,6,1,3,5,7,25])
draw_stars([])
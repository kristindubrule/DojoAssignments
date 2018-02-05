def draw_stars(col_arr):
	for cols in col_arr:
		if isinstance(cols,int):
			for i in range (0,cols):
				print "*",
		elif isinstance(cols,str):
			for i in range (0,len(cols)):
				print cols[0].lower(),
		print

draw_stars([4,6,1,3,5,7,25])
draw_stars([])
draw_stars([4, "Tom", 1, "Michael", 5, 7, "Jimmy Smith"])
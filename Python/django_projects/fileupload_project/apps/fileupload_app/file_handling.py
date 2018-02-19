def handle_uploaded_file(f):
	with open('/Users/kristinf/Desktop/DojoAssignments/uploaded_files/test.txt', 'wb+') as destination:
		for chunk in f.chunks():
			destination.write(chunk)
def handle_uploaded_file(f):
	with open('/Users/kristinf/Desktop/DojoAssignments/uploaded_files/'+f.name, 'wb+') as destination:
		for chunk in f.chunks():
			destination.write(chunk)
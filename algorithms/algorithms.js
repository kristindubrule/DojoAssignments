function DoubleUpPointers (arr) {
	var origArrayPosition = arr.length;
	arr.length = arr.length*2;

	for (var i = arr.length-1; i >= 0; i-=2) {
		arr[i] = arr[origArrayPosition];
		arr[i] = arr[origArrayPosition-1];
	}
	return arr;
}
console.log(DoubleUpPointers([7,4,10]));

function DoubleUpMath (arr) {
	for (var i = arr.length-1; i >=0; i--) {
		arr[i*2] = arr[i];
		arr[i*2+1] = arr[i];
	}
	return arr;
}

console.log(DoubleUpMath([7,4,10]));

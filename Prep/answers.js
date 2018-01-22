// CHAPTER 0
// Setting & swapping
var myNumber = 42;
var myName = 'Kristin';
var temp = myName;
myName = myNumber;
myNumber = temp;

// Print -52 to 1066
for (var i = -52; i < 1067; i++)
{
	console.log(i);
}

// Don't worry be happy
function beCheerful() {
	for (var i = 0; i < 98; i++)
	{
		console.log("good morning!")
	}
}

// Multiples of three, but not all
for (var i = -300; i < 1; i++)
{
	if (i%3 === 0)
	{
		if (i !== -3 && i !== -6)
		{
			console.log(i);
		}
	}
}

// Printing integers with while
var i = 2000;
while (i < 5281)
{
	console.log(i);
	i++;
}

// You say it's your birthday
function happyBirthdayToMe(num1, num2) {
	if ((num1 === 11 && num2 === 1) || (num2 === 11 && num1 === 1))
	{
		console.log("How did you know?");
	}
	else {
		console.log("Just another day...");
	}
}

// Leap year
function isLeapYear(year) {
	if (year%4 === 0)
	{
		if (year%100 === 0 && year%400 !== 0)
		{
			return false;
		}
		return true;
	}
	return false;
}

// Print & count
var fiveMultipleCount = 0;
for (var i = 512; i < 4097; i++)
{
	if (i%5 === 0)
	{
		console.log(i);
		fiveMultipleCount++;
	}
}

// Multiples of Six
var sixMultiples = 0;
while (sixMultiples < 60001)
{
	if (sixMultiples%6 === 0)
	{
		console.log(sixMultiples);
	}
	sixMultiples++;	
}

// Counting the Dojo way
for (var i = 1; i < 101; i++)
{
	var message = "Coding";
	if (i%5 === 0)
	{
		if (i%10 === 0)
		{
			message += " Dojo";
		}
		console.log(message);
	} else {
		console.log(i);
	}
}

// What do you know?
function whatDoYouKnow(incoming)
{
	console.log(incoming);
}

// Whoa that sucker's huge
// Negative odd numbers cancel out positive odd numbers, so no need to 
// do this loop.
var hugeSum = 0;
for (var i = -299999; i < 300001; i+=2)
{
	if (i%2 !== 0)
	{
		hugeSum += i;
	}
}

// Countdown by fours
var fourCounter = 2016;
while (fourCounter >= 4)
{
	console.log(fourCounter);
	fourCounter -= 4;
}

// Flexible countdown
function countdown(lowNum, highNum, mult)
{
	for (var i = highNum; i >= lowNum; i--)
	{
		if (i%mult === 0)
		{
			console.log(i);
		}
	}
}

// Final countdown
function finalCountdown(param1,param2,param3,param4)
{
	var counter = param2;
	while (counter <= param3)
	{
		if (counter%param1 === 0 && counter!=param4)
		{
			console.log(counter);
		}
		counter++;
	}
}

// CHAPTER 1 - page 20

// Countdown
function arrayCountdown(maxNumber)
{
	var arr = [];
	if (maxNumber < 0)
	{
		return arr;
	}
	for (var i = maxNumber; i >= 0; i--)
	{
		arr.push(i);
	}
	return arr;
}

// Print and return
function printFirstReturnSecond(arr)
{
	console.log(arr[0]);
	return(arr[1]);
}

// First plus length
function firstPlusLength(arr)
{
	return arr[0] + arr.length;
}

// Values greater than second
var valuesArray = [1,3,5,7,9,13];
var greaterThanCount = 0;
for (var i = 0; i < valuesArray.length; i++)
{
	if (valuesArray[i] > valuesArray[1])
	{
		console.log(valuesArray[i]);
		greaterThanCount++;
	}
}

// Greater than second, generalized
function greaterThanSecond(arr)
{
	var greaterThanCount = 0;
	console.log("Values greater than 2nd value in: ", arr);
	for (var i = 0; i < arr.length; i++)
	{
		if (arr[i] > arr[1])
		{
			console.log(arr[i]);
			greaterThanCount++;
		}
	}
	return greaterThanCount;
}

// This length, that value
function lengthAndValue(num1, num2)
{
	var arr = [];
	for (var i = 0; i < num1; i++)
	{
		arr.push(num2);
	}
	if (num1 === num2)
	{
		console.log("Jinx!");
	}
	return arr;
}

// Fit first value
function fitFirstValue(arr)
{
	if (arr[0] > arr.length)
	{
		console.log("Too big!");
		return;

	}
	if (arr[0] < arr.length)
	{
		console.log("Too small!");
		return;
	}
	console.log("Just right!");
}

// Fahrenheit to Celsius
function fahrenheitToCelsius(fDegrees)
{
	return ((fDegrees - 32) * (5.0/9.0));
}

function celsiusToFahrenheit(celsius)
{
	return (celsius * (9/5)) + 32;
}

// CHAPTER 1 - page 22

// Biggie size
function biggieArray (arr)
{
	for (var i = 0; i < arr.length; i++)
	{
		if (arr[i] > 0)
		{
			arr[i] = 'big';
		}
	}
	return arr;
}

// Print low, return high
function printLowReturnHigh (arr)
{
	var min = arr[0];
	var max = arr[0];
	for (var i = 1; i < arr.length; i++)
	{
		if (arr[i] > max)
		{
			max = arr[i];
		}
		if (arr[i] < min)
		{
			min = arr[i];
		}
	}
	console.log(min);
	return max;
}

// Print One, Return Another
function printOneReturnAnother (arr)
{
	console.log(arr[arr.length-2]);
	for (var i = 0; i < arr.length; i++)
	{
		if (i%2 !== 0)
		{
			return arr[i];
		}
	}
}

// Double vision
function doubleArrayValues (arr)
{
	var doubledArray = [];
	for (var i = 0; i < arr.length; i++)
	{
		doubledArray.push(arr[i]*2);
	}
	return doubledArray;
}

// Count positives
function countPositives (arr)
{
	positiveCount = 0;
	for (var i = 0; i < arr.length; i++)
	{
		if (arr[i] > 0)
		{
			positiveCount++;
		}
	}
	arr[arr.length-1] = positiveCount;
	return arr;
}

// Evens and Odds
function evensAndOdds(arr)
{
	oddCount = 0;
	evenCount = 0;
	for (var i = 0; i < arr.length; i++)
	{
		if (arr[i]%2 === 0)
		{
			evenCount++;
			if (evenCount >= 3)
			{
				console.log("Even more so!");
			}
			oddCount = 0;
		} else {
			oddCount++;
			if (oddCount >= 3)
			{
				console.log("That's odd!");
			}
			evenCount = 0;
		}

	}
}

// Increment seconds
function incrementSeconds (arr)
{
	for (var i = 0; i < arr.length; i++)
	{
		console.log(arr[i]);
		if (arr[i] %2 !== 0)
		{
			arr[i] += 1;
		}
	}
	return arr;
}

// Previous lengths
function previousLengths(arr)
{
	var previousLength = 0;
	for (var i = 0; i < arr.length; i++)
	{
		var tempLength = arr[i].length;
		arr[i] = previousLength;
		previousLength = tempLength;
	}
	return arr;
}

// Add 7 to most
function addSevenToMost(arr)
{
	var newArr = [];
	for (var i = 1; i < arr.length; i++)
	{
		newArr.push(arr[i]+7);
	}
	return newArr;
}

// Reverse array
function reverseInPlace(arr)
{
	var lastSwapped = arr.length-1;
	for (var i = 0; i < arr.length; i++)
	{
		var temp = arr[i];
		arr[i] = arr[lastSwapped];
		arr[lastSwapped] = temp;
		if (i === lastSwapped-1 || i === lastSwapped)
		{
			return arr;
		}
		lastSwapped--;
	}
	return arr;
}

// Outlook Negative
function makeValuesNegative(arr)
{
	var negativeArr = [];
	for (var i = 0; i < arr.length; i++)
	{
		if (arr[i] > 0)
		{
			negativeArr.push(arr[i]*-1);
		} else {
			negativeArr.push(arr[i]);
		}
	}
	return negativeArr
}

// Always hungry
function reportArrayFood (arr)
{
	var fed = false;
	for (var i = 0; i < arr.length; i++)
	{
		if (arr[i] === "food")
		{
			console.log("yummy");
			fed = true;
		}
	}
	if (!fed)
	{
		console.log("I'm hungry");
	}
}

// Swap towards center
function swapTowardsCenter (arr)
{

	var lastSwapped = arr.length-1;
	for (var i = 0; i < arr.length; i+=2)
	{
		var temp = arr[i];
		arr[i] = arr[lastSwapped];
		arr[lastSwapped] = temp;
		if (i === lastSwapped-1 || i === lastSwapped)
		{
			return arr;
		}
		lastSwapped -= 2;
	}
	return arr;
}

// Scale Array
function scaleArray (arr, num)
{
	for (var i = 0; i < arr.length; i++)
	{
		arr[i] *= num;
	}
	return arr;
}



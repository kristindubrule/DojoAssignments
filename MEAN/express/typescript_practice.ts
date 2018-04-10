//////////////////////
var myString: string;
var myNum: number;
// I can assign myString like this:
myString = "Bee stinger";
// Why is there a problem with this? What can I do to fix this?
// Added variable with type number
myNum = 9;

//////////////////////
function sayHello(name: string){
   return `Hello, ${name}!`;
}

// This is working great:
console.log(sayHello("Kermit"));
// Why isn't this working? I want it to return "Hello, 9!"
// Wrapped 9 in "" to make it a string
console.log(sayHello("9"));

//////////////////////
function fullName(firstName: string, lastName: string, middleName?: string){
   let fullName = `${firstName} ${middleName} ${lastName}`;
   return fullName;
}
// This works:
console.log(fullName("Mary", "Moore", "Tyler"));
// Made middle name optional parameter
// What do I do if someone doesn't have a middle name?
console.log(fullName("Jimbo", "Jones"));

//////////////////////
interface Student {
   firstName: string;
   lastName: string;
   belts: number;
}
function graduate(ninja: Student){
   return `Congratulations, ${ninja.firstName} ${ninja.lastName}, you earned ${ninja.belts} belts!`;
}
const christine = {
   firstName: "Christine",
   lastName: "Yang",
   belts: 2
}
const jay = {
   firstName: "Jay",
   lastName: "Patel",
   belts: 4
}
// This seems to work fine:
console.log(graduate(christine));
// This one has problems:
// Changed belt --> belts
console.log(graduate(jay));

//////////////////////
class Ninja {
   fullName: string;
   constructor(
      public firstName?: string,
      public lastName?: string){
         this.fullName = `${firstName} ${lastName}`;
      }
   debug(){
      console.log("Console.log() is my friend.")
   }
}
// This is not making an instance of Ninja, for some reason:
// Added "new" keyword, make name optional
const shane = new Ninja();
// Since I'm having trouble making an instance of Ninja, I decided to do this:
const turing = {
   fullName: "Alan Turing",
   firstName: "Alan",
   lastName: "Turing"
}
// Now I'll make a study function, which is a lot like our graduate function from above:
function study(programmer){
   return `Ready to whiteboard an algorithm, ${programmer.fullName}?`
}
// Now this has problems:
console.log(study(shane));
// Removed type from study signature
console.log(study(turing));

//////////////////////
var increment = x => x + 1;
// This works great:
console.log(increment(3));

// Added return value
var square = x => { return x * x };
// This is not showing me what I want:
console.log(square(4));
// This is not working:
// Added parens around parameters
var multiply = (x, y) => x * y;
console.log(multiply(2, 5));
// Nor is this working:
// Added {} around function body
var math = (x, y) => {
    let sum = x + y;
    let product = x * y;
    let difference = Math.abs(x - y);
    return [sum, product, difference];
}
console.log(math(3, 6));

//////////////////////
// converted birthday to be an arrow function
class Elephant {
   constructor(public age: number){}
    birthday = () => {
      this.age++;
   }
}
const babar = new Elephant(8);
setTimeout(babar.birthday, 1000)
setTimeout(function(){
   console.log(`Babar's age is ${babar.age}.`)
   }, 2000)
// Why didn't babar's age change? Fix this by using an arrow function in the Elephant class.
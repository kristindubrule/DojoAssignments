class Bike {
    miles: number;
    constructor(public price: number, public max_speed: number) { 
        this.miles = 0;
    }

    displayInfo() {
        console.log("Price: " + this.price + " Max Speed " + this.max_speed +
            " Miles " + this.miles);
        return this;
    }

    ride() {
        console.log("Riding");
        this.miles += 10;
        return this;
    }

    reverse() {
        if (this.miles >= 5) {
            console.log("Reversing");
            this.miles -= 5;
        }
        return this;
    }
}

let bike1 = new Bike(500, 15);
let bike2 = new Bike(3000, 40);
let bike3 = new Bike(2000, 35);

bike1.ride().ride().ride().displayInfo();
bike2.ride().ride().reverse().reverse().displayInfo();
bike3.reverse().reverse().reverse().displayInfo();


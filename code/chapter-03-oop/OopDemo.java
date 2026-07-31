// Chapter 3: Object-Oriented Programming
// Topic: classes, inheritance, encapsulation, polymorphism.

/*
 UNSOLVED (exercise) -- will NOT compile.
 Task: Dog should inherit from Animal and override makeSound().
 Two bugs: (1) wrong keyword to inherit, (2) @Override method
 signature doesn't match the parent method.

 class Animal {
     protected String name;
     Animal(String name) { this.name = name; }
     String makeSound() { return name + " makes a sound."; }
 }

 class Dog implements Animal {              // <-- bug: should be 'extends'
     Dog(String name) { super(name); }
     @Override
     String makeSound(String extra) {        // <-- bug: signature mismatch
         return name + " barks.";
     }
 }
*/

// SOLVED
class Animal {
    protected String name;
    private final int legCount; // encapsulated: no public setter

    Animal(String name, int legCount) {
        this.name = name;
        this.legCount = legCount;
    }

    String makeSound() {
        return name + " makes a generic animal sound.";
    }

    int getLegCount() {
        return legCount;
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name, 4);
    }

    @Override
    String makeSound() {
        return name + " barks.";
    }
}

class Bird extends Animal {
    Bird(String name) {
        super(name, 2);
    }

    @Override
    String makeSound() {
        return name + " chirps.";
    }
}

public class OopDemo {
    public static void main(String[] args) {
        // Polymorphism: an Animal reference, different runtime behavior
        Animal[] animals = { new Dog("Rex"), new Bird("Tweety") };

        for (Animal a : animals) {
            System.out.println(a.makeSound() + " (" + a.getLegCount() + " legs)");
        }
    }
}

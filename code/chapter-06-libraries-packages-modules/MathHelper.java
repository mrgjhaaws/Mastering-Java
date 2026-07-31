// Chapter 6: Libraries, Packages, and Modules
// Topic: organizing code with packages, and using the standard library.

/*
 UNSOLVED (exercise) -- will NOT compile.
 Task: this file declares itself in package "com.example.util" but is
 saved at the wrong path, AND it tries to use a class from another
 package without importing it.

 package com.example.util;

 public class MathHelper {
     public static double circleArea(double radius) {
         return PI * radius * radius;   // <-- bug: PI isn't imported/qualified
     }
 }
*/

// SOLVED
package com.example.util;

import java.util.List;
import java.util.ArrayList;

public class MathHelper {

    // A package-level constant -- other classes in this package can use
    // it without an import, since they share the same package.
    public static final double PI_APPROX = 3.14159;

    public static double circleArea(double radius) {
        return Math.PI * radius * radius; // java.lang.Math is auto-imported
    }

    public static List<Double> areasFor(double... radii) {
        List<Double> areas = new ArrayList<>();
        for (double r : radii) {
            areas.add(circleArea(r));
        }
        return areas;
    }

    public static void main(String[] args) {
        System.out.println("Area of radius 2: " + circleArea(2));
        System.out.println("Areas: " + areasFor(1, 2, 3));
        System.out.println();
        System.out.println("Fully-qualified name of this class: "
                + MathHelper.class.getName());
        System.out.println("Package: " + MathHelper.class.getPackageName());
    }
}

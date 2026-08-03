import java.util.*;

/*
=========================================================
                BUILDER PATTERN
=========================================================

Problem:
--------
Suppose we are ordering a Burger Meal.

Required:
1. Bun Type
2. Patty

Optional:
1. Cheese
2. Toppings
3. Side
4. Drink

Without Builder:

BurgerMeal meal = new BurgerMeal(
    "Wheat",
    "Chicken",
    true,
    toppings,
    "Fries",
    "Coke"
);

Problems:
---------
1. Constructor becomes very long.
2. Hard to remember what each value means.
3. Many constructor combinations are needed.
4. Easy to pass parameters in the wrong order.

Solution:
---------
Create the object step by step.

BurgerMeal meal = new BurgerMeal.Builder(...)
                    .withCheese(true)
                    .withDrink("Coke")
                    .build();

This makes the code easy to read and maintain.

=========================================================
*/

/*
=========================================================
                PRODUCT CLASS

This is the final object that we want to build.

Notice:
The constructor is PRIVATE.

Why?

Because we don't want anyone to create BurgerMeal
directly using "new".

Instead, everyone must use the Builder.

=========================================================
*/

class BurgerMeal {

    /*
     * --------------------------
     * Required Fields
     * --------------------------
     */

    private final String bunType;
    private final String patty;

    /*
     * --------------------------
     * Optional Fields
     * --------------------------
     */

    private final boolean hasCheese;
    private final List<String> toppings;
    private final String side;
    private final String drink;

    /*
     * ----------------------------------------------------
     * Private Constructor
     * 
     * Only Builder can call this constructor.
     * 
     * It copies all values collected by the Builder
     * into the final BurgerMeal object.
     * ----------------------------------------------------
     */

    private BurgerMeal(BurgerBuilder builder) {

        this.bunType = builder.bunType;
        this.patty = builder.patty;

        this.hasCheese = builder.hasCheese;
        this.toppings = builder.toppings;
        this.side = builder.side;
        this.drink = builder.drink;
    }

    /*
     * ----------------------------------------------------
     * Display Burger Details
     * ----------------------------------------------------
     */

    public void display() {

        System.out.println("========== Burger Meal ==========");
        System.out.println("Bun Type : " + bunType);
        System.out.println("Patty    : " + patty);
        System.out.println("Cheese   : " + hasCheese);
        System.out.println("Toppings : " + toppings);
        System.out.println("Side     : " + side);
        System.out.println("Drink    : " + drink);
        System.out.println();
    }

    /*
     * =====================================================
     * BUILDER CLASS
     * 
     * Builder collects information
     * step by step.
     * 
     * It does NOT create BurgerMeal immediately.
     * 
     * It waits until build() is called.
     * 
     * =====================================================
     */

    public static class BurgerBuilder {

        /*
         * --------------------------
         * Required Fields
         * 
         * These are final because they
         * must always be provided.
         * --------------------------
         */

        private final String bunType;
        private final String patty;

        /*
         * --------------------------
         * Optional Fields
         * --------------------------
         */

        private boolean hasCheese;

        private List<String> toppings = new ArrayList<>();

        private String side;

        private String drink;

        /*
         * ------------------------------------------------
         * Builder Constructor
         * 
         * Only required fields are asked here.
         * 
         * Optional fields can be added later.
         * ------------------------------------------------
         */

        public BurgerBuilder(String bunType, String patty) {

            this.bunType = bunType;
            this.patty = patty;
        }

        /*
         * ------------------------------------------------
         * Add Cheese
         * 
         * Returns Builder itself.
         * 
         * Returning "this" allows method chaining.
         * 
         * Example
         * 
         * builder.withCheese(true)
         * .withDrink("Coke")
         * ------------------------------------------------
         */

        public BurgerBuilder withCheese(boolean hasCheese) {

            this.hasCheese = hasCheese;

            return this;
        }

        /*
         * ------------------------------------------------
         * Add Toppings
         * ------------------------------------------------
         */

        public BurgerBuilder withToppings(List<String> toppings) {

            this.toppings = toppings;

            return this;
        }

        /*
         * ------------------------------------------------
         * Add Side
         * ------------------------------------------------
         */

        public BurgerBuilder withSide(String side) {

            this.side = side;

            return this;
        }

        /*
         * ------------------------------------------------
         * Add Drink
         * ------------------------------------------------
         */

        public BurgerBuilder withDrink(String drink) {

            this.drink = drink;

            return this;
        }

        /*
         * ------------------------------------------------
         * Build Method
         * 
         * Finally create the BurgerMeal object.
         * 
         * Until build() is called,
         * only Builder exists.
         * 
         * build() returns the completed object.
         * 
         * ------------------------------------------------
         */

        public BurgerMeal build() {

            return new BurgerMeal(this);
        }
    }
}

/*
 * =========================================================
 * CLIENT
 * 
 * Client never calls
 * 
 * new BurgerMeal(...)
 * 
 * Instead it uses the Builder.
 * 
 * =========================================================
 */

public class Main {

    public static void main(String[] args) {

        /*
         * -----------------------------------------
         * Plain Burger
         * 
         * Only required fields.
         * -----------------------------------------
         */

        BurgerMeal plainBurger =

                new BurgerMeal.BurgerBuilder(

                        "Wheat",

                        "Veg"

                ).build();

        plainBurger.display();

        /*
         * -----------------------------------------
         * Burger with Cheese
         * -----------------------------------------
         */

        BurgerMeal cheeseBurger =

                new BurgerMeal.BurgerBuilder(

                        "Wheat",

                        "Veg"

                )

                        .withCheese(true)

                        .build();

        cheeseBurger.display();

        /*
         * -----------------------------------------
         * Fully Loaded Burger
         * -----------------------------------------
         */

        List<String> toppings = Arrays.asList(

                "Lettuce",

                "Onion",

                "Jalapeno"

        );

        BurgerMeal loadedBurger =

                new BurgerMeal.BurgerBuilder(

                        "Multigrain",

                        "Chicken"

                )

                        .withCheese(true)

                        .withToppings(toppings)

                        .withSide("Fries")

                        .withDrink("Coke")

                        .build();

        loadedBurger.display();
    }
}
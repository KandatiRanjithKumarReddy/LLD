/*
============================================================
                PROTOTYPE PATTERN
============================================================

Definition:
-----------
Instead of creating a new object from scratch,
create a copy of an existing object.

Why?

Because creating some objects can be expensive.

Example:
--------
Suppose we are developing a game.

Every Enemy needs:

1. Images
2. Animations
3. Sounds
4. Weapons

Loading these resources is expensive.

Instead of creating every Enemy from scratch,
we create ONE Enemy and copy it.

That original object is called the Prototype.

============================================================
*/

class Enemy {

    private String type;
    private int health;
    private String weapon;

    /*
     * --------------------------------------------------------
     * This constructor represents expensive work.
     * Imagine loading images, animations and sounds.
     * --------------------------------------------------------
     */
    public Enemy(String type, int health, String weapon) {

        System.out.println("Loading Images...");
        System.out.println("Loading Animations...");
        System.out.println("Loading Sounds...");
        System.out.println();

        this.type = type;
        this.health = health;
        this.weapon = weapon;
    }

    /*
     * --------------------------------------------------------
     * Private constructor.
     * 
     * It creates an empty object.
     * 
     * We use it only while copying.
     * 
     * Notice that expensive loading DOES NOT happen here.
     * --------------------------------------------------------
     */
    private Enemy() {

    }

    /*
     * --------------------------------------------------------
     * Prototype Method
     * 
     * Instead of creating everything again,
     * simply copy the existing object's data.
     * 
     * This method creates a NEW object
     * whose values are copied from the current object.
     * --------------------------------------------------------
     */
    public Enemy cloneEnemy() {

        Enemy copy = new Enemy();

        copy.type = this.type;
        copy.health = this.health;
        copy.weapon = this.weapon;

        return copy;
    }

    /*
     * --------------------------------------------------------
     * Used to modify copied object.
     * 
     * This proves that every copy is independent.
     * --------------------------------------------------------
     */
    public void setHealth(int health) {
        this.health = health;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    /*
     * --------------------------------------------------------
     * Display Enemy Information
     * --------------------------------------------------------
     */
    public void display() {

        System.out.println("Enemy Type : " + type);
        System.out.println("Health     : " + health);
        System.out.println("Weapon     : " + weapon);

        System.out.println("---------------------------");
    }
}

/*
 * ============================================================
 * CLIENT
 * ============================================================
 */

public class ProtoType {

    public static void main(String[] args) {

        /*
         * ----------------------------------------------------
         * Step 1
         * 
         * Create ONE original object.
         * 
         * Expensive constructor executes ONLY ONCE.
         * ----------------------------------------------------
         */

        Enemy zombie = new Enemy("Zombie", 100, "Axe");

        /*
         * ----------------------------------------------------
         * Step 2
         * 
         * Instead of calling
         * 
         * new Enemy()
         * 
         * again,
         * 
         * simply clone the prototype.
         * ----------------------------------------------------
         */

        Enemy zombie2 = zombie.cloneEnemy();

        Enemy zombie3 = zombie.cloneEnemy();

        /*
         * ----------------------------------------------------
         * Step 3
         * 
         * Customize copied objects.
         * 
         * Original object remains unchanged.
         * ----------------------------------------------------
         */

        zombie2.setHealth(150);

        zombie3.setWeapon("Sword");

        /*
         * ----------------------------------------------------
         * Display All Objects
         * ----------------------------------------------------
         */

        System.out.println("Original Enemy");

        zombie.display();

        System.out.println("Copied Enemy 1");

        zombie2.display();

        System.out.println("Copied Enemy 2");

        zombie3.display();
    }
}
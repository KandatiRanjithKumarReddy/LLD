/*
===========================================================
            ABSTRACT FACTORY PATTERN
===========================================================

Problem:
--------
We are building a UI application that supports
multiple Operating Systems.

Supported OS:
1. Windows
2. Mac

Each Operating System has its own UI components.

Windows
--------
WindowsButton
WindowsTextBox

Mac
---
MacButton
MacTextBox

Without Abstract Factory:

Main creates every object itself.

Problems:
---------
1. Main knows every concrete class.
2. Every new Operating System requires modifying Main.
3. Easy to mix Windows and Mac components.

Abstract Factory solves this problem by creating
an entire family of related objects.
===========================================================
*/

/*
===========================================================
                    PRODUCT INTERFACES
===========================================================

These are contracts.

Every Button must implement draw().
Every TextBox must implement draw().

Main depends on these interfaces instead of
concrete classes.
===========================================================
*/

interface Button {
    void draw();
}

interface TextBox {
    void draw();
}

/*
 * ===========================================================
 * WINDOWS IMPLEMENTATIONS
 * ===========================================================
 */

class WindowsButton implements Button {

    @Override
    public void draw() {
        System.out.println("Windows Button");
    }
}

class WindowsTextBox implements TextBox {

    @Override
    public void draw() {
        System.out.println("Windows TextBox");
    }
}

/*
 * ===========================================================
 * MAC IMPLEMENTATIONS
 * ===========================================================
 */

class MacButton implements Button {

    @Override
    public void draw() {
        System.out.println("Mac Button");
    }
}

class MacTextBox implements TextBox {

    @Override
    public void draw() {
        System.out.println("Mac TextBox");
    }
}

/*
 * ===========================================================
 * ABSTRACT FACTORY
 * 
 * This interface promises that every factory
 * can create an entire family of UI components.
 * 
 * Notice:
 * 
 * It returns Button and TextBox.
 * 
 * NOT
 * 
 * WindowsButton
 * MacButton
 * 
 * That keeps Main independent of concrete classes.
 * ===========================================================
 */

interface UIFactory {

    Button createButton();

    TextBox createTextBox();
}

/*
 * ===========================================================
 * WINDOWS FACTORY
 * 
 * Creates only Windows components.
 * 
 * Whenever Main asks for a Button,
 * it receives a WindowsButton.
 * 
 * Whenever Main asks for a TextBox,
 * it receives a WindowsTextBox.
 * ===========================================================
 */

class WindowsFactory implements UIFactory {

    @Override
    public Button createButton() {

        return new WindowsButton();
    }

    @Override
    public TextBox createTextBox() {

        return new WindowsTextBox();
    }
}

/*
 * ===========================================================
 * MAC FACTORY
 * 
 * Creates only Mac components.
 * 
 * This guarantees that every component
 * belongs to the same family.
 * ===========================================================
 */

class MacFactory implements UIFactory {

    @Override
    public Button createButton() {

        return new MacButton();
    }

    @Override
    public TextBox createTextBox() {

        return new MacTextBox();
    }
}

/*
 * ===========================================================
 * CLIENT
 * 
 * The client knows ONLY
 * 
 * UIFactory
 * Button
 * TextBox
 * 
 * It DOES NOT know
 * 
 * WindowsButton
 * MacButton
 * WindowsTextBox
 * MacTextBox
 * 
 * This reduces coupling.
 * 
 * Coupling means:
 * How much one class depends on another class.
 * 
 * Less dependency = Better design.
 * ===========================================================
 */

public class AFP {

    public static void main(String[] args) {

        /*
         * Decide which Operating System we are using.
         * 
         * Try changing this to
         * 
         * "MAC"
         * 
         * without changing anything else.
         */

        String os = "WINDOWS";

        /*
         * Create the appropriate factory.
         * 
         * Main decides ONLY ONCE
         * which factory to use.
         */

        UIFactory factory;

        if (os.equalsIgnoreCase("WINDOWS")) {

            factory = new WindowsFactory();

        } else {

            factory = new MacFactory();
        }

        /*
         * Main never creates
         * 
         * new WindowsButton()
         * 
         * or
         * 
         * new MacButton()
         * 
         * It simply asks the factory.
         */

        Button button = factory.createButton();

        TextBox textBox = factory.createTextBox();

        /*
         * Draw UI
         */

        button.draw();

        textBox.draw();

    }
}
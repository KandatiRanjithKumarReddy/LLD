class Singleton { // The Singleton Pattern ensures that a class has only one instance and provides
                  // a global point of access to that instance

    // Step 2: Private static instance (created only once)
    private static final Singleton instance = new Singleton();

    // Step 1: Private constructor
    private Singleton() {
        System.out.println("Singleton Instance Created");
    }

    // Step 3: Public static method to return the same instance
    public static Singleton getInstance() {
        return instance;
    }
}

public class SingletonDemo {
    public static void main(String[] args) {

        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println(s1 == s2); // true
    }
}

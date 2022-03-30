package networking_programming.thread.demo.demo1;

// A simplest example: using a single class which extends Thread

public class SimpleThread extends Thread {
    SimpleThread(String name) {
        super(name);
    }

    public static void main(String[] args) {
        System.out.println("hello");
        Thread thread = new SimpleThread("T1");
        thread.start();

    }

    public void run() {
        System.out.println(getName() + ": " + "thread is running");
    }
}

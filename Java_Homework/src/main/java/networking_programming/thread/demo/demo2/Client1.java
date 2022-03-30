//Wrong example: Thread without a synchronized block

package networking_programming.thread.demo.demo2;

public class Client1 {
    public static final Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        ThreadC t = new ThreadC();
        t.start();
        synchronized (object) {
            object.wait();
            System.out.println("Total is " + t.total);
        }
    }
}

class ThreadC extends Thread {
    int total;

    public void run() {
        synchronized (Client1.object) {
            for (int i = 0; i <= 10; i++)
                total += i;
            Client1.object.notify();
        }
    }
}


package networking_programming.thread.homework1.util;

public class valuePutter extends Thread {

    private final Message message;

    public valuePutter(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        while (true)
            this.message.putValue();
    }
}
package networking_programming.thread.homework1.util;

public class valueGetter extends Thread {

    private final Message message;

    public valueGetter(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        while (true)
            this.message.getValue();
    }
}


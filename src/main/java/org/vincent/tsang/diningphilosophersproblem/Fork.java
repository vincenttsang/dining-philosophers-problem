package org.vincent.tsang.diningphilosophersproblem;

public class Fork {
    private final int id;
    private boolean picked_up;

    Fork(int id) {
        this.picked_up = false;
        this.id = id;
    }

    public boolean isPicked_up() {
        return picked_up;
    }

    public synchronized void lock() {
        while (picked_up) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.picked_up = true;
    }

    public synchronized void release() {
        this.picked_up = false;
        notifyAll();
    }
}

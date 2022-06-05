package org.vincent.tsang.diningphilosophersproblem;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Philosopher implements Runnable {
    static String log;
    private final int id;
    private final Fork left_fork;
    private final Fork right_fork;
    private Label my_label;
    private TextArea log_text_area;

    Philosopher(int id, Fork left_fork, Fork right_fork) {
        this.left_fork = left_fork;
        this.right_fork = right_fork;
        this.id = id;
        log = "";
    }

    public void setLog_text_area(TextArea log_text_area) {
        this.log_text_area = log_text_area;
    }

    public void setMy_label(Label my_label) {
        this.my_label = my_label;
    }

    public void think() {
        System.out.println("哲学家" + id + "正在思考...");
        log += "哲学家" + id + "正在思考...\n";
        Platform.runLater(() -> {
            my_label.setText("哲学家" + id + "正在思考...");
            log_text_area.setText(log);
        });
        try {
            Thread.sleep((int) (Math.random() * 6) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eat() {
        System.out.println("哲学家" + id + "正在进食...");
        log += "哲学家" + id + "正在进食...\n";
        Platform.runLater(() -> {
            my_label.setText("哲学家" + id + "正在进食...");
            log_text_area.setText(log);
        });
        try {
            Thread.sleep((int) (Math.random() * 8) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.left_fork.release();
        System.out.println("哲学家" + id + "放下左叉");
        log += "哲学家" + id + "放下左叉\n";
        this.right_fork.release();
        System.out.println("哲学家" + id + "放下右叉");
        log += "哲学家" + id + "放下右叉\n";
        Platform.runLater(() -> log_text_area.setText(log));
    }

    public void wait_for_fork() {
        System.out.println("哲学家" + id + "正在等待...");
        log += "哲学家" + id + "正在等待...\n";
        while (this.left_fork.isPicked_up() == false || this.left_fork.isPicked_up() == false) {
            if (!this.left_fork.isPicked_up()) {
                System.out.println("哲学家" + id + "缺少左叉...");
                log += "哲学家" + id + "缺少左叉...\n";
                Platform.runLater(() -> {
                    my_label.setText("哲学家" + id + "等待左叉...");
                    log_text_area.setText(log);
                });
            }
            if (!this.right_fork.isPicked_up()) {
                System.out.println("哲学家" + id + "缺少右叉...");
                log += "哲学家" + id + "缺少右叉...\n";
                Platform.runLater(() -> {
                    my_label.setText("哲学家" + id + "等待右叉...");
                    log_text_area.setText(log);
                });
            }
            this.left_fork.lock();
            this.right_fork.lock();
        }
    }

    public void run() {
        while (true) {
            think();
            wait_for_fork();
            eat();
        }
    }
}

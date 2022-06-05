package org.vincent.tsang.diningphilosophersproblem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MethodController {
    private final List<Fork> forks = new LinkedList<>();
    private final List<Label> labels = new LinkedList<>();
    ExecutorService event = Executors.newCachedThreadPool();
    @FXML
    private ImageView Phi_0;
    @FXML
    private ImageView Phi_1;
    @FXML
    private ImageView Phi_2;
    @FXML
    private ImageView Phi_3;
    @FXML
    private ImageView Phi_4;
    @FXML
    private TextArea LogTextArea;
    @FXML
    private Label Label_0;
    @FXML
    private Label Label_1;
    @FXML
    private Label Label_2;
    @FXML
    private Label Label_3;
    @FXML
    private Label Label_4;

    public void init() {
        for (int i = 0; i < 5; i++) {
            forks.add(new Fork(i));
        }
        labels.add(Label_0);
        labels.add(Label_1);
        labels.add(Label_2);
        labels.add(Label_3);
        labels.add(Label_4);
        LogTextArea.setEditable(false);
    }

    public void method_1() {
        //不作任何处理，有可能死锁
        for (int i = 0; i < 5; i++) {
            Philosopher philosopher = new Philosopher(i, forks.get((i + 4) % 5), forks.get(i));
            philosopher.setMy_label(labels.get(i));
            philosopher.setLog_text_area(LogTextArea);
            event.execute(philosopher);
        }
    }

    public void method_2() {
        //最多允许4个哲学家进餐
        for (int i = 0; i < 4; i++) {
            Philosopher philosopher = new Philosopher(i, forks.get((i + 4) % 5), forks.get(i));
            philosopher.setMy_label(labels.get(i));
            philosopher.setLog_text_area(LogTextArea);
            event.execute(philosopher);
        }
        Philosopher philosopher = new Philosopher(4, forks.get(4), forks.get(3));
        philosopher.setMy_label(labels.get(4));
        philosopher.setLog_text_area(LogTextArea);
        event.execute(philosopher);
    }

    public void method_3() {
        //偶数哲学家先拿右叉，奇数哲学家先拿左叉
        for (int i = 0; i < 5; i++) {
            Philosopher philosopher;
            if (i % 2 == 0) {
                philosopher = new Philosopher(i, forks.get((i + 4) % 5), forks.get(i));
            } else {
                philosopher = new Philosopher(i, forks.get(i), forks.get((i + 4) % 5));
            }
            philosopher.setMy_label(labels.get(i));
            philosopher.setLog_text_area(LogTextArea);
            event.execute(philosopher);
        }
    }

}
package org.vincent.tsang.diningphilosophersproblem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.System.exit;

public class StartScreenController {
    @FXML
    private MenuButton MenuBtn;
    @FXML
    private TextArea MyTextArea;
    @FXML
    private Button StartBtn;

    private int method;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void method_1(ActionEvent actionEvent) {
        StartBtn.setDisable(false);
        MenuBtn.setText("方法1");
        MyTextArea.setEditable(false);
        MyTextArea.setText("方法1：\n不作任何处理，可能导致死锁");
        method = 1;
    }

    @FXML
    public void method_2(ActionEvent actionEvent) {
        StartBtn.setDisable(false);
        MenuBtn.setText("方法2");
        MyTextArea.setEditable(false);
        MyTextArea.setText("方法2：\n最多允许4个哲学家进餐");
        method = 2;
    }

    @FXML
    public void method_3(ActionEvent actionEvent) {
        StartBtn.setDisable(false);
        MenuBtn.setText("方法3");
        MyTextArea.setEditable(false);
        MyTextArea.setText("方法3：\n偶数哲学家先拿右叉，\n奇数哲学家先拿左叉");
        method = 3;
    }

    @FXML
    public void ClickedStart(MouseEvent mouseEvent) throws IOException {
        stage.close();
        FXMLLoader mainLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Parent mainRoot = mainLoader.load();
        MethodController controller = mainLoader.getController();
        controller.init();
        Scene mainScene = new Scene(mainRoot, 1270, 930);
        stage.setTitle("Dining Philosophers Problem: " + "方法" + method);
        stage.setScene(mainScene);
        stage.setOnCloseRequest(event -> System.exit(0));
        stage.show();
        switch (method) {
            case 1 -> {
                controller.method_1();
            }
            case 2 -> {
                controller.method_2();
            }
            case 3 -> {
                controller.method_3();
            }
            default -> {
                System.out.println("Error!");
                exit(-1);
            }
        }
    }
}

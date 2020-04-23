package sample;

import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField a_field;

    @FXML
    private TextField b_field;

    @FXML
    private TextField c_field;

    @FXML
    private TextField res_field;

    @FXML
    private Button send_button;

    public Controller() throws SocketException, UnknownHostException {
    }

    @FXML
    void send() {
        Calc calc = new Calc();
        String a = a_field.getText();
        String b = b_field.getText();
        String c = c_field.getText();
        try {
            String res = calc.result(Byte.parseByte(a), Byte.parseByte(b), Byte.parseByte(c));
            res_field.setText(res);

        } catch (IOException e) {
            res_field.setText(String.valueOf(e));
        }
    }

    @FXML
    void initialize() {
    }
}

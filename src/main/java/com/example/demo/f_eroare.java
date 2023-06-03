package com.example.demo;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class f_eroare {

    public static void fereastra_de_error(String text)
    {
        Stage stage1=new Stage();
        Button buton=new Button("ok");
        VBox v=new VBox(4,new Label(text),buton);
        stage1.setScene(new Scene(v,200,200));
        buton.setOnAction(
                actionEvent1 -> {
                    stage1.close();
                }
        );
        v.setAlignment(Pos.CENTER);
        stage1.show();
    }
}

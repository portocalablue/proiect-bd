package com.example.demo;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HelloApplication extends Application {

    Stage stage1;
    Config_user config_user=new Config_user();

    Config_clienti config_clienti;

    Config_sali config_sali;
    config_intrari config_intrari=new config_intrari();

    Utilizatori utilizator_activ=null;

    public ListView<String>Lista_optiuni(int tip)
    {
        ListView<String> sir=new ListView<String>();
        if(tip==1)
        {
            sir.getItems().add("Intrari/Iesiri");
            sir.getItems().add("");
            sir.getItems().add("Configurare Clienti");
            sir.getItems().add("");
            sir.getItems().add("Configurare Sali");
            sir.getItems().add("");
        }
        else if(tip==2)
        {
            sir.getItems().add("Configurare Sali");
            sir.getItems().add("");
        }
        else if(tip==3)
        {

            sir.getItems().add("Intrari/Iesiri");
            sir.getItems().add("");
            sir.getItems().add("Configurare Clienti");
            sir.getItems().add("");
            sir.getItems().add("Configurare Sali");
            sir.getItems().add("");
            sir.getItems().add("Configurare utilizatori");
            sir.getItems().add("");
        }

        return sir;
    }

    public VBox panou_login()
    {

        Button b=new Button("Login");
        Label alias_l=new Label("User Name");
        Label password_l=new Label("Password");
        Label titlu=new Label("Aplicatie de inot");
        TextField alias_t=new TextField();
        TextField password_t=new TextField();
        HBox alias_login=new HBox(10,alias_l,alias_t);
        HBox password_login=new HBox(10,password_l,password_t);

        VBox v=new VBox(50,titlu,alias_login,password_login,b);
        b.setOnAction(actionEvent -> {
            utilizator_activ=Baza_de_date.login(alias_t.getText(),password_t.getText());
            if(utilizator_activ!=null)
            {
                config_clienti=new Config_clienti(utilizator_activ.tip_user);
                config_sali=new Config_sali(utilizator_activ.tip_user);
                Scene s=new Scene(getpanou(),800,600);
                stage1.setScene(s);
            }
            else
            {
                f_eroare.fereastra_de_error("Username sau Parola gresita!");
            }

        });
        Font f=new Font(30);
        titlu.setFont(f);
        alias_login.setAlignment(Pos.CENTER);
        password_login.setAlignment(Pos.CENTER);
        return v;
    }
    public BorderPane getlogin()
    {

        BorderPane p=new BorderPane();
        VBox v=panou_login();
        p.setCenter(v);
        v.setAlignment(Pos.TOP_CENTER);
        return p;
    }
    public BorderPane getpanou()
    {
        BorderPane pane=new BorderPane();
        ListView<String>sir=Lista_optiuni(utilizator_activ.tip_user);
        pane.setLeft(sir);
        pane.setCenter(new VBox());
        MultipleSelectionModel<String> lvSelModel = sir.getSelectionModel();
        lvSelModel.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> changed, String oldVal, String newVal) {

                String selItems = "";
                ObservableList<String> selected =sir.getSelectionModel().getSelectedItems();
                for (int i = 0; i < selected.size(); i++) {
                    if(selected.get(i).equals("Configurare utilizatori"))
                    {
                        pane.setCenter(config_user.config_user());
                    }
                    else if(selected.get(i).equals("Configurare Clienti"))
                    {
                        pane.setCenter(config_clienti.config_clienti());

                    }
                    else if(selected.get(i).equals("Configurare Sali"))
                    {
                        pane.setCenter(config_sali.Sali_config());
                    }
                    else if(selected.get(i).equals("Intrari/Iesiri"))
                    {
                        pane.setCenter(config_intrari.getConfig_Intrari());
                    }
                }
            }
        });

        return pane;
    }


    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(getlogin(), 400, 300);
        this.stage1=stage;
        stage1.setTitle("Proiect BD");
        stage1.setScene(scene);
        stage1.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
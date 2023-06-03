package com.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Config_sali {
    private TableView<Sali> table=new TableView<>();

    static Sali sala_activ;
    static int nr,id;
    List<Sali> Sali_l;

    ObservableList<Sali> lista_sali;

    Config_sali(int id)
    {
        this.id=id;
    }


    public ObservableList<Sali> getSali()
    {
        List<Sali>temp=new ArrayList<>();
        for(int i=0;i<Sali_l.size();i++)
        {
            temp.add(Sali_l.get(i));
        }
        ObservableList<Sali> observableList= FXCollections.observableArrayList(temp);
        return observableList;
    }
    public TableView<Sali> gettable_sali_config()
    {
        TableView<Sali> tableView=new TableView<Sali>();
        tableView.setEditable(true);
        TableColumn secondNameCol = new TableColumn("Rand");
        secondNameCol.setMinWidth(110);
        secondNameCol.setCellValueFactory(new PropertyValueFactory<Clienti,Integer>("rand"));
        TableColumn forthNameCol=new TableColumn("Nume antrenor");
        forthNameCol.setMinWidth(110);
        forthNameCol.setCellValueFactory(new PropertyValueFactory<Clienti,String>("den_ocupta"));
        TableColumn fifthNameCol=new TableColumn("Rezervat");
        fifthNameCol.setMinWidth(100);
        fifthNameCol.setCellValueFactory(new PropertyValueFactory<Clienti,String>("rezt"));
        TableColumn sixthNameCol=new TableColumn("Ingrijire");
        sixthNameCol.setMinWidth(100);
        sixthNameCol.setCellValueFactory(new PropertyValueFactory<Clienti,String>("ingt"));
        lista_sali=getSali();
        tableView.setItems(lista_sali);
        tableView.getColumns().addAll(secondNameCol,forthNameCol,fifthNameCol,sixthNameCol);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                sala_activ=newSelection;
            }
        });

        return tableView;
    }

    public VBox Sali_config()
    {
        VBox v=new VBox(10);
        Sali_l=Baza_de_date.get_lista_sali();
        table=gettable_sali_config();
        v.getChildren().add(table);
        HBox h=new HBox(10);
        Button[] butoane=new Button[5];
        butoane[0]=new Button("Adauga");
        butoane[1]=new Button("Sterge");
        butoane[2]=new Button("Rezerva");
        butoane[3]=new Button("Derezerva");
        butoane[4]=new Button("Curatenie");
        TextField text=new TextField();
        if(id==1)
        {
            v.getChildren().add(new HBox(10,butoane[4]));
        }
        else if(id==2)
        {
            v.getChildren().add(new HBox(10,butoane[2],butoane[3]));
        }
        else
        {
            h.getChildren().addAll(butoane[0],text);
            v.getChildren().add(h);
            v.getChildren().add(new HBox(10,butoane[2],butoane[3],butoane[4]));
        }


        butoane[0].setOnAction(actionEvent -> {
            float newrand=Float.parseFloat(text.getText());
            if(Baza_de_date.find_sali(newrand)==true)
            {
               f_eroare.fereastra_de_error("Nu se poate adauga rand identic");
            }
            else
            {
                Baza_de_date.add_sali(new Sali(newrand,0,0,0));
                Sali_l=Baza_de_date.get_lista_sali();
                lista_sali=getSali();
                table.setItems(lista_sali);
            }

        });
        butoane[1].setOnAction(actionEvent -> {
            if(sala_activ!=null)
            {
                Baza_de_date.delete_sali(sala_activ.id_sala);
                Sali_l=Baza_de_date.get_lista_sali();
                lista_sali=getSali();
                table.setItems(lista_sali);
            }
        });
        butoane[2].setOnAction(actionEvent -> {
            if(sala_activ!=null)
            {
                if(sala_activ.ingrijire==1)
                {
                    f_eroare.fereastra_de_error("Sala este in ingrijire\nnu se poate rezervata");
                }
                else if(sala_activ.rezervat==1)
                {
                    f_eroare.fereastra_de_error("Sala este rezervata\nVa rog frumos sa derezervati");
                }
                else {
                    Stage stage=new Stage();
                    Scene scena=new Scene(getAntrenori(stage),400,400);
                    stage.setScene(scena);
                    stage.show();
                }

            }
        });
        butoane[3].setOnAction(actionEvent -> {
            sala_activ.rezervat_id=-1;
            sala_activ.rezervat=0;
            Baza_de_date.update_sali(sala_activ);
            Sali_l=Baza_de_date.get_lista_sali();
            lista_sali=getSali();
            table.setItems(lista_sali);
        });
        butoane[4].setOnAction(actionEvent -> {
            if(sala_activ!=null)
            {
                if(sala_activ.ingrijire==1)
                {
                    sala_activ.ingrijire=0;
                }
                else
                {
                    sala_activ.ingrijire=1;
                }
                Baza_de_date.update_sali(sala_activ);
                Sali_l=Baza_de_date.get_lista_sali();
                lista_sali=getSali();
                table.setItems(lista_sali);
            }
        });
        return v;
    }

    public VBox getAntrenori(Stage stage1)
    {
        VBox v=new VBox(10);
        v.setAlignment(Pos.CENTER);
        Label l=new Label("Lista de antrenori");
        l.setFont(new Font(20));
        v.getChildren().add(l);
        List<Utilizatori>u=Baza_de_date.get_lista_de_user();
        List<String>temp=new ArrayList<>();
        for(int i=0;i<u.size();i++)
        {
            if(u.get(i).tip_user==2)
            {
                temp.add(u.get(i).getUser());
            }
        }
        ObservableList<String>lista_user=FXCollections.observableArrayList(temp);

        ListView<String>list=new ListView<>();

        list.setItems(lista_user);

        list.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                        String[] sir=t1.split("-");
                        nr=Integer.parseInt(sir[0]);
                    }
                }
        );

        v.getChildren().add(list);
        Button button=new Button("Rezerva");
        v.getChildren().add(button);

        button.setOnAction(actionEvent -> {
            if(nr>0)
            {
                sala_activ.rezervat_id=nr;
                sala_activ.rezervat=1;
                Baza_de_date.update_sali(sala_activ);
                Sali_l=Baza_de_date.get_lista_sali();
                lista_sali=getSali();
                table.setItems(lista_sali);
            }
            stage1.close();

        });

        return v;
    }
}

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

public class config_intrari {
    private TableView<Intrari> table=new TableView<>();

    static Intrari intrare_activ;

    List<Intrari> Intrari_l;

    ObservableList<Intrari> lista_intrari;

    static int nr=0;
    public ObservableList<Intrari>getIntrari(String filtru_nume,String filtru_prenume,String nume_antr,String abonat_f)
    {
        List<Intrari>temp=new ArrayList<>();
        for(int i=0;i<Intrari_l.size();i++)
        {
            Intrari c=Intrari_l.get(i);
            if(c.nume.contains(filtru_nume)&&c.prenume.contains(filtru_prenume)&&c.nume_antrenor.contains(nume_antr)&&c.abonat.contains(abonat_f))
            {
                temp.add(c);
            }

        }
        ObservableList<Intrari> observableList= FXCollections.observableArrayList(temp);
        return observableList;
    }
    public ObservableList<Intrari> getIntrari()
    {
        List<Intrari>temp=new ArrayList<>();
        for(int i=0;i<Intrari_l.size();i++)
        {
            Intrari c=Intrari_l.get(i);
            temp.add(c);
        }
        ObservableList<Intrari> observableList= FXCollections.observableArrayList(temp);
        return observableList;
    }
    public TableView<Intrari> gettable_Intrari_config()
    {
        TableView<Intrari> tableView=new TableView<Intrari>();
        tableView.setEditable(true);
        TableColumn firstNameCol = new TableColumn("Nume");
        firstNameCol.setMinWidth(110);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Intrari,Integer>("nume"));
        TableColumn secondNameCol = new TableColumn("Prenume");
        secondNameCol.setMinWidth(110);
        secondNameCol.setCellValueFactory(new PropertyValueFactory<Intrari,String>("prenume"));
        TableColumn thirdNameCol=new TableColumn("Nume antrenor");
        thirdNameCol.setMinWidth(110);
        thirdNameCol.setCellValueFactory(new PropertyValueFactory<Intrari,String>("nume_antrenor"));
        TableColumn forthNameCol=new TableColumn("tip_abonament");
        forthNameCol.setMinWidth(110);
        forthNameCol.setCellValueFactory(new PropertyValueFactory<Intrari,String>("tip_abonament_text"));
        TableColumn fifthNameCol=new TableColumn("Abonament");
        fifthNameCol.setMinWidth(100);
        fifthNameCol.setCellValueFactory(new PropertyValueFactory<Intrari,String>("abonat"));
        TableColumn sixthNameCol=new TableColumn("nr_intrari");
        sixthNameCol.setMinWidth(100);
        sixthNameCol.setCellValueFactory(new PropertyValueFactory<Intrari,String>("nr_intraritext"));
        lista_intrari=getIntrari();
        tableView.setItems(lista_intrari);
        tableView.getColumns().addAll(firstNameCol,secondNameCol,thirdNameCol,forthNameCol,fifthNameCol,sixthNameCol);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                intrare_activ=newSelection;
            }
        });

        return tableView;
    }

    public VBox getConfig_Intrari()
    {
        String[] filtru = {"","","","",""};

        VBox v=new VBox(10);

        HBox h=new HBox(10);
        HBox h1=new HBox(10);
        //bara de cautare plus alinierea
        TextField nume_f=new TextField();
        TextField prenume_f=new TextField();
        TextField antrenor_f=new TextField();
        TextField abonat_f=new TextField();
        h.getChildren().add(new Label("Nume"));
        h.getChildren().addAll(nume_f,new Label("Prenume"),prenume_f);
        h1.getChildren().addAll(new Label("Nume antrenor"),antrenor_f,new Label("Abonat"),abonat_f);
        v.setAlignment(Pos.TOP_CENTER);
        h.setAlignment(Pos.BASELINE_LEFT);
        h1.setAlignment(Pos.BASELINE_LEFT);
        v.getChildren().add(h);
        v.getChildren().add(h1);


        nume_f.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filtru[0]=newValue;
            lista_intrari=getIntrari(filtru[0],filtru[1],filtru[2],filtru[3]);
            table.setItems(lista_intrari);
        }));
        prenume_f.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filtru[1]=newValue;
            lista_intrari=getIntrari(filtru[0],filtru[1],filtru[2],filtru[3]);
            table.setItems(lista_intrari);
        }));
        antrenor_f.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filtru[2]=newValue;
            lista_intrari=getIntrari(filtru[0],filtru[1],filtru[2],filtru[3]);
            table.setItems(lista_intrari);
        }));

        abonat_f.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filtru[3]=newValue;
            lista_intrari=getIntrari(filtru[0],filtru[1],filtru[2],filtru[3]);
            table.setItems(lista_intrari);
        }));

        Intrari_l=Baza_de_date.get_lista_intrari();
        table=gettable_Intrari_config();
        v.getChildren().add(table);
        HBox optiuni=new HBox(10);
        TextField text=new TextField();
        Button[] butoane=new Button[3];
        butoane[0]=new Button("select antrenor");
        butoane[1]=new Button("add intari");
        butoane[2]=new Button("remove intrari");
        butoane[0].setOnAction(actionEvent -> {

            if(intrare_activ.tip_abonament==3)
            {
                Stage stage=new Stage();
                Scene scena=new Scene(getAntrenori(stage),400,400);
                stage.setScene(scena);
                stage.show();
            }
            else
            {
                f_eroare.fereastra_de_error("Nu are abonament cu antrenor");
            }

        });
        butoane[1].setOnAction(actionEvent -> {
            if(intrare_activ!=null) {
                if(intrare_activ.tip_abonament==1)
                {
                    Clienti c = Baza_de_date.get_Clienti(intrare_activ.id_client);
                    c.nr_intrari = c.nr_intrari + Integer.parseInt(text.getText());
                    Baza_de_date.update_client(c);
                    Intrari_l = Baza_de_date.get_lista_intrari();
                    lista_intrari = getIntrari();
                    table.setItems(lista_intrari);
                }
                else
                {
                    f_eroare.fereastra_de_error("Clientul are intrari nelimitate");
                }

            }
        });
        butoane[2].setOnAction(actionEvent -> {
            if(intrare_activ!=null) {
                if(intrare_activ.tip_abonament==1)
                {
                    Clienti c = Baza_de_date.get_Clienti(intrare_activ.id_client);
                    if (c.nr_intrari >= Integer.parseInt(text.getText())) {
                        c.nr_intrari = c.nr_intrari - Integer.parseInt(text.getText());
                        Baza_de_date.update_client(c);
                        Intrari_l = Baza_de_date.get_lista_intrari();
                        lista_intrari = getIntrari();
                        table.setItems(lista_intrari);
                    } else {
                        f_eroare.fereastra_de_error("Nr prea mare");
                    }
                }
                else
                {
                    f_eroare.fereastra_de_error("Clientul are intrari nelimitate");
                }

            }
        });


        optiuni.getChildren().addAll(butoane);
        optiuni.getChildren().add(text);
        v.getChildren().add(optiuni);
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

        ListView<String> list=new ListView<>();

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
                Baza_de_date.add_clienti_antrenor(new Clienti_antrenor(intrare_activ.id_client, nr));
                Intrari_l=Baza_de_date.get_lista_intrari();
                lista_intrari=getIntrari();
                table.setItems(lista_intrari);
            }
            stage1.close();

        });

        return v;
    }


}

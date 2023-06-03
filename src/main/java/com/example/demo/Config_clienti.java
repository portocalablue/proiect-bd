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
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Config_clienti {

    static int tip;

    public Config_clienti(int tip)
    {
        this.tip=tip;
    }

    private TableView<Clienti>table=new TableView<>();

    static Clienti client_activ;

    List<Clienti> Clienti_l;

    ObservableList<Clienti> lista_clienti;

    public ObservableList<Clienti> getClienti(String filtru_nume,String filtru_prenume,String filtru_email,String filtru_telefon)
    {
        List<Clienti>temp=new ArrayList<>();
        for(int i=0;i<Clienti_l.size();i++)
        {
            Clienti c=Clienti_l.get(i);
            if(c.nume.contains(filtru_nume)&&c.prenume.contains(filtru_prenume)&&(c.email.contains(filtru_email))&&(c.telefon.contains(filtru_telefon)))
            {
                temp.add(Clienti_l.get(i));
            }
        }
        ObservableList<Clienti> observableList= FXCollections.observableArrayList(temp);
        return observableList;
    }
    public TableView<Clienti> gettable_user_config()
    {
        TableView<Clienti> tableView=new TableView<Clienti>();
        tableView.setEditable(true);
        TableColumn secondNameCol = new TableColumn("Nume");
        secondNameCol.setMinWidth(110);
        secondNameCol.setCellValueFactory(new PropertyValueFactory<Clienti,String>("nume"));
        TableColumn thirdNameCol=new TableColumn("Prenume");
        thirdNameCol.setMinWidth(110);
        thirdNameCol.setCellValueFactory(new PropertyValueFactory<Clienti,String>("prenume"));
        TableColumn forthNameCol=new TableColumn("tip_abonament");
        forthNameCol.setMinWidth(110);
        forthNameCol.setCellValueFactory(new PropertyValueFactory<Clienti,String>("tip_abonament_text"));
        TableColumn fifthNameCol=new TableColumn("Abonament");
        fifthNameCol.setMinWidth(100);
        fifthNameCol.setCellValueFactory(new PropertyValueFactory<Clienti,String>("abonat"));
        TableColumn sixthNameCol=new TableColumn("Telefon");
        sixthNameCol.setMinWidth(100);
        sixthNameCol.setCellValueFactory(new PropertyValueFactory<Clienti,String>("telefon"));
        TableColumn seventhNameCol=new TableColumn("Email");
        seventhNameCol.setCellValueFactory(new PropertyValueFactory<Clienti,String>("email"));
        seventhNameCol.setMinWidth(100);
        TableColumn octNameCol=new TableColumn("Activ");
        octNameCol.setCellValueFactory(new PropertyValueFactory<Clienti,String>("activt"));
        octNameCol.setMinWidth(100);
        lista_clienti=getClienti("","","","");
        tableView.setItems(lista_clienti);
        tableView.getColumns().addAll(secondNameCol,thirdNameCol,forthNameCol,fifthNameCol,sixthNameCol,seventhNameCol,octNameCol);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                client_activ=newSelection;
            }
        });

        return tableView;
    }
    public VBox config_clienti()
    {
        String[] filtru = {"","","","",""};
        Clienti_l=Baza_de_date.get_lista_clienti();
        VBox v=new VBox(20);
        HBox h=new HBox(10);
        HBox h1=new HBox(10);
        //bara de cautare plus alinierea
        TextField nume_f=new TextField();
        TextField prenume_f=new TextField();
        TextField email_f=new TextField();
        TextField telefon_f=new TextField();
        h.getChildren().add(new Label("Nume"));
        h.getChildren().addAll(nume_f,new Label("Prenume"),prenume_f);
        h1.getChildren().addAll(new Label("Email"),email_f,new Label("Telefon"),telefon_f);
        v.setAlignment(Pos.TOP_CENTER);
        h.setAlignment(Pos.BASELINE_LEFT);
        h1.setAlignment(Pos.BASELINE_LEFT);
        v.getChildren().add(h);
        v.getChildren().add(h1);

        //filtre
        nume_f.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filtru[0]=newValue;
            lista_clienti=getClienti(filtru[0],filtru[1],filtru[2],filtru[3]);
            table.setItems(lista_clienti);
        }));
        prenume_f.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filtru[1]=newValue;
            lista_clienti=getClienti(filtru[0],filtru[1],filtru[2],filtru[3]);
            table.setItems(lista_clienti);
        }));
        email_f.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filtru[2]=newValue;
            lista_clienti=getClienti(filtru[0],filtru[1],filtru[2],filtru[3]);
            table.setItems(lista_clienti);
        }));

        telefon_f.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filtru[3]=newValue;
            lista_clienti=getClienti(filtru[0],filtru[1],filtru[2],filtru[3]);
            table.setItems(lista_clienti);
        }));

        //tabelul cu configurare
        table=gettable_user_config();
        v.getChildren().add(table);
        HBox h3=new HBox(10);
        Button[] butoane=new Button[4];
        butoane[0]= new Button("Adauga");
        butoane[1]=new Button("Sterge");
        butoane[2]=new Button("Actualizare");
        butoane[3]=new Button("Activare/Dezactivare");
        butoane[0].setOnAction(actionEvent -> {
            Stage stage=new Stage();
            Scene scene=new Scene(adauga_Clienti(stage),400,400);
            stage.setScene(scene);
            stage.show();

        });

        butoane[1].setOnAction(actionEvent -> {
            if(client_activ!=null)
            {
                Baza_de_date.delete_client(client_activ.id_client);
                Clienti_l=Baza_de_date.get_lista_clienti();
                lista_clienti=getClienti("","","","");
                table.setItems(lista_clienti);
            }
        });
        butoane[2].setOnAction(actionEvent -> {
            if(client_activ!=null)
            {
                int index=client_activ.id_client;
                Stage stage=new Stage();
                Scene scene=new Scene(update_Clienti(stage,index),400,400);
                stage.setScene(scene);
                stage.show();
            }
        });
        butoane[3].setOnAction(actionEvent -> {
            if(client_activ!=null)
            {
                if(client_activ.activ==0)
                {
                    client_activ.activ=1;

                }
                else
                {
                    client_activ.activ=0;
                }
                Baza_de_date.update_client(client_activ);
                Clienti_l=Baza_de_date.get_lista_clienti();
                lista_clienti=getClienti("","","","");
                table.setItems(lista_clienti);
            }
        });
        if(tip==1)
        {
            h3.getChildren().addAll(butoane[0],butoane[2],butoane[3]);
        }
        else
        {
            h3.getChildren().addAll(butoane);
        }

        v.getChildren().add(h3);
        return v;
    }

    public VBox adauga_Clienti(Stage stage1)
    {
        VBox v=new VBox(10);
        v.setAlignment(Pos.CENTER);
        HBox[] boxes=new HBox[6];
        CheckBox activat=new CheckBox("Activ");
        final int[] activ = {0};
        TextField[] texte=new TextField[4];
        for(int i=0;i<texte.length;i++)
        {
            texte[i]=new TextField();
        }
        boxes[0]=new HBox(10,new Label("Nume"),texte[0]);
        boxes[1]=new HBox(10,new Label("Prenume"),texte[1]);
        boxes[2]=new HBox(10,new Label("Tip abonament"));
        boxes[3]=new HBox(10,new Label("telefon"),texte[2]);
        boxes[4]=new HBox(10,new Label("Email"),texte[3]);
        boxes[5]=new HBox(activat);

        for(int i=0;i<boxes.length;i++)
        {
            boxes[i].setAlignment(Pos.CENTER);
        }
        final int[] rol = {0};
        final ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("cu intrari");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);
        rol[0]=1;
        activ[0] =0;
        rb1.setSelected(true);
        RadioButton rb2 = new RadioButton("fara intrari");
        rb2.setToggleGroup(group);
        RadioButton rb3 = new RadioButton("fara intrari cu antrenor");
        rb3.setToggleGroup(group);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton rb = (RadioButton)group.getSelectedToggle();
                if(rb.getText().equals("cu intrari"))
                {
                    rol[0]=1;
                    activ[0]=0;
                }
                else if(rb.getText().equals("fara intrari"))
                {
                    rol[0]=2;
                    activ[0] =1;
                }
                else if(rb.getText().equals("fara intrari cu antrenor"))
                {
                    rol[0]=3;
                    activ[0]=1;
                }
            }
        });
        boxes[2].getChildren().addAll(rb1,rb2,rb3);
        v.getChildren().addAll(boxes);
        Button buton=new Button("Adauga");
        v.getChildren().add(buton);

        buton.setOnAction(actionEvent -> {
            Baza_de_date.add_client(new Clienti(texte[0].getText(),texte[1].getText(),rol[0],activ[0],(texte[2].getText().isEmpty()==true)?" ":texte[2].getText(),(texte[3].getText().isEmpty())?" ":texte[3].getText(),0,(activat.isSelected()==true)?1:0));
            Clienti_l=Baza_de_date.get_lista_clienti();
            lista_clienti=getClienti("","","","");
            table.setItems(lista_clienti);
            stage1.close();
        });
        return v;
    }

    public VBox update_Clienti(Stage stage1,int id)
    {
        Clienti c=Baza_de_date.get_Clienti(id);
        VBox v=new VBox(10);
        v.setAlignment(Pos.CENTER);
        CheckBox activat=new CheckBox("Activ");
        activat.setSelected(c.activ==1);
        HBox[] boxes=new HBox[6];
        TextField[] texte=new TextField[4];
        for(int i=0;i<texte.length;i++)
        {
            texte[i]=new TextField();
        }
        texte[0].setText(c.nume);
        texte[1].setText(c.prenume);
        texte[2].setText(c.telefon);
        texte[3].setText(c.email);
        boxes[0]=new HBox(10,new Label("Nume"),texte[0]);
        boxes[1]=new HBox(10,new Label("Prenume"),texte[1]);
        boxes[2]=new HBox(10,new Label("Tip abonament"));
        boxes[3]=new HBox(10,new Label("telefon"),texte[2]);
        boxes[4]=new HBox(10,new Label("Email"),texte[3]);
        boxes[5]=new HBox(10,activat);
        for(int i=0;i<boxes.length;i++)
        {
            boxes[i].setAlignment(Pos.CENTER);
        }
        final int[] rol = {0};
        final int[]activ={0};
        rol[0]=c.tip_abonament;
        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("cu intrari");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);
        rol[0]=1;
        activ[0]=0;
        RadioButton rb2 = new RadioButton("fara intrari");
        rb2.setToggleGroup(group);
        RadioButton rb3 = new RadioButton("fara intrari cu antrenor");
        rb3.setToggleGroup(group);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton rb = (RadioButton)group.getSelectedToggle();
                if(rb.getText().equals("cu intrari"))
                {
                    rol[0]=1;
                    activ[0]=0;
                }
                else if(rb.getText().equals("fara intrari"))
                {
                    rol[0]=2;
                    activ[0]=1;
                }
                else if(rb.getText().equals("fara intrari cu antrenor"))
                {
                    rol[0]=3;
                    activ[0]=1;
                }
            }
        });
        boxes[2].getChildren().addAll(rb1,rb2,rb3);
        v.getChildren().addAll(boxes);
        Button buton=new Button("Adauga");
        v.getChildren().add(buton);
        buton.setOnAction(actionEvent -> {
            Baza_de_date.update_client(new Clienti(c.id_client,texte[0].getText(),texte[1].getText(),rol[0],activ[0],texte[2].getText(),texte[3].getText(),c.nr_intrari,(activat.isSelected()==true)?1:0));
            Clienti_l=Baza_de_date.get_lista_clienti();
            lista_clienti=getClienti("","","","");
            table.setItems(lista_clienti);
            stage1.close();
        });
        return v;
    }
    
}

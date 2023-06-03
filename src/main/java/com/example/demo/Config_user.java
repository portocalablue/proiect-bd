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

public class Config_user {
    private TableView<Utilizatori> table = new TableView<Utilizatori>();
    Utilizatori u_activ;
    static List<Utilizatori> utilizatori_l;

    ObservableList<Utilizatori> lista_utiliz;
    public ObservableList<Utilizatori> getUtilizatori(String filtru_nume,String filtru_activ,String filtru_tip,String filtru_id)
    {
        List<Utilizatori>temp=new ArrayList<>();
        for(int i=0;i<utilizatori_l.size();i++)
        {
            if(utilizatori_l.get(i).alias.contains(filtru_nume)&utilizatori_l.get(i).activat.contains(filtru_activ)&&
            utilizatori_l.get(i).tip_usertext.contains(filtru_tip)&&String.valueOf(utilizatori_l.get(i).id_user).contains(filtru_id))
            {
                temp.add(utilizatori_l.get(i));
            }
        }
        ObservableList<Utilizatori> observableList= FXCollections.observableArrayList(temp);
        return observableList;
    }

    public TableView<Utilizatori> gettable_user_config()
    {
        TableView<Utilizatori> tableView=new TableView<Utilizatori>();
        tableView.setEditable(true);
        TableColumn firstNameCol = new TableColumn("Id");
        firstNameCol.setMinWidth(110);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Utilizatori,Integer>("id_user"));
        TableColumn secondNameCol = new TableColumn("Username");
        secondNameCol.setMinWidth(110);
        secondNameCol.setCellValueFactory(new PropertyValueFactory<Utilizatori,String>("alias"));
        TableColumn thirdNameCol=new TableColumn("Tip_user");
        thirdNameCol.setMinWidth(110);
        thirdNameCol.setCellValueFactory(new PropertyValueFactory<Utilizatori,String>("tip_usertext"));
        TableColumn forthNameCol=new TableColumn("activ");
        forthNameCol.setMinWidth(110);
        forthNameCol.setCellValueFactory(new PropertyValueFactory<Utilizatori,String>("activat"));
        TableColumn fifthNameCol=new TableColumn("Parola");
        fifthNameCol.setMinWidth(100);
        fifthNameCol.setCellValueFactory(new PropertyValueFactory<Utilizatori,String>("parola"));

        lista_utiliz=getUtilizatori("","","","");
        tableView.setItems(lista_utiliz);
        tableView.getColumns().addAll(firstNameCol,secondNameCol,fifthNameCol,thirdNameCol,forthNameCol);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                u_activ=newSelection;
            }
        });

        return tableView;
    }

    public VBox adauga_user(Stage stage1)
    {
        VBox v=new VBox(10);
        v.setAlignment(Pos.CENTER);
        HBox[] boxes=new HBox[4];
        TextField[] texte=new TextField[2];
        for(int i=0;i<texte.length;i++)
        {
            texte[i]=new TextField();
        }
        CheckBox checkBox=new CheckBox("ACTIV");
        boxes[0]=new HBox(10,new Label("Utilizator"),texte[0]);
        boxes[1]=new HBox(10,new Label("Parola"),texte[1]);
        boxes[2]=new HBox(10,new Label("activ"),checkBox);
        boxes[3]=new HBox(10,new Label("Rol"));
        for(int i=0;i<boxes.length;i++)
        {
            boxes[i].setAlignment(Pos.CENTER);
        }
        final int[] rol = {0};
        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Casier");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);
        rol[0]=1;

        RadioButton rb2 = new RadioButton("Antrenor");
        rb2.setToggleGroup(group);

        RadioButton rb3 = new RadioButton("Admin");
        rb3.setToggleGroup(group);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton rb = (RadioButton)group.getSelectedToggle();
                if(rb.getText().equals("Casier"))
                {
                    rol[0]=1;
                }
                else if(rb.getText().equals("Antrenor"))
                {
                    rol[0]=2;
                }
                else if(rb.getText().equals("Admin"))
                {
                    rol[0]=3;
                }
            }
        });

        v.getChildren().addAll(boxes);
        v.getChildren().addAll(rb1,rb2,rb3);
        Button buton=new Button("Adauga");
        v.getChildren().add(buton);
        buton.setOnAction(actionEvent -> {
            Baza_de_date.add_user(new Utilizatori(rol[0],checkBox.isSelected(),texte[0].getText(),texte[1].getText()));
            utilizatori_l=Baza_de_date.get_lista_de_user();
            lista_utiliz=getUtilizatori("","","","");
            table.setItems(lista_utiliz);
            stage1.close();
        });
        return v;
    }

    public VBox update_user(Stage stage1,int id)
    {
        Utilizatori u=Baza_de_date.get_user(id);
        VBox v=new VBox(10);
        v.setAlignment(Pos.CENTER);
        HBox[] boxes=new HBox[4];
        TextField[] texte=new TextField[2];
        for(int i=0;i<texte.length;i++)
        {
            texte[i]=new TextField();
        }
        texte[0].setText(u.alias);
        texte[1].setText(u.parola);
        CheckBox checkBox=new CheckBox("ACTIV");
        checkBox.setSelected(u.active);
        boxes[0]=new HBox(10,new Label("Utilizator"),texte[0]);
        boxes[1]=new HBox(10,new Label("Parola"),texte[1]);
        boxes[2]=new HBox(10,new Label("activ"),checkBox);
        boxes[3]=new HBox(10,new Label("Rol"));
        for(int i=0;i<boxes.length;i++)
        {
            boxes[i].setAlignment(Pos.CENTER);
        }


        final int[] rol = {0};
        rol[0]=u.tip_user;
        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Casier");
        rb1.setToggleGroup(group);
        RadioButton rb2 = new RadioButton("Antrenor");
        rb2.setToggleGroup(group);
        RadioButton rb3 = new RadioButton("Admin");
        rb3.setToggleGroup(group);

        if(u.tip_user==1)
        {
            rb1.setSelected(true);
        }
        else if(u.tip_user==2)
        {
            rb2.setSelected(true);
        }
        else if(u.tip_user==3) {
            rb3.setSelected(true);
        }

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton rb = (RadioButton)group.getSelectedToggle();
                if(rb.getText().equals("Casier"))
                {
                    rol[0]=1;
                }
                else if(rb.getText().equals("Antrenor"))
                {
                    rol[0]=2;
                }
                else if(rb.getText().equals("Admin"))
                {
                    rol[0]=3;
                }
            }
        });

        v.getChildren().addAll(boxes);
        v.getChildren().addAll(rb1,rb2,rb3);
        Button buton=new Button("Actualizeaza");
        v.getChildren().add(buton);
        buton.setOnAction(actionEvent -> {
            Baza_de_date.update_user(new Utilizatori(u.id_user,rol[0],checkBox.isSelected(),texte[0].getText(),texte[1].getText()));
            utilizatori_l=Baza_de_date.get_lista_de_user();
            lista_utiliz=getUtilizatori("","","","");
            table.setItems(lista_utiliz);
            stage1.close();
        });
        return v;
    }
    public VBox config_user()
    {
        String[] filtru = {"","","","",""};
        utilizatori_l=Baza_de_date.get_lista_de_user();
        VBox v=new VBox(20);
        HBox h=new HBox(10);
        HBox h1=new HBox(10);
        //bara de cautare plus alinierea
        TextField username_f=new TextField();
        TextField ida_f=new TextField();
        TextField idt_f=new TextField();
        TextField id_f=new TextField();
        h.getChildren().add(new Label("Username"));
        h.getChildren().addAll(username_f,new Label("id_active"),ida_f);
        h1.getChildren().addAll(new Label("Tip user"),idt_f,new Label("ID"),id_f);
        v.setAlignment(Pos.TOP_CENTER);
        h.setAlignment(Pos.BASELINE_LEFT);
        h1.setAlignment(Pos.BASELINE_LEFT);
        v.getChildren().add(h);
        v.getChildren().add(h1);
        username_f.textProperty().addListener((observable, oldValue, newValue) -> {
            filtru[0] =newValue;
            lista_utiliz=getUtilizatori(filtru[0],filtru[1],filtru[2],filtru[3]);
            table.setItems(lista_utiliz);
        });
        ida_f.textProperty().addListener((observable,oldValue,newValue)->{
            filtru[1]=newValue;
            lista_utiliz=getUtilizatori(filtru[0],filtru[1],filtru[2],filtru[3]);
            table.setItems(lista_utiliz);
        });
        idt_f.textProperty().addListener(((observableValue, oldValue,newValue) ->{
            filtru[2]=newValue;
            lista_utiliz=getUtilizatori(filtru[0],filtru[1],filtru[2],filtru[3]);
            table.setItems(lista_utiliz);
        } ));
        id_f.textProperty().addListener(((observableValue, oldValue,newValue) ->{
            filtru[3]=newValue;
            lista_utiliz=getUtilizatori(filtru[0],filtru[1],filtru[2],filtru[3]);
            table.setItems(lista_utiliz);
        } ));

        //tabelul cu configurare
        table=gettable_user_config();
        v.getChildren().add(table);
        HBox h3=new HBox(10);
        Button[] butoane=new Button[4];
        butoane[0]= new Button("Adauga");
        butoane[1]=new Button("Sterge");
        butoane[2]=new Button("Actualizare");
        butoane[3]=new Button("Activeaza/Dezactiveaza");
        butoane[0].setOnAction(actionEvent -> {
            Stage stage=new Stage();
            Scene scene=new Scene(adauga_user(stage),400,400);
            stage.setScene(scene);
            stage.show();

        });

        butoane[1].setOnAction(actionEvent -> {
            if(u_activ!=null)
            {
                Baza_de_date.delete_user(u_activ.id_user);
                utilizatori_l=Baza_de_date.get_lista_de_user();
                lista_utiliz=getUtilizatori("","","","");
                table.setItems(lista_utiliz);
            }
        });
        butoane[2].setOnAction(actionEvent -> {
            if(u_activ!=null)
            {
                int index=u_activ.id_user;
                Stage stage=new Stage();
                Scene scene=new Scene(update_user(stage,index),400,400);
                stage.setScene(scene);
                stage.show();
            }
        });

        butoane[3].setOnAction(actionEvent -> {
            Utilizatori u=new Utilizatori(u_activ.id_user,u_activ.tip_user,!(u_activ).active,u_activ.alias,u_activ.parola);
            Baza_de_date.update_user(u);
            utilizatori_l=Baza_de_date.get_lista_de_user();
            lista_utiliz=getUtilizatori("","","","");
            table.setItems(lista_utiliz);
        });


        h3.getChildren().addAll(butoane);
        v.getChildren().add(h3);
        return v;
    }
}

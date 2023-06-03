package com.example.demo;

import java.io.Serializable;

public class Utilizatori implements Serializable {
    static int id_user_c;
    int id_user;
    int tip_user;
    boolean active;
    String alias;
    String parola;

    String tip_usertext,activat;


    public Utilizatori(int id,int tip_user, boolean active, String alias, String parola) {
        this.id_user =id;
        this.tip_user = tip_user;
        this.active = active;
        this.alias = alias;
        this.parola = parola;
        switch (this.tip_user)
        {
            case 1:
                this.tip_usertext="Casier";
                break;
            case 2:
                this.tip_usertext="Antrenor";
                break;
            case 3:
                this.tip_usertext="Admin";
                break;
        }
        this.activat=(this.active==true)?"DA":"NU";
    }

    public Utilizatori(int tip_user, boolean active, String alias, String parola) {
        this.id_user = id_user_c+1;
        this.id_user_c++;
        this.tip_user = tip_user;
        this.active = active;
        this.alias = alias;
        this.parola = parola;
        switch (this.tip_user)
        {
            case 1:
                this.tip_usertext="Casier";
                break;
            case 2:
                this.tip_usertext="Antrenor";
                break;
            case 3:
                this.tip_usertext="Admin";
                break;
        }
        this.activat=(this.active==true)?"DA":"NU";
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getTip_user() {
        return tip_user;
    }

    public void setTip_user(int tip_user) {
        this.tip_user = tip_user;
    }

    public int isActive() {
        int activ=((active==true)?1:0);
        return activ;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getUser()
    {
        return id_user+"-"+alias;
    }

    public String getTip_usertext() {
        return tip_usertext;
    }

    public void setTip_usertext(String tip_usertext) {
        this.tip_usertext = tip_usertext;
    }

    public String getActivat() {
        return activat;
    }

    public void setActivat(String activat) {
        this.activat = activat;
    }

    @Override
    public String toString() {
        return
                "id_user:" + id_user +
                ", tip_user:" + tip_user +
                ", active:" + active +
                ", alias:" + alias +
                ", parola:'" + parola;
    }
}

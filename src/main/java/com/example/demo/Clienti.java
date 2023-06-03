package com.example.demo;

import java.time.LocalDate;
import java.util.Date;

public class Clienti {
    static int id_client_c;
    int id_client;
    String nume,prenume;
    int tip_abonament;
    int abonament;
    String telefon;
    String email;
    int nr_intrari;
    Date data_crearii;
    Date data_stergerii;
    int activ;

    String tip_abonament_text,abonat,activt;
    //add
    public Clienti(String nume, String prenume, int tip_abonament, int abonament, String telefon, String email, int nr_intrari,int activ) {
        id_client=id_client_c;
        id_client_c++;
        this.nume = nume;
        this.prenume = prenume;
        this.tip_abonament = tip_abonament;
        this.abonament = abonament;
        this.telefon = telefon;
        this.email = email;
        this.nr_intrari = nr_intrari;
        this.data_crearii=new Date();
        this.data_stergerii=null;
        this.abonat=(this.abonament==1)?"DA":"NU";
        switch (this.tip_abonament)
        {
            case 1:
                this.tip_abonament_text="Cu intrari";
                break;
            case 2:
                this.tip_abonament_text="Fara intrari";
                break;
            case 3:
                this.tip_abonament_text="Cu antrenor";
                break;
        }
        this.activ=activ;
        this.activt=(activ==1)?"DA":"NU";
    }
    //update
    public Clienti(int id_client, String nume, String prenume, int tip_abonament, int abonament, String telefon, String email, int nr_intrari,int activ) {
        this.id_client = id_client;
        this.nume = nume;
        this.prenume = prenume;
        this.tip_abonament = tip_abonament;
        this.abonament = abonament;
        this.telefon = telefon;
        this.email = email;
        this.nr_intrari = nr_intrari;
        this.abonat=(this.abonament==1)?"DA":"NU";
        switch (this.tip_abonament)
        {
            case 1:
                this.tip_abonament_text="Cu intrari";
                break;
            case 2:
                this.tip_abonament_text="Fara intrari";
                break;
            case 3:
                this.tip_abonament_text="Cu antrenor";
                break;
        }
        this.activ=activ;
        this.activt=(activ==1)?"DA":"NU";
    }

    public Clienti(int id_client, String nume, String prenume, int tip_abonament, int abonament, String telefon, String email, int nr_intrari, Date data_crearii, Date data_stergerii,int activ) {
        this.id_client = id_client;
        this.nume = nume;
        this.prenume = prenume;
        this.tip_abonament = tip_abonament;
        this.abonament = abonament;
        this.telefon = telefon;
        this.email = email;
        this.nr_intrari = nr_intrari;
        this.data_crearii = data_crearii;
        this.data_stergerii = data_stergerii;
        this.abonat=(this.abonament==1)?"DA":"NU";
        switch (this.tip_abonament)
        {
            case 1:
                this.tip_abonament_text="Cu intrari";
                break;
            case 2:
                this.tip_abonament_text="Fara intrari";
                break;
            case 3:
                this.tip_abonament_text="Cu antrenor";
                break;
        }
        this.activ=activ;
        this.activt=(activ==1)?"DA":"NU";
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getTip_abonament() {
        return tip_abonament;
    }

    public void setTip_abonament(int tip_abonament) {
        this.tip_abonament = tip_abonament;
    }

    public int getAbonament() {
        return abonament;
    }

    public void setAbonament(int abonament) {
        this.abonament = abonament;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNr_intrari() {
        return nr_intrari;
    }

    public void setNr_intrari(int nr_intrari) {
        this.nr_intrari = nr_intrari;
    }

    public Date getData_crearii() {
        return data_crearii;
    }

    public void setData_crearii(Date data_crearii) {
        this.data_crearii = data_crearii;

    }

    public Date getData_stergerii() {
        return data_stergerii;
    }

    public void setData_stergerii(Date data_stergerii) {
        this.data_stergerii = data_stergerii;
    }

    public String getTip_abonament_text() {
        return tip_abonament_text;
    }

    public void setTip_abonament_text(String tip_abonament_text) {
        this.tip_abonament_text = tip_abonament_text;
    }

    public String getAbonat() {
        return abonat;
    }

    public void setAbonat(String abonat) {
        this.abonat = abonat;
    }

    public int getActiv() {
        return activ;
    }

    public void setActiv(int activ) {
        this.activ = activ;
    }

    public String getActivt() {
        return activt;
    }

    public void setActivt(String activt) {
        this.activt = activt;
    }

    @Override
    public String toString() {
        return id_client +
                ";" + nume +
                ";" + prenume +
                ";" + tip_abonament +
                ";" + abonament +
                ";" + telefon  +
                ";" + email  +
                ";" + nr_intrari +
                ";" + data_crearii +
                ";" + data_stergerii+
                ";"+activ;
    }
}

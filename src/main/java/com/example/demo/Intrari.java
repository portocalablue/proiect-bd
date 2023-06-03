package com.example.demo;

import java.util.Date;

public class Intrari {
    int id_client;
    int id_antrenor;
    String nume;
    String prenume;

    String nume_antrenor;
    int abonament;
    int tip_abonament;
    int nr_intrari;

    String abonat,nr_intraritext,tip_abonament_text;

    public Intrari(int id_client, int id_antrenor, String nume, String prenume, String nume_antrenor, int abonament, int tip_abonament, int nr_intrari) {
        this.id_client = id_client;
        this.id_antrenor = id_antrenor;
        this.nume = nume;
        this.prenume = prenume;
        this.nume_antrenor = nume_antrenor;
        this.abonament = abonament;
        this.tip_abonament = tip_abonament;
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
        this.nr_intraritext=(tip_abonament==1)?String.valueOf(this.nr_intrari):"Nelimitat";
    }


    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_antrenor() {
        return id_antrenor;
    }

    public void setId_antrenor(int id_antrenor) {
        this.id_antrenor = id_antrenor;
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

    public int getAbonament() {
        return abonament;
    }

    public void setAbonament(int abonament) {
        this.abonament = abonament;
    }

    public int getTip_abonament() {
        return tip_abonament;
    }

    public void setTip_abonament(int tip_abonament) {
        this.tip_abonament = tip_abonament;
    }

    public int getNr_intrari() {
        return nr_intrari;
    }

    public void setNr_intrari(int nr_intrari) {
        this.nr_intrari = nr_intrari;
    }

    public String getNume_antrenor() {
        return nume_antrenor;
    }

    public void setNume_antrenor(String nume_antrenor) {
        this.nume_antrenor = nume_antrenor;
    }

    public String getAbonat() {
        return abonat;
    }

    public void setAbonat(String abonat) {
        this.abonat = abonat;
    }

    public String getNr_intraritext() {
        return nr_intraritext;
    }

    public void setNr_intraritext(String nr_intraritext) {
        this.nr_intraritext = nr_intraritext;
    }

    public String getTip_abonament_text() {
        return tip_abonament_text;
    }

    public void setTip_abonament_text(String tip_abonament_text) {
        this.tip_abonament_text = tip_abonament_text;
    }

    @Override
    public String toString() {
        return id_client+" "+id_antrenor+" "+abonament+" "+tip_abonament+" "+nume+" "+prenume+" "+nume_antrenor+" "+nr_intrari;
    }
}

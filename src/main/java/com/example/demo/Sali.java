package com.example.demo;

public class Sali {
    int id_sala;
    float rand;
    int rezervat_id;
    int rezervat;
    int ingrijire;
    String den_ocupta;
    String rezt,ingt;

    public Sali(float rand, int rezervat_id, int rezervat, int ingrijire) {
        this.rand = rand;
        this.rezervat_id = rezervat_id;
        this.rezervat = rezervat;
        this.ingrijire = ingrijire;
        this.rezt=(this.rezervat==1)?"DA":"NU";
        this.ingt=(this.ingrijire==1)?"DA":"NU";
    }

    public Sali(int id_sala, float rand, int rezervat_id, int rezervat, int ingrijire,String den_ocupta) {
        this.id_sala = id_sala;
        this.rand = rand;
        this.rezervat_id = rezervat_id;
        this.rezervat = rezervat;
        this.ingrijire = ingrijire;
        this.den_ocupta=den_ocupta;
        this.rezt=(this.rezervat==1)?"DA":"NU";
        this.ingt=(this.ingrijire==1)?"DA":"NU";
    }

    public Sali(int id_sala, float rand, int rezervat_id, int rezervat, int ingrijire) {
        this.id_sala = id_sala;
        this.rand = rand;
        this.rezervat_id = rezervat_id;
        this.rezervat = rezervat;
        this.ingrijire = ingrijire;
        this.rezt=(this.rezervat==1)?"DA":"NU";
        this.ingt=(this.ingrijire==1)?"DA":"NU";
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public float getRand() {
        return rand;
    }

    public void setRand(float rand) {
        this.rand = rand;
    }

    public int getRezervat_id() {
        return rezervat_id;
    }

    public void setRezervat_id(int rezervat_id) {
        this.rezervat_id = rezervat_id;
    }

    public int getRezervat() {
        return rezervat;
    }

    public void setRezervat(int rezervat) {
        this.rezervat = rezervat;
    }

    public int getIngrijire() {
        return ingrijire;
    }

    public void setIngrijire(int ingrijire) {
        this.ingrijire = ingrijire;
    }

    public String getDen_ocupta() {
        return den_ocupta;
    }

    public void setDen_ocupta(String den_ocupta) {
        this.den_ocupta = den_ocupta;
    }

    public String getRezt() {
        return rezt;
    }

    public void setRezt(String rezt) {
        this.rezt = rezt;
    }

    public String getIngt() {
        return ingt;
    }

    public void setIngt(String ingt) {
        this.ingt = ingt;
    }

    @Override
    public String toString() {
        return id_sala+";"+rand+";"+rezervat_id+";"+rezervat+";"+ingrijire+";"+den_ocupta;
    }
}

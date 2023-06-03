package com.example.demo;

import java.util.Date;

public class Clienti_antrenor {
    int id_client;
    int id_antrenor;
    Date data_crearii;
    Date data_expirarii;

    public Clienti_antrenor(int id_client, int id_antrenor) {

        this.id_client = id_client;
        this.id_antrenor = id_antrenor;
    }

    public Clienti_antrenor(int id_client, int id_antrenor, Date data_crearii, Date data_expirarii) {
        this.id_client = id_client;
        this.id_antrenor = id_antrenor;
        this.data_crearii = data_crearii;
        this.data_expirarii = data_expirarii;
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

    public Date getData_crearii() {
        return data_crearii;
    }

    public void setData_crearii(Date data_crearii) {
        this.data_crearii = data_crearii;
    }

    public Date getData_expirarii() {
        return data_expirarii;
    }

    public void setData_expirarii(Date data_expirarii) {
        this.data_expirarii = data_expirarii;
    }

    @Override
    public String toString() {
        return id_client+" "+id_antrenor+" "+data_crearii+" "+data_expirarii;
    }
}

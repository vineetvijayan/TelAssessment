package com.example.telassessment.model;

import java.io.Serializable;
import java.util.ArrayList;

public class DataModel implements Serializable {

    private String title;
    private ArrayList<Rows> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Rows> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Rows> rows) {
        this.rows = rows;
    }
}

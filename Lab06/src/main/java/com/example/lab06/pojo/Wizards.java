package com.example.lab06.pojo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Wizards {
    private ArrayList<Wizard> model;
    public Wizards(ArrayList<Wizard> model) {
        this.model = model;
    }

    public ArrayList<Wizard> getModel() {
        return model;
    }

    public void setModel(ArrayList<Wizard> model) {
        this.model = model;
    }
}

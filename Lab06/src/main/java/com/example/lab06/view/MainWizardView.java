package com.example.lab06.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "/mainPage.it")
public class MainWizardView extends VerticalLayout {
    public MainWizardView() {
        TextField tName = new TextField();
        tName.setPlaceholder("Fullname");

        RadioButtonGroup<String> sex = new RadioButtonGroup<>();
        sex.setLabel("Gender :");
        sex.setItems("Male", "Female");

        ComboBox<String> posiBox = new ComboBox<>();
        posiBox.setItems("Student", "Teacher");
        posiBox.setPlaceholder("Position");

        TextField tDollar = new TextField("Dollars");
        tDollar.setPrefixComponent(new Span("$"));

        ComboBox<String> schoolBox = new ComboBox<>();
        schoolBox.setItems("Hogwarts", "Beauxbatons", "Durmstrang");
        schoolBox.setPlaceholder("School");

        ComboBox<String> houseBox = new ComboBox<>();
        houseBox.setItems("Gryffindor", "Ravenclaw", "Hufflepuff", "Slyther");
        houseBox.setPlaceholder("House");

        HorizontalLayout h1 = new HorizontalLayout();

        Button left = new Button("<<");
        Button create = new Button("Create");
        Button update = new Button("Update");
        Button delete = new Button("Delete");
        Button right = new Button(">>");

        h1.add(left, create, update, delete, right);

        add(tName, sex, posiBox, tDollar, schoolBox, houseBox, h1);
    }
}

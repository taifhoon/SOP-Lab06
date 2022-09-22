package com.example.lab06.view;

import com.example.lab06.pojo.Wizard;
import com.example.lab06.pojo.Wizards;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
//import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//import java.util.ArrayList;
//import java.util.List;

@Route(value = "/mainPage.it")
public class MainWizardView extends VerticalLayout {
    Wizard thisWizard;
    int indexPage = -1;
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

        left.addClickListener(e -> {
            Wizards listWizard = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/wizards")
                    .retrieve()
                    .bodyToMono(Wizards.class)
                    .block();
            if (indexPage > 0) {
                indexPage--;
            }
            Wizard selectW = listWizard.getModel().get(indexPage);
            thisWizard = selectW;
            if (selectW.getSex().equals("m")){
                sex.setValue("Male");
            }
            else if (selectW.getSex().equals("f")){
                sex.setValue("Female");
            }
            tName.setValue(selectW.getName());
            posiBox.setValue(selectW.getPosition());
            tDollar.setValue(String.valueOf(selectW.getMoney()));
            schoolBox.setValue(selectW.getSchool());
            houseBox.setValue(selectW.getHouse());
                });
        right.addClickListener(e -> {
            Wizards listWizard = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/wizards")
                    .retrieve()
                    .bodyToMono(Wizards.class)
                    .block();
            if (indexPage < listWizard.getModel().size()-1) {
                indexPage++;
            }
            Wizard selectW = listWizard.getModel().get(indexPage);
            thisWizard = selectW;
            if (selectW.getSex().equals("m")){
                sex.setValue("Male");
            }
            else if (selectW.getSex().equals("f")){
                sex.setValue("Female");
            }
            tName.setValue(selectW.getName());
            posiBox.setValue(selectW.getPosition());
            tDollar.setValue(String.valueOf(selectW.getMoney()));
            schoolBox.setValue(selectW.getSchool());
            houseBox.setValue(selectW.getHouse());
            Notification.show("wowwwwwww",5000, Notification.Position.TOP_END);
        });

        create.addClickListener(e -> {
            String selectSex = "";
            if (sex.getValue().equals("Female")){
                selectSex = "f";
            }
            else if (sex.getValue().equals("Male")){
                selectSex = "m";
            }
            WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addWizard")
                    .body(Mono.just(new Wizard(null, selectSex, tName.getValue(), schoolBox.getValue(), houseBox.getValue(), Integer.parseInt(tDollar.getValue()), posiBox.getValue())), Wizard.class)
                    .retrieve()
                    .bodyToMono(Wizard.class)
                    .block();
            Notification.show("Wizard has been Created", 5000, Notification.Position.BOTTOM_START);
        });

        update.addClickListener(e -> {
            String selectSex = "";
            if (sex.getValue().equals("Female")){
                selectSex = "f";
            }
            else if (sex.getValue().equals("Male")){
                selectSex = "m";
            }
           WebClient.create()
                   .post()
                   .uri("http://localhost:8080/updateWizard")
                   .body(Mono.just(new Wizard(this.thisWizard.get_id(), selectSex, tName.getValue(), schoolBox.getValue(), houseBox.getValue(), Integer.parseInt(tDollar.getValue()), posiBox.getValue())), Wizard.class)
                   .retrieve()
                   .bodyToMono(Wizard.class)
                   .block();
            Notification.show("Wizard has been Updated", 5000, Notification.Position.BOTTOM_START);
        });


        delete.addClickListener(e -> {
            try {
                WebClient.create()
                        .post()
                        .uri("http://localhost:8080/deleteWizard")
                        .body(Mono.just(thisWizard.get_id()), String.class)
//                    .body(Mono.just(new Wizard(thisWizard.get_id(), thisWizard.getSex(), thisWizard.getName(), thisWizard.getSchool(), thisWizard.getHouse(), thisWizard.getMoney(), thisWizard.getPosition())), Wizard.class)
                        .retrieve()
                        .bodyToMono(boolean.class)
                        .block();
            }catch (NullPointerException err){
                System.out.println("Error");
            }
            Notification.show("Wizard has been Removed", 5000, Notification.Position.BOTTOM_START);
        });
    }
}

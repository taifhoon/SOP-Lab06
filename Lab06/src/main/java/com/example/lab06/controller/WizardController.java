package com.example.lab06.controller;

import com.example.lab06.pojo.Wizard;
import com.example.lab06.pojo.Wizards;
import com.example.lab06.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WizardController {
    @Autowired
    private WizardService wizardService;

    @RequestMapping(value = "/wizards", method = RequestMethod.GET)
    public ResponseEntity<?> getWizards(){
        List<Wizard> wizards = wizardService.getWizards();
        return ResponseEntity.ok(wizards);
    }

    @RequestMapping(value = "/addWizard", method = RequestMethod.POST)
    public ResponseEntity<?> addWizards(@RequestBody Wizard w){
        Wizard newWi = wizardService.insertWizard(w);
        return ResponseEntity.ok(newWi);
    }

    @RequestMapping(value = "/updateWizard", method = RequestMethod.POST)
    public ResponseEntity<?> updateWizards(@RequestBody Wizard w){
        Wizard upWi = wizardService.updateWizard(w);
        return ResponseEntity.ok(upWi);
    }

    @RequestMapping(value = "/deleteWizard", method = RequestMethod.POST)
    public ResponseEntity<?> deleteWizards(@RequestBody String id){
        Wizard w = wizardService.getById(id);
        boolean check = wizardService.deleteWizard(w);
        return ResponseEntity.ok(check);
    }
}

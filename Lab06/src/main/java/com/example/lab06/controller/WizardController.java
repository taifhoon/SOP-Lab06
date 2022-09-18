package com.example.lab06.controller;

import com.example.lab06.pojo.Wizard;
import com.example.lab06.pojo.Wizards;
import com.example.lab06.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WizardController {
    @Autowired
    private WizardService wizardService;

    @RequestMapping(value = "/wizards", method = RequestMethod.GET)
    public List<Wizard> getWizards(){
        List<Wizard> wizards = wizardService.getWizards();
        return wizards;
    }

//    @RequestMapping(value = "/addWizard", method = RequestMethod.GET)
//    public ResponseEntity<?> addWizards(@RequestBody MultiValueMap<String, String> w){
//        return ;
//    }

}

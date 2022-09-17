package com.example.lab06.repository;

import com.example.lab06.pojo.Wizard;
import com.example.lab06.pojo.Wizards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WizardService {
    @Autowired
    private WizardRepository repository;

    public WizardService(WizardRepository repository) {
        this.repository = repository;
    }

    public List<Wizard> getWizards(){
        return repository.findAll();
    }
    public Wizard insertWizard(Wizard wizard){
        return repository.insert(wizard);
    }
}

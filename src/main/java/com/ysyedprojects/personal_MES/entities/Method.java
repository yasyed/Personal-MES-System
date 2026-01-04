package com.ysyedprojects.personal_MES.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//method object has a arraylist of methodStep objects
@Entity
@Table(name="Methods")
public class Method {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "Method_ID")
    private Long methodID;

    @Column(name = "Method_Name")
    private String name;

    @Column (name = "Description")
    private String description;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "method")
    private List<MethodStep> methodSteps = new ArrayList<>();

    @Transient
    private int currentStepNumber = 0;

    public void setMethodID(Long methodID) {
        this.methodID = methodID;
    }

    public Long getMethodID() {
        return methodID;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setMethodSteps(List<MethodStep> methodSteps) {
        this.methodSteps = methodSteps;
    }

    public List<MethodStep> getMethodSteps() {
        return methodSteps;
    }

    public int  getCurrentStepNumber() {
        return currentStepNumber;
    }
    public void setCurrentStepNumber(int currentStepNumber) {
        this.currentStepNumber = currentStepNumber;
    }

    public void addMethodStep (MethodStep methodStep) {
        if (methodStep != null) {
            methodSteps.add(methodStep);
        }
    }


    //One to Many Relationship with Method Steps
    //So an arraylist of methodsteps; each method contains many methodSteps
}

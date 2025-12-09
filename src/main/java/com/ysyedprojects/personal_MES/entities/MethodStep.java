package com.ysyedprojects.personal_MES.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Method_Step")
public class MethodStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long methodStepID;

    @Column(name = "step_number")
    private Long methodStepNumber;

    @ManyToOne
    @JoinColumn (name = "Method_Id")
    private Method method;

    @Column(name = "instruction")
    private String instruction;

    @Column(name = "tools")
    private List<Tools> toolsList ;


    public void setToolsList(List<Tools> toolsList) {
        this.toolsList = toolsList;
    }

    public  List<Tools> getToolsList() {
        return toolsList;
    }

    public MethodStep() {}
    public void setMethod(Method method) {
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
    public String getInstruction() {
        return instruction;
    }

    public Long getMethodStepID() {
        return methodStepID;
    }

    //find a way to assign methodstepNumber properly
}

//method step object basically has a string description
package com.ysyedprojects.personalMES.services;

import com.ysyedprojects.personalMES.entities.MethodStep;

import java.util.List;

public interface MethodStepsService {


    public void createMethodStep(MethodStep methodStep);

    public List<MethodStep> getAllMethodSteps() ;

    public MethodStep getMethodStep (Long id);

    public void updateMethodStep (Long id, String instruction);
}


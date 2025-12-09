package com.ysyedprojects.personal_MES.services;

import com.ysyedprojects.personal_MES.entities.MethodStep;
import com.ysyedprojects.personal_MES.repositories.MethodStepRepository;

import java.util.List;

public interface MethodStepsService {


    public void createMethodStep(MethodStep methodStep);

    public List<MethodStep> getAllMethodSteps() ;


}


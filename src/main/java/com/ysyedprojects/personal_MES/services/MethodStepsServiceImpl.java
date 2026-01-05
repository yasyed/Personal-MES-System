package com.ysyedprojects.personal_MES.services;

import com.ysyedprojects.personal_MES.entities.Method;
import com.ysyedprojects.personal_MES.entities.MethodStep;
import com.ysyedprojects.personal_MES.repositories.MethodStepRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MethodStepsServiceImpl implements MethodStepsService {

    MethodStepRepository methodStepRepository;

    public MethodStepsServiceImpl(MethodStepRepository methodStepRepository) {
        this.methodStepRepository = methodStepRepository;
    }

    public void createMethodStep(MethodStep methodStep) {
        methodStepRepository.save(methodStep);
    }

    public MethodStep getMethodStep (Long id) {
        MethodStep methodStep = methodStepRepository.findById(id).get();
        return methodStep;
    }

    //use methods entity to get actual array list of method steps, and THEN-->
    // Add a function to get method steps entity's description, name, etc


    public List<MethodStep> getAllMethodSteps() {
        return methodStepRepository.findAll();
    }

    @Transactional
    public void updateMethodStep(Long id, String instruction) {
        MethodStep methodStep = methodStepRepository.findById(id).get();
        methodStep.setInstruction(instruction);
    }

}

package com.ysyedprojects.personal_MES.services;

import com.ysyedprojects.personal_MES.entities.Method;
import com.ysyedprojects.personal_MES.entities.MethodStep;
import com.ysyedprojects.personal_MES.repositories.MethodRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MethodServiceImpl implements MethodService {

    MethodRepository methodRepository;

    public MethodServiceImpl(MethodRepository methodRepository) {
        this.methodRepository = methodRepository;
    }

    public void createMethod (Method method) {
        methodRepository.save(method);
    }

    public List<Method> getAllMethods () {
        return methodRepository.findAll();
    }

    public Method getMethod (Long id) {
        Method method = methodRepository.findById(id).get();
        return method;
    }

    public List <MethodStep> getStepsList (Long methodId) {
        Method method = methodRepository.findById(methodId).get();
        return method.getMethodSteps();
    }

    public void setStepCounter (Long methodId, int stepCounter) {
        Method method = methodRepository.findById(methodId).get();
        method.setCurrentStepNumber(stepCounter + 1);
    }

    public MethodStep getMethodStep (Long methodId, int stepNumber) {
        Method method = methodRepository.findById(methodId).get();
        List <MethodStep> methodSteps = method.getMethodSteps();
        MethodStep  stepToExecute = methodSteps.get(stepNumber);
        return stepToExecute;
    }

    public int stepCheck (long methodId, int stepNumber) {
        Method method = methodRepository.findById(methodId).get();
        List <MethodStep> methodSteps = method.getMethodSteps();
        if ((methodSteps.size() - stepNumber == 1)) {
            return 0;
        } else  {
            return 1;
        }
    }
}

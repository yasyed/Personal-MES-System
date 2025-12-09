package com.ysyedprojects.personal_MES.services;

import com.ysyedprojects.personal_MES.entities.Method;
import com.ysyedprojects.personal_MES.entities.MethodStep;

import java.util.List;

public interface MethodService {

    public void createMethod (Method method);

    public Method getMethod (Long id);

    public List<Method> getAllMethods ();

    public List <MethodStep> getStepsList (Long methodId);

    public void setStepCounter (Long methodId, int stepCounter);

    public MethodStep getMethodStep (Long methodId, int stepNumber);

    public int stepCheck (long methodId, int stepNumber);
}

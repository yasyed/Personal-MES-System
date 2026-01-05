package com.ysyedprojects.personal_MES.controllers;
import com.ysyedprojects.personal_MES.entities.Method;
import com.ysyedprojects.personal_MES.entities.MethodStep;
import com.ysyedprojects.personal_MES.services.MethodService;
import com.ysyedprojects.personal_MES.services.MethodStepsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/executemethod/")
public class ExecuteMethodController {

    MethodStepsService methodStepsService;
    MethodService methodService;

    ExecuteMethodController(MethodService methodService, MethodStepsService methodStepsService) {
        this.methodService = methodService;
        this.methodStepsService = methodStepsService;
    }

    @GetMapping("homepage/{id}")
    public String executeMethodHomePage (@PathVariable("id") Long id, Model model) {
        Method method = methodService.getMethod(id);
        model.addAttribute("methodName", method);
        return "methodhomepage";
    }

    @GetMapping("{id}/{stepnumber}")
    public String executeMethod (@PathVariable("id") Long id,  @PathVariable("stepnumber") int stepnumber, Model model) {
        Method method = methodService.getMethod(id);
        model.addAttribute("methodName", method);
        methodService.setStepCounter(id, stepnumber);
        MethodStep stepToExecute = methodService.getMethodStep(id,stepnumber);
        model.addAttribute("currentStep", stepToExecute);
        int stepCheck = methodService.stepCheck(id, stepnumber);

        if (stepCheck == 1) {
            return "finalmethodstep";
        }

        //step number is incremented for the display (array starts at 0, but step should start at 1
        return "methodexecutionstep";
    }

    @GetMapping("{id}/complete")
    public String methodComplete (@PathVariable("id") Long id, Model model) {
        Method method = methodService.getMethod(id);
        model.addAttribute("methodName", method);
        return "methodcompletion";
    }

}

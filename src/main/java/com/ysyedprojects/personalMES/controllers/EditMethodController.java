package com.ysyedprojects.personalMES.controllers;

import com.ysyedprojects.personalMES.entities.Method;
import com.ysyedprojects.personalMES.entities.MethodStep;
import com.ysyedprojects.personalMES.services.MethodService;
import com.ysyedprojects.personalMES.services.MethodStepsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/editmethod/")
public class EditMethodController {

    MethodStepsService methodStepsService;
    MethodService methodService;

    EditMethodController(MethodService methodService, MethodStepsService methodStepsService) {
        this.methodService = methodService;
        this.methodStepsService = methodStepsService;
    }

    @GetMapping("editmethodsteps/{id}")
    public String editMethodSteps (@PathVariable("id") Long id, Model model) {
        Method method = methodService.getMethod(id);
        List<MethodStep> methodSteps = method.getMethodSteps(); //non-service step
        model.addAttribute("method", method);
        model.addAttribute("listofmethodsteps", methodSteps);
        return "viewmethodsteps";
    }

    @GetMapping("updateMethodStep/{id}")
    public String updateMethod (@PathVariable("id") Long id, Model model) {
        MethodStep methodStep = methodStepsService.getMethodStep(id);
        Method method = methodStep.getMethod();
        model.addAttribute("method", method);
        model.addAttribute("methodStep", methodStep);
        return "updatemethodstep";
    }

    @PostMapping("stepupdated")
    public String stepadded (@ModelAttribute("methodStep")  MethodStep methodStep) {
        Long id = methodStep.getMethodStepID();
        Method method = methodStep.getMethod();
        Long methodID = method.getMethodID();
        String instructions = methodStep.getInstruction();
        methodStepsService.updateMethodStep(id, instructions);
        return "redirect:/editmethod/editmethodsteps/" + methodID;
    }
}





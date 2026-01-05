package com.ysyedprojects.personal_MES.controllers;

import com.ysyedprojects.personal_MES.entities.Method;
import com.ysyedprojects.personal_MES.entities.MethodStep;
import com.ysyedprojects.personal_MES.entities.Tools;
import com.ysyedprojects.personal_MES.services.MethodService;
import com.ysyedprojects.personal_MES.services.MethodServiceImpl;
import com.ysyedprojects.personal_MES.services.MethodStepsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomePageController {

    MethodStepsService methodStepsService;
    MethodService methodService;

    HomePageController(MethodService methodService, MethodStepsService methodStepsService) {
        this.methodService = methodService;
        this.methodStepsService = methodStepsService;
    }

    @GetMapping()
    public String homePage() {
        return "homepage";
    }

    @GetMapping("executemethod")
    public String methodListToExecute (Model model) {
        List<Method> methods = methodService.getAllMethods();
        model.addAttribute("listofmethods", methods);
        return "executemethodlist";
    }

    @GetMapping("editmethod")
    public String editmethod (Model model) {
        List<Method> methods = methodService.getAllMethods();
        model.addAttribute("listofmethods", methods);
        List<MethodStep> methodSteps = methodStepsService.getAllMethodSteps();
        return "editmethod";
    }

    @GetMapping("newmethod")
    public String createnewmethod (@ModelAttribute("method") Method method) {

        return "createnewmethod";
    }
}
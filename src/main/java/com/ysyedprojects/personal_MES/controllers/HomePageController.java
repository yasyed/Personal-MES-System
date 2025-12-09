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

    @GetMapping
    public String homePage() {
        return "homepage";
    }

    @GetMapping("executemethodlist")
    public String methodListToExecute (Model model) {
        List <Method> methods = methodService.getAllMethods();
        model.addAttribute("listofmethods", methods);
        return "executemethodlist";
    }

    //when you click execute method, have it go to a method home page with a start or cancel at the bottom


    @GetMapping("executemethodhomepage/{id}")
    public String executeMethodHomePage (@PathVariable("id") Long id, Model model) {
        Method method = methodService.getMethod(id);
        model.addAttribute("methodName", method);
        return "methodhomepage";
    }

    @GetMapping("executemethod/{id}/{stepnumber}")
    public String executeMethod (@PathVariable("id") Long id,  @PathVariable("stepnumber") int stepnumber, Model model) {
        Method method = methodService.getMethod(id);
        model.addAttribute("methodName", method);
        MethodStep stepToExecute = methodService.getMethodStep(id,stepnumber);
        model.addAttribute("currentStep", stepToExecute);
        int stepCheck = methodService.stepCheck(id, stepnumber);

        if (stepCheck == 0) {
            return "finalmethodstep"; //this page I can turn into form and direct to postmapping method
        }

        methodService.setStepCounter(id, stepnumber);
        return "methodexecutionstep";
    }

    //use first model object for method name
    //Make some metadata for method like number of steps, approximate execution time, etc (make additional fields in Entity)

    @GetMapping("editmethod")
    public String editmethod (Model model) {
        List<Method> methods = methodService.getAllMethods();
        model.addAttribute("listofmethods", methods);
        List<MethodStep> methodSteps = methodStepsService.getAllMethodSteps();
        return "editmethod";
    }

    @GetMapping("viewmethodsteps/{id}")
    public String viewMethodSteps (@PathVariable("id") Long id, Model model) {
        Method method = methodService.getMethod(id);
        List<MethodStep> methodSteps = method.getMethodSteps(); //non-service step
        model.addAttribute("method", method);
        model.addAttribute("listofmethodsteps", methodSteps);
        return "viewmethodsteps";
    }


    @GetMapping("createnewmethod")
    public String createnewmethod (@ModelAttribute("method") Method method) {
        return "createnewmethod";
    }

    @PostMapping("addmethod")
    public String addmethod (@ModelAttribute("method") Method method) {
        methodService.createMethod(method);
        return "redirect:/editmethod";
    }


    @GetMapping("createmethodstep/{id}")
    public String createmethodstep (@PathVariable("id") Long id, Model model) {
        Method method = methodService.getMethod(id);
        MethodStep methodStep = new MethodStep();
        methodStep.setMethod(method); // non-service step; if you cancel adding a step, this won't ever get saved to database so its fine
        model.addAttribute("method", method);
        model.addAttribute("methodStep", methodStep);
        model.addAttribute("toolslist", Tools.values());
        return "addmethodsteps";
    }

    @PostMapping("stepadded")
    public String stepadded (@RequestParam String action, @ModelAttribute("methodStep")  MethodStep methodStep) {
       Method method = methodStep.getMethod(); // non-service step
       method.addMethodStep(methodStep); // non-service step
       methodStepsService.createMethodStep(methodStep);

        if (action.equals("save")) {
            return "redirect:/editmethod";
        }

        if (action.equals("add")) {
            Long id = method.getMethodID(); // non-service step
            return "redirect:/createmethodstep/" + id;
        }

            return "/";
    }

}

/*
-organize controller to include multiple controllers maybe to better organize
-need to add fixes for if there are blank entries (maybe include validation to not let you submit if blank)
 */
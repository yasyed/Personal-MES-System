
package com.ysyedprojects.personalMES.controllers;

import com.ysyedprojects.personalMES.entities.Method;
import com.ysyedprojects.personalMES.entities.MethodStep;
import com.ysyedprojects.personalMES.entities.Tools;
import com.ysyedprojects.personalMES.services.MethodService;
import com.ysyedprojects.personalMES.services.MethodStepsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/create/")
public class CreateController {

    MethodStepsService methodStepsService;
    MethodService methodService;

    CreateController(MethodService methodService, MethodStepsService methodStepsService) {
        this.methodService = methodService;
        this.methodStepsService = methodStepsService;
    }

    @PostMapping("addmethod")
    public String addmethod (@ModelAttribute("method") Method method) {
        methodService.createMethod(method);
        return "redirect:/editmethod";
    }

    @GetMapping("newmethodstep/{id}")
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
            return "redirect:/create/newmethodstep/" + id;
        }
        return "/";
    }
}

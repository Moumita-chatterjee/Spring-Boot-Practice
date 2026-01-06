package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class GradeController {

    List<Grade> studentGrades = new ArrayList<>();

    @GetMapping("/")
    //Request param= false means the url may or maynot have id parameter e.g localhost:8080/?id= 123
    public String getForm(Model model, @RequestParam(required = false) String id) {
        int index = getGradeIndex(id);

        Grade grade = (index == Constants.NOT_FOUND)? new Grade() : studentGrades.get(index);
        model.addAttribute("grade",grade);
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submmitForm(Grade grade) {
        int index = getGradeIndex(grade.getId());
        if(index==Constants.NOT_FOUND){
            //add a new grade
            studentGrades.add(grade);
        } else {
            //update the existing grade
            studentGrades.set(index, grade);
        }
        //TODO: process POST request
        
        return "redirect:/grades";
    }
    

    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", studentGrades);
        return "grades";
    }

    public int getGradeIndex(String id) {
        for (int i = 0; i < studentGrades.size(); i++) {
            if (studentGrades.get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

}
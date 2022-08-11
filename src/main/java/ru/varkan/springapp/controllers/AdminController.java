package ru.varkan.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.varkan.springapp.dao.StudentDAO;
import ru.varkan.springapp.models.Student;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final StudentDAO studentDAO;

    @Autowired
    public AdminController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;

    }

    @GetMapping()
    public String adminPage(Model model, @ModelAttribute("person") Student student) {
        model.addAttribute("people", studentDAO.index());
        return "adminPage";
    }

    @PatchMapping("/add")
    public String makeAdmin(@ModelAttribute("person") Student student) {
        System.out.println(student.getId());

        return "redirect:/students";
    }
}


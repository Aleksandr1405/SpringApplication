package ru.varkan.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.varkan.springapp.dao.StudentDAO;
import ru.varkan.springapp.models.Student;
import javax.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentsController {

    private final StudentDAO studentDAO;

    @Autowired
    public StudentsController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("students", studentDAO.index());
        return "students/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("student",studentDAO.show(id));
        return "students/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("student") @Valid Student students) {
        return "students/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Student student,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "students/new";

        studentDAO.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", studentDAO.show(id));
        return "students/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Student student, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "students/edit";

        studentDAO.update(id, student);
        return "redirect:/students";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        studentDAO.delete(id);
        return "redirect:/students";
    }

}

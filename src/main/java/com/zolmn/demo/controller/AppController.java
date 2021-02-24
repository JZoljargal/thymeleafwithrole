package com.zolmn.demo.controller;

import java.util.List;

import com.zolmn.demo.model.Employee;
import com.zolmn.demo.model.User;
import com.zolmn.demo.provider.SecurityService;
import com.zolmn.demo.provider.UserService;
import com.zolmn.demo.provider.UserValidator;
import com.zolmn.demo.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    // @Autowired
    // private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    // @GetMapping("/registration")
    // public String registration(Model model) {
    // if (securityService.isAuthenticated()) {
    // return "redirect:/";
    // }

    // model.addAttribute("userForm", new User());

    // return "registration";
    // }

    // @PostMapping("/registration")
    // public String registration(@ModelAttribute("userForm") User userForm,
    // BindingResult bindingResult) {
    // userValidator.validate(userForm, bindingResult);

    // if (bindingResult.hasErrors()) {
    // return "registration";
    // }

    // userService.save(userForm);

    // securityService.autoLogin(userForm.getName(), userForm.getPasswordConfirm());

    // return "redirect:/welcome";
    // }

    // @GetMapping("/login")
    // public String login(Model model, String error, String logout) {
    // if (securityService.isAuthenticated()) {
    // return "redirect:/";
    // }

    // if (error != null)
    // model.addAttribute("error", "Your username and password is invalid.");

    // if (logout != null)
    // model.addAttribute("message", "You have been logged out successfully.");

    // return "login";
    // }

    // @GetMapping({ "/", "/welcome" })
    // public String welcome(Model model) {
    // // return "welcome";
    // List<Employee> lstEmployee = employeeService.getEmployees();
    // model.addAttribute("employees", lstEmployee);
    // return "index";
    // }

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Employee> lstEmployee = employeeService.getEmployees();
        model.addAttribute("employees", lstEmployee);
        return "index";
    }

    @RequestMapping("/new")
    public String newEmployeePage(Model model) {
        Employee employee = new Employee();
        model.addAttribute(employee);
        return "new_employee";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("employee") Employee employee) throws Exception {
        employeeService.addEmployee(employee);
        return "redirect:/";
    }

    @RequestMapping("edit/{sid}")
    public ModelAndView showEditEmployeePage(@PathVariable(name = "sid") Long id) throws Exception {
        ModelAndView mav = new ModelAndView("edit_employee");
        Employee emp = employeeService.findEmployeeById(id);
        mav.addObject("employee", emp);
        return mav;
    }

    @RequestMapping("/delete/{sid}")
    public String deleteStudentPage(@PathVariable(name = "sid") Long sid) {
        employeeService.deleteEmployee(sid);
        return "redirect:/";
    }
}

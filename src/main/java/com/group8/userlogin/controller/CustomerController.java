package com.group8.userlogin.controller;

import com.group8.userlogin.service.CustomerService;
import com.group8.userlogin.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Random;

@ControllerAdvice
@Controller
public class CustomerController {

    //CRUD
    @Autowired
    private CustomerService customerService;


    @RequestMapping("/home")
    public String getAllCustomer(Model model) {
        model.addAttribute("getAllCustomer", customerService.getAllCustomerList());
        return "home";
    }

    @RequestMapping("/edit")
    public String getAllCustomes(Model model) {
        model.addAttribute("getAllCustomerss", customerService.getAllCustomerList());
        return "edit";
    }


    @RequestMapping("/saveform")
    public String saveform(Model model) {
        Customer customer = new Customer();
        model.addAttribute("saveCustomer", customer);
        return "newCustomer";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/home";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id) {
        //Call delete employee method
        this.customerService.deleteCustomerById(id);
        return "redirect:/home";
    }

    @GetMapping("/updateCustomer/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id, Model model){
//Get customer from the service
        Customer customer = customerService.getCustomerById(id);
        //Set customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);
        return "updateCustomer";
    }


    /*private static Logger logger = Logger.getLogger();

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(final Throwable throwable, final Model model) {
        logger.error("Exception during execution of SpringSecurity application", throwable);
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        return "error";*/



 /*Some type of 404 error */
    public class MyErrorController implements ErrorController {

        @RequestMapping("/error")
        public String handleError() {
        return "error";
        }

    }

    /*Math random for thank you page - ticket number autogenerated something*/
    @GetMapping("/thankyou")
    public ModelAndView getMyPage() {
        return new ModelAndView("thankyou", "randomSkaicius", randomSkaicius());
    }

    public int randomSkaicius() {
        Random rand = new Random();
        int skaicius = (int) (Math.random() * 50 + 1);
        return skaicius;
    }


}




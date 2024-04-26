 package com.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.model.Customer;
import com.gym.service.CustomerService;
import com.gym.service.FeedbackService;

import jakarta.validation.Valid;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService cusService;
	@Autowired
	private FeedbackService feedService;

	@GetMapping("/customer/new")
	public String showCreateForm(@Valid Model model) {
		model.addAttribute("membership", cusService.viewmembers());
		model.addAttribute("customers", new Customer());
		System.out.println("inside cus get");
		return "customer/customer_registration";
	}

	@PostMapping("/customer")
	public String registerUser(@Valid @ModelAttribute("customers") Customer customer, BindingResult result,
			Model model) {
		System.out.println("inside post customer");
		String view = "";
		if (result.hasErrors()) {
			model.addAttribute("membership", cusService.viewmembers());
			view = "customer/customer_registration";
			System.out.println("Inside error");
		}

		else {
			cusService.saveCustomer(customer);
			model.addAttribute("success", "Successfully Registered " + customer.getName());
			view = "redirect:/home";
			System.out.println("Inside save");
		}

		return view;
	}

	@GetMapping("/home")
	public String listcustomer(Model model) {
		model.addAttribute("customer", cusService.viewCustomer());

		return "home/home";
	}

	@GetMapping("/add/feedback")
	public String feedbackadd() {
		return "feedback/feedback";
	}

	@GetMapping("/customer/{cusId}")
	public String showUpdateForm(@PathVariable("cusId") int id, Model model) {
		model.addAttribute("customer",cusService.viewCustomerById(id) );
		return "AdminCrud/AdminUpdate";
	}

	@PostMapping("customer/update")
	public String updatecustomer(@Valid Customer cus, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "AdminCrud/AdminUpdate";
		}
		cusService.updateCustomer(cus);
		return "redirect:/submit";
	}

	@GetMapping("/customer/delete/{cusId}")
	public String deleteCustomer(@PathVariable("cusId") int id, Model model) {
		cusService.deleteCustomer(id);
		model.addAttribute("customer", cusService.viewCustomer());
		return "redirect:/submit";
	}

	@GetMapping("/admin")
	public String adminLoginPage(Model model) {
		System.out.println("Inside admin login");
		return "Admin/admin";
	}

	@PostMapping("/submit")
	public String submit(@RequestParam String username, @RequestParam String password, Model model) {
		String res = "";
		if (username.equalsIgnoreCase("admin@gmail.com") && password.equals("Admin@123")) {
			System.out.println("Inside submit");
			model.addAttribute("customer", cusService.viewCustomer());
			res = "Admin/AdminView";
		} else {
			res = "redirect:/admin";
		}
		return res;

	}

	@GetMapping("/submit")
	public String submitPage(Model model) {
	model.addAttribute("customer", cusService.viewCustomer());
		return "Admin/AdminView";
	}

	@GetMapping("/admin/deleteFeed")
	public String deleteFeed(@RequestParam String email) {
		feedService.deleteFeed(email);
		return "redirect:/admin/feedbacks";
	}
}

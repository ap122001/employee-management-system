package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data

	private List<Employee> theEmployees;

    private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService){
		super();
		employeeService =theEmployeeService;
	}

	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> theEmployees= employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
        System.out.println("hello2");
		return "employees/list-employees";
	}
//	@GetMapping("/hello")
//	public String hellos() {
//
//		// add to the spring model
//         String s= new String("kya haal h");
//        //throw new ReachedException("u reached here, mtlb ki thi sis working");
//		return "kya haal h";
//	}
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
//		System.out.println("hello");
		return new ResponseEntity<>("ho gya print", HttpStatus.OK);
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		Employee theEmployee =new Employee();
        System.out.println("hello");
		theModel.addAttribute("employee", theEmployee);
		return "employees/employee-form";
	}
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
		//save the employee
		employeeService.save(theEmployee);
		//use a redirect too prevent duplicate submisiions
		return "redirect:/employees/list";
	}

	@GetMapping("showFormForUpdate")
	public String showformForUpdate(@RequestParam("employeeId") int theId, Model theModel){
		//get the employee form the service
		Employee theEmployee= employeeService.findById(theId);

		//set employee in the model to prepopulate the form
		theModel.addAttribute("employee", theEmployee);

		//send over to our form

		return "employees/employee-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId){
		employeeService.deleteById(theId);

		return "redirect:/employees/list";
	}



}










package com.example.EMS.controller;

import com.example.EMS.model.EmployeeModel;
import com.example.EMS.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String homePage(){
        return employeeService.homePage();
    }

    @PostMapping("add")
    public EmployeeModel addEmployee(@Valid @RequestBody EmployeeModel employeeModel){
        return employeeService.addEmployee(employeeModel);
    }

    @GetMapping("displayAll")
    public List<EmployeeModel> displayEmployee(){
        return employeeService.displayEmployee();
    }

    @GetMapping("display/{Id}")
    public EmployeeModel getEmployeeById(@PathVariable("Id") Long Id){
        return employeeService.getEmployeeById(Id);
    }

    @PutMapping("update/{Id}")
    public EmployeeModel updateEmployee(@PathVariable("Id") Long Id,@Valid @RequestBody EmployeeModel employeeModel){

        return  employeeService.updateEmployee(Id,employeeModel);

    }

    @DeleteMapping("delete/{Id}")
    public String deleteEmployeeById(@PathVariable("Id") Long Id){
        return employeeService.deleteEmployeeById(Id);
    }


}

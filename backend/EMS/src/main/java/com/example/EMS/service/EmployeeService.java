package com.example.EMS.service;

import com.example.EMS.exception.EmployeeNotFoundException;
import com.example.EMS.model.EmployeeModel;
import com.example.EMS.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    public EmployeeModel addEmployee(EmployeeModel employeeModel) {
        return employeeRepo.save(employeeModel);

    }

    public List<EmployeeModel> displayEmployee() {
        return  employeeRepo.findAll();
    }

    public EmployeeModel getEmployeeById(Long id) {

        return employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
    }

    public EmployeeModel updateEmployee(Long id, EmployeeModel employeeModel) {

        EmployeeModel existingEmployee = employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));

        if(existingEmployee!=null){

            existingEmployee.setName(employeeModel.getName());
            existingEmployee.setRole(employeeModel.getRole());
            existingEmployee.setSalary(employeeModel.getSalary());
            existingEmployee.setEmail(employeeModel.getEmail());

            return employeeRepo.save(existingEmployee);
        }
        else {
            return null;
        }
    }

    public String deleteEmployeeById(Long id) {

        EmployeeModel existingEmployee = employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));

        employeeRepo.delete(existingEmployee);

       return "Employee deleted successfully";

    }

    public String homePage() {
        return "redirect:/index.html";
    }
}

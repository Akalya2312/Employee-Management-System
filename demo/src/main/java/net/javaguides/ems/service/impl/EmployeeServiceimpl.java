package net.javaguides.ems.service.impl;

import net.javaguides.ems.dto.Employeedto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.exception.Employeevalidator;
import net.javaguides.ems.exception.BadRequestException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.Employeerepo;
import net.javaguides.ems.service.Employeeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

import java.util.stream.Collectors;

@Service
public class EmployeeServiceimpl implements Employeeservice {

    private final Employeerepo employeeRepository;

    @Autowired
    public EmployeeServiceimpl(Employeerepo employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employeedto createEmployee(Employeedto employeeDto) {

        Employeevalidator.validate(employeeDto, employeeRepository);
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeedto(savedEmployee);
    }

    @Override
    public Employeedto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given id:" + employeeId));
        return EmployeeMapper.mapToEmployeedto(employee);
    }

    @Override
    public List<Employeedto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeedto(employee))
                .collect(Collectors.toList());
    }
    @Override
    public Employeedto updateEmployee(Long employeeId, Employeedto updatedEmployee) {
      Employee employee= employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee is not exist with given id:"+employeeId));
        Employeevalidator.validate(updatedEmployee, employeeRepository);
        EmployeeMapper.updateEmployeeFromDto(employee, updatedEmployee);
        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeedto(updatedEmployeeObj);

    }
    @Override
    public void deleteEmployee(Long employeeId)
    {
        Employee employee= employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee is not exist with given id:"+employeeId));
        employeeRepository.deleteById(employeeId);
    }
}



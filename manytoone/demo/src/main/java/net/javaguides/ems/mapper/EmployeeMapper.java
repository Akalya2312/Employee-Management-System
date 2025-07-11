package net.javaguides.ems.mapper;

import net.javaguides.ems.dto.Employeedto;
import net.javaguides.ems.entity.Department;
import net.javaguides.ems.entity.Employee;

    public class EmployeeMapper {

        public static Employeedto mapToEmployeedto(Employee employee) {
            Employeedto dto= new Employeedto(
                    employee.getEmpid(),
                    employee.getEmpname(),
                    employee.getPhno(),
                    employee.getEmail(),
                    employee.getPassword(),
                    employee.getDepartment() != null ? employee.getDepartment().getDeptid() : null,
                    employee.getDepartment() != null ? employee.getDepartment().getDeptname() : null
            );
            if(employee.getDepartment()!=null)
            {
               dto.setDeptname(employee.getDepartment().getDeptname());
            }
            return dto;
        }

        public static Employee mapToEmployee(Employeedto employeedto) {


            return new Employee(
                    employeedto.getEmpid(),
                    employeedto.getEmpname(),
                    employeedto.getPhno(),
                    employeedto.getEmail(),
                    employeedto.getPassword(),
                    null
            );
        }
        public static void updateEmployeeFromDto(Employee employee, Employeedto employeeDto){
            employee.setEmpname(employeeDto.getEmpname());
            employee.setPhno(employeeDto.getPhno());
            employee.setEmail(employeeDto.getEmail());
            employee.setPassword(employeeDto.getPassword());
        }
    }


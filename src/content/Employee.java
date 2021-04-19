package content;

import java.util.LinkedList;


public class Employee implements Comparable<Employee>{
    
    private String id;
    private String name;
    private String city;
    private String employee;
    private LinkedList<Employee> employeeList = new LinkedList<>();
    
    public Employee(){
    
    }
    
    public Employee(String id, String name, String city, String employee) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.employee = employee;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
    
    @Override
    public String toString(){
        return id + "," + name + "," + city + "," + employee; 
    }

    @Override
    public int compareTo(Employee one) {
        if (Integer.parseInt(this.id) < Integer.parseInt(one.id))
            return -1;
        else if(Integer.parseInt(this.id) > Integer.parseInt(one.id))
            return 1;
        else
            return 0;
    }

    
    
}

package ec.ecu.ups.icc.employees.entity;

public class Employees {
    private int id;
    private String firstName;
    private String LastName;
    private String email;
    private float salary;
    private String active;


    public Employees(){

    }
    

    public Employees(int id, String firstName, String lastName, String email, float salary, String active) {
        super();
        this.id = id;
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
        this.salary = salary;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }
   
    public float getSalary() {
        return salary;
    }
    public void setSalary(float salary) {
        this.salary = salary;
    }
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }

     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

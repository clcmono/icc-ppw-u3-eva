package ec.ecu.ups.icc.Department.entity;

public class Departments {

private int id;
private String name;
private  float budget;
private String active;

public Departments() {    
}


public Departments(int id, String name, float budget, String active) {
    super();
    this.id = id;
    this.name = name;
    this.budget = budget;
    this.active = active;
}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getBudget() {
        return budget;
    }
    public void setBudget(float budget) {
        this.budget = budget;
    }
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }


    
}

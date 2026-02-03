package ec.ecu.ups.icc.Company.entity;

import ec.ecu.ups.icc.core.entities.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "companies")
public class Companies extends BaseModel {

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "country", length = 100, nullable = false)
    private String country;

    @Column(name = "active", length = 1, nullable = false)
    private String active;

    // ================= CONSTRUCTORS =================
    public Companies() {
    }

    public Companies(String name, String country, String active) {
        this.name = name;
        this.country = country;
        this.active = active;
    }

    // ================= GETTERS =================
    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getActive() {
        return active;
    }

    // ================= SETTERS =================
    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setActive(String active) {
        this.active = active;
    }
}

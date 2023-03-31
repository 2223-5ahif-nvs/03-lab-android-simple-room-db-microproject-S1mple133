package at.htl.airport.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "AB_Airport")
public class Airport extends PanacheEntityBase {
    @Id @Column(name = "a_icao")
    private String icao;
    @Column(name = "a_name")
    private String name;
    @Column(name = "a_city")
    private String city;
    @Column(name = "a_country")
    private String country;

    public Airport(String icao, String name, String city, String country) {
        this.icao = icao;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public Airport() {

    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

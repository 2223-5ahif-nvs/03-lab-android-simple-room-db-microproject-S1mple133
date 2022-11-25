package at.htl.airport.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Airport extends PanacheEntityBase {
    @Id
    String icao;
    String name;
    String city;
    String country;

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

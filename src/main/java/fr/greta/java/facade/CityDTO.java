package fr.greta.java.facade;

import nonapi.io.github.classgraph.json.Id;

import java.util.Objects;

public class CityDTO {

    private int id;
    private String name;

    //------------------------


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDTO cityDTO = (CityDTO) o;
        return id == cityDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

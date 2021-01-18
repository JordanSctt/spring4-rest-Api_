package fr.greta.java.facade;

public class MeteoDTO {

    private int id;
    private String date;
    private int tempMatin;
    private int tempAprem;
    private CityDTO cityDTO;

    //--------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTempMatin() {
        return tempMatin;
    }

    public void setTempMatin(int tempMatin) {
        this.tempMatin = tempMatin;
    }

    public int getTempAprem() {
        return tempAprem;
    }

    public void setTempAprem(int tempAprem) {
        this.tempAprem = tempAprem;
    }

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }
}

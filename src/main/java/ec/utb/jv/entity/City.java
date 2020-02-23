package ec.utb.jv.entity;

import java.util.Objects;

public class City {

    private int cityId;
    private String cityCode;
    private String name;

    public City(int cityId, String cityCode, String name) {
        this.cityId = cityId;
        this.cityCode = cityCode;
        this.name = name;
    }

    public City(String cityCode, String name) {
        this(0, cityCode, name);
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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
        City city = (City) o;
        return cityId == city.cityId &&
                Objects.equals(cityCode, city.cityCode) &&
                Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, cityCode, name);
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityCode='" + cityCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

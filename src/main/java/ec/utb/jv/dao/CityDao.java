package ec.utb.jv.dao;

import ec.utb.jv.entity.City;

import java.util.List;

public interface CityDao {

    City findById(int id);

    List<City>findByCode(String code);

    List<City> findByName(String name);

    City add(City city);

    City update(City city);

    int delete(City city);
}

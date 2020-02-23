package ec.utb.jv.dao;

import ec.utb.jv.entity.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ec.utb.jv.dao.Connection.getConnection;

public class CityDaoJDBC implements CityDao {

    private static final String INSERT =
            "INSERT INTO city(cityCode, name) VALUES (?,?)";

    private static final String FIND_BY_ID =
        "Select * FROM city WHERE city_Id = ?";

    private static final String FIND_BY_CODE =
            "Select * FROM city WHERE city_code = ?";

    private static final String FIND_BY_NAME =
            "Select * FROM city WHERE city_name = ?";

    private static final String UPDATE_CITY =
            "UPDATE city SET cityCode = ?, name = ? WHERE city_id = ?";

    private static final String DELETE_CITY =
            "DELETE FROM city WHERE city = ?";

    @Override
    public City findById(int id) {
        City cityById = null;
        try (
                java.sql.Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                cityById = new City(resultSet.getInt("city_id"), resultSet.getString("citycode")
                        , resultSet.getString("cityname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityById;
    }

    @Override
    public List<City> findByCode(String code) {
        List<City> codeList = new ArrayList<>();
        try (
                java.sql.Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_BY_CODE);
                ResultSet resultSet = statement.executeQuery();
                ){
                    while (resultSet.next()){
                        City addedCity = new City(resultSet.getInt("city_id"),
                                resultSet.getString("citycode"), resultSet.getString("cityname"));
                        codeList.add(addedCity);
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codeList;
    }

    @Override
    public List<City> findByName(String name) {
        List<City> nameList = new ArrayList<>();
        try (
                java.sql.Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                City addedCity = new City(resultSet.getInt("city_id"),
                        resultSet.getString("citycode"), resultSet.getString("cityname"));
                nameList.add(addedCity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameList;
    }

    @Override
    public City add(City city) {
        java.sql.Connection connection = null;
        PreparedStatement statement = null;
        ResultSet keySet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, city.getCityCode());
            statement.setString(1, city.getName());
            statement.execute();
            keySet = statement.getGeneratedKeys();
            while (keySet.next()){
                city = new City(
                        keySet.getInt(1),
                        city.getCityCode(), city.getName();
                )
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (keySet != null){
                    keySet.close();
                }
                if (statement != null){
                    statement.close();
                }
                if (connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return city;
    }

    @Override
    public City update(City city) {
        try (java.sql.Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CITY)
        ){
            statement.setString(1, city.getCityCode());
            statement.setString(2, city.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;

    }

    @Override
    public int delete(City city) {
        int deletedCount = 0;
        try(java.sql.Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_CITY);
            PreparedStatement count = connection.prepareStatement("Select count(*) from City where City = ?");
            ResultSet countSet = count.executeQuery();
        ) {

            deletedCount = countSet.getInt(1);

            statement.setObject(1, city);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedCount;
    }
}

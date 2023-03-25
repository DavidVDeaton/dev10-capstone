package learn.easypacking.data.mappers;

import learn.easypacking.models.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet resultSet, int i) throws SQLException {
        Location location = new Location();
        location.setLocationId(resultSet.getInt("location_id"));
        location.setStreetAddress(resultSet.getString("street_address"));
        location.setCity(resultSet.getString("city"));
        location.setZip(resultSet.getInt("zip"));
        location.setState(resultSet.getString("state"));
        return location;
    }
}

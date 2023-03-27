package learn.easypacking.data;

import learn.easypacking.data.mappers.ItemMapper;
import learn.easypacking.models.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class ItemJdbcTemplateRepository implements ItemRepository{

    private final JdbcTemplate jdbcTemplate;

    public ItemJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Item> findAll() {
        final String sql = "select item_id, item_name, pack_status, quantity, description, user_id, container_id "
                + "from item;";
        return jdbcTemplate.query(sql, new ItemMapper());
    }

    public Item findById(int itemId) {
        final String sql = "select item_id, item_name, pack_status, quantity, description, user_id, container_id "
                + "from item "
                + "where item_id = ?;" ;

        return jdbcTemplate.query(sql, new ItemMapper(), itemId).stream()
             .findFirst().orElse(null);
    }

    public Item add (Item item) {
        final String sql = "insert into location (item_name, pack_status, quantity, description, user_id, container_id)"
                + "values (?,?,?,?,?,?);" ;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getItemName());
            ps.setBoolean(2, item.isPackStatus());
            ps.setInt(3, item.getQuantity());
            ps.setString(4, item.getDescription());
            ps.setInt(5, item.getUserId());
            ps.setInt(6, item.getContainerId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        item.setItemId(keyHolder.getKey().intValue());
        return item;
    }

    public boolean update(Item item) {
        final String sql = "update item set "
                + "item_name = ?, "
                + "pack_status = ?, "
                + "quantity = ?, "
                + "description = ?, "
                + "user_id = ?, "
                + "container_id = ?, "
                + "where item_id = ?;";

        return jdbcTemplate.update(sql,
                item.getItemName(),
                item.isPackStatus(),
                item.getQuantity(),
                item.getDescription(),
                item.getUserId(),
                item.getContainerId(),
                item.getItemId()) > 0;
    }

    public boolean deleteById(int itemId) {
        return jdbcTemplate.update(
                "delete from location where item_id = ?", itemId
        ) > 0;
    }


}

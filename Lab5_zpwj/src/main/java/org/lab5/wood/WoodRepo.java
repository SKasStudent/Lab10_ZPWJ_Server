package org.lab5.wood;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class WoodRepo {
    private final JdbcTemplate jdbc;

    private final RowMapper<Wood> mapper = (rs, rowNum) -> {
        Wood wood = new Wood(rs.getString("species"), rs.getDouble("price"), rs.getInt("quantitykg"));
        wood.setId(rs.getInt("id"));
        wood.setAvailable(rs.getBoolean("available"));
        return wood;
    };

    public WoodRepo(JdbcTemplate jdbc){this.jdbc = jdbc;}

    public Wood save(Wood wood){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement("INSERT INTO wood (species, price, quantitykg, available) VALUES (?, ?, ?, ?)", new String[]{"id"});
            ps.setString(1, wood.getSpecies());
            ps.setDouble(2, wood.getPrice());
            ps.setInt(3, wood.getQuantity());
            ps.setBoolean(4, wood.isAvailable());
            return ps;
        }, keyHolder);
        wood.setId(keyHolder.getKey().intValue());
        return wood;
    }

    public List<Wood> findAll(){
        return jdbc.query("SELECT * FROM wood ORDER BY id", mapper);
    }

    public Optional<Wood> findById(int id){
        return jdbc.query("SELECT * FROM wood WHERE id = ?", mapper, id).stream().findFirst();
    }

    public int update(int id, Wood wood){
        return jdbc.update("UPDATE wood SET species = ?, price = ?, quantitykg = ? WHERE id = ?", wood.getSpecies(), wood.getPrice(), wood.getQuantity(), id);
    }

    public int makeUnavailable(int id){
        return jdbc.update("UPDATE wood SET available = FALSE WHERE id = ?", id);
    }

    public int delete(int id){
        return jdbc.update("DELETE FROM wood WHERE id = ?", id);
    }
}

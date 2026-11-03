package org.lab5.client;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepo {
    private final JdbcTemplate jdbc;

    private final RowMapper<Client> mapper = (rs, rowNum) -> {
        Client client = new Client(rs.getString("name"), rs.getString("surname"), rs.getString("email"), rs.getString("phone"));
        client.setId(rs.getInt("id"));
        client.setActive(rs.getBoolean("active"));
        return client;
    };

    public ClientRepo(JdbcTemplate jdbc){this.jdbc = jdbc;}

    public Client save(Client client){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement("INSERT INTO clients (name, surname, email, phone, active) VALUES (?, ?, ?, ?, ?)", new String[]{"id"});
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPhone());
            ps.setBoolean(5, client.isActive());
            return ps;
        }, keyHolder);
        client.setId(keyHolder.getKey().intValue());
        return client;
    }

    public List<Client> findAll(){
        return jdbc.query("SELECT * FROM clients ORDER BY id", mapper);
    }

    public Optional<Client> findById(int id){
        return jdbc.query("SELECT * FROM clients WHERE id = ?", mapper, id).stream().findFirst();
    }

    public int update(int id, Client client){
        return jdbc.update("UPDATE clients SET name = ?, surname = ?, email = ?, phone = ? WHERE id = ?", client.getName(), client.getSurname(), client.getEmail(), client.getPhone(), id);
    }

    public int makeInactive(int id){
        return jdbc.update("UPDATE clients SET active = FALSE WHERE id = ?", id);
    }

    public int delete(int id){
        return jdbc.update("DELETE FROM clients WHERE id = ?", id);
    }
}

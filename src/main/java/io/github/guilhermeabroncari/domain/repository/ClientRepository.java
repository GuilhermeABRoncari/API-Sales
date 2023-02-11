package io.github.guilhermeabroncari.domain.repository;

import io.github.guilhermeabroncari.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static String INSERT = "insert into client (name) values (?)";
    private static String SELECT_ALL = "SELECT * FROM CLIENT";
    private static String UPDATE = "UPDATE CLIENT SET NAME = ? WHERE ID = ?";
    private static String DELETE = "DELETE FROM CLIENT WHERE ID = ? ";

    public Client persist(Client client) {
        jdbcTemplate.update(INSERT, new Object[]{client.getName()});
        return client;
    }

    public Client update(Client client) {
        jdbcTemplate.update(UPDATE, new Object[]{client.getName(), client.getId()});
        return client;
    }

    public void delete(Client client) {
        delete(client.getId());
    }

    public void delete(Long id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public List<Client> findByName(String name) {
        return jdbcTemplate.query(SELECT_ALL.concat(" WHERE NAME LIKE ?"),
                new Object[]{"%" + name + "%"},
                obtainClientMapper());
    }

    public List<Client> findAll() {
        return jdbcTemplate.query(SELECT_ALL, obtainClientMapper());
    }

    private static RowMapper<Client> obtainClientMapper() {
        return new RowMapper<Client>() {
            @Override
            public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("ID");
                String name = rs.getString("NAME");
                return new Client(id, name);
            }
        };
    }
}

package org.escapeone.repository.impl;

import org.escapeone.db.exception.DatabaseException;
import org.escapeone.entity.Player;
import org.escapeone.repository.interfaces.PlayerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PlayerDAOJDBC implements PlayerDAO {

    private static final Logger logger = Logger.getLogger(PlayerDAOJDBC.class.getName());
    private final Connection connection;

    public PlayerDAOJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Player player) {
        String sql = "INSERT INTO player (name, email, phone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, player.getName());
            stmt.setString(2, player.getEmail());
            stmt.setString(3, player.getPhone());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        player.setId(rs.getInt(1));
                    }
                }
            }
        }catch (SQLException e) {
            logger.severe("Error inserting player: " + e.getMessage());
            throw new RuntimeException("Error inserting Player", e);
        }
    }

    @Override
    public void updateById(int id, Player player) {
        String sql = "UPDATE player SET name = ?, email = ?, phone = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, player.getName());
            stmt.setString(2, player.getEmail());
            stmt.setString(3, player.getPhone());
            stmt.setInt(4, player.getId());

            int rowsUpdated = stmt.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
        } catch (SQLException e) {
            logger.severe("Error updating player: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM player WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            logger.severe("Error deleting player: " + e.getMessage());
            throw new DatabaseException("Error deleting player with ID " + id, e);
        }
    }

    @Override
    public Player findById(int id) {
        String sql = "SELECT * FROM player WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Player player = new Player();
                player.setId(rs.getInt("id"));
                player.setName(rs.getString("name"));
                player.setEmail(rs.getString("email"));
                player.setPhone(rs.getString("phone"));
                return player;
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.severe("Error finding player: " + e.getMessage());
            throw new DatabaseException("Error finding player with ID " + id, e);
        }
    }

    @Override
    public List<Player> findByName(String name) {
        String sql = "SELECT * FROM player WHERE UPPER(name) LIKE UPPER(?);";
        List<Player> players = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Player player = new Player();
                player.setId(rs.getInt("id"));
                player.setName(rs.getString("name"));
                player.setEmail(rs.getString("email"));
                player.setPhone(rs.getString("phone"));
                players.add(player);
            }

            return players;

        } catch (SQLException e) {
            logger.severe("Error finding player by name: " + e.getMessage());
            throw new DatabaseException("Error finding players with name: " + name, e);
        }
    }

    @Override
    public List<Player> findAll() {
        String sql = "SELECT * FROM player";
        List<Player> players = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Player player = new Player();
                player.setId(rs.getInt("id"));
                player.setName(rs.getString("name"));
                player.setEmail(rs.getString("email"));
                player.setPhone(rs.getString("phone"));
                players.add(player);
            }

            return players;

        } catch (SQLException e) {
            logger.severe("Error finding all players: " + e.getMessage());
            throw new DatabaseException("Error retrieving all players", e);
        }
    }
}

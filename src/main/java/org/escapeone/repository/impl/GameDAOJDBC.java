package org.escapeone.repository.impl;

import org.escapeone.db.exception.DatabaseException;
import org.escapeone.entity.Game;
import org.escapeone.entity.Player;
import org.escapeone.entity.enums.Room;
import org.escapeone.repository.interfaces.GameDAO;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class GameDAOJDBC implements GameDAO {

    private static final Logger logger = Logger.getLogger(GameDAOJDBC.class.getName());
    private final Connection connection;

    public GameDAOJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Game game) {
        String sql = "INSERT INTO game (room, players_qty, game_datetime, total_price, notes) " +
                "VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            st.setString(1, game.getRoom().name());
            st.setInt(2, game.getPlayersQty());
            st.setTimestamp(3, Timestamp.valueOf(game.getDate()));
            st.setBigDecimal(4, BigDecimal.valueOf(game.getTotalPrice()));
            st.setString(5, game.getNotes());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                try(ResultSet rs = st.getGeneratedKeys()){
                    if(rs.next()){
                        int id = rs.getInt(1);
                        game.setId(id);
                    }
                }
            } else {
                throw new DatabaseException("unexpected error!");
            }


        }catch (SQLException e) {
            logger.severe("Error inserting the Game: " + e.getMessage());
            throw new RuntimeException("Error inserting the Game", e);
        }
    }

    @Override
    public void updateById(int id, Game game) {
        String sql = "UPDATE game SET room = ?, players_qty = ?, game_datetime = ?, total_price = ?, notes = ? " +
                "WHERE id = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, game.getRoom().name());              // enum como String
            st.setInt(2, game.getPlayersQty());                 // quantidade de jogadores
            st.setTimestamp(3, Timestamp.valueOf(game.getDate())); // LocalDateTime -> Timestamp
            st.setBigDecimal(4, BigDecimal.valueOf(game.getTotalPrice())); // Double -> BigDecimal
            st.setString(5, game.getNotes());                   // notas (pode ser null)
            st.setInt(6, id);                                   // id do jogo a ser atualizado

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new DatabaseException("Error: No records updated. Check ID.");
            }

        } catch (SQLException e) {
            logger.severe("Error updating the Game: " + e.getMessage());
            throw new RuntimeException("Error updating the Game", e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM game WHERE id = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("No record found to delete with ID: " + id);
            }

        } catch (SQLException e) {
            logger.severe("Error deleting the Game: " + e.getMessage());
            throw new RuntimeException("Error while deleting game: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Game> findById(int id) {
        String sql = "SELECT * FROM game WHERE id = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Game game = new Game();
                    game.setId(rs.getInt("id"));
                    game.setRoom(Room.valueOf(rs.getString("room"))); // enum from DB
                    game.setPlayersQty(rs.getInt("players_qty"));
                    game.setDate(rs.getTimestamp("game_datetime").toLocalDateTime()); // Timestamp -> LocalDateTime
                    game.setTotalPrice(rs.getBigDecimal("total_price").doubleValue());
                    game.setNotes(rs.getString("notes"));

                    return Optional.of(game);
                } else {
                    return Optional.empty();
                }
            }

        } catch (SQLException e) {
            logger.severe("Error finding the game by id: " + e.getMessage());
            throw new RuntimeException("Error while retrieving game: " + e.getMessage(), e);
        }
    }


    @Override
    public Optional<List<Game>> findAll() {
        String sql = "SELECT * FROM game";

        List<Game> games = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Game game = new Game();
                game.setId(rs.getInt("id"));
                game.setRoom(Room.valueOf(rs.getString("room")));
                game.setPlayersQty(rs.getInt("players_qty"));
                game.setDate(rs.getTimestamp("game_datetime").toLocalDateTime());
                game.setTotalPrice(rs.getBigDecimal("total_price").doubleValue());
                game.setNotes(rs.getString("notes"));

                games.add(game);
            }

            return games.isEmpty() ? Optional.empty() : Optional.of(games);

        } catch (SQLException e) {
            logger.severe("Error finding the Game list: " + e.getMessage());
            throw new RuntimeException("Error while retrieving all games: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Game> findByRoom(String room) {
        String sql = "SELECT * FROM game WHERE room = ?";

        List<Game> games = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, room);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Game game = new Game();
                    game.setId(rs.getInt("id"));
                    game.setRoom(Room.valueOf(rs.getString("room")));
                    game.setPlayersQty(rs.getInt("players_qty"));
                    game.setDate(rs.getTimestamp("game_datetime").toLocalDateTime());
                    game.setTotalPrice(rs.getBigDecimal("total_price").doubleValue());
                    game.setNotes(rs.getString("notes"));

                    games.add(game);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving games by room: " + e.getMessage(), e);
        }

        return games; // pode ser vazio, nunca null
    }

    @Override
    public List<Game> findByDate(LocalDateTime date) {
        String sql = "SELECT * FROM game WHERE DATE(game_datetime) = ?";

        List<Game> games = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            // pass only the LocalDate part
            st.setDate(1, java.sql.Date.valueOf(date.toLocalDate()));

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Game game = new Game();
                    game.setId(rs.getInt("id"));
                    game.setRoom(Room.valueOf(rs.getString("room")));
                    game.setPlayersQty(rs.getInt("players_qty"));
                    game.setDate(rs.getTimestamp("game_datetime").toLocalDateTime());
                    game.setTotalPrice(rs.getBigDecimal("total_price").doubleValue());
                    game.setNotes(rs.getString("notes"));

                    games.add(game);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving games by date: " + e.getMessage(), e);
        }

        return games;
    }





    @Override
    public void addPlayersToGame(Game game, List<Player> players) {
        String sql = "INSERT INTO player_game (player_id, game_id) VALUES (?, ?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            int gameId = game.getId();
            for (Player player : players) {
                st.setInt(1, player.getId()); // pega o ID do objeto Player
                st.setInt(2, gameId);
                st.addBatch(); // adiciona ao batch
            }

            st.executeBatch(); // executa todos os inserts de uma vez

        } catch (SQLException e) {
            throw new RuntimeException("Error while adding players to game: " + e.getMessage(), e);
        }
    }

    @Override
    public void removePlayerFromGame(int gameId, int playerId) {
        String sql = "DELETE FROM player_game WHERE game_id = ? AND player_id = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, gameId);
            st.setInt(2, playerId);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("No record found to remove for game ID " + gameId + " and player ID " + playerId);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while removing player from game: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Player> getPlayersByGame(int gameId) {
        String sql = """
        SELECT p.id, p.name, p.email, p.phone
        FROM player p
        INNER JOIN player_game pg ON p.id = pg.player_id
        WHERE pg.game_id = ?
        """;

        List<Player> players = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, gameId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Player player = new Player();
                    player.setId(rs.getInt("id"));
                    player.setName(rs.getString("name"));
                    player.setEmail(rs.getString("email"));
                    player.setPhone(rs.getString("phone"));

                    players.add(player);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving players by game: " + e.getMessage(), e);
        }

        return players;
    }
}

package org.escapeone.repository.interfaces;

import org.escapeone.entity.Game;
import org.escapeone.entity.Player;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GameDAO {

    void insert(Game game);
    void updateById(int id, Game game);
    void deleteById(int id);
    Optional<Game> findById(int id);
    Optional<List<Game>> findAll();

    List<Game> findByRoom(String room);
    List<Game> findByDate(LocalDateTime date);


    void addPlayersToGame(Game game, List<Player> players);
    void removePlayerFromGame(int gameId, int playerId);
    List<Player> getPlayersByGame(int gameId);
}

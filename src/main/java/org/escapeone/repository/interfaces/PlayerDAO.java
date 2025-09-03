package org.escapeone.repository.interfaces;

import org.escapeone.entity.Player;

import java.util.List;

public interface PlayerDAO {

    public void insert(Player player);

    public void updateById(int id,Player player);

    public void deleteById(int id);

    public Player findById(int id);

    public List<Player> findByName(String name);

    public List<Player> findAll();
}

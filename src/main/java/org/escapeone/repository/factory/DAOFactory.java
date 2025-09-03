package org.escapeone.repository.factory;

import org.escapeone.db.Database;
import org.escapeone.repository.impl.GameDAOJDBC;
import org.escapeone.repository.impl.PlayerDAOJDBC;
import org.escapeone.repository.interfaces.GameDAO;
import org.escapeone.repository.interfaces.PlayerDAO;

public class DAOFactory {

    public static PlayerDAO createPlayerDAO(){
        return new PlayerDAOJDBC(Database.getConnection());
    }

    public static GameDAO createGameDAO(){
        return new GameDAOJDBC(Database.getConnection());
    }


}

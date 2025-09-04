package org.escapeone;


import org.escapeone.entity.Game;
import org.escapeone.entity.Player;
import org.escapeone.entity.enums.Room;
import org.escapeone.repository.factory.DAOFactory;
import org.escapeone.repository.interfaces.GameDAO;
import org.escapeone.repository.interfaces.PlayerDAO;
import org.escapeone.view.main.MainFrame;


import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //PlayerDAO playerDAO = DAOFactory.createPlayerDAO();
        //GameDAO gameDAO = DAOFactory.createGameDAO();


        //Player p1 = new Player("bob", "bob@teste.com", "99999999");
        //Player p2 = new Player("Ana", "ana@email.com", "88888888");
        //Player p3 = new Player("Caio", "caio@email.com", "77777777");

        //playerDAO.insert(p1);
        //playerDAO.insert(p2);
        //playerDAO.insert(p3);

        //System.out.println("Players inserted: " + p1.getId() + ", " + p2.getId() + ", " + p3.getId());


        //Game game = new Game(Room.ALCATRAZ,3,LocalDateTime.now().plusDays(1),150.0,"Test game with friends");

        //Inserindo o jogo
       // gameDAO.insert(game);
        //System.out.println("Game inserted with id: " + game.getId());


        //List<Player> players = List.of(p1, p2, p3);

        //gameDAO.addPlayersToGame(game, players);
        //System.out.println("Players added to game");

        // Buscar players do jogo
        //List<Player> playersInGame = gameDAO.getPlayersByGame(game.getId());

        //System.out.println("Players in game " + game.getId() + ":");
        //for (Player p : playersInGame) {
        //    System.out.println(p.getId() + " - " + p.getName() + " (" + p.getEmail() + ")");
        //}

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
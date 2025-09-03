# EscapeOne Game Management System

A Java application to manage games, rooms, and players, designed for escape room scenarios. The system provides CRUD operations for games and players, and allows associating multiple players to a game using a many-to-many relationship.

## Features

- **Game Management**  
  - Create, read, update, and delete games.  
  - Store game details including room, date & time, total price, and notes.  

- **Player Management**  
  - Create, read, update, and delete players.  
  - Store player details including name, email, and phone.  

- **Game-Player Association**  
  - Add multiple players to a game.  
  - Retrieve all players for a specific game.  
  - Remove a player from a game.

## Database

- MySQL database with the following tables:  
  - `game`: stores game details.  
  - `player`: stores player information.  
  - `player_game`: junction table to associate players and games (many-to-many relationship).  

- Game date and time are stored using a single `DATETIME` field.

- Enum `Room` is used to define available game rooms:
  ```java
  public enum Room {
      ALCATRAZ, BOMB, OPERACAO_X, PORTAL, REFENS, SEGREDOS, TIC_TAC
  }

## Technologies

- Java 21
- JDBC for database interaction
- MySQL as relational database


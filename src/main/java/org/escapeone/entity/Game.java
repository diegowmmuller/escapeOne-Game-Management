package org.escapeone.entity;

import org.escapeone.entity.enums.Room;

import java.time.LocalDateTime;

public class Game {

    private Integer id;
    private Room room;
    private Integer playersQty;
    private LocalDateTime date;
    private Double totalPrice;
    private String notes;

    public Game(){

    }

    public Game(Room room, Integer playersQty, LocalDateTime date, Double totalPrice, String notes) {
        this.room = room;
        this.playersQty = playersQty;
        this.date = date;
        this.totalPrice = totalPrice;
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getPlayersQty() {
        return playersQty;
    }

    public void setPlayersQty(Integer playersQty) {
        this.playersQty = playersQty;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

package com.jamsiedaly.events;

public class Event {

    private int number;
    private Float price;
    private int numberOfTickets;

    public Event(int number, Float price){
        this.number = number;
        this.price = price;
        this.numberOfTickets = 10;
    }

    public int getNumber(){
        return number;
    }

    public Float getPrice() {
        return price;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void buyTickets(int numberOfTicketsRequested){
        this.numberOfTickets -= numberOfTicketsRequested;
    }
}

package com.jamsiedaly.coordinate;

import com.jamsiedaly.events.Event;

import java.util.ArrayList;

public class CoordinateLocation {

    private ArrayList<Event> eventList = new ArrayList <Event>();
    private int coordinateX;
    private int coordinateY;

    public CoordinateLocation(int coordinateX, int coordinateY){
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public void addEvent(Event event){
        eventList.add(event);
    }

    public ArrayList <Event> getEventList() {
        return eventList;
    }

    public int getNumberOfEvents(){
        return eventList.size();
    }
}

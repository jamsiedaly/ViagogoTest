package com.jamsiedaly;

import com.jamsiedaly.coordinate.CoordinateLocation;
import com.jamsiedaly.events.Event;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static CoordinateLocation[][] map;
    static ArrayList<Event> nearEvents;

    public static void main(String[] args) {
        int mapWidth = 10;
        int mapHeight = 10;
        int numberOfEventsToDisplay = 3;
        ArrayList<Integer> nearEventsDistances = new ArrayList <Integer>();
        map = createMap(mapWidth, mapHeight);
        while (true) {
            processInputs(numberOfEventsToDisplay);
        }
    }

    private static void processInputs(int numberOfEventsToDisplay) {
        ArrayList <Integer> nearEventsDistances;
        String input = getInput();
        if (input != "Incorrect") {
            int[] userCoordinates = getUserCoordinates(input);
            int userX = userCoordinates[1];
            int userY = userCoordinates[0];
            if(userX > 9 || userX < 0 || userY > 9 || userY < 0){
              System.out.println("***********************************************" +
                                 "\nPlease only enter coordinates between 0 and 9\n" +
                                 "***********************************************\n");
            } else {
                System.out.println("Closest Events to you: (" + userX + "," + userY + ")");
                nearEventsDistances = getNearestEvents(userCoordinates, map, numberOfEventsToDisplay);
                for (int eventNumber = 0; eventNumber < nearEvents.size() && eventNumber < numberOfEventsToDisplay; eventNumber++) {
                    System.out.print("Event ID:  " + nearEvents.get(eventNumber).getNumber()
                            + "\tEvent Price:  " + nearEvents.get(eventNumber).getPrice()
                            + "\tEvent distance:  " + nearEventsDistances.get(eventNumber)
                            + "\n\n");
                }
            }
        } else {
            System.out.println("************\n" +
                                "Invalid Input\n" +
                                "************\n");
        }
    }

    private static int[] getUserCoordinates(String input) {
        String[] coordinates = input.split(",");
        int[] userCoordinates = new int[2];
        userCoordinates[1] = Integer.parseInt(coordinates[0]);
        userCoordinates[0] = Integer.parseInt(coordinates[1]);
        return userCoordinates;
    }

    private static String getInput() {
        System.out.println("Please input coordinates: ");
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        boolean verifiedInput = verifyUserInput(userInput);
        if(verifiedInput){
            return userInput;
        }
        else
            return "Incorrect";
    }

    private static boolean verifyUserInput(String userInput){
        boolean validInput;
        String regexInput = "[0-9]+,[0-9]+";
        validInput = userInput.matches(regexInput);
        return validInput;
    }

    private static CoordinateLocation[][] createMap(int mapWidth, int mapHeight) {
        int eventIndex = 0;
        CoordinateLocation[][] newMap = new CoordinateLocation[mapWidth][mapHeight];
        Random randomGenerator = new Random();
        for(int i = 0; i < mapWidth; i++){
            for(int j = 0; j < mapHeight; j++){
                int randomNumber = randomGenerator.nextInt(10);
                newMap[i][j] = new CoordinateLocation(i, j);
                if(randomNumber < 2){
                    float price = randomGenerator.nextFloat()*100;
                    Event myEvent = new Event(eventIndex, price);
                    newMap[i][j].addEvent(myEvent);
                    eventIndex++;
                }
            }
        }
        return newMap;
    }

    private static ArrayList<Integer> getNearestEvents(int[] userCoordinates,
                                                       CoordinateLocation[][] map,
                                                       int numberOfRequestedEvents){
        nearEvents = new ArrayList <Event>();
        int userX = userCoordinates[0];
        int userY = userCoordinates[1];
        nearEvents = map[userX][userY].getEventList();
        ArrayList<Integer> eventDistances = new ArrayList <Integer>();
        for(int index = 0; index < nearEvents.size(); index++)
            eventDistances.add(0);
        if(nearEvents.size() >= numberOfRequestedEvents){
            return eventDistances;
        }
        for(int i = 1; i < map.length; i++){
            int distance = i;
            for(int j = 0; j < distance; j++){
                int numberOfCloserEvents = nearEvents.size();
                int distanceChanging = distance - j;

                nearEvents.addAll(getNeighbourEvents(userX + distanceChanging, userY + j));
                nearEvents.addAll(getNeighbourEvents(userX - distanceChanging, userY + j));
                nearEvents.addAll(getNeighbourEvents(userX + j, userY  -distanceChanging));
                nearEvents.addAll(getNeighbourEvents(userX - j, userY - distanceChanging));

                int numberOfNewEvents = nearEvents.size() - numberOfCloserEvents;
                for(int index = 0; index < numberOfNewEvents; index++)
                        eventDistances.add(distance);
                if(nearEvents.size() >= numberOfRequestedEvents)
                    break;
            }
            if(nearEvents.size() >= numberOfRequestedEvents)
                break;
        }
        return eventDistances;
    }
    public static ArrayList<Event> getNeighbourEvents(int x, int y){
        ArrayList<Event> neighboursEvents = new ArrayList <Event>();
        try{
            CoordinateLocation point = map[x][y];
            neighboursEvents.addAll(point.getEventList());
        }catch (ArrayIndexOutOfBoundsException e){
            //Went outside the map
        }
        return neighboursEvents;
    }
}

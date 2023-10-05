package main;

import Chara.Character;

import java.util.ArrayList;

public class GameStatus {
    private map theMap;
    private ArrayList<Character> players;
    private ArrayList<Character> enemies;

    public void setCurrentMap(map m){theMap = m;}
    public map getCurrentMap(){return theMap;}

    public void setPlayers(ArrayList<Character> p){players = p;}
    public ArrayList<Character> getPlayers(){return players;}

    public void setEnemies(ArrayList<Character> e){enemies = e;}
    public ArrayList<Character> getEnemies(){return enemies;}
}

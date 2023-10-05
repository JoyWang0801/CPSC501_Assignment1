package Junit.src.test.java;


import Chara.*;
import Chara.Character;
import main.GameStatus;
import main.map;
import main.mapGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestGameStatus {
    static ArrayList<Character> character_list;
    static ArrayList<Character> enemy_list;
    static map m;

    static GameStatus gameStatus;

    public void SetUpTest()
    {
        mapGenerator mg = new mapGenerator();
        m = mg.generate();

        int i = 0;
        character_list  = new ArrayList<>();

        Character testCharacter = new KinesiologyMajor(i++);
        character_list.add(testCharacter);
        testCharacter = new ZoologyMajor(i++);
        character_list.add(testCharacter);
        testCharacter = new EngineeringMajor(i++);
        character_list.add(testCharacter);
        testCharacter = new ChemistryMajor(i++);
        character_list.add(testCharacter);
        testCharacter = new BiomedicalMajor(i++);
        character_list.add(testCharacter);
        testCharacter = new PhilosophyMajor(i++);
        character_list.add(testCharacter);

        enemy_list = new ArrayList<>();
        Character enemy = new Enemy("Enemy", i++, 15, 150, 0, 15, 150, 0, 3, 1);
        enemy_list.add(enemy);
        enemy = new Enemy("Enemy", i, 15, 150, 0, 15, 150, 0, 3, 1);
        enemy_list.add(enemy);
    }

    @Test
    public void testGameStatus()
    {

        gameStatus = new GameStatus();
        gameStatus.players = character_list;
        gameStatus.enemies = enemy_list;

        assertSame(character_list, gameStatus.players);
        assertSame(enemy_list, gameStatus.enemies);
    }

    @Test
    public void testGameStatusAttack()
    {
        gameStatus = new GameStatus();
        ArrayList<Character> characters  = new ArrayList<>();
        ArrayList<Character> enemies = new ArrayList<>();
        gameStatus.players = characters;
        gameStatus.enemies = enemies;

        mapGenerator mg = new mapGenerator();
        m = mg.generate();

        int i = 0;

        Character testCharacter = new KinesiologyMajor(i++);
        characters.add(testCharacter);

        Character e = new Enemy("Enemy", i++, 15, 150, 0, 15, 150, 0, 3, 1);
        enemies.add(e);

        gameStatus.theMap = m;

        int attack_before = gameStatus.players.get(0).getAttack();
        int attack_after = 33;
        gameStatus.players.get(0).setAttack(attack_after);

        assertEquals(characters.size(), gameStatus.players.size());
        assertEquals(enemies.size(), gameStatus.enemies.size());
        assertNotEquals(attack_before, gameStatus.players.get(0).getAttack());
        assertEquals(attack_after, gameStatus.players.get(0).getAttack());
    }

    @Test
    public void testGameStatusMap()
    {
        gameStatus = new GameStatus();

        mapGenerator mg = new mapGenerator();
        map m1 = mg.generate();
        map m2 = mg.generate();

        gameStatus.theMap = m1;
        assertNotSame(m2, gameStatus.theMap);
        gameStatus.theMap = m2;
        assertSame(m2, gameStatus.theMap);
        assertNotSame(m1, gameStatus.theMap);
    }
}

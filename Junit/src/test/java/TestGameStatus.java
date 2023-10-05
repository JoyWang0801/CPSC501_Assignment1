package Junit.src.test.java;


import Chara.*;
import Chara.Character;
import main.GameStatus;
import main.map;
import main.mapGenerator;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestGameStatus {
    static ArrayList<Character> character_list;
    static ArrayList<Character> enemy_list;
    static map m;

    static GameStatus gameStatus;

    @Test
    public void testGameStatus()
    {

        gameStatus = new GameStatus();
        gameStatus.setPlayers(character_list);
        gameStatus.setEnemies(enemy_list);

        assertSame(character_list, gameStatus.getPlayers());
        assertSame(enemy_list, gameStatus.getEnemies());
    }

    @Test
    public void testGameStatusAttack()
    {
        gameStatus = new GameStatus();
        ArrayList<Character> characters  = new ArrayList<>();
        ArrayList<Character> enemies = new ArrayList<>();
        gameStatus.setPlayers(characters);
        gameStatus.setEnemies(enemies);

        mapGenerator mg = new mapGenerator();
        m = mg.generate();

        int i = 0;

        Character testCharacter = new KinesiologyMajor(i++);
        characters.add(testCharacter);

        Character e = new Enemy("Enemy", i++, 15, 150, 0, 15, 150, 0, 3, 1);
        enemies.add(e);

        gameStatus.setCurrentMap(m);

        int attack_before = gameStatus.getPlayers().get(0).getAttack();
        int attack_after = 33;
        gameStatus.getPlayers().get(0).setAttack(attack_after);

        assertEquals(characters.size(), gameStatus.getPlayers().size());
        assertEquals(enemies.size(), gameStatus.getEnemies().size());
        assertNotEquals(attack_before, gameStatus.getPlayers().get(0).getAttack());
        assertEquals(attack_after, gameStatus.getPlayers().get(0).getAttack());
    }

    @Test
    public void testGameStatusMap()
    {
        gameStatus = new GameStatus();

        mapGenerator mg = new mapGenerator();
        map m1 = mg.generate();
        map m2 = mg.generate();

        gameStatus.setCurrentMap(m1);
        assertNotSame(m2, gameStatus.getCurrentMap());
        gameStatus.setCurrentMap(m2);
        assertSame(m2, gameStatus.getCurrentMap());
        assertNotSame(m1, gameStatus.getCurrentMap());
    }
}

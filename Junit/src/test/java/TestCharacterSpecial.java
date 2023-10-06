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

public class TestCharacterSpecial {

    static ArrayList<Character> character_list;
    static ArrayList<Character> enemy_list;
    static map m;
    GameStatus game_status;

    int character_list_size;
    @Before
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

        game_status = new GameStatus();
        game_status.setPlayers(character_list);
        game_status.setEnemies(enemy_list);
        game_status.setCurrentMap(m);

        // making sure all characters are initiated
        character_list_size = character_list.size();
        assertEquals(6, character_list_size);
    }

    @Test
    public void testKinesiologySpecial()
    {
        Character current_character = character_list.get(0);
        Character victim = enemy_list.get(0);

        int character_id = current_character.getID();
        int victim_xpos = m.getPos(character_id)[0];
        int victim_ypos = m.getPos(character_id)[1] + 1;
        m.setPos(victim.getID(), victim_xpos, victim_ypos);
        int new_health = victim.getHealth() - current_character.getAttack() * 2;

        boolean success = current_character.Special(victim_xpos, victim_ypos, game_status);

        if(success)
        {
            assertEquals(new_health, victim.getHealth());
        }
        else
        {
            fail(current_character.getName() + " failed to perform special attack");
        }

        current_character.setMana(0);
        success = current_character.Special(victim_xpos, victim_ypos, game_status);

        assertFalse("Mana is not enough to use special", success);
    }

    @Test
    public void testZoologySpecial()
    {
        Character current_character = character_list.get(1);
        int character_id = current_character.getID();
        m.setPos(character_id, 6, 6);

        int new_x = m.getPos(character_id)[0];
        int new_y = m.getPos(character_id)[1] + 1;

        boolean success = current_character.Special(new_y, new_x, game_status);

        if(success)
        {
            assertEquals(new_x, m.getPos(character_id)[0]);
            assertEquals(new_y, m.getPos(character_id)[1]);
        }
        else
        {
            fail(current_character.getName() + " failed to perform special attack");
        }

        current_character.setMana(0);
        success = current_character.Special(new_y, new_x, game_status);
        assertFalse("Mana is not enough to use special", success);
    }

    @Test
    public void testEngineerSpecial()
    {
        Character current_character = character_list.get(2);
        Character victim = enemy_list.get(0);
        Character victim2 = enemy_list.get(1);

        int character_id = current_character.getID();
        m.setPos(character_id, 6, 6);

        int victim_xpos = m.getPos(character_id)[0] + 2;
        int victim_ypos = m.getPos(character_id)[1];
        int victim_new_health = victim.getHealth() - current_character.getAttack();
        int victim2_health = victim2.getHealth();
        m.setPos(victim.getID(), victim_xpos, victim_ypos);
        m.setPos(victim2.getID(), victim_xpos, victim_ypos + 2);

        boolean success = current_character.Special( victim_xpos, victim_ypos, game_status);
        current_character.setMana(current_character.getMaxMana());
        boolean success2 = current_character.Special( victim_xpos, victim_ypos + 2, game_status);

        if(success & !success2)
        {
            assertEquals(victim_new_health, victim.getHealth());
            assertEquals(victim2_health, victim2.getHealth());
        }
        else
        {
            fail(current_character.getName() + " failed to perform special attack");
        }

        current_character.setMana(0);
        success = current_character.Special(victim_xpos, victim_ypos, game_status);
        assertFalse("Mana is not enough to use special", success);
    }

    @Test
    public void testChemistrySpecial()
    {
        Character current_character = character_list.get(3);
        Character alias = character_list.get(2);
        Character victim = enemy_list.get(0);

        current_character.setMana(current_character.getMaxMana());

        m.setPos(current_character.getID(), 6, 6);
        m.setPos(alias.getID(), 6, 7);

        int xpos = m.getPos(alias.getID())[0] + 1;
        int ypos = m.getPos(alias.getID())[1];
        int new_health = victim.getHealth() - victim.getAttack() - 5;
        m.setPos(victim.getID(), xpos, ypos);

        boolean success = current_character.Special( 7, 6, game_status);
        boolean s = alias.Special( ypos, xpos, game_status);

        if(success && s)
        {
            assertEquals(new_health, victim.getHealth());
        }
        else
        {
            fail(current_character.getName() + " failed to perform special attack");
        }

        current_character.setMana(0);
        success = current_character.Special(7, 6, game_status);
        assertFalse("Mana is not enough to use special", success);
    }

    @Test
    public void testBiomedicalSpecial()
    {
        Character current_character = game_status.getPlayers().get(4);
        Character alias = game_status.getPlayers().get(2);

        m.setPos(current_character.getID(), 6, 6);
        m.setPos(alias.getID(), 6, 7);
        alias.setHealth(1);
        int new_health = alias.getHealth() + 40;

        boolean success = current_character.Special( 6, 7, game_status);

        if(success)
        {
            assertEquals(new_health, alias.getHealth());
        }
        else
        {
            fail(current_character.getName() + " failed to perform special attack");
        }

        current_character.setMana(0);
        success = current_character.Special(6, 7, game_status);
        assertFalse("Mana is not enough to use special", success);
    }

    @Test
    public void testPhilosophyMajorSpecial()
    {
        Character current_character = character_list.get(5);
        Character enemy = enemy_list.get(0);

        enemy.setID(0);
        m.setPos(current_character.getID(), 6, 6);
        m.setPos(enemy.getID(), 7, 6);
        int new_health = current_character.getHealth() + 5 - enemy.getAttack();

        boolean success = current_character.Special( 7, 6, game_status);
        enemy.attack(current_character);

        if(success)
        {
            assertEquals(new_health, current_character.getHealth());
        }
        else
        {
            fail(current_character.getName() + " failed to perform special attack");
        }

        current_character.setMana(0);
        success = current_character.Special(7, 6, game_status);
        assertFalse("Mana is not enough to use special", success);
    }

    @Test
    public void testEnemyMidtermSpecial()
    {
        Character current_character = character_list.get(5);
        Character enemy = new Midterm("Final", 9, 15, 150, 0, 15, 150, 0, 3, 1);

        m.setPos(current_character.getID(), 6, 6);
        m.setPos(enemy.getID(), 7, 7);
        int new_attack = current_character.getAttack() - 1;

        boolean success = enemy.Special( 0, 0, game_status);

        if(success)
        {
            assertEquals(new_attack, current_character.getAttack());
            enemy.setMana(10);
            enemy.Special( 0, 0, game_status);
            assertEquals(new_attack - 1, current_character.getAttack());
        }
        else
        {
            fail(current_character.getName() + " failed to perform special attack");
        }

        current_character.setMana(0);
        success = current_character.Special(0, 0, game_status);
        assertFalse("Mana is not enough to use special", success);
    }

    @Test
    public void testEnemyFinalSpecial()
    {
        Character current_character = game_status.getPlayers().get(5);
        Character enemy = new Final("Final", 9, 15, 150, 0, 15, 150, 0, 3, 1);

        m.setPos(current_character.getID(), 6, 6);
        m.setPos(enemy.getID(), 7, 6);
        int new_health = current_character.getHealth() - 3 * enemy.getAttack();

        boolean success = enemy.Special( 6, 6, game_status);
        if(success)
        {
            assertEquals(new_health, current_character.getHealth());
        }
        else
        {
            fail(current_character.getName() + " failed to perform special attack");
        }

        current_character.setMana(0);
        success = current_character.Special(6, 6, game_status);
        assertFalse("Mana is not enough to use special", success);
    }
}

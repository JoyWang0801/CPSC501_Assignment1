package Junit.src.test.java;

import Chara.*;
import Chara.Character;
import main.*;
import main.mapGenerator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

public class TestCharacter {

    static ArrayList<Character> character_list;
    static ArrayList<Character> enemy_list;
    int character_list_size;

    static map m;


    /*
    private static void putPawnsOnMap()
    {
        int[] test = m.getCharPos();// get default instatiated character position list
        int[] test2 = m.getEnemyPos();// get default enemy posiyions
        for(int i = 0; i < 3; i++) {//for all player characters
            int index = i * 2;//get an index to use for the 1-D list of deafult positions (goes x then y)
            m.setPos(players.get(i).getID(), m.getCharPos()[index], m.getCharPos()[index + 1]);//set the id of that character on the map
        }

        m.setPos(enemies.getID(), m.getEnemyPos()[0], m.getEnemyPos()[1]);

        m.setPos(7, m.getItemPos()[0], m.getItemPos()[1]);//set the id of an item on the map (only one for now - will generate a random number when new items are eventually added
    }
*/
    @BeforeClass
    public static void SetUpClass()
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

    @Before
    public void SetUpTest()
    {
        // making sure all characters are initiated
        character_list_size = character_list.size();
        assertEquals(6, character_list_size);
    }

    @Test
    public void TestSetAndGetID()
    {
        for (int i = 0; i < character_list_size; i++)
        {
            // verify current id
            assertEquals(i, character_list.get(i).getID());

            // set new id
            character_list.get(i).setID(10 * i);

            // verify new id
            assertEquals(i * 10, character_list.get(i).getID());
        }
    }

    @Test
    public void testHealth()
    {
        for(Character currentCharacter : character_list)
        {
            int health = currentCharacter.getHealth();
            String msg = "Testing " + currentCharacter.getName();
            currentCharacter.setHealth(health - 1);

            assertNotEquals(msg, health,  currentCharacter.getHealth());
            assertEquals(msg, health - 1,  currentCharacter.getHealth());
        }
    }

    @Test
    public void testHealthExceedMaxHealth()
    {
        for(Character currentCharacter : character_list)
        {
            int maxHealth = currentCharacter.getMaxHealth();
            String msg = "Testing " + currentCharacter.getName();
            currentCharacter.setHealth(maxHealth + 10);

            assertNotEquals(msg, maxHealth + 10,  currentCharacter.getHealth());
            assertEquals(msg, maxHealth,  currentCharacter.getHealth());
        }
    }

    @Test
    public void testSetNewMaxHealth()
    {
        for(Character currentCharacter : character_list)
        {
            int health = currentCharacter.getHealth();
            int maxHealth = currentCharacter.getMaxHealth();
            int newMaxHealth = maxHealth + 100;
            String msg = "Testing " + currentCharacter.getName();
            currentCharacter.setMaxHealth(newMaxHealth);

            assertEquals(msg, health, currentCharacter.getHealth()); // should not affect current health
            assertNotEquals(msg, maxHealth, currentCharacter.getMaxHealth());
            assertEquals(msg, newMaxHealth, currentCharacter.getMaxHealth());
        }
    }

    @Test
    public void testSetMaxHealthBelowCurrentHealth()
    {
        for(Character currentCharacter : character_list)
        {
            int health = currentCharacter.getHealth();
            int maxHealth = currentCharacter.getMaxHealth();
            int newMaxHealth = health - 1;
            String msg = "Testing " + currentCharacter.getName();
            currentCharacter.setMaxHealth(newMaxHealth);

            assertNotEquals(msg, health, currentCharacter.getHealth());
            assertEquals(msg, newMaxHealth, currentCharacter.getHealth());

            assertNotEquals(msg, maxHealth, currentCharacter.getMaxHealth());
            assertEquals(msg, newMaxHealth, currentCharacter.getMaxHealth());
        }
    }

    @Test
    public void testMana()
    {
        for(Character currentCharacter : character_list)
        {
            String msg = "Testing " + currentCharacter.getName();
            int mana = currentCharacter.getMana();
            currentCharacter.setMana(mana);
            assertEquals(msg, mana,  currentCharacter.getMana());
        }
    }

    @Test
    public void testManaExceedMaxMana()
    {
        for(Character currentCharacter : character_list)
        {
            String msg = "Testing " + currentCharacter.getName();
            int maxMana = currentCharacter.getMaxMana();
            currentCharacter.setMana(maxMana + 10);
            assertNotEquals(msg, maxMana + 10,  currentCharacter.getMana());
            assertEquals(msg, maxMana,  currentCharacter.getMana());
        }
    }

    @Test
    public void testMaxMana()
    {
        for(Character currentCharacter : character_list)
        {
            String msg = "Testing " + currentCharacter.getName();
            int maxMana = currentCharacter.getMaxMana();
            int newMaxMana = maxMana + 100;
            currentCharacter.setMaxMana(newMaxMana);
            assertNotEquals(msg, maxMana,  currentCharacter.getMaxMana());
            assertEquals(msg, newMaxMana,  currentCharacter.getMaxMana());
        }
    }

    @Test
    public void testNegativeMaxMana()
    {
        for(Character currentCharacter : character_list)
        {
            String msg = "Testing " + currentCharacter.getName();
            // test mana number negative
            currentCharacter.setMaxMana(-10);
            assertNotEquals(msg, -10, currentCharacter.getMaxMana());
            assertEquals(msg, 0, currentCharacter.getMaxMana());
        }
    }

    @Test
    public void tesAttack()
    {
        for(Character currentCharacter : character_list)
        {
            String msg = "Testing " + currentCharacter.getName();
            int attack = currentCharacter.getAttack();
            currentCharacter.setAttack(attack - 1);
            assertEquals(msg, attack - 1,  currentCharacter.getAttack());
        }
    }

    @Test
    public void testMaxAttack()
    {
        for(Character currentCharacter : character_list)
        {
            String msg = "Testing " + currentCharacter.getName();
            int maxAttack = currentCharacter.getMaxAttack();
            currentCharacter.setMaxAttack(maxAttack + 10);
            assertEquals(msg, maxAttack + 10,  currentCharacter.getMaxAttack());
        }
    }

    @Test
    public void testMaxAttackBelowCurrentAttack()
    {
        for(Character currentCharacter : character_list )
        {
            String msg = "Testing " + currentCharacter.getName();
            int maxAttack = currentCharacter.getMaxAttack();
            int attack = currentCharacter.getAttack();
            currentCharacter.setMaxAttack(attack - 1);
            assertNotEquals(msg, maxAttack,  currentCharacter.getMaxAttack());
            assertNotEquals(msg, attack,  currentCharacter.getAttack());
            assertEquals(msg, attack - 1,  currentCharacter.getAttack());
            assertEquals(msg, attack - 1,  currentCharacter.getMaxAttack());
        }
    }


    @Test
    public void testAttackAction()
    {
        for(int i = 0; i < character_list.size() - 1; i++)
        {
            Character attackter = character_list.get(i);
            Character victim = character_list.get( (i + 1) % character_list.size());
            String msg = attackter.getName() + " attacks " + victim.getName();

            int attacker_health = attackter.getHealth();
            int attacker_attack = attackter.getAttack();
            int victim_health = victim.getHealth();
            attackter.attack(victim);

            assertEquals(msg, victim_health - attacker_attack, victim.getHealth());
            assertEquals(msg, victim_health - victim.getHealth(), attacker_attack);
        }
    }


    @Test
    public void testBiomedicalSpecial()
    {
        Character current_character = character_list.get(0);
        Character vitim = enemy_list.get(0);
        int character_id = current_character.getID();
        int victim_xpos = m.getPos(character_id)[0];
        int victim_ypos = m.getPos(character_id)[1] + 1;
        m.setPos(vitim.getID(), victim_xpos, victim_ypos);
        int new_health = vitim.getHealth() - current_character.getAttack() * 2;

        boolean success = current_character.Special(m, character_list, enemy_list, victim_xpos, victim_ypos);

        assertEquals(new_health, vitim.getHealth());
    }

    @Test
    public void testZoologySpecial()
    {
        Character current_character = character_list.get(1);
        int character_id = current_character.getID();
        m.setPos(character_id, 6, 6);

        int new_x = m.getPos(character_id)[0];
        int new_y = m.getPos(character_id)[1] + 1;

        boolean success = current_character.Special(m, character_list, enemy_list, new_y, new_x);

        assertEquals(new_x, m.getPos(character_id)[0]);
        assertEquals(new_y, m.getPos(character_id)[1]);
    }
}

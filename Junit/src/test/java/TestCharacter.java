package Junit.src.test.java;

import Chara.*;
import Chara.Character;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestCharacter {

    static ArrayList<Character> character_list;
    int list_size;

    @BeforeClass
    public static void SetUpClass()
    {
        int i = 0;
        character_list  = new ArrayList<>();

        Character testCharacter = new KinesiologyMajor(i++);
        character_list.add(testCharacter);
        testCharacter = new ZoologyMajor(i++);
        character_list.add(testCharacter);
        testCharacter = new EngMajor(i++);
        character_list.add(testCharacter);
        testCharacter = new ChemistryMajor(i++);
        character_list.add(testCharacter);
        testCharacter = new BiomedMajor(i++);
        character_list.add(testCharacter);
        testCharacter = new PhilosophyMajor(i++);
        character_list.add(testCharacter);
    }

    @Before
    public void SetUpTest(){
        list_size = character_list.size();
    }

    @Test
    public void TestSetAndGetID()
    {
        for (int i = 0; i < list_size; i++)
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
}

package Junit.src.test.java;

import Chara.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestCharacter {

    static ArrayList<Chara> character_list;
    int list_size;

    @BeforeClass
    public static void SetUpClass()
    {
        character_list  = new ArrayList<Chara>();
    }

    @Before
    public void SetUpTest(){
        list_size = character_list.size();
    }

    @Test
    public void testKinesiology()
    {
        Chara TestCharacter = new KinesiologyMajor(list_size);
        character_list.add(TestCharacter);

        // Test get name
        assertEquals("KinesiologyMajor",  character_list.get(list_size).getName());
    }

    @Test
    public void testZooMajor()
    {
        Chara TestCharacter = new ZoologyMajor(list_size);
        character_list.add(TestCharacter);

        assertNotEquals("Engineering Major", character_list.get(list_size).getName());
        assertEquals("Zoology Major",  character_list.get(list_size).getName());
    }

    @Test
    public void testEngMajor()
    {
        Chara TestCharacter = new EngMajor(list_size);
        character_list.add(TestCharacter);

        assertNotEquals("Zoology Major",  character_list.get(list_size).getName());
        assertEquals("Engineering Major",  character_list.get(list_size).getName());
    }

    @Test
    public void testChemistryMajor()
    {
        Chara TestCharacter = new ChemistryMajor(list_size);
        character_list.add(TestCharacter);

        assertNotEquals("Engineering Major", character_list.get(list_size).getName());
        assertEquals("Chemistry Major",  character_list.get(list_size).getName());
    }

    @Test
    public void testBiomedMajor()
    {
        Chara TestCharacter = new BiomedMajor(list_size);
        character_list.add(TestCharacter);

        assertNotEquals("Engineering Major", character_list.get(list_size).getName());
        assertEquals("Biomedical Major",  character_list.get(list_size).getName());
    }

    @Test
    public void testPhilosophyMajor()
    {
        Chara TestCharacter = new PhilosophyMajor(list_size);
        character_list.add(TestCharacter);

        assertNotEquals("Engineering Major", character_list.get(list_size).getName());
        assertEquals("Philosophy Major",  character_list.get(list_size).getName());
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
        for(Chara currentCharacter : character_list)
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
        for(Chara currentCharacter : character_list)
        {
            int health = currentCharacter.getHealth();
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
        for(Chara currentCharacter : character_list)
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
        for(Chara currentCharacter : character_list)
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
        for(Chara currentCharacter : character_list)
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
        for(Chara currentCharacter : character_list)
        {
            String msg = "Testing " + currentCharacter.getName();
            int maxMana = currentCharacter.getMaxMana();
            currentCharacter.setMana(maxMana + 10);
            assertNotEquals(msg, maxMana + 10,  currentCharacter.getMana());
            assertEquals(msg, maxMana,  currentCharacter.getMana());
        }
    }

    @Test
    public void testNegativeMana()
    {
        for(Chara currentCharacter : character_list)
        {
            String msg = "Testing " + currentCharacter.getName();
            // test mana number negative
            currentCharacter.setMana(-10);
            assertNotEquals(msg, -10, currentCharacter.getMana());
            assertEquals(msg, 0, currentCharacter.getMana());
        }
    }

    @Test
    public void testMaxMana()
    {
        for(Chara currentCharacter : character_list)
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
        for(Chara currentCharacter : character_list)
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
        for(Chara currentCharacter : character_list)
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
        for(Chara currentCharacter : character_list)
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
        for(Chara currentCharacter : character_list )
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
}

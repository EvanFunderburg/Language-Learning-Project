package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
/**
 * Test file for StoryPage
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public class TestStoryPage {
    private StoryPage storyPage;
    private ArrayList<Phrase> phrases;
    private String imagePath = "image1.png";
    private int pageNumber = 1;

    @Before
    public void setup() {
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(Language.GER, Language.ENG, "hallo", "hello", UUID.randomUUID(), "phonetics"));
        words.add(new Word(Language.GER, Language.ENG, "auf Wiedersehen", "goodbye", UUID.randomUUID(), "phonetics"));
        phrases = new ArrayList<>();
        phrases.add(new Phrase("hallo.", 1, words, UUID.randomUUID()));
        phrases.add(new Phrase("auf Wiedersehen.", 1, words, UUID.randomUUID()));
        storyPage = new StoryPage(imagePath, phrases, pageNumber);
    }

    @Test
    public void testGetQuestion() {
        Question question = storyPage.getQuestion();
        assertNotNull(question);
        assertTrue(phrases.contains(question.getPhrase()));

        Question question2 = storyPage.getQuestion();
        assertNotNull(question2);
        assertTrue(phrases.contains(question2.getPhrase()));

        assertEquals(question.getPhrase(), question2.getPhrase());
    }

    @Test
    public void testToString() {
        String result = storyPage.toString(); // Calling the StoryPage's toString()
        assertNotNull(result);
        System.out.println("StoryPage toString result: " + result); // Debug output

        assertTrue(result.contains(imagePath));

        for (Phrase phrase : phrases) {
            assertTrue(result.contains(phrase.toString()));
        }

        assertTrue(result.contains(storyPage.getQuestion().toString()));
    }


    @Test
    public void testGetPageNum() {
        assertEquals(pageNumber, storyPage.getPageNum());
        
        storyPage = new StoryPage(imagePath, phrases, 2);
        assertEquals(2, storyPage.getPageNum());
        
        storyPage = new StoryPage(imagePath, phrases, 0);
        assertEquals(0, storyPage.getPageNum());

        storyPage = new StoryPage(imagePath, phrases, -5);
        assertEquals(-5, storyPage.getPageNum());
    }

    @Test
    public void testGetImage() {
        assertEquals(imagePath, storyPage.getImage());
        
        String newImagePath = "image2.png";
        storyPage = new StoryPage(newImagePath, phrases, pageNumber);
        assertEquals(newImagePath, storyPage.getImage());

        storyPage = new StoryPage("image3.png", phrases, pageNumber);
        assertEquals("image3.png", storyPage.getImage());

        storyPage = new StoryPage("asdf4354", phrases, pageNumber);
        assertEquals("asdf4354", storyPage.getImage());
    }

    @Test
    public void testGetSentences() {
        assertNotNull(storyPage.getSentences());
        assertEquals(phrases.size(), storyPage.getSentences().size());
        assertEquals(phrases.get(0), storyPage.getSentences().get(0));
        
        assertEquals(phrases.get(1), storyPage.getSentences().get(1));
        assertEquals(phrases.size(), storyPage.getSentences().size());
        
        Phrase newPhrase = new Phrase("Willkommen.", 1, new ArrayList<>(), UUID.randomUUID());
        phrases.add(newPhrase);
        storyPage = new StoryPage(imagePath, phrases, pageNumber);
        assertEquals(3, storyPage.getSentences().size());
        assertEquals(newPhrase, storyPage.getSentences().get(2));
    }
}

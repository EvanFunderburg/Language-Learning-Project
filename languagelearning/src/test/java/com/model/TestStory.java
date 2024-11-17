package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
/**
 * Test file for Story
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public class TestStory {
    private Story testStory;
    private ArrayList<StoryPage> storyPages;
    private int stageLevel = 1;
    private String title = "Sample Story";

    @Before
    public void setup() {
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(Language.GER, Language.ENG, "hallo", "hello", UUID.randomUUID(), "phonetics"));
        ArrayList<Phrase> phrases = new ArrayList<>();
        phrases.add(new Phrase("hallo.", 1, words, UUID.randomUUID()));
        storyPages = new ArrayList<>();
        storyPages.add(new StoryPage("image1.png", phrases, 1));
        storyPages.add(new StoryPage("image2.png", phrases, 2));
        testStory = new Story(storyPages, UUID.randomUUID(), title, stageLevel);
    }

    @Test
    public void testGetQuestion() {
        Question firstPageQuestion = storyPages.get(0).getQuestion();
        assertEquals(firstPageQuestion, testStory.getQuestion());
        
        testStory.turnPage();
        
        Question secondPageQuestion = storyPages.get(1).getQuestion();
        assertEquals(secondPageQuestion, testStory.getQuestion());
    }

    @Test
    public void testTurnPage() {
        testStory.turnPage();
        assertEquals(1, testStory.getPage());

        testStory.turnPage();
        assertEquals(2, testStory.getPage());

        testStory.turnPage();
        assertEquals(2, testStory.getPage());
        testStory.turnPage();
        assertEquals(2, testStory.getPage());
    }

    @Test
    public void testIsStoryComplete() {
        assertFalse(testStory.isStoryComplete());
        testStory.turnPage();

        assertFalse(testStory.isStoryComplete());
        testStory.turnPage();

        assertTrue(testStory.isStoryComplete());
    }

    @Test
    public void testStoryProgress() {
        assertEquals(0, testStory.storyProgress(), 0);

        testStory.turnPage();
        double expectedProgress = 1.0 / storyPages.size();
        assertEquals(expectedProgress, testStory.storyProgress(), 0);
        
        testStory.turnPage();
        assertEquals(1.0, testStory.storyProgress(), 0);

        testStory.turnPage();
        assertEquals(1.0, testStory.storyProgress(), 0);
    }

    @Test
    public void testReadPage() {
        String firstPageContent = storyPages.get(0).toString();
        assertEquals(firstPageContent, testStory.readPage(0));

        String secondPageContent = storyPages.get(1).toString();
        assertEquals(secondPageContent, testStory.readPage(1));
    }
}

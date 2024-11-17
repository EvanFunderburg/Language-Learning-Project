package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
/**
 * Test file for Stage
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public class TestStage {
    private Stage stageWithLesson;
    private Stage stageWithoutLesson;
    private Story testStory;
    private ArrayList<Phrase> phrases;
    private int stageLevel = 1;

    @Before
    public void setup() {
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(Language.GER, Language.ENG, "hallo", "hello", UUID.randomUUID(), "phonetics"));
        words.add(new Word(Language.GER, Language.ENG, "auf Wiedersehen", "goodbye", UUID.randomUUID(), "phonetics"));
        phrases = new ArrayList<>();
        phrases.add(new Phrase("hallo.", 1, words, UUID.randomUUID()));
        phrases.add(new Phrase("auf Wiedersehen.", 1, words, UUID.randomUUID()));
        ArrayList<StoryPage> storyPages = new ArrayList<>();
        storyPages.add(new StoryPage("image1.png", phrases, 1));
        storyPages.add(new StoryPage("image2.png", phrases, 2));
        testStory = new Story(storyPages, UUID.randomUUID(), "Greetings", 1);
        stageWithLesson = new Stage(testStory, stageLevel, true);
        stageWithoutLesson = new Stage(testStory, stageLevel, false);
    }

    @Test
    public void testGetLesson() {
        assertNotNull(stageWithLesson.getLesson());
        assertTrue(stageWithLesson.getLesson() instanceof Lesson);

        assertEquals(null, stageWithoutLesson.getLesson());
    }

    @Test
    public void testGetStory() {
        assertNotNull(stageWithLesson.getStory());
        assertEquals(testStory, stageWithLesson.getStory());

        assertEquals(testStory.getBook().size(), stageWithLesson.getStory().getBook().size());
        assertTrue(stageWithLesson.getStory().getBook().get(0) instanceof StoryPage); 
    }


    @Test
    public void testIsStageComplete() {
        assertFalse(stageWithLesson.isStageComplete());
        while (!testStory.isStoryComplete()) {
            testStory.turnPage();
        }
        assertTrue(stageWithLesson.isStageComplete());

        stageWithoutLesson.isStageComplete();
        assertTrue(stageWithoutLesson.isStageComplete()); 
    }

    @Test
    public void testGetStageLevel() {
        assertEquals(stageLevel, stageWithLesson.getStageLevel());

        assertEquals(stageLevel, stageWithoutLesson.getStageLevel());
        assertTrue(stageWithLesson.getStageLevel() > 0);
    }

    @Test
    public void testToStringWithLesson() {
        String result1 = stageWithLesson.toString();
        assertNotNull(result1);
        assertTrue(result1.contains("Stage Level: " + stageLevel));
        assertTrue(result1.contains(testStory.toString()));
        assertTrue(result1.contains(stageWithLesson.getLesson().toString()));
        assertTrue(result1.length() > 0);
        assertTrue(result1.contains("Story"));


        String result2 = stageWithoutLesson.toString();
        assertNotNull(result2);
        assertTrue(result2.contains("Stage Level: " + stageLevel));
        assertTrue(result2.contains(testStory.toString()));
        assertTrue(result2.contains("Lesson: Null"));
        assertTrue(result2.length() > 0);
        assertTrue(result2.contains("Stage")); 
    }

    @Test
    public void testToStringWithoutLesson() {
        String result = stageWithoutLesson.toString();
        assertNotNull(result);
        assertTrue(result.contains("Stage Level: " + stageLevel));
        assertTrue(result.contains(testStory.toString()));
        assertTrue(result.contains("Lesson: Null"));

        assertTrue(result.length() > 0);
        assertTrue(result.contains("Stage")); 
    }
}

package com.model;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
/**
 * Test file for StageList
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public class TestStageList {
    private StageList stages;
    private ArrayList<Stage> stageList;

    @Before
    public void setup() {
        stages = StageList.getInstance();
        stageList = new ArrayList<>();
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(Language.GER, Language.ENG, "hallo", "hello", UUID.randomUUID(), "phonetics"));
        words.add(new Word(Language.GER, Language.ENG, "auf Wiedersehen", "goodbye", UUID.randomUUID(), "phonetics"));
        ArrayList<Phrase> phrases = new ArrayList<>();
        phrases.add(new Phrase("hallo.", 1, words, UUID.randomUUID()));
        phrases.add(new Phrase("auf Wiedersehen.", 1, words, UUID.randomUUID()));
        ArrayList<StoryPage> storyPages = new ArrayList<>();
        storyPages.add(new StoryPage("image1.png", phrases, 1));
        storyPages.add(new StoryPage("image2.png", phrases, 2));
        Story story = new Story(storyPages, UUID.randomUUID(), "Greeting and Farewell", 1);
        Stage stage = new Stage(story, 1, true);
        stageList.add(stage);
        DataWriter.saveStages(stageList);
        stages.getStages().addAll(stageList);
    }

    @After
    public void tearDown() {
        stages.getStages().clear();
        DataWriter.saveStages(stages.getStages());
    }

    @Test
    public void testAddStage() {
        assertEquals(1, stages.getStages().size());
        
        Stage newStage = new Stage(new Story(new ArrayList<>(), UUID.randomUUID(), "New Story", 2), 2, false);
        boolean added = stages.addStage(newStage);
        assertTrue(added);
        assertEquals(2, stages.getStages().size());

        Stage newStage2 = new Stage(new Story(new ArrayList<>(), UUID.randomUUID(), "New Story2", 2), 2, false);
        added = stages.addStage(newStage2);
        assertTrue(added);
        assertEquals(3, stages.getStages().size());
    }

    @Test
    public void testRemoveStage() {
        Stage newStage = new Stage(new Story(new ArrayList<>(), UUID.randomUUID(), "New Story", 2), 2, false);
        stages.addStage(newStage);
        boolean removed = stages.removeStage(newStage);
        assertTrue(removed);
        assertFalse(stages.getStages().contains(newStage));

        Stage newStage2 = new Stage(new Story(new ArrayList<>(), UUID.randomUUID(), "New Story2", 2), 2, false);
        Stage newStage3 = new Stage(new Story(new ArrayList<>(), UUID.randomUUID(), "New Story3", 2), 2, false);
        stages.addStage(newStage2);
        stages.addStage(newStage3);
        
        boolean removed2 = stages.removeStage(newStage2);
        assertTrue(removed2);
        assertFalse(stages.getStages().contains(newStage2));

        boolean removed3 = stages.removeStage(newStage3);
        assertTrue(removed3);
        assertFalse(stages.getStages().contains(newStage3));
    }

    @Test
    public void testGetStages() {
        assertNotNull(stages);
        assertEquals(1, stages.getStages().size());
        Stage stage = stages.getStage(1);
        assertEquals(1, stage.getStageLevel());

        Stage newStage = new Stage(new Story(new ArrayList<>(), UUID.randomUUID(), "New Story", 2), 2, false);
        stages.addStage(newStage);
        stage = stages.getStage(2);
        assertEquals(2, stage.getStageLevel());
    }
    
    @Test
    public void testGetStory() {
        assertNotNull(stages);
        Stage stage = stages.getStage(1);
        assertNotNull(stage);

        Story retrievedStory = stage.getStory();
        assertNotNull(retrievedStory);
        assertEquals("Greeting and Farewell", retrievedStory.getTitle());
        assertEquals(2, retrievedStory.getTotalPages());

        Stage newStage = new Stage(new Story(new ArrayList<>(), UUID.randomUUID(), "Another Story", 1), 1, true);
        stages.addStage(newStage);
        Story retrievedStory2 = newStage.getStory();
        assertNotNull(retrievedStory2);
        assertEquals("Another Story", retrievedStory2.getTitle());
        assertEquals(0, retrievedStory2.getTotalPages());
    }
}

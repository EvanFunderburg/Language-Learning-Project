package com.model;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test file for Question
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public class TestQuestion {
    private Phrase testPhrase;
    private Question testQuestion;
    private UUID testUUID;
    private UUID testPhraseID;

    @Before
    public void setup() {
        UUID wordID = UUID.randomUUID();
        Word word1 = new Word(Language.GER, Language.ENG, "kartoffel", "potato", wordID, "phonetics");

        ArrayList<Word> words = new ArrayList<>();
        words.add(word1);
        
        testPhraseID = UUID.randomUUID();
        testPhrase = new Phrase("This is a test definition.", 1, words, testPhraseID);
        
        testUUID = UUID.randomUUID();
        testQuestion = new TrueFalse(testPhrase, true, 1, testUUID);
    }

    @After
    public void tearDown() {
        testPhrase = null;
        testQuestion = null;
        testUUID = null;
        testPhraseID = null;
    }

    @Test
    public void testQuestionInitialization() {
        assertNotNull(testQuestion);
        assertEquals(testUUID, testQuestion.getUUID());
        assertEquals(1, testQuestion.getStageLevel());
        assertEquals(testPhrase, testQuestion.getPhrase());
    }

    @Test
    public void testToString() {
        String expectedString = "True or False: The definition of the word kartoffel in english is potato";
        assertEquals(expectedString, testQuestion.toString());
        
        testQuestion = new TrueFalse(testPhrase, false, 1, testUUID);
        String resultString = testQuestion.toString();
        assertTrue(resultString.startsWith("True or False: The definition of the word kartoffel in english is"));
        assertFalse(resultString.contains("potato")); 

        testQuestion = new TrueFalse(testPhrase, true, 2, testUUID);
        expectedString = "True or False: The definition of the word kartoffel in english is potato";
        assertEquals(expectedString, testQuestion.toString());
    }

    @Test
    public void testPromptUserResponse() {
        boolean isCorrect = testQuestion.promptUserResponse("true");
        assertEquals(true, isCorrect);
        
        isCorrect = testQuestion.promptUserResponse("false");
        assertEquals(false, isCorrect);

        isCorrect = testQuestion.promptUserResponse("maybe");
        assertEquals(false, isCorrect);
    }

    @Test
    public void testGetUUID() {
        assertNotNull(testQuestion.getUUID());
        assertEquals(testUUID, testQuestion.getUUID());

        UUID newUUID = UUID.randomUUID();
        testQuestion = new TrueFalse(testPhrase, true, 1, newUUID);
        assertEquals(newUUID, testQuestion.getUUID());
        
        assertNotEquals(testUUID, testQuestion.getUUID()); 
    }

    @Test
public void testGetQuestionWords() {
    assertNotNull(testQuestion.getQuestionWords());
    assertEquals(1, testQuestion.getQuestionWords().size());
    assertEquals("kartoffel", testQuestion.getQuestionWords().get(0).getLearningDefinition());

    ArrayList<Word> emptyWords = new ArrayList<>();
    UUID emptyPhraseID = UUID.randomUUID();
    Phrase emptyPhrase = new Phrase("Empty definition.", 1, emptyWords, emptyPhraseID);
    Question emptyQuestion = new TrueFalse(emptyPhrase, true, 1, UUID.randomUUID());

    assertNotNull(emptyQuestion.getQuestionWords());
    assertEquals(0, emptyQuestion.getQuestionWords().size());

    assertNotNull(testQuestion.getQuestionWords());
    assertEquals(1, testQuestion.getQuestionWords().size());
    assertEquals("kartoffel", testQuestion.getQuestionWords().get(0).getLearningDefinition());
}



    @Test
    public void testGetStageLevel() {
        assertEquals(1, testQuestion.getStageLevel());

        testQuestion = new TrueFalse(testPhrase, true, 3, testUUID);
        assertEquals(3, testQuestion.getStageLevel());
        
        testQuestion = new TrueFalse(testPhrase, true, Integer.MAX_VALUE, testUUID);
        assertEquals(Integer.MAX_VALUE, testQuestion.getStageLevel()); 
    }

    @Test
    public void testGetQuestionType() {
        assertNotNull(testQuestion.getQuestionType());
        String questionType = testQuestion.getQuestionType();
        assertEquals("truefalse", questionType);
    
        assertNotEquals("multiplechoice", questionType);
    
        ArrayList<Word> wordsForMC = new ArrayList<>();
        wordsForMC.add(new Word(Language.GER, Language.ENG, "blume", "flower", UUID.randomUUID(), "phonetics"));
        Phrase mcPhrase = new Phrase("What is the English word for 'blume'?", 1, wordsForMC, UUID.randomUUID());
        

        char correctAnswer = 'A';
        String[] potentialAnswers = {"flower", "tree", "bush"};

        Question mcQuestion = new MultipleChoice(mcPhrase, potentialAnswers, correctAnswer, 1, UUID.randomUUID());
        
        assertNotNull(mcQuestion.getQuestionType());
        assertEquals("multiplechoice", mcQuestion.getQuestionType());
    }
    


    @Test
    public void testGetPhrase() {
        assertNotNull(testQuestion.getPhrase());

        Phrase newPhrase = new Phrase("Another test definition.", 1, new ArrayList<>(), UUID.randomUUID());
        testQuestion = new TrueFalse(newPhrase, false, 1, testUUID);
        assertEquals(newPhrase, testQuestion.getPhrase());
    }
}

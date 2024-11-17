package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestTrueFalse {
    ArrayList<Word> test = WordList.getInstance().getWords();
    Phrase p = QuestionList.getInstance().g;
    Question testQuestion = new TrueFalse(p, false, 1, UUID.randomUUID());
	
	@Before
	public void setup() {
		
	}
	
	@After
	public void tearDown() {
		
	}

	@Test
	public void testPromptUserResponseWrongAns() {
		assertFalse(testQuestion.promptUserResponse("true"));
	}

	@Test
	public void testPromptUserResponseRightAns() {
		assertFalse(testQuestion.promptUserResponse("false"));
	}

	@Test
	public void testToString() {
		assertTrue(testQuestion.toString() instanceof String);
	}

	@Test
	public void testGetTFAnswerAsString() {
		assertTrue(testQuestion.getTFAnswerAsString() instanceof String);
	}
}
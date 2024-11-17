package com.model;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class TestWordList {
    WordList wList = WordList.getInstance();
    ArrayList<Word> wordList = wList.getWords();
	Word testWord = new Word(Language.GER, Language.ENG, "hallo", "hello");
	
	@Before
	public void setup() {
		wordList.clear();
		wordList.add(testWord);
        wordList.add(new Word(Language.GER, Language.ENG, "essen", "eat"));
        wordList.add(new Word(Language.GER, Language.ENG, "guten", "good"));
        DataWriter.saveWords(wordList);
	}
	
	@After
	public void tearDown() {
		WordList.getInstance().getWords().clear();
        DataWriter.saveWords(wordList);
	}
	
	@Test
	public void testSearchWord() {
		assertTrue(wList.search(Language.GER, "essen") != null);
	}

	@Test
	public void testSearchWordWithCaps() {
		assertTrue(wList.search(Language.GER, "Essen") != null);
	}

	@Test
	public void testSearchInvalidWord() {
		assertTrue(wList.search(Language.GER, "eat") != null);
	}

	@Test
	public void testSearchOtherWord() {
		assertTrue(wList.search(Language.ENG, "eat") != null);
	}
	
	@Test
	public void testSearchWordByID() {
		assertTrue(wList.search(UUID.fromString("6d84bf3d-016c-4d6e-b2ed-fec27c9a0ad0")) != null);
	}

	@Test
	public void testSearchWordInvalidID() {
		assertTrue(wList.search(UUID.randomUUID()) == null);
	}

	@Test
	public void testSearchWordOneParam() {
		assertTrue(wList.search(testWord) != null);
	}

	@Test
	public void testSearchWordInvalidOneParam() {
		Word w = new Word(Language.GER, Language.ENG, "tschuss", "goodbye");
		assertFalse(wList.search(w) != null);
	}

	@Test
	public void testStartingWithLetter() {
		boolean methodWorks = false;
		ArrayList<Word> hWords = wList.startingWithLetter('h');
		for(Word w : hWords) {
			if(w.getBaseDefinition().charAt(0) == 'h') {
				methodWorks = true;
			} else {
				methodWorks = false;
				break;
			}
		}
		assertTrue(methodWorks);
	}

	@Test
	public void testStartingWithLetterCaps() {
		boolean methodWorks = false;
		ArrayList<Word> hWords = wList.startingWithLetter('H');
		for(Word w : hWords) {
			if(w.getBaseDefinition().charAt(0) == 'H') {
				methodWorks = true;
			} else {
				methodWorks = false;
				break;
			}
		}
		assertTrue(methodWorks);
	}

    @Test
	public void testAddWord() {
		wList.addWord(testWord);
		assertTrue(wList.search(testWord) == testWord);
	}

	@Test
	public void testAddWordEmptyString() {
		wList.addWord(new Word(Language.GER, Language.ENG, "", ""));
		assertTrue(wList.search(Language.GER, "") != null);
	}

	@Test
	public void testAddNewWord() {
		wList.addWord(testWord.getLearningLanguage(), testWord.getBaseLanguage(), testWord.getLearningDefinition(), testWord.getBaseDefinition());
		assertTrue(wList.search(Language.GER, "hallo") != null);
	}

	@Test
	public void testAddNewWordEmptyString() {
		wList.addWord(Language.GER, Language.ENG, "", "");
		assertTrue(wList.search(Language.GER, "") != null);
	}

	@Test
	public void testRemoveWord() {
		wList.removeWord(testWord);
		assertTrue(wList.search(testWord) == null);
	}

	@Test
	public void testRemoveInvalidWord() {
		wList.removeWord(new Word(Language.GER, Language.ENG, "auto", "car"));
		assertFalse(wList.search(testWord) == null);
	}

	@Test
	public void testGetRandomWord() {
		assertTrue(wList.getRandomWord() instanceof Word);
	}
}
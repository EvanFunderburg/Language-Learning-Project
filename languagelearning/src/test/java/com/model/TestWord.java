package com.model;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class TestWord {
	Word word = new Word(Language.GER, Language.ENG, "fenster", "window");

	@Before
	public void setup() {
		
	}
	
	@After
	public void tearDown() {
        
	}

	@Test
	public void testOneParamEqualsMethod() {
		boolean test = word.equals(word);
		assertTrue(test);
	}

	@Test
	public void testInvalidWordOneParamEqualsMethod() {
		boolean test = word.equals(new Word(Language.GER, Language.ENG, "essen", "window"));
		assertFalse(test);
	}

	@Test
	public void testInvalidLanguageOneParamEqualsMethod() {
		boolean test = word.equals(new Word(Language.FRA, Language.ENG, "fenster", "window"));
		assertFalse(test);
	}

	@Test
	public void testEqualsMethod() {
		boolean test = word.equals("fenster", Language.GER);
		assertTrue(test);
	}

	@Test
	public void testSameWordDifferentStrings() {
		boolean test = word.equals("FenStER", Language.GER);
		assertTrue(test);
	}

	@Test
	public void testIsEqualsWithCorrectLang() {
		boolean test = word.equals("fenster", Language.GER);
		assertTrue(test);
	}

	@Test
	public void testIsEqualsWithIncorrectLang() {
		boolean test = word.equals("fenster", Language.FRA);
		assertFalse(test);
	}

	@Test
	public void testIsEqualsWithCorrectLangWrongWord() {
		boolean test = word.equals("essen", Language.GER);
		assertFalse(test);
	}
}
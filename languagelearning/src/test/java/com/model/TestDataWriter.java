package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;


public class TestDataWriter {
    private static UserList users = UserList.getInstance();
    private static ArrayList<User> userList = users.getUsers();

    private static StageList stages = StageList.getInstance();
    private static ArrayList<Stage> sList = stages.getStages();

    private static PhraseList phrases = PhraseList.getInstance();
    private static ArrayList<Phrase> pList = phrases.getPhrases(1);

    private static WordList words = WordList.getInstance();
    private static ArrayList<Word> wList = words.getWords();

    @Before
	public void setup() {
		UserList.getInstance().getUsers().clear();
		DataWriter.saveUsers(userList);

        StageList.getInstance().getStages().clear();
        DataWriter.saveStages(sList);

        PhraseList.getInstance().getPhrases().clear();
        DataWriter.savePhrases(pList);

        WordList.getInstance().getWords().clear();
        DataWriter.saveWords(wList);
	}
	
	@After
	public void tearDown() {
		userList.clear();
		DataWriter.saveUsers(userList);

        sList.clear();
        DataWriter.saveStages(sList);

        pList.clear();
        DataWriter.savePhrases(pList);

        wList.clear();
        DataWriter.saveWords(wList);
	}

    @Test
	public void testWritingZeroUsers() {
		userList = DataLoader.getUsers();
		assertEquals(0, userList.size());
	}

	@Test
	public void testWritingOneUser() {
        userList.add(new User(UUID.randomUUID(), "Evan", "Funderburg", "efundy21", "EvanRocks6", 0, 0, 0, new ArrayList<LanguageTrack>(), new ArrayList<Phrase>()));
		DataWriter.saveUsers(userList);
		assertEquals("efundy21", DataLoader.getUsers().get(0).getUsername());
	}
	
	@Test
	public void testWritingFourUsers() {
		userList.add(new User(UUID.randomUUID(), "Evan", "Funderburg", "efundy21", "EvanRocks6", 0, 0, 0, new ArrayList<LanguageTrack>(), new ArrayList<Phrase>()));
        userList.add(new User(UUID.randomUUID(), "Sajiv", "Patel", "sajivp", "passowrd", 0, 0, 0, new ArrayList<LanguageTrack>(), new ArrayList<Phrase>()));
		userList.add(new User(UUID.randomUUID(), "Joe", "Giacomo", "JoeyG", "654321", 0, 0, 0, new ArrayList<LanguageTrack>(), new ArrayList<Phrase>()));
		userList.add(new User(UUID.randomUUID(), "Will", "Thacher", "WillyT", "123456", 0, 0, 0, new ArrayList<LanguageTrack>(), new ArrayList<Phrase>()));

        
        DataWriter.saveUsers(userList);
		assertEquals("WillyT", DataLoader.getUsers().get(3).getUsername());
	}
	
	@Test
	public void testWritingEmptyUser() {
        userList.add(new User(UUID.randomUUID(), "", "", "", "", 0, 0, 0, new ArrayList<LanguageTrack>(), new ArrayList<Phrase>()));
        DataWriter.saveUsers(userList);

		assertEquals("", DataLoader.getUsers().get(0).getUsername());
	}
	
	@Test
	public void testWritingNullUser() {
		userList.add(new User(UUID.randomUUID(), null, "", "", "", 0, 0, 0, new ArrayList<LanguageTrack>(), new ArrayList<Phrase>()));
        DataWriter.saveUsers(userList);
		assertEquals(null, DataLoader.getUsers().get(0).getUsername());
	}

    @Test
	public void testWritingZeroStages() {
		sList = DataLoader.getStages();
		assertEquals(0, sList.size());
	}

	@Test
	public void testWritingOneStage() {
        sList.add(new Stage(null, 1, false));
        DataWriter.saveStages(sList);
		assertEquals(1, sList.get(0).getStageLevel());
	}
	
	@Test
	public void testWritingFourStages() {
		sList.add(new Stage(null, 1, false));
        sList.add(new Stage(null, 2, false));
        sList.add(new Stage(null, 3, false));
        sList.add(new Stage(null, 4, false));
        DataWriter.saveStages(sList);
		assertEquals(4, sList.get(3).getStageLevel());
	}


    @Test
	public void testWritingZeroPhrases() {
		pList = DataLoader.getPhrases();
		assertEquals(0, pList.size());
	}

	@Test
	public void testWritingOnePhrase() {
		pList.add(new Phrase("one two three", 1, new ArrayList<Word>(), UUID.randomUUID()));
        DataWriter.savePhrases(pList);
		assertEquals(". ", DataLoader.getPhrases().get(0).getPhraseStr());
	}
	
	@Test
	public void testWritingFourPhrases() {
		pList.add(new Phrase("one two three", 1, new ArrayList<Word>(), UUID.randomUUID()));
		pList.add(new Phrase("two three four", 1, new ArrayList<Word>(), UUID.randomUUID()));
		pList.add(new Phrase("three four five", 1, new ArrayList<Word>(), UUID.randomUUID()));
		pList.add(new Phrase("four five six", 1, new ArrayList<Word>(), UUID.randomUUID()));

        DataWriter.savePhrases(pList);
		assertEquals("four five six", DataLoader.getPhrases().get(3).getDefinition());
	}
	
	@Test
	public void testWritingEmptyPhrase() {
        pList.add(new Phrase("", 1, new ArrayList<Word>(), UUID.randomUUID()));
		
        DataWriter.savePhrases(pList);
		assertEquals("", DataLoader.getPhrases().get(0).getDefinition());
	}
	
	@Test
	public void testWritingNullPhrase() {
		pList.add(new Phrase(null, 1, new ArrayList<Word>(), UUID.randomUUID()));
		
        DataWriter.savePhrases(pList);
		assertEquals(null, DataLoader.getPhrases().get(0).getDefinition());
	}


    @Test
	public void testWritingZeroWords() {
		wList = DataLoader.getWords();
		assertEquals(0, wList.size());
	}

	@Test
	public void testWritingOneWord() {
        wList.add(new Word(Language.GER, Language.ENG, "eins", "one"));
		DataWriter.saveWords(wList);
		assertEquals("eins", DataLoader.getWords().get(0).getLearningDefinition());
	}
	
	@Test
	public void testWritingFourWords() {
		wList.add(new Word(Language.GER, Language.ENG, "eins", "one"));
        wList.add(new Word(Language.GER, Language.ENG, "zwei", "two"));
        wList.add(new Word(Language.GER, Language.ENG, "drei", "three"));
        wList.add(new Word(Language.GER, Language.ENG, "vier", "four"));
		DataWriter.saveWords(wList);
		assertEquals("eins", DataLoader.getWords().get(0).getLearningDefinition());
	}
	
	@Test
	public void testWritingEmptyWord() {
        wList.add(new Word(Language.GER, Language.ENG, "", ""));
		DataWriter.saveWords(wList);
		assertEquals("", DataLoader.getWords().get(0).getLearningDefinition());
	}
	
	@Test
	public void testWritingNullWord() {
		wList.add(new Word(Language.GER, Language.ENG, null, null));
		DataWriter.saveWords(wList);
		assertEquals(null, DataLoader.getWords().get(0).getLearningDefinition());
	}





}
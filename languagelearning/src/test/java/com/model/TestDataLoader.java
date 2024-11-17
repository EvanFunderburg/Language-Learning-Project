package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDataLoader {
    private static UserList users = UserList.getInstance();
    private static ArrayList<User> uList = users.getUsers();

    private static StageList stages = StageList.getInstance();
    private static ArrayList<Stage> sList = stages.getStages();

    private static PhraseList phrases = PhraseList.getInstance();
    private static ArrayList<Phrase> pList = phrases.getPhrases(1);

    private static WordList words = WordList.getInstance();
    private static ArrayList<Word> wList = words.getWords();

    public static void main(String[] args) {
        
        System.out.println(uList.size());
    }

    @Before
    public void setup() {
        uList.clear();
        uList.add(new User("Evan", "Funderburg", "efundy", "456789"));
        uList.add(new User("Joe", "Giacomo", "JOEYG", "BOOM"));
        UserList.saveUsers();

    }

    @After
    public void teardown(){
        // UserList.getInstance().getUsers().clear();
        UserList.saveUsers();
        
    }

    @Test
    public void GetUsersSize(){
        assertEquals(2, DataLoader.getUsers().size());
    }

    @Test
    public void UserListEmpty(){
        UserList.getInstance().getUsers().clear();
        UserList.saveUsers();
        assertEquals(0, DataLoader.getUsers().size());
    }

    @Test
    public void GetUserFirstName(){
        uList = DataLoader.getUsers();
        assertEquals("efundy", uList.get(0).getUsername());
    }

    @Test
    public void GetUserStageLevel(){
        uList = DataLoader.getUsers();
        assertEquals(1, uList.get(0).getCurrentLanguageTrack().getCurrentStageLevel());
    }

    @Test
    public void GetUserStruggleList(){
        uList = DataLoader.getUsers();
        uList.get(0).addToStruggleList(new Phrase("blah", 1, new ArrayList<Word>(), UUID.randomUUID()));
        uList.get(0).addToStruggleList(new Phrase("blah blah", 1, new ArrayList<Word>(), UUID.randomUUID()));
        UserList.saveUsers();
        uList = UserList.getInstance().getUsers();
        assertEquals(2, uList.get(0).getStruggleList().size());
    }

    @Test
    public void GetUserStruggleListSizeZero(){
        uList = DataLoader.getUsers();
        assertEquals(0, uList.get(0).getStruggleList().size());
    }

    @Test
    public void GetStageSize(){
        sList = StageList.getInstance().getStages();
        assertEquals(1, sList.size());
    }


    @Test
    public void GetStageLevel(){
        sList = StageList.getInstance().getStages();
        assertEquals(1, sList.get(0).getStageLevel());
    }

    
    @Test
    public void GetStageLesson(){
        sList = StageList.getInstance().getStages();
        assertEquals(null, sList.get(0).getLesson());
    }

    
    @Test
    public void GetStageStory(){
        sList = StageList.getInstance().getStages();
        assertEquals("Stage 1 story", sList.get(0).getStory().getTitle());
    }

    @Test
    public void GetWordSize() {
        wList = DataLoader.getWords();
        assertEquals(51, wList.size());
    }

    @Test
    public void GetDefinitionSize() {
        wList = DataLoader.getWords();
        assertEquals(5, wList.get(0).getLearningDefinition().length());
    }

    @Test
    public void GetWordLanguage() {
        wList = DataLoader.getWords();
        assertEquals(Language.GER, wList.get(10).getLearningLanguage());
    }

    @Test
    public void GetWordPhonetics() {
        wList = DataLoader.getWords();
        assertEquals("phonetics", wList.get(10).getPhonetics());
    }

    @Test
    public void getPhraseSize() {
        pList = DataLoader.getPhrases();
        assertEquals(31,pList.size());
    }

    @Test
    public void getPhraseLength() {
        pList = DataLoader.getPhrases();
        assertEquals(3,pList.get(0).getPhrase().size());
    }
    @Test
    public void getPhraseDefinition() {
        pList = DataLoader.getPhrases();
        assertEquals("one, two, three",pList.get(0).getDefinition());
    }
    
    @Test
    public void getPhraseLevel() {
        pList = DataLoader.getPhrases();
        assertEquals(1,pList.get(0).getStageLevel());
    }



}
package com.model;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class TestPhraseList{
    private static PhraseList phraseList = PhraseList.getInstance();
    private ArrayList<Phrase> phrases = phraseList.getPhrases();
    private UUID ID1 = new UUID(0, 0);
    private UUID ID2 = new UUID(1, 1);
    private UUID ID3 = new UUID(2, 2);
    @Before
    public void setup() {
        phrases.clear();
        ArrayList<Word> a = new ArrayList<>();
        ArrayList<Word> b = new ArrayList<>();
        ArrayList<Word> c = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.ENG, "hi", "Hi");
        Word hello = new Word(Language.ENG, Language.SPA, "hola", "Hello");
        a.add(hi);
        a.add(hello);
        Word test = new Word(Language.ENG, Language.SPA, "prueba", "test");
        c.add(test);
        phrases.add(new Phrase("Hi hello", 0, a, ID1));
        phrases.add(new Phrase("", 0, b, ID2));
        phrases.add(new Phrase("TEST", 1, c, ID3));
        DataWriter.savePhrases(phrases);
    }
    //didnt test savePhrases since thats literally DataWriter.savePhrases (redunant)
    @After
    public void tearDown() {
        phraseList.getPhrases(0).clear();
        phraseList.getPhrases(1).clear();
        phrases.clear();
        DataWriter.savePhrases(phrases);

    }   
    @Test
    public void testgetInstancePhraseList(){
        PhraseList p = phraseList.getInstance();
        assertEquals(phraseList,p );
    }
    @Test
    public void testgetPhrase(){
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.ENG, "hi", "Hi");
        Word hello = new Word(Language.ENG, Language.SPA, "hola", "Hello");
        a.add(hi);
        a.add(hello);
        Phrase expected = new Phrase("Hi hello", 0, a, ID1);
        assertEquals(expected.toString(), phraseList.getPhrase(ID1).toString());
    }
    @Test
    public void testgetPhrasenonexistant(){
        assertEquals(null, phraseList.getPhrase(null));
    }
    @Test
    public void testgetPhrasenonexistant2(){
        UUID a = new UUID(3, 3);
        assertEquals(null, phraseList.getPhrase(a));
    }
    @Test
    public void testgetPhraseslvl0(){
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.ENG, "hi", "Hi");
        Word hello = new Word(Language.ENG, Language.SPA, "hola", "Hello");
        a.add(hi);
        a.add(hello);
        ArrayList<Word> b = new ArrayList<>();
        Phrase expected1 = new Phrase("Hi hello", 0, a, ID1);
        Phrase expected2 =  new Phrase("", 0, b, ID2);
        ArrayList<Phrase> expected = new ArrayList<>();
        expected.add(expected1);
        expected.add(expected2);
        assertEquals(expected.toString(), phraseList.getPhrases(0).toString());
    }
    @Test
    public void testgetPhraseslvl1(){
        ArrayList<Word> c = new ArrayList<>();
        Word test = new Word(Language.ENG, Language.SPA, "prueba", "test");
        c.add(test);
        ArrayList<Phrase> expected = new ArrayList<>();
        Phrase expected1 = new Phrase("TEST", 1, c, ID3);
        expected.add(expected1);
        assertEquals(expected.toString(), phraseList.getPhrases(1).toString());
    }
    @Test
    public void testgetPhrasesnull(){
        ArrayList<Phrase> expected = new ArrayList<>();
        assertEquals(expected, phraseList.getPhrases(2));
    }
    @Test
    public void testgetPhrasesnegative(){
        ArrayList<Phrase> expected = new ArrayList<>();
        assertEquals(expected, phraseList.getPhrases(-1));
    }
    @Test
    public void testgetPhrasesbig(){
        ArrayList<Phrase> expected = new ArrayList<>();
        assertEquals(expected, phraseList.getPhrases(9999));
    }
    //parsePhrase is deprecated (we dont use it ) also uses system.in input (bad)
}    
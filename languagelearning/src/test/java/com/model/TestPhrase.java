package com.model;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
public class TestPhrase{
    private ArrayList<Word> phrase;
    private UUID phraseID = new UUID(1, 1);
    private String definition;
    private int stageLevel;
    private Phrase basephrase;
    @Before
    public void setup() {
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.SPA, "Oi", "Hi");
        Word hello = new Word(Language.ENG, Language.SPA, "hola", "Hello");
        a.add(hi);
        a.add(hello);
        basephrase = new Phrase("Hi hello", 0, a, new UUID(0, 0));
        stageLevel = 0;
        definition = null;
        phraseID = new UUID(1, 1);
    }
    
    @After
    public void tearDown() {
        basephrase = null;
        stageLevel = 0;
        definition = null;
        phraseID = null;
        phrase = null;
    }
    @Test
    public void TestmakingPhrase(){
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.SPA, "Oi", "Hi");
        Word hello = new Word(Language.ENG, Language.SPA, "hola", "Hello");
        a.add(hi);
        a.add(hello);
        Phrase e = new Phrase("Hi hello", 0, a, new UUID(0, 0));
        assertEquals(basephrase.toString(),e.toString());
    }
    @Test
    public void TestmakingPhrasewithnegstagelvl(){
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.SPA, "Oi", "Hi");
        a.add(hi);
        Phrase e = new Phrase("hi", -1, a, phraseID);
        assertEquals(null,e);
    }
    @Test
    public void TestmakingPhrasewithnullDef(){
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.SPA, "Oi", "Hi");
        a.add(hi);
        Phrase e = new Phrase(null, 0, a, phraseID);
        assertEquals(null,e);
    }
    @Test
    public void TestmakingPhrasewithemptyphrase(){
        ArrayList<Word> a = new ArrayList<>();
        Phrase e = new Phrase("hi", 0, a, phraseID);
        assertEquals(null,e);
    }

    @Test
    public void TestgetPhrase(){
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.SPA, "Oi", "Hi");
        a.add(hi);
        Phrase e = new Phrase("hi", 0, a, phraseID);
        assertEquals(a,e.getPhrase());
    }
    @Test
    public void TestgetPhraseempty(){
        ArrayList<Word> a = new ArrayList<>();
        Phrase e = new Phrase("hi", 0, a, phraseID);
        assertEquals(new ArrayList<>(),e.getPhrase());
    }
    @Test
    public void TestgetPhrasenull(){
        ArrayList<Word> a = null;
        Phrase e = new Phrase("hi", 0, a, phraseID);
        assertEquals(null,e.getPhrase());
    }
    @Test
    public void getphrasestr(){
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.SPA, "Oi", "Hi");
        a.add(hi);
        Phrase e = new Phrase("hi", 0, a, phraseID);
        assertEquals("Oi. ",e.getPhraseStr());
    }
    @Test
    public void getphrasestr2words(){
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.SPA, "swag", "Hi");
        Word hello = new Word(Language.ENG, Language.SPA, "swagg", "Hi");

        a.add(hi);
        Phrase e = new Phrase("hi", 0, a, phraseID);
        assertEquals("swag swagg",e.getPhraseStr());
    }
    @Test
    public void getphrasestrnothing(){
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.SPA, "", "");
        Word hello = new Word(Language.ENG, Language.SPA, "swag", "swag");

        a.add(hi);
        Phrase e = new Phrase("hi", 0, a, phraseID);
        assertEquals("swag",e.getPhraseStr());
    }
    @Test
    public void getindividualDef(){
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.SPA, "yoyo", "yoyo");
        Word hello = new Word(Language.ENG, Language.SPA, "swag", "swag");
        a.add(hi);
        a.add(hello);
        Phrase e = new Phrase("hi", 0, a, phraseID);
        assertEquals("swag",e.getIndividualDefinition(1));
    }
    @Test
    public void getindividualDef2(){
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.SPA, "yoyo", "yoyo");
        Word hello = new Word(Language.ENG, Language.SPA, "swag", "swag");
        a.add(hi);
        a.add(hello);
        Phrase e = new Phrase("hi", 0, a, phraseID);
        assertEquals("yoyo",e.getIndividualDefinition(0));
    }
    @Test
    public void getindividualDefbadindex(){
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.SPA, "yoyo", "Hi");
        Word hello = new Word(Language.ENG, Language.SPA, "swag", "Hi");
        a.add(hi);
        a.add(hello);
        Phrase e = new Phrase("hi", 0, a, phraseID);
        assertEquals("Index out of bounds",e.getIndividualDefinition(-1));
    }
    @Test
    public void getindividualDefbadindex2(){
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.SPA, "yoyo", "Hi");
        Word hello = new Word(Language.ENG, Language.SPA, "swag", "Hi");
        a.add(hi);
        a.add(hello);
        Phrase e = new Phrase("hi", 0, a, phraseID);
        assertEquals("Index out of bounds",e.getIndividualDefinition(2));
    }
    @Test
    public void lengthnormal(){
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.SPA, "yoyo", "Hi");
        Word hello = new Word(Language.ENG, Language.SPA, "swag", "Hi");
        a.add(hi);
        a.add(hello);
        Phrase e = new Phrase("hi", 0, a, phraseID);
        assertEquals(2,e.length());
    }
    @Test
    public void lengthempty(){
        ArrayList<Word> a = new ArrayList<>();
        Phrase e = new Phrase("hi", 0, a, phraseID);
        assertEquals(0,e.length());
    }
    @Test
    public void lengthfullofnulls(){
        ArrayList<Word> a = new ArrayList<>();
        Word hi = new Word(Language.ENG, Language.SPA, "yoyo", "Hi");
        Word hello = new Word(Language.ENG, Language.SPA, "swag", "Hi");
        Word n1 = null;
        Word n2 = null;
        Word n4 = null;
        Word n3 = null;
        Word n5 = null;
        Word n6 = null;
        Word n7 = null;
        Word sigma = new Word(Language.ENG, Language.SPA, "sigma", "Hi");
        a.add(hi);
        a.add(hello);
        a.add(n6);
        a.add(n5);
        a.add(n4);
        a.add(n3);
        a.add(n2);
        a.add(n1);
        a.add(sigma);
        a.add(n7);
        Phrase e = new Phrase("hi", 0, a, phraseID);
        assertEquals(3,e.length());
    }
    
}
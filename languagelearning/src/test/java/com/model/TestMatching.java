package com.model;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
public class TestMatching{
    private  ArrayList<Word> words = new ArrayList<>();
    private UUID iD = new UUID(0, 0);
    private Phrase a = new Phrase("", 0, words, iD);
    @Before
    public void setup(){
        words.clear();
        Word you = new Word(Language.ENG, Language.SPA, "tu", "you");
        Word hello = new Word(Language.ENG, Language.SPA, "hola", "Hello");
        words.add(hello);
        Word test = new Word(Language.ENG, Language.SPA, "prueba", "test");
        Word forr = new Word(Language.ENG, Language.SPA, "para", "for");
        words.add(test);
        words.add(forr);
        words.add(you);

        a = new Phrase("hello test for you", 0, words, iD);
    }
    @After
    public void tear(){
        words.clear();
        iD = null;
        a = null;
    }
    @Test
    public void makeQuestion(){
        Matching question = new Matching(a, 4, 0, UUID.randomUUID());
        assertEquals(false, question==null);
    }
    @Test
    public void makeQuestionimpropernumwords(){
        Matching question = new Matching(a, -1, 0, UUID.randomUUID());
        assertEquals(null, question);
    }
    @Test
    public void makeQuestionnegstagelvl(){
        Matching question = new Matching(a, 4, -1, UUID.randomUUID());
        assertEquals(null, question);
    }
    @Test
    public void makeQuestionimpropernumwords2(){
        Matching question = new Matching(a, 9, 0, UUID.randomUUID());
        assertEquals(null, question);
    }
    @Test
    public void getword(){
        Matching question = new Matching(a, 4, -1, UUID.randomUUID());
        assertEquals(a.getPhrase().get(2).toString(), question.getWord(2).toString());
    }
    @Test
    public void getwordbadindex(){
        Matching question = new Matching(a, 4, -1, UUID.randomUUID());
        assertEquals(null, question.getWord(-1).toString());
    }
    @Test
    public void getwordoutofbounds(){
        Matching question = new Matching(a, 4, -1, UUID.randomUUID());
        assertEquals(null, question.getWord(5).toString());
    }
    @Test
    public void testresponseNull(){
        Matching question = new Matching(a, 4, -1, UUID.randomUUID());
        assertEquals(false, question.promptUserResponse(null));
    }
    @Test
    public void testresponseWrong(){
        Matching question = new Matching(a, 4, -1, UUID.randomUUID());
        assertEquals(false, question.promptUserResponse("aaaaaaaaaahhhhghghghhghghg"));
    }
   
}
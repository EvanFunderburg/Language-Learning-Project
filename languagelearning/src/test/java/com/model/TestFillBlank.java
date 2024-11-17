package com.model;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
public class TestFillBlank{
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
        FillBlank question = new FillBlank(a, 0, iD);
        assertEquals(false, question==null);
    }
    @Test
    public void makeQuestionstagelvl(){
        FillBlank question = new FillBlank(a, -1, iD);
        assertEquals(null, question);
    }
    @Test
    public void makeQuestionNullQ(){
        Phrase b = null;
        FillBlank question = new FillBlank(b, 0, iD);
        assertEquals(false, question==null);
    }
    @Test
    public void promptUserResponse(){
        FillBlank question = new FillBlank(a, 0, iD);
        assertEquals(true, question.promptUserResponse(question.getCorrectWord().getLearningDefinition()));
    }
    @Test
    public void promptUserResponseincorrect(){
        FillBlank question = new FillBlank(a, 0, iD);
        assertEquals(false, question.promptUserResponse("uhhh"));
    }
    @Test
    public void promptUserResponsenull(){
        FillBlank question = new FillBlank(a, 0, iD);
        assertEquals(false, question.promptUserResponse(null));
    }
    @Test
    public void promptUserResponseEmpty(){
        FillBlank question = new FillBlank(a, 0, iD);
        assertEquals(false, question.promptUserResponse(""));
    }
    @Test
    public void promptUserResponsewackystring(){
        words.clear();
        Word empty = new Word(Language.ENG, Language.SPA, "", "");
        words.add(empty);
        a = new Phrase("", 0, words, iD);
        FillBlank question = new FillBlank(a, 0, iD);
        assertEquals(true, question.promptUserResponse(""));
    }
    @Test
    public void promptUserResponsewackystring2(){
        words.clear();
        Word dupe1 = new Word(Language.ENG, Language.SPA, "a", "a");
        Word dupe2 = new Word(Language.ENG, Language.SPA, "a", "a");
        words.add(dupe1);
        words.add(dupe2);
        a = new Phrase("aa", 0, words, iD);
        FillBlank question = new FillBlank(a, 0, iD);
        assertEquals(true, question.promptUserResponse("a"));
    }
    @Test
    public void promptUserResponsewackystring3(){
        words.clear();
        Word dupe1 = new Word(Language.ENG, Language.SPA, null, null);
        Word dupe2 = new Word(Language.ENG, Language.SPA, null, null);
        words.add(dupe1);
        words.add(dupe2);
        a = new Phrase("yeah...", 0, words, iD);
        FillBlank question = new FillBlank(a, 0, iD);
        assertEquals(true, question.promptUserResponse(""));
    }

}
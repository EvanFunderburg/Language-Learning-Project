package com.model;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
public class TestMultChoice{
    private  ArrayList<Word> words = new ArrayList<>();
    private String[] list = new String[4];
    private UUID iD = new UUID(0, 0);
    private Phrase a = new Phrase("", 0, words, iD);
    @Before
    public void setup(){
        words.clear();
        list[0] = null;
        list[1] = null;
        list[2] = null;
        list[3] = null;
        Word you = new Word(Language.ENG, Language.SPA, "tu", "you");
        Word hello = new Word(Language.ENG, Language.SPA, "hola", "Hello");
        words.add(hello);
        Word test = new Word(Language.ENG, Language.SPA, "prueba", "test");
        Word forr = new Word(Language.ENG, Language.SPA, "para", "for");
        words.add(test);
        words.add(forr);
        words.add(you);
        list[0] = hello.getLearningDefinition();
        list[1] = test.getLearningDefinition();
        list[2] = forr.getLearningDefinition();
        list[3] = you.getLearningDefinition();
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
        MultipleChoice question = new MultipleChoice(a, list, 'a', 0, iD);
        assertEquals(false, question==null);
    }
    @Test
    public void makeQuestionemptypotentialanswer(){
        String[] emptyarray = new String[0];
        MultipleChoice question = new MultipleChoice(a, emptyarray, 'a', 0, iD);
        assertEquals(null, question);
    }
    @Test
    public void makeQuestionnullpotentialanswer(){
        String[] emptyarray = null;
        MultipleChoice question = new MultipleChoice(a, emptyarray, 'a', 0, iD);
        assertEquals(null, question);
    }
    @Test
    public void makeQuestionbadchar(){
        MultipleChoice question = new MultipleChoice(a, list, '!', 0, iD);
        assertEquals(null, question);
    }
    @Test
    public void makeQuestionnegstagelvl(){
        MultipleChoice question = new MultipleChoice(a, list, 'a', -1, iD);
        assertEquals(null, question);
    }
    @Test
    public void promptUserResponsecorrect(){
        MultipleChoice question = new MultipleChoice(a, list, 'a', 0, iD);
        assertEquals(true, question.promptUserResponse("a"));
    }
    @Test
    public void promptUserResponsenull(){
        MultipleChoice question = new MultipleChoice(a, list, 'a', 0, iD);
        assertEquals(false, question.promptUserResponse(null));
    }
    @Test
    public void promptUserResponselongstring(){
        MultipleChoice question = new MultipleChoice(a, list, 'a', 0, iD);
        assertEquals(false, question.promptUserResponse("ambition for cash"));
    }
    @Test
    public void promptUserResponseincorrect(){
        MultipleChoice question = new MultipleChoice(a, list, 'a', 0, iD);
        assertEquals(false, question.promptUserResponse("onomonopia"));
    }
}
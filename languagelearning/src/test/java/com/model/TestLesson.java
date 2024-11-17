package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestLesson {
    private ArrayList<Question> questions;
    private int score;
    private int stageLevel;
    private ArrayList<Question> answeredQuestions;
    private Question currentQuestion;
    private String content;

    private Lesson lesson1;
    private Lesson lesson2;



    @Before
    public void setup() {
        questions = new ArrayList<Question>();
        answeredQuestions = new ArrayList<Question>();
        currentQuestion = new Matching(new Phrase("phrase1", 1, new ArrayList<Word>(), UUID.randomUUID()), 0, 0,UUID.randomUUID());
       
        questions.add(currentQuestion);
        questions.add(new TrueFalse(new Phrase("phrase2", 1, new ArrayList<Word>(), UUID.randomUUID()), false, 0, UUID.randomUUID()));
        questions.add(new MultipleChoice(new Phrase("phrase3", 1, new ArrayList<Word>(), UUID.randomUUID()), new String[4], 'b', 0, UUID.randomUUID()));
        // questions.add(new FillBlank(new Phrase("phrase4", 1, new ArrayList<Word>(), UUID.randomUUID()), 1, UUID.randomUUID()));
        questions.add(new Matching(new Phrase("phrase5", 1, new ArrayList<Word>(), UUID.randomUUID()), 0, 0,UUID.randomUUID()));
        questions.add(new TrueFalse(new Phrase("phrase6", 1, new ArrayList<Word>(), UUID.randomUUID()), false, 0, UUID.randomUUID()));
        questions.add(new MultipleChoice(new Phrase("phrase7", 1, new ArrayList<Word>(), UUID.randomUUID()), new String[4], 'b', 0, UUID.randomUUID()));
        //questions.add(new FillBlank(new Phrase("phrase8", 1, new ArrayList<Word>(), UUID.randomUUID()), 1, UUID.randomUUID()));
        
        answeredQuestions.add(new Matching(new Phrase("phrase9", 1, new ArrayList<Word>(), UUID.randomUUID()), 0, 0,UUID.randomUUID()));
        answeredQuestions.add(new TrueFalse(new Phrase("phrase10", 1, new ArrayList<Word>(), UUID.randomUUID()), false, 0, UUID.randomUUID()));
        
    
        score = 2;
        stageLevel = 1;
        content = "content";

        lesson1 = new Lesson(questions, content, stageLevel);
        lesson2 = new Lesson(content, questions, answeredQuestions, score, currentQuestion, stageLevel);
       
    }

    @After
    public void teardown(){
        
    }
    @Test
    public void TestConstructorQuestions() {
        assertEquals(6, lesson1.getQuestions().size());
    }
    @Test
    public void TestConstructorContent() {
        assertEquals(content, lesson1.getContent());
    }
    @Test
    public void TestConstructorScore() {
        assertEquals(0, lesson1.getScore());
    }
    @Test
    public void TestConstructorScore2() {
        assertEquals(score, lesson2.getScore());
    }
    @Test
    public void TestConstructorStageLevel() {
        assertEquals(stageLevel, lesson1.getStageLevel());
    }
    @Test
    public void TestConstructorAnsweredQuestions() {
        assertEquals(0, lesson1.getAnsweredQuestions().size());
    }
    @Test
    public void TestConstructorAnsweredQuestions2() {
        assertEquals(2, lesson2.getAnsweredQuestions().size());
    }
    @Test
    public void TestConstructorCurrentQuestion() {
        assertNotNull(lesson1.getCurrentQuestion());
    }

    @Test
    public void TestConstructorCurrentQuestion2() {
        assertNotNull(lesson2.getCurrentQuestion());
    }

    @Test
    public void TestResetQuestionsAnswered() {
        lesson2.resetQuestions();
        assertEquals(new ArrayList<Question>(), lesson2.getAnsweredQuestions());
    }

    @Test
    public void TestResetQuestionsScore() {
        lesson2.resetQuestions();
        assertEquals(0, lesson2.getScore());
    }

    @Test
    public void TwoQuestionsCorrect() {
        lesson2.questionCorrect();
        lesson2.questionCorrect();
        assertEquals(score+2, lesson2.getScore());
    }
    @Test
    public void TwoQuestionsIncorrect() {
        lesson2.questionIncorrect();
        lesson2.questionIncorrect();
        assertEquals(score-2, lesson2.getScore());
    }
    @Test
    public void OneRightOneWrong() {
        lesson2.questionCorrect();
        lesson2.questionIncorrect();
        assertEquals(score, lesson2.getScore());
    }

    @Test
    public void FinishedLesson() {
        lesson1.questionCorrect();
        lesson1.questionCorrect();
        lesson1.questionCorrect();
        lesson1.questionCorrect();
        lesson1.questionCorrect();
        lesson1.questionCorrect();
        assertTrue(lesson1.isLessonComplete());
    }

    @Test
    public void UnFinishedLesson() {
        assertFalse(lesson1.isLessonComplete());
    }

    @Test
    public void TestStudySheet() {
        String txt = "LESSON ROADMAP\n\nBase Definition \t \t | Learning definition\n";
        txt += "Base language: " + this.questions.get(0).getPhrase().getPhrase().get(0).getBaseDefinition() + " Learning Language: " + this.questions.get(0).getPhrase().getPhrase().get(0).getBaseDefinition()+"\n";
        for (int i = 0; i < this.questions.size(); i++) {
            txt += "\n";
            txt += (i + 1) + ". " + this.questions.get(i).getPhrase().getDefinition() + " : " + this.questions.get(i).getPhrase().toString() + "\n";
        } 
        
        assertEquals(txt, lesson1.getStudySheet());
    }


    

    


}
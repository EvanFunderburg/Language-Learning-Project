package com.model;
import java.util.ArrayList;

/**
 * Class representing a lesson in the language learning program
 * @author Will Thacher
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author Evan Funderburg
 */
public class Lesson {
    private String content;
    private ArrayList<Question> questions;
    private ArrayList<Question> answeredQuestions;
    private int score;
    private Question currentQuestion;
    private int stageLevel;
    
    /**
     * Creates a new Lesson object with the given questions, content, and stage level
     * @param questions The list of questions in the lesson
     * @param content The content of the lesson
     * @param stageLevel The level of difficulty or stage of the lesson
     */
    public Lesson(ArrayList<Question> questions, String content, int stageLevel) {
        this.questions = questions;
        this.content = content;
        this.stageLevel = stageLevel;
        this.answeredQuestions = new ArrayList<>();
        this.score = 0;
        setCurrentQuestion(0);
    }

    /**
     * Creates a new Lesson object with the given parameters, including the state of the lesson
     * @param content The content of the lesson
     * @param questions The list of questions in the lesson
     * @param answeredQuestions The list of questions that have been answered
     * @param score The current score of the user in the lesson
     * @param currentQuestion The current question being asked
     * @param stageLevel The level of difficulty or stage of the lesson
     */
    public Lesson(String content, ArrayList<Question> questions, ArrayList<Question> answeredQuestions, 
                    int score, Question currentQuestion, int stageLevel) {
        this.content = content;
        this.questions = questions;
        this.answeredQuestions = answeredQuestions;
        this.score = score;
        this.currentQuestion = currentQuestion;
        this.stageLevel = stageLevel;
    }
    
    /**
     * Resets the state of the lesson, clearing the list of answered questions, setting the score to 0, and resetting the stage level.
     * It also sets the current question to the first question in the list if the list is not empty.
     */
    public void resetQuestions() {
        answeredQuestions.clear();
        score = 0;
        // stageLevel = 0;
        if (!questions.isEmpty()) {
            currentQuestion = questions.get(0);
        }
    }
    
    /**
     * Marks the current question as correct, increments the score, and sets the next question
     */
    public void questionCorrect() {
        answeredQuestions.add(currentQuestion);
        score++;
        setNextQuestion();
    }
    
    /**
     * Marks the current question as incorrect, decrements the score, and sets the next question
     */
    public void questionIncorrect() {
        answeredQuestions.add(currentQuestion);
        score--;
        setNextQuestion();
    }
    
    /**
     * Gets the current question being asked
     * @return The current question
     */
    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int n) {
        if(n < this.questions.size())
            this.currentQuestion = this.questions.get(n);
    }
    
    /**
     * Sets the next question based on the number of questions that have been answered.
     * If all questions have been answered, sets the current question to null.
     */
    private void setNextQuestion() {
        if (answeredQuestions.size() < questions.size()) {
            currentQuestion = questions.get(answeredQuestions.size());
        } else {
            currentQuestion = null;
        }
    }
    
    /**
     * Checks if the lesson is complete by comparing the number of answered questions to the total number of questions
     * @return True if the lesson is complete, false otherwise
     */
    public boolean isLessonComplete() {
        return answeredQuestions.size() == questions.size();
    }
    
    /**
     * Gets the current score of the user in the lesson
     * @return The current score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Gets the content of the lesson
     * @return The lesson content
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the list of questions in the lesson
     * @return The list of questions
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    /**
     * Gets the list of answered questions
     * @return The list of answered questions
     */
    public ArrayList<Question> getAnsweredQuestions() {
        return answeredQuestions;
    }

    /**
     * Gets the stage level of the lesson
     * @return The stage level
     */
    public int getStageLevel() {
        return stageLevel;
    }

    /**
     * Returns a string representation of the lesson including stage level, content, score, and questions
     * @return The formatted lesson string
     */
    public String toString(){
        String s1 = "Lesson for stage "+ stageLevel + "\nContent: "+ content + "\nScore: " + score + "\nQuestions: \n";
        for(Question q : questions) {
            s1 += q.toString() + "\n";
        }
        s1 += "\nAnswered Question: \n";
        for(Question q : answeredQuestions) {
            s1 += q.toString() + "\n";
        }
        return s1;
    }

    /**
     * Creates a study sheet showing the lesson content and questions in both languages
     * @return The formatted study sheet string
     */
    public String getStudySheet(){
        String txt = "LESSON ROADMAP\n\nBase Definition \t \t | Learning definition\n";
        txt += "Base language: " + this.questions.get(0).getPhrase().getPhrase().get(0).getBaseDefinition() + " Learning Language: " + this.questions.get(0).getPhrase().getPhrase().get(0).getBaseDefinition()+"\n";
        for (int i = 0; i < this.questions.size(); i++) {
            txt += "\n";
            txt += (i + 1) + ". " + this.questions.get(i).getPhrase().getDefinition() + " : " + this.questions.get(i).getPhrase().toString() + "\n";
        } 
        return txt;
    }

    
}

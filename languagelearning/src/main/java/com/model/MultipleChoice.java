package com.model;
import java.util.UUID;

/**
 * Class representing a multiple choice question in the language learning program
 * @author Will Thacher
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author Evan Funderburg
 */
public class MultipleChoice extends Question {
    /** Array of potential answer choices */
    private String[] potentialAnswers;
    /** The correct answer choice */
    private char correctAnswer;

    /**
     * Constructs a new MultipleChoice question object.
     * @param question The list of words that make up the question.
     * @param potentialAnswers The list of potential answers to the question.
     * @param correctAnswer The correct answer to the question, represented by a character (a, b, c, etc.).
     * @param stageLevel The level of difficulty or stage of the question.
     * @param questionID A unique identifier for the question.
     */
    public MultipleChoice(Phrase question, String[] potentialAnswers, char correctAnswer, int stageLevel, UUID questionID) {
        super(question, stageLevel, questionID);
        this.potentialAnswers = potentialAnswers;
        this.correctAnswer = correctAnswer;
        this.questionType = "multiple_choice";
    }

    /**
     * Converts the question into a string representation, including the potential answers.
     * @return A string representation of the question, including the potential answers.
     */
    @Override
    public String toString() {
        String questionString = "What is the defintion of *" + question.getPhrase().get(0).getLearningDefinition() + "*\n";
        char option = 'a';
        for (String answer : potentialAnswers) {
            questionString += option + ": " + answer + "\n";
            option++;
        }
        return questionString;
    }

    /**
     * Prompts the user for a response to the question and checks if it is correct.
     * @param userAnswer The user's response to the question.
     * @return True if the answer is correct, false otherwise.
     */
    @Override
    public boolean promptUserResponse(String userAnswer) {
        char userResponse = userAnswer.toLowerCase().charAt(0);
        return this.correctAnswer == userResponse;
    }

    /**
     * Gets the array of potential answer choices
     * @return Array of potential answers
     */
    public String[] getMCPotAnswers(){
        return this.potentialAnswers;
    }

    /**
     * Gets the correct answer choice
     * @return Character representing correct answer
     */
    public char getMCCorrectAnswer() {
        return this.correctAnswer;
    }
    /**
     * Gets only the question as a string. None of part of the answer choices
     */
    public String getQuestionAsString() {
        return "What is the defintion of *" + question.getPhrase().get(0).getLearningDefinition() + "*\n";
    }
}

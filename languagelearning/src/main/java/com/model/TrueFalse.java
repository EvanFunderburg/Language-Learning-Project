package com.model;
import java.util.UUID;

/**
 * Represents a true or false question in the language learning program.
 * This class extends the Question class and provides specific functionality for true or false questions.
 * 
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public class TrueFalse extends Question {
    private boolean correctAnswer; // Indicates the correct answer to the question, true or false.
    private String potAnswer; // The potential answer to the question, either the definition of a word or a random word.

    /**
     * Constructs a new TrueFalse question object.
     * 
     * @param question The list of words that make up the question.
     * @param correctAnswer The correct answer to the question (true or false).
     * @param stageLevel The level of difficulty or stage of the question.
     * @param questionID A unique identifier for the question.
     */
    public TrueFalse(Phrase question, boolean correctAnswer, int stageLevel, UUID questionID) {
        super(question, stageLevel, questionID);
        this.correctAnswer = correctAnswer;
        this.questionType = "truefalse"; // Sets the question type to "truefalse".
        WordList w = WordList.getInstance(); // Gets the instance of WordList.
        // Randomly selects a word from the question phrase if the correct answer is true, otherwise selects a random word.
        if(this.correctAnswer)
            potAnswer = this.question.getPhrase().get((int)(Math.random()*question.getPhrase().size())).getBaseDefinition();
        else
            potAnswer = w.getRandomWord().getBaseDefinition(); // write a get random word method in WordList
    }

    /**
     * Converts the question into a string representation, including the type of question.
     * 
     * @return A string representation of the question, including "(True/False)".
     */
    @Override
    public String toString() {
        return "True or False: The definition of the word " + question.getPhrase().get(0).getLearningDefinition() + " in english is "+ potAnswer;
    }

    /**
     * Prompts the user for a response to the question and checks if it is correct.
     * 
     * @param answer The user's response to the question.
     * @return True if the answer is correct, false otherwise.
     */
    public boolean promptUserResponse(String answer) {
        boolean userResponse = Boolean.parseBoolean(answer.toLowerCase()); // Converts the user's answer to lowercase and parses it as a boolean.
        return this.correctAnswer == userResponse; // Checks if the user's response matches the correct answer.
    }

    /**
     * Returns the correct answer as a string, either "true" or "false".
     * 
     * @return The correct answer as a string.
     */
    public String getTFAnswerAsString() {
        return correctAnswer ? "true" : "false";
    }

    public String getQuestionAsString() {
        return toString();
    }
}

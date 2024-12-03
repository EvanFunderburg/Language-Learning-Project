package com.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

/**
 * Class representing a matching question where users match words between base and learning languages
 * @author Will Thacher
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author Evan Funderburg
 */
public class Matching extends Question{
    /** Number of words to match in the question */
    private int numWords;
    /** List of words in the base language */
    private ArrayList<String> baseSet;
    /** List of words in the learning language */
    private ArrayList<String> learningSet;
    /** List of correct pairs represented as two-digit numbers */
    private ArrayList<String> correctPairs;

    /**
     * Constructs a new Matching question object.
     * @param question The list of words that make up the question.
     * @param numWords The number of words to match in the question.
     * @param stageLevel The level of difficulty or stage of the question.
     * @param questionID A unique identifier for the question.
     */
    public Matching(Phrase question, int numWords, int stageLevel, UUID questionID) {
        super(question, stageLevel, questionID);
        this.numWords = numWords;
        this.baseSet = new ArrayList<String>();
        this.learningSet = new ArrayList<String>();
        this.correctPairs = new ArrayList<String>();
        this.questionType = "matching";
        // for (Word word : question.getPhrase()) {
        //     baseSet.add(word.getBaseDefinition());
        //     learningSet.add(word.getLearningDefinition());
        // }
        for(int i = 0; i < numWords; i++){
            Word word = question.getPhrase().get(i);
            baseSet.add(word.getBaseDefinition());
            learningSet.add(word.getLearningDefinition());
        }
        Collections.shuffle(learningSet);
        for (int i = 0; i < baseSet.size(); i++) {
            int baseIndex = i + 1;
            int learningIndex = learningSet.indexOf(question.getPhrase().get(i).getLearningDefinition()) + 1;
            correctPairs.add(baseIndex + "" + learningIndex);
        }
    }
    /**
     * Returns a Word object at the specified index from the question list.
     * @param i The index of the Word object to return.
     * @return The Word object at the specified index.
     */
    public Word getWord(int i) {
        return question.getPhrase().get(i);
    }
    /**
     * Converts the question into a string representation, including the number of words to match.
     * @return A string representation of the question, including the number of words to match.
     */
    @Override
    public String toString() {
        String questionString = "Base Set : Learning Set\n";
        for (int i = 0; i < baseSet.size(); i++) {
            questionString += (i + 1) + ". " + baseSet.get(i) + " : " + learningSet.get(i) + "\n";
        }
        questionString += "\nNumber of words to match: " + numWords + "\nAnswer the match with numbers\n(left being the word # of base, right being of learning)\n(i.e. \"12,23,31\")";
        return questionString;
    }
    /**
     * Compares the user's response to the correct pairs after sorting both.
     * @param answer The user's answer as comma-separated two-digit numbers.
     * @return true if the sorted user answer matches the correct pairs, false otherwise.
     */
    public boolean promptUserResponse(String answer) {
        ArrayList<String> userAnswerPairs = new ArrayList<>(Arrays.asList(answer.split(",")));
        Collections.sort(userAnswerPairs);
        Collections.sort(correctPairs);
        return userAnswerPairs.equals(correctPairs);
    }
    /**
     * Returns the number of words to match in the question.
     * @return The number of words to match.
     */
    public int getMatchingNumWords() {
        return numWords;
    }

    public String getQuestionAsString() {
        return "\nNumber of words to match: " + numWords;
    }
    public ArrayList<String> getBaseSet() {
        return baseSet;
    }
    public ArrayList<String> getLearningSet() {
        return learningSet;
    }

    public String getAnwersChoicesAsString() {
        String s1 = "";
        for(String s : baseSet) {
            s1+=s+"#";
        }
        s1 += "!";
        for(String s : learningSet) {
            s1+=s+"#";
        }
        return s1;
    }

    
}

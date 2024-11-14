package com.model;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Class representing a phrase in the language learning program
 * @author Will Thacher
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author Evan Funderburg
 */
public class Phrase {
    /** List of words that make up the phrase */
    private ArrayList<Word> phrase;
    /** Unique identifier for the phrase */
    private UUID phraseID;
    /** Definition of the phrase */
    private String definition;
    /** Stage level of difficulty for the phrase */
    private int stageLevel;

    /**
     * Constructs a new Phrase object.
     * @param definition The definition of the phrase.
     * @param stageLevel The level of difficulty or stage of the phrase.
     * @param phrase The list of words that make up the phrase.
     * @param phraseID A unique identifier for the phrase.
     */
    public Phrase(String definition, int stageLevel, ArrayList<Word> phrase, UUID phraseID) {
        this.definition = definition;
        this.phrase = phrase;
        this.phraseID = phraseID;
        this.stageLevel = stageLevel;
    }

    /**
     * Returns the list of words that make up the phrase.
     * @return The list of words in the phrase.
     */
    public ArrayList<Word> getPhrase() {
        return this.phrase;
    }

    /**
     * Converts the phrase into a string representation.
     * @return A string representation of the phrase.
     */
    public String getPhraseStr() {
        String phraseStr = "";
        for (Word word : this.phrase) {
            phraseStr += word.getLearningDefinition() + " ";
        }
        return phraseStr.trim()+". ";
    }

    /**
     * Returns a string representation of the phrase by calling getPhraseStr()
     * @return A string representation of the phrase
     */
    public String toString() {
        return getPhraseStr();
    }

    /**
     * Returns the base definition of a word at a specific index in the phrase.
     * @param i The index of the word in the phrase.
     * @return The base definition of the word at the specified index, or "Index out of bounds" if the index is invalid.
     */
    public String getIndividualDefinition(int i) {
        if (i >= 0 && i < this.phrase.size()) {
            return this.phrase.get(i).getBaseDefinition();
        } else {
            return "Index out of bounds";
        }
    }

    /**
     * Returns the definition of the phrase.
     * @return The definition of the phrase.
     */
    public String getDefinition() {
        return this.definition;
    }

    /**
     * Gets the unique identifier for this phrase
     * @return The UUID of the phrase
     */
    public UUID getUUID(){
        return this.phraseID;
    }

    /**
     * Gets the stage level of difficulty for this phrase
     * @return The stage level number
     */
    public int getStageLevel(){
        return this.stageLevel;
    }

    /**
     * Gets the number of non-null words in the phrase
     * @return The count of valid words
     */
    public int length(){
        int a = 0;
        for(Word b: phrase){
            if(b != null)
                a++; 
        }
        return a;
    }
}

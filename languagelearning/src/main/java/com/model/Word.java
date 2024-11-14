package com.model;
import java.util.UUID;

/**
 * Represents a word with translations and associated information.
 * 
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public class Word {
    private String phonetics;
    private Language learningLanguage;
    private Language baseLanguage;
    private String learningWord;
    private String baseWord;
    private UUID wordID;

    /**
     * Constructs a new Word object.
     *
     * @param learning      The language being learned
     * @param base          The base language
     * @param learningDef   The word in the learning language
     * @param baseDef       The word in the base language
     * @param wordID        Unique identifier for the word
     * @param phonetics     Phonetic representation of the word
     * 
     * @author Will Thacher
     */
    public Word(Language learning, Language base, String learningDef, String baseDef, UUID wordID, String phonetics) {
        this.baseLanguage = base;
        this.learningLanguage = learning;
        this.learningWord = learningDef;
        this.baseWord = baseDef;
        this.phonetics = phonetics;
        this.wordID = wordID;
    }
    /**
     * Constructs a new Word object - generates a random UUID instead of being passed in
     * 
     * @param learning      The langauge being learned
     * @param base          The base language
     * @param learningDef   The word in the learning langauge
     * @param baseDef       The word in the base language
     * 
     * @author Evan Funderburg
     */
    public Word(Language learning, Language base, String learningDef, String baseDef) {
        this.baseLanguage = base;
        this.learningLanguage = learning;
        this.learningWord = learningDef;
        this.baseWord = baseDef;
        this.phonetics = "phonetics";
        this.wordID = UUID.randomUUID();
    }

    /**
     * Gets the language being learned.
     *
     * @return The learning language
     * 
     * @author Will Thacher
     */
    public Language getLearningLanguage() {
        return this.learningLanguage;
    }

    /**
     * Gets the base language.
     *
     * @return The base language
     * 
     * @author Will Thacher
     */
    public Language getBaseLanguage() {
        return this.baseLanguage;
    }

    /**
     * Gets the word in the learning language.
     *
     * @return The word in the learning language
     * 
     * @author Will Thacher
     */
    public String getLearningDefinition() {
        return this.learningWord;
    }

    /**
     * Gets the word in the base language.
     *
     * @return The word in the base language
     * 
     * @author Will Thacher
     */
    public String getBaseDefinition() {
        return this.baseWord;
    }

    /**
     * Gets the unique identifier of the word.
     *
     * @return The UUID of the word
     * 
     * @author Will Thacher
     */
    public UUID getUuid() {
        return this.wordID;
    }

    /**
     * Checks if this word is equal to another word.
     *
     * @param word The word to compare with
     * @return true if the words are equal, false otherwise
     * 
     * @author Will Thacher
     */
    public boolean equals(Word word) {
        return this.getLearningDefinition().equals(word.getLearningDefinition());
    }

    /**
     * Checks if this word is equal to a given word in a specific language.
     *
     * @param word The word to compare with
     * @param lang The language of the word
     * @return true if the words are equal in the specified language, false otherwise
     * 
     * @author Will Thacher
     */
    public boolean equals(String word, Language lang) {
        if (lang.label.equals(this.baseLanguage.label)) {
            return this.baseWord.equals(word);
        }
        else if (lang.label.equals(this.learningLanguage.label)) {
            return this.learningWord.equals(word);
        }
        else {
            return false;
        }
    }

    /**
     * Gets the phonetic representation of the word.
     *
     * @return The phonetic representation
     * 
     * @author Will Thacher
     */
    public String getPhonetics() {
        return this.phonetics;
    }
    /**
     * Returns a string representation of the word.
     *
     * @return A string containing the learning and base language definitions
     * 
     * @author Will Thacher
     */
    public String toString() {
        return "Learning language: " + this.getLearningLanguage().label + " the word in said language: " + this.getLearningDefinition() + "\n" +
               "Base language: " + this.getBaseLanguage().label + " the word in said language: " + this.getBaseDefinition();
    }
}

package com.model;
/**
 * Class representing a fill-in-the-blank question type
 * @author Will Thacher
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis  
 * @author Evan Funderburg
 */
import java.util.Random;
import java.util.UUID;

/**
 * Class representing a fill-in-the-blank question where users must fill in a missing word
 * from a phrase in the learning language
 */
public class FillBlank extends Question{
    /** The correct word that should be filled in the blank */
    private Word correctword;

    /**
     * Creates a new fill-in-the-blank question
     * @param question The phrase containing the word to be blanked out
     * @param stageLevel The difficulty level of this question
     * @param questionID Unique identifier for this question
     */
    public FillBlank(Phrase question, int stageLevel, UUID questionID) {
        super(question, stageLevel, questionID);
        this.stageLevel = stageLevel;
        this.question = question;
        this.questionID = questionID;
        Random rand = new Random();
        this.correctword = question.getPhrase().get(rand.nextInt(question.getPhrase().size())); //word in german
        this.questionType = "fillblank";
    }

    @Override
    /**
     * Checks if the user's answer matches the correct word for this fill-in-the-blank question
     * @param answer The user's submitted answer
     * @return true if the answer matches the correct word (case-insensitive), false otherwise
     */
    public boolean promptUserResponse(String answer){
        return answer.toLowerCase().equals(this.correctword.getLearningDefinition().toLowerCase());
    }
    @Override
    /**
     * Returns a string representation of this fill-in-the-blank question
     * Creates a phrase with the correct word replaced by blanks and adds a prompt
     * asking for the word that matches the base language definition
     * @return The formatted question string with blanks and prompt
     */
    public String toString(){
        String phrase ="";
        for(Word a: this.question.getPhrase()){
            if(!a.equals(this.correctword)){
                phrase = phrase + a.getLearningDefinition() + " ";
            }
            else{
                phrase = phrase + "___________ ";
            }
        }
        return phrase + "\n" + "What word fits in the blank that means: " + correctword.getBaseDefinition() + "?";
    }

    public String getQuestionAsString() {
        String phrase ="";
        for(Word a: this.question.getPhrase()){
            if(!a.equals(this.correctword)){
                phrase = phrase + a.getLearningDefinition() + " ";
            }
            else{
                phrase = phrase + "___________ ";
            }
        }
        return phrase;
    }
    
}

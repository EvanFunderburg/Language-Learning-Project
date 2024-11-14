package com.model;
import java.util.Random;

/**
 * Class representing a user's progress track in learning a specific language
 * @author Will Thacher
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author Evan Funderburg
 */
public class LanguageTrack {
    /** The language being learned in this track */
    private Language learningLanguage;
    /** The current stage the user is on */
    private Stage currentStage;
    /** The level of the current stage */
    private int stageLevel;

    /**
     * Creates a new language track starting at stage 1
     * @param language The language to learn
     */
    public LanguageTrack(Language language) {
        this.learningLanguage = language;
        StageList stageList = StageList.getInstance();
        this.currentStage = stageList.getStage(1);
        this.stageLevel = 1;
    }

    /**
     * Creates a new language track starting at the specified stage
     * @param language The language to learn
     * @param stage The stage to start at
     */
    public LanguageTrack(Language language, Stage stage) {
        this.learningLanguage = language;
        this.currentStage = stage;
        this.stageLevel = stage.getStageLevel();
    }

    /**
     * Gets a random question appropriate for the current stage level
     * @return A question at or below the current stage level
     */
    // public Question dailyQuestion() {
    //     // Random random = new Random();
    //     // int rand = random.nextInt(100);
    //     // QuestionList qList = QuestionList.getInstance();
    //     // Question question = qList.getQuestion(rand);
    //     // this.currentStage.getStageLevel();
    //     // while(this.stageLevel<=question.getStageLevel()){
    //     //     question = qList.getQuestion(rand);
    //     // }
    //     // return question; 
    // }

    /**
     * Gets the current stage
     * @return The current Stage object
     */
    public Stage getCurrentStage() {
        return currentStage;
    }

    /**
     * Advances to the next stage level
     */
    public void advanceStage() {
        //if(max lvl of stages we have > stageLevel)
        this.stageLevel++;
        StageList stageList = StageList.getInstance();
        this.currentStage = stageList.getStage(stageLevel);
    }

    /**
     * Gets the current stage level
     * @return The current stage level number
     */
    public int getCurrentStageLevel(){
        return this.stageLevel;
    }

    /**
     * Gets the language being learned
     * @return The Language enum value
     */
    public Language getLearningLanguage() {
        return this.learningLanguage;
    }

    @Override
    public String toString(){
        return "Language: " + learningLanguage.name() + " stagelvl: " + this.stageLevel + " (j incase there is a disconnect im getting currrentstage lvl) " + currentStage.getStageLevel() + "\n";
    }
    
}

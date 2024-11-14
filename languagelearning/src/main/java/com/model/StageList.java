package com.model;
import java.util.ArrayList;

/**
 * Represents a list of stages in the language learning program.
 * This class follows the Singleton pattern.
 *
 * @author Will Thacher
 */
public class StageList {
    private static StageList stageList;
    private ArrayList<Stage> stages;
    
    /**
     * Private constructor to initialize the stages list.
     *
     * @author Will Thacher
     */
    private StageList(){
        this.stages = DataLoader.getStages();
    }

    /**
     * Gets the instance of StageList.
     *
     * @return A new instance of StageList
     * @author Will Thacher
     */
    public static StageList getInstance(){
        StageList a = new StageList(); 
        return a;
    }

    /**
     * Retrieves a stage by its level.
     *
     * @param stageLevel The level of the stage to retrieve
     * @return The Stage object at the specified level
     * @author Will Thacher
     */
    public Stage getStage(int stageLevel){
        return this.stages.get(stageLevel-1);
    } 

    /**
     * Adds a new stage to the list if it doesn't already exist.
     *
     * @param stage The Stage object to add
     * @return true if the stage was added, false if it already exists
     * @author Will Thacher
     */
    public boolean addStage(Stage stage){
        for(int i =0;i<stages.size();i++){
            if(stages.get(i).equals(stage))
                return false;
        }
        stages.add(stage);
        return true;  
    }

    /**
     * Removes a stage from the list.
     *
     * @param stage The Stage object to remove
     * @return true if the stage was removed, false if it was not found
     * @author Will Thacher
     */
    public boolean removeStage(Stage stage){
        for(int i =0;i<stages.size();i++){
            if(stages.get(i).equals(stage)){
                stages.remove(i);
                return true;
            }  
        }
        return false;
    }

    public Story getStory(int stageLevel) {
        return getStage(stageLevel).getStory();
    }

    public ArrayList<Stage> getStages() {
        return this.stages;
    }

    public static void main(String[] args) {
        StageList sL = getInstance();

        for(Stage s : sL.stages) {
            System.out.println(s.getStory().toString(0));
            System.out.println(s.getStory().toString(1));
            System.out.println(s.getStory().toString(2));
        }

        DataWriter.saveStages(sL.stages);
    }
}

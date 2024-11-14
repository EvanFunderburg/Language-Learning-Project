package com.model;
import java.util.ArrayList;
import java.util.UUID;
/**
 * Represents a stage in a language learning program.
 * A stage consists of a lesson and a story, and has a specific level.
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public class Stage {
    private Lesson lesson;
    private Story story;
    private int stageLevel;
    private ArrayList<Phrase> phrases;

    /**
     * Constructs a new Stage with the given lesson, story, and stage level.
     *
     * @param lesson The lesson associated with this stage
     * @param story The story associated with this stage
     * @param stageLevel The level of this stage
     */
    public Stage(Story story, int stageLevel, boolean createLesson) {
        // create 10 questions with words from the phrases
        // create a lesson with those questions
        // 
        if(createLesson) {
            PhraseList pL = PhraseList.getInstance();
            phrases = pL.getPhrases(stageLevel);  // get the phrases that have the same stagelevel
            ArrayList<Question> list = new ArrayList<Question>();
            int i = 0;
            
            ArrayList<String> questionTypes = new ArrayList<>();  // Track unique question types

            while (phrases.size() > 0 && i < 10) {
                Phrase p = phrases.remove((int)(Math.random() * phrases.size()));  // gets a random phrase
                Question question = Question.createQuestion(p, stageLevel);

                // For the first four questions, ensure unique types
                if (i < 4) {
                    String questionType = question.getQuestionType();

                    if (questionTypes.contains(questionType)) {
                        phrases.add(p);  // Put the phrase back if type is duplicate
                        continue;
                    }

                    questionTypes.add(questionType);  // Add new type to the list
                }

                list.add(question);  // Add the question to the list
                i++;
            }

            this.phrases = pL.getPhrases(stageLevel);
            this.lesson = new Lesson(list, "(Lesson content)", stageLevel);
        }
        else {
            this.lesson = null;
        }
        this.story = story;
        this.stageLevel = stageLevel;
    }

    /**
     * Gets the lesson associated with this stage.
     *
     * @return The lesson of this stage
     * 
     * @author Will Thacher
     */
    public Lesson getLesson(){
        return this.lesson;
    }

    /**
     * Gets the story associated with this stage.
     *
     * @return The story of this stage
     * 
     */
    public Story getStory() {
        return this.story;
    }

    /**
     * Checks if the stage is complete.
     * A stage is considered complete if either the story or the lesson is complete.
     *
     * @return true if the stage is complete, false otherwise
     * 
     */
    public boolean isStageComplete() {
        if(this.story.isStoryComplete())
            //DataWriter.removeStory or update progress (depends on how we implement)
            return true;
        else if(this.lesson.isLessonComplete())
            //DataWriter.removeLesson or update progress (depends on how we implement
            return true;
        return false;
    }

    /**
     * Gets the stage level
     * @return stage level
     * 
     * @author Evan Funderburg
     */
    public int getStageLevel() {
        return stageLevel;
    }

    public String toString(){
        //if(lesson == null)
            //System.out.println("Lesson is null");
        String s1 = "Stage Level: "+ stageLevel + "\nStory: "+story.toString() + "\nLesson: "+ (lesson == null ? "Null" : lesson.toString());
        return s1;
    }

    public void setPhrases(ArrayList<Phrase> phrases) {
        this.phrases = phrases;
    }

    /*
    public static void main(String[] args) {
        System.out.println("Before creating stage");
        Stage stage = new Stage(null, 1, true);

        PhraseList pL = PhraseList.getInstance();

        ArrayList<Phrase> phr = pL.getPhrases(1);

        //for(Phrase p : phr)
          //  System.out.println(p.getPhraseStr());

        System.out.println(stage.getLesson().toString());
    }
    */
}

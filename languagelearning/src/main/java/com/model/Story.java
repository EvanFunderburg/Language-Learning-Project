package com.model;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a story in the language learning program.
 * A story consists of a collection of story pages, and has a specific level and title.
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public class Story {
    private ArrayList<StoryPage> book;
    private int page;
    private int totalpages;
    private int stageLevel;
    private UUID storyID;
    private String title;
    /**
     * Constructs a new Story with the given story pages, unique identifier, title, and stage level.
     *
     * @param book The collection of story pages that make up the story
     * @param storyID The unique identifier of the story
     * @param title The title of the story
     * @param stageLevel The level of the story
     */
    public Story(ArrayList<StoryPage> book,UUID storyID,String title, int stageLevel){
        this.book = book;
        this.page = 0;
        this.stageLevel = stageLevel;
        this.title = title;
        this.storyID = storyID;
        this.totalpages = book.size();
        System.out.println("story created");
    }
    /**
     * Gets the question on the current page of the story.
     *
     * @return The question on the current page of the story
     */
    public Question getQuestion(){
        return this.book.get(page).getQuestion();
    }
    /**
     * Turns the page of the story.
     * If the story is complete, it prints a message and removes the stage.
     */
    public void turnPage(){
        if(!this.isStoryComplete())
            this.page++;
        else{
            System.out.println("story finished");
            //Stage.removeStage(this); or something along the lines 
        }
    }
    //This method maybe should be in Stage? 
    public void changeMode(){
        System.out.println("mode changed to Lesson");
    }
    /**
     * Checks if the story is complete.
     *
     * @return true if the story is complete, false otherwise
     */
    public boolean isStoryComplete(){
        int nextPage = this.page +1;
        return nextPage>totalpages;
    }
    /**
     * Gets the title of the story.
     *
     * @return The title of the story
     */
    public String getTitle(){
        return this.title;
    }
    /**
     * Gets the collection of story pages that make up the story.
     *
     * @return The collection of story pages that make up the story
     */
    public ArrayList<StoryPage> getBook() {
        return this.book;
    }
    /**
     * Gets the current page of the story.
     *
     * @return The current page of the story
     */
    public int getPage() {
        return this.page;
    }
    /**
     * Gets the stage level of the story.
     *
     * @return The stage level of the story
     */
    public int getStageLevel() {
        return this.stageLevel;
    }
    /**
     * Gets the unique identifier of the story.
     *
     * @return The unique identifier of the story
     */
    public UUID getStoryID() {
        return storyID;
    }

    /**
     * Gets the total number of pages in the story.
     *
     * @return The total number of pages in the story
     */
    public int getTotalPages(){
        return this.totalpages;
    }
    /**
     * Calculates the progress of the story.
     *
     * @return The progress of the story as a percentage
     */
    public double storyProgress(){
        return this.page / this.totalpages;
    }
    /**
     * Converts the story into a string representation.
     * This method calls the toString method of the current page of the story.
     *
     * @param pagenum The page number to be converted into a string
     * @return A string representation of the page
     */
    public String toString(int pagenum) {
        return book.get(pagenum).toString();
    }
    /**
     * Reads the content of a specific page of the story.
     *
     * @param page The page number to be read
     * @return The content of the page
     */
    public String readPage(int page){
        return book.get(page).toString();
    }
    /**
     * Gets the question on a specific page of the story.
     *
     * @param pagenum The page number to get the question from
     * @return The question on the specified page
     */
    public Question getPageQuestion(int pagenum){
        return book.get(pagenum).getQuestion();
    }
    public StoryPage getStoryPage(){
        return book.get(page);
    }
    /**
     * Main method for testing the Story class.
     * This method creates a new story with a single page, a single phrase, and a single word.
     */

    /*
    public static void main(String[] args) {
        ArrayList<Phrase> a = new ArrayList<>();
        Word b  = new Word(Language.ENG, Language.GER, "pee", "piss");
        UUID f = new UUID(1,2);
        ArrayList<Word> e =  new ArrayList<>();
        e.add(b);
        Phrase c = new Phrase("a", 1,e, f);
        a.add(c);
        StoryPage g = new StoryPage("filepath", a, 0);
        ArrayList<StoryPage> h = new ArrayList<>();
        h.add(g);
        UUID d = new UUID(1,3);
        Story i = new Story(h, d, "title", 1);
        i.readPage(0);

    }
    */
}

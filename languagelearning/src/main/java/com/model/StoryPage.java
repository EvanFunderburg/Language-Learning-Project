package com.model;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a page in a story.
 * A page consists of an image, a collection of sentences, and a question.
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public class StoryPage {
    private int pageNum;
    private String image; //filepath to image
    private ArrayList<Phrase> sentences;
    private Question question;
    
    /**
     * Constructs a new StoryPage with the given image, sentences, and page number.
     *
     * @param image The filepath to the image of the page
     * @param sentence The collection of sentences that make up the page
     * @param pageNum The page number of the page
     */
    public StoryPage(String image, ArrayList<Phrase> sentence, int pageNum){
        this.image = image;
        this.pageNum = pageNum;
        this.sentences = sentence;
        Random rand = new Random();
        int phrasePicker = rand.nextInt(sentences.size());
        this.question = Question.createQuestion(sentences.get(phrasePicker), pageNum);
    }
    public String getsentenceinstr(){
        String str = "";
        for(int i =0; i<sentences.size();i++){
            str = str + this.sentences.get(i).toString() + " ";
        }
        return str;
    }
    /**
     * Gets the question on the page.
     *
     * @return The question on the page
     */
    public Question getQuestion(){
        return this.question;
    }
    
    /**
     * Converts the page into a string representation.
     *
     * @return A string representation of the page
     */
    @Override
    public String toString(){
        String text="";
        for(int i = 0;i<sentences.size();i++){
            text = text + "\n" + sentences.get(i).toString();
        }
        return "\n" + this.image + "\n"+text +"\n ------------------------------------" +this.question.toString();
    }
    
    /**
     * Gets the page number of the page.
     *
     * @return The page number of the page
     */
    public int getPageNum() {
        return pageNum;
    }
    
    /**
     * Gets the image of the page.
     *
     * @return The image of the page
     */
    public String getImage() {
        return image;
    }
    
    /**
     * Gets the sentences of the page.
     *
     * @return The sentences of the page
     */
    public ArrayList<Phrase> getSentences() {
        return sentences;
    }
}
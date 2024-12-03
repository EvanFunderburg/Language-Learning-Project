package com.model;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a question in the language learning program.
 * This abstract class serves as a base for different types of questions, such as true/false, multiple choice, and fill-in-the-blank.
 * It provides common attributes and methods for all question types, including a phrase that makes up the question, a unique identifier, a stage level indicating difficulty, and a method to prompt the user for a response.
 * 
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public abstract class Question {
    protected Phrase question;
    protected UUID questionID;
    protected int stageLevel;
    protected String questionType;

    /**
     * Constructs a new Question object.
     * This constructor initializes a question with a given phrase, stage level, and unique identifier.
     * 
     * @param question The list of words that make up the question.
     * @param stageLevel The level of difficulty or stage of the question.
     * @param questionID A unique identifier for the question.
     */
    public Question(Phrase question, int stageLevel, UUID questionID) {
        this.question = question;
        this.stageLevel = stageLevel;
        this.questionID = questionID;
    }

    public static Question createQuestion(Phrase phrase, int stageLevel) {
        int length = phrase.getPhrase().size();
        Question question;
        ArrayList<Word> seq = new ArrayList<Word>();
        WordList w = WordList.getInstance();
        if(length==0){
            return null;
        }
        if(length == 1) {
            if(Math.random() < .5) {
                // create a true false question
                boolean correctAnswer = false;
                if(Math.random() < .5)
                    correctAnswer = true;
                question = new TrueFalse(phrase, correctAnswer, stageLevel, UUID.randomUUID());
            }
            else{
                // create a multiple choice question
                String[] list = new String[4];
                char correctAnswer;
                seq = phrase.getPhrase();
                double rando = Math.random();
                for(int i = 0; i < list.length; i++) {
                    String s1 = "";
                    boolean cont = true;
                    while(cont) {
                        cont = false;
                        s1 = w.getRandomWord().getBaseDefinition();
                        for(int j = 0; j < i; j++) {
                            if(s1.equals(list[j]) || s1.equals(seq.get(0).getBaseDefinition()))
                            cont = true;
                        }
                        break;
                    }
                    list[i] = s1;
                }
                if(rando < .25) {
                    correctAnswer = 'a';
                    list[0] = seq.get(0).getBaseDefinition();
                }
                else if(rando < .5) {
                    correctAnswer = 'b';
                    list[1] = seq.get(0).getBaseDefinition();
                }
                else if(rando < .75) {
                    correctAnswer = 'c';
                    list[2] = seq.get(0).getBaseDefinition();
                }
                else {
                    correctAnswer = 'd';
                    list[3] = seq.get(0).getBaseDefinition();
                }
                question = new MultipleChoice(phrase, list, correctAnswer, stageLevel, UUID.randomUUID());
            }
        }
        else if(length>1){
            double rand = Math.random();
            if (rand<.5) {//make fill in the blank
                question = new FillBlank(phrase, stageLevel, UUID.randomUUID());
            } 
            else {
                // create a matching question
                int numWords = 0;
                while(numWords < 5 && numWords < phrase.getPhrase().size()){
                    seq.add(phrase.getPhrase().get(numWords));
                    numWords++;
                }
                question = new Matching(phrase, numWords, stageLevel, UUID.randomUUID());
            }  
        }
        else{
            question = null;
        }
        return question;
    }

    /**
     * Converts the question into a string representation.
     * This method iterates through the words in the question phrase and concatenates them into a single string, separated by spaces.
     * 
     * @return A string representation of the question.
     */
    @Override
    public String toString() {
        String questionString = "";
        for (Word word : question.getPhrase()) {
            questionString += word.getLearningDefinition() + " ";
        }
        return questionString;
    }

    public abstract String getQuestionAsString();

    public abstract String getAnwersChoicesAsString();


    /**
     * Prompts the user for a response to the question and checks if it is correct.
     * This method is abstract and must be implemented by subclasses to handle the specific question type.
     * 
     * @param answer The user's response to the question.
     * @return True if the answer is correct, false otherwise.
     */
    
    public abstract boolean promptUserResponse(String answer);

    /**
     * Returns the unique identifier of the question.
     * 
     * @return The UUID of the question.
     */
    public UUID getUUID() {
        return this.questionID;
    }

    /**
     * Returns the list of words that make up the question.
     * 
     * @return An ArrayList of Word objects representing the question.
     */
    public ArrayList<Word> getQuestionWords() {
        return this.question.getPhrase();
    }

    /**
     * Returns the stage level of the question.
     * 
     * @return The stage level of the question.
     */
    public int getStageLevel() {
        return stageLevel;
    }

    /**
     * Returns the number of words for a matching question type.
     * This method is overridden by subclasses to provide the correct number of words.
     * 
     * @return The number of words for a matching question type.
     */
    public int getMatchingNumWords() {
        return -1;
    }

    /**
     * Returns the answer to a true/false question as a string.
     * This method is overridden by subclasses to provide the correct answer.
     * 
     * @return The answer to a true/false question as a string.
     */
    public String getTFAnswerAsString() {
        return "null";
    }

    /**
     * Returns the potential answers for a multiple choice question.
     * This method is overridden by subclasses to provide the correct potential answers.
     * 
     * @return An array of strings representing the potential answers.
     */
    public String[] getMCPotAnswers(){
        return null;
    }

    /**
     * Returns the correct answer for a multiple choice question.
     * This method is overridden by subclasses to provide the correct answer.
     * 
     * @return The correct answer for a multiple choice question.
     */
    public char getMCCorrectAnswer() {
        return 'X';
    }

    /**
     * Returns the type of the question.
     * 
     * @return A string representing the type of the question.
     */
    public String getQuestionType() {
        return this.questionType;
    }

    /**
     * Returns the phrase of the question.
     * 
     * @return The Phrase object representing the question.
     */
    public Phrase getPhrase(){
        return this.question;
    }
}

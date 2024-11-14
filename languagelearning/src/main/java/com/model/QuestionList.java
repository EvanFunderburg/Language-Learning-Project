package com.model;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Scanner;
/**
 * QuestionList Class
 * This class represents a list of questions in the language learning program.
 * It provides methods to manage and access the questions in the list.
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public class QuestionList {
    private ArrayList<Question> questions;
    private static QuestionList questionList;

    /**
     * Constructor for QuestionList
     * This constructor initializes the list of questions by loading them from the data source.
     */
    //private QuestionList() {
    //    questions = DataLoader.getQuestions();
    //}

    /**
     * Method to get instance of QuestionList
     * This method returns the singleton instance of the QuestionList.
     * If the instance does not exist, it creates a new one.
     * @return QuestionList instance
     */
    public static QuestionList getInstance() {
        if (questionList == null) {
            questionList = new QuestionList();
        }
        return questionList;
    }
    // gets a question when given its UUID
    public Question getQuestion(UUID quesID) {
        Question question = null;

        for(Question q : questions) {
            if(q.getUUID().equals(quesID))
                question = q;
        }
        return question;
    }
    /**
     * get question by order in qlist, useful for randomly getting questions
     * overloads other method
     * This method returns a question from the list based on its order.
     * It is useful for randomly getting questions.
     * @param quesID The order of the question in the list.
     * @return The question at the specified order.
     */
    public Question getQuestion(int quesID) {
        return questions.get(quesID);
    }

    /**
     * Checks if a question with the given UUID exists in the list.
     * This method iterates through the list of questions to find a match based on the UUID.
     * 
     * @param quesID The UUID of the question to search for.
     * @return True if the question exists, false otherwise.
     */
    public boolean containsQuestion(UUID quesID){
        for(Question q : questions) 
            if (q.getUUID().equals(quesID))
                return true;
        return false;
    }

    /**
     * Adds a question to the list if it does not already exist.
     * This method first checks if a question with the same UUID already exists in the list.
     * If not, it adds the question to the list.
     * 
     * @param question The question to be added.
     * @return True if the question was successfully added, false if it already exists.
     */
    public boolean addQuestion(Question question) {
        if(containsQuestion(question.getUUID()))
            return false;
        questions.add(question);
        return true;
    }

    /**
     * Parses a string into a list of words.
     * This method splits the input string into individual words, adds each word to the WordList,
     * and then searches for the word in the WordList to add it to the list of words.
     * 
     * @param question The string to be parsed into words.
     * @return An ArrayList of Word objects representing the parsed words.
     */
    private static ArrayList<Word> parseQuestion(String question) {
        ArrayList<Word> words = new ArrayList<Word>();
        String[] array = question.split(" ");
        WordList w = WordList.getInstance();
        for(String s : array) {
            w.addWord(Language.GER, Language.ENG, s, "placeholder");
            words.add(w.search(Language.GER, s));
        }
            
        return words;
    }

    /**
     * Saves the list of questions to a data storage.
     * This method uses the DataWriter class to save the list of questions.
     */
    public static void saveQuestions() {
        QuestionList q = getInstance();
        DataWriter.saveQuestions(q.questions);
    }

    /**
     * Returns the list of questions.
     * 
     * @return An ArrayList of Question objects representing the list of questions.
     */
    public ArrayList<Question> getQuestions() {
        return this.questions;
    }
    /*
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        QuestionList questionList = QuestionList.getInstance();
        StageList stageList = StageList.getInstance();
        int menu;
        String ques;
        do {
            System.out.print("What type of Question would you like to create\n\t1. Matching"
                                +"\n\t2. Multiple choice"
                                +"\n\t3. True False"
                                +"\n\t9. Quit"
                                +"\nEnter your choice here: ");
            menu = reader.nextInt();
            reader.nextLine();
            switch(menu){
                case 1:
                    System.out.println("Enter the text for your matching question:");
                    ques = reader.nextLine();
                    System.out.println("Enter the stage level:");
                    int level = reader.nextInt();
                    ArrayList<Word> words = parseQuestion(ques);
                    Question qe = new Matching(words, words.size(), level, UUID.randomUUID());
                    // System.out.println(qe);
                    questionList.questions.add(qe);
                break;

                case 2:
                    System.out.println("Enter the text for your multiple choice question:");
                    ques = reader.nextLine();
                    System.out.println("Enter choice A:");
                    String a = reader.nextLine();
                    System.out.println("Enter choice B:");
                    String b = reader.nextLine();
                    System.out.println("Enter choice C:");
                    String c = reader.nextLine();
                    System.out.println("Enter choice D:");
                    String d = reader.nextLine();
                    System.out.println("Enter the correct answer:");
                    String ans = reader.nextLine();
                    System.out.println("Enter the stage level:");
                    level = reader.nextInt();
                    reader.nextLine();
                    String[] list = {a,b,c,d};
                    

                    qe = new MultipleChoice(parseQuestion(ques), list, ans.charAt(0), level, UUID.randomUUID());
                    // System.out.println(qe);
                    questionList.questions.add(qe);
                break;

                case 3:
                    System.out.println("Enter the text for your true/false question:");
                    ques = reader.nextLine();
                    System.out.println("Enter the stage level:");
                    level = reader.nextInt();
                    qe = new TrueFalse(parseQuestion(ques), true, level, UUID.randomUUID());
                    // System.out.println(qe);
                    questionList.questions.add(qe);
                break;

                case 9:
                    System.out.println("Thanks for adding some questions");
                break;

                default:
                System.out.println("Error Please choice a valid selection");
            }

        } while(menu != 9);

        //for(Question q : questionList.questions) {
        //    System.out.println("\n" + q + "\n");
        //}

        System.out.println(stageList.getStage(1));
        WordList.saveWords();
        QuestionList.saveQuestions();
        
    } */
}

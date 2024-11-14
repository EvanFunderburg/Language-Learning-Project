package com.model;
/**
 * Class that handles writing data to JSON files
 * @author Will Thacher
 * @author Ritvik Neerattil  
 * @author Aidan Van Voorhis
 * @author Evan Funderburg
 */
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

/**
 * Class that handles writing user data to JSON files
 * Extends DataConstants to access file paths and data field names
 */
public class DataWriter extends DataConstants {
    /**
     * Saves a list of User objects to a JSON file
     * @param list ArrayList of User objects to be saved
     */
    public static void saveUsers(ArrayList<User> list){
        JSONArray jsonUsers = new JSONArray();

        //creating all the json objects
		for(int i=0; i< list.size(); i++) {
			jsonUsers.add(getUserJSON(list.get(i)));
		}
		
		//Write JSON file
        try {
            String path = getFileWritingPath(USER_FILE_NAME, USER_FILE_NAME_JUNIT);
			FileWriter file = new FileWriter(path);

            file.write(jsonUsers.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // needs testing
    /**
     * Saves a list of Question objects to a JSON file
     * @param list ArrayList of Question objects to be saved
     */
    public static void saveQuestions(ArrayList<Question> list){
        JSONArray jsonQuestions = new JSONArray();

        //creating all the json objects
		for(int i=0; i< list.size(); i++) {
			jsonQuestions.add(getQuestionJSON(list.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(QUESTION_FILE_NAME)) {
 
            file.write(jsonQuestions.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    /**
     * Saves a list of Stage objects to a JSON file
     * @param list ArrayList of Stage objects to be saved
     */
    public static void saveStages(ArrayList<Stage> list){
        JSONArray jsonStages = new JSONArray();

        //creating all the json objects
		for(int i=0; i< list.size(); i++) {
			jsonStages.add(getStageJSON(list.get(i)));
		}
		
		//Write JSON file
        try {
            String path = getFileWritingPath(STAGE_FILE_NAME, STAGE_FILE_NAME_JUNIT);
			FileWriter file = new FileWriter(path);

            file.write(jsonStages.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


     /**
      * Saves a list of Phrase objects to a JSON file
      * @param list ArrayList of Phrase objects to be saved
      */
     public static void savePhrases(ArrayList<Phrase> list){
        JSONArray jsonPhrases = new JSONArray();

        //creating all the json objects
		for(int i=0; i< list.size(); i++) {
			jsonPhrases.add(getPhraseJSON(list.get(i)));
		}
		
		//Write JSON file
        try  {
            String path = getFileWritingPath(PHRASE_FILE_NAME, PHRASE_FILE_NAME_JUNIT);
			FileWriter file = new FileWriter(path);

            file.write(jsonPhrases.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // tested and works
    /**
     * Saves a list of Word objects to a JSON file
     * @param list ArrayList of Word objects to be saved
     */
    public static void saveWords(ArrayList<Word> list){
        JSONArray jsonWords = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< list.size(); i++) {
			jsonWords.add(getWordJSON(list.get(i)));
		}
		
		//Write JSON file
        try {
            String path = getFileWritingPath(WORD_FILE_NAME, WORD_FILE_NAME_JUNIT);
			FileWriter file = new FileWriter(path);

            file.write(jsonWords.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a User object into a JSONObject containing all user data
     * @param user The User object to convert to JSON
     * @return JSONObject containing the user's data including:
     *         - ID
     *         - Friends list
     *         - First and last name
     *         - Username and password
     *         - Questions statistics
     *         - Currency amount
     *         - Language tracks
     *         - Struggle list
     */
    private static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();

        JSONArray friendJSON = new JSONArray();
        
        JSONArray trackJSON = new JSONArray();
        ArrayList<LanguageTrack> tracks = user.getLanguageTracks();
        for(LanguageTrack t : tracks)
            trackJSON.add(getLanguageTrackJSON(t));

        JSONArray struggleJSON = new JSONArray();
        ArrayList<Phrase> strugs = user.getStruggleList();
            for(Phrase p : strugs)
                struggleJSON.add(p.getUUID().toString());
        

        userDetails.put(USER_ID, user.getID().toString());
		userDetails.put(USER_FRIENDS, friendJSON);
		userDetails.put(USER_FIRST_NAME, user.getFirstName());
		userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_USERNAME, user.getUsername());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_QUESTIONS_CORRECT, user.getQuestionsCorrect());
        userDetails.put(USER_QUESTIONS_WRONG, user.getQuestionsWrong());
        userDetails.put(USER_CURRENCY, user.getCurrency());
        userDetails.put(USER_LANGUAGE_TRACKS, trackJSON);
        userDetails.put(USER_STRUGGLE_LIST, struggleJSON);
        
        return userDetails;
    }

    /**
     * Converts a LanguageTrack object into a JSONObject containing the track data
     * @param languageTrack The LanguageTrack object to convert to JSON
     * @return JSONObject containing the language track's data including:
     *         - Learning language
     *         - Current stage level
     *         - Current stage details
     */
    private static JSONObject getLanguageTrackJSON(LanguageTrack languageTrack) {
        JSONObject trackDetails = new JSONObject();

        trackDetails.put(LANGUAGE_TRACK_LANGUAGE, languageTrack.getLearningLanguage().toString());
        trackDetails.put(LANGUAGE_TRACK_STAGE_LEVEL, languageTrack.getCurrentStageLevel());
        trackDetails.put(LANGUAGE_TRACK_CURRENT_STAGE, getStageJSON(languageTrack.getCurrentStage()));

        return trackDetails;
    }

    /**
     * Converts a Question object into a JSONObject containing the question data
     * @param question The Question object to convert to JSON
     * @return JSONObject containing the question's data including:
     *         - Question ID
     *         - Question words as UUIDs
     *         - Number of words to match
     *         - True/False answer
     *         - Multiple choice potential answers
     *         - Multiple choice correct answer
     *         - Stage level
     */
    private static JSONObject getQuestionJSON(Question question) {
		JSONObject questionDetails = new JSONObject();

        questionDetails.put(QUESTION_ID, question.getUUID().toString());
        JSONArray questionWordsJSON = new JSONArray();
        ArrayList<Word> questionWords = question.getQuestionWords();
        for(Word w : questionWords) {
            questionWordsJSON.add(w.getUuid().toString());
        }
        questionDetails.put(QUESTION_QUESTION,questionWordsJSON);
        questionDetails.put(QUESTION_NUM_WORDS, question.getMatchingNumWords());
        questionDetails.put(QUESTION_TRUE_FALSE_ANSWER, question.getTFAnswerAsString());

        String[] potAns = question.getMCPotAnswers();

        if(potAns == null)
            questionDetails.put(QUESTION_CHOICE_POT_ANSWERS, null);
        else{
            JSONArray potAnsJSON = new JSONArray();
            for(String s : potAns){
                potAnsJSON.add(s);
            }
            questionDetails.put(QUESTION_CHOICE_POT_ANSWERS, potAnsJSON);
        }
        String correctAnswer = "" + question.getMCCorrectAnswer();
        questionDetails.put(QUESTION_CHOICE_CORRECT_ANSWER, correctAnswer);
        questionDetails.put(QUESTION_STAGE_LEVEL, question.getStageLevel());

        return questionDetails;
    }


    /**
     * Converts a Word object into a JSONObject containing the word data
     * @param word The Word object to convert to JSON
     * @return JSONObject containing the word's data including:
     *         - Phonetic spelling
     *         - Learning language
     *         - Base language 
     *         - Definition in learning language
     *         - Definition in base language
     *         - Word ID
     */
    private static JSONObject getWordJSON(Word word) {
		JSONObject wordDetails = new JSONObject();
		wordDetails.put(WORD_PHONETICS, word.getPhonetics());
        wordDetails.put(WORD_LEARNING_LANG, word.getLearningLanguage().toString());
        wordDetails.put(WORD_BASE_LANG, word.getBaseLanguage().toString());
        wordDetails.put(WORD_LEARNING_DEF, word.getLearningDefinition());
        wordDetails.put(WORD_BASE_DEF, word.getBaseDefinition());
        wordDetails.put(WORD_ID, word.getUuid().toString());
		
        return wordDetails;
    }

    /**
     * Converts a Story object into a JSONObject containing the story data
     * @param story The Story object to convert to JSON
     * @return JSONObject containing the story's data including:
     *         - Story ID
     *         - Book array of story pages
     *         - Total number of pages
     *         - Stage level
     *         - Story title
     *         - Current page number
     */
    private static JSONObject getStoryJSON(Story story) {
		JSONObject storyDetails = new JSONObject();
       
        // put the book into json form
        JSONArray bookArray = new JSONArray();
        // ArrayList<ArrayList<Word>> book = story.getBook();

        ArrayList<StoryPage> book = story.getBook();
        for(StoryPage list : book) {
            bookArray.add(getStoryPageJSON(list));
        }
        
        storyDetails.put(STORY_ID, story.getStoryID().toString());
        storyDetails.put(STORY_BOOK, bookArray);
        storyDetails.put(STORY_TOTAL_PAGES, story.getTotalPages());
        storyDetails.put(STORY_STAGE_LEVEL, story.getStageLevel());
        storyDetails.put(STORY_TITLE, story.getTitle());
        storyDetails.put(STORY_PAGE, story.getPage());
        
        return storyDetails;
    }

    /**
     * Converts a StoryPage object into a JSONObject containing the page data
     * @param storyPage The StoryPage object to convert to JSON
     * @return JSONObject containing the page's data including:
     *         - Page number
     *         - Image path/reference
     *         - Array of sentence UUIDs
     */
    private static JSONObject getStoryPageJSON(StoryPage storyPage) {
		JSONObject pageDetails = new JSONObject();
        JSONArray sentenceJSON = new JSONArray();
        ArrayList<Phrase> sentences = storyPage.getSentences();
        for(Phrase p : sentences) {
            sentenceJSON.add(p.getUUID().toString());
        }
        pageDetails.put(STORYPAGE_PAGE_NUM, storyPage.getPageNum());
        pageDetails.put(STORYPAGE_IMAGE, storyPage.getImage());
        pageDetails.put(STORYPAGE_SENTENCES, sentenceJSON);
        
        
        return pageDetails;
    }

    /**
     * Converts a Phrase object into a JSONObject containing the phrase data
     * @param phrase The Phrase object to convert to JSON
     * @return JSONObject containing the phrase's data including:
     *         - Array of word UUIDs that make up the phrase
     *         - Phrase UUID
     *         - Phrase definition
     *         - Stage level
     */
    private static JSONObject getPhraseJSON(Phrase phrase) {
        JSONObject phraseDetails = new JSONObject();
        JSONArray phraseArray = new JSONArray();
        ArrayList<Word> words = phrase.getPhrase();
        for(Word w : words) {
            phraseArray.add(w.getUuid().toString());
        }

        phraseDetails.put(PHRASE_PHRASE, phraseArray);
        phraseDetails.put(PHRASE_ID, phrase.getUUID().toString());
        phraseDetails.put(PHRASE_DEFINITION, phrase.getDefinition());
        phraseDetails.put(PHRASE_STAGE_LEVEL, phrase.getStageLevel());
        
        return phraseDetails;
    }

    /**
     * Converts a Lesson object into a JSONObject containing the lesson data
     * @param lesson The Lesson object to convert to JSON
     * @return JSONObject containing the lesson's data including:
     *         - Lesson content
     *         - Array of question UUIDs
     *         - Array of answered question UUIDs 
     *         - Lesson score
     *         - Current question UUID
     *         - Stage level
     */
    private static JSONObject getLessonJSON(Lesson lesson) {
		JSONObject lessonDetails = new JSONObject();

		JSONArray quesArray = new JSONArray();
        ArrayList<Question> questions = lesson.getQuestions();
        for(Question q : questions) {
            quesArray.add(q.getUUID().toString());
        }
        JSONArray ansQuesArray = new JSONArray();
        ArrayList<Question> ansQuestions = lesson.getAnsweredQuestions();
        for(Question q : ansQuestions) {
            ansQuesArray.add(q.getUUID().toString());
        }


        lessonDetails.put(LESSON_CONTENT, lesson.getContent());
        lessonDetails.put(LESSON_QUESTIONS, quesArray);
        lessonDetails.put(LESSON_ANSWERED, ansQuesArray);
        lessonDetails.put(LESSON_SCORE, lesson.getScore());
        lessonDetails.put(LESSON_CURRENT_QUESTION, lesson.getCurrentQuestion().getUUID().toString());
        lessonDetails.put(LESSON_STAGE_LEVEL, lesson.getStageLevel());
		
        return lessonDetails;
    }

    /**
     * Converts a Stage object into a JSONObject containing the stage data
     * @param stage The Stage object to convert to JSON
     * @return JSONObject containing the stage's data including:
     *         - Stage level
     *         - Story JSON object
     */
    private static JSONObject getStageJSON(Stage stage) {
		JSONObject stageDetails = new JSONObject();
		stageDetails.put(STAGE_LEVEL, stage.getStageLevel());
        stageDetails.put(STAGE_STORY, getStoryJSON(stage.getStory()));
		
        return stageDetails;
    }
    private static String getFileWritingPath(String PATH_NAME, String JUNIT_PATH_NAME) {
    try {
        if(isJUnitTest()){
            URI url = DataWriter.class.getResource(JUNIT_PATH_NAME).toURI();
            return url.getPath();
        } else {
            return PATH_NAME;
        }
    } catch(Exception e){
        System.out.println("Difficulty getting resource path");
        return "";
    }
	}
    public static void main(String[] args) {

        System.out.println(Language.GER);
        /*ArrayList<User2> list = DataLoader.getUser2();
        System.out.println("User List before");
        for(User2 u : list) {
            System.out.println(u);
        }
        list.add(DataWriter.addNewUser2());
        list.add(DataWriter.addNewUser2());
        DataWriter.saveUser2(list);

        list = DataLoader.getUser2();
        System.out.println("\nUser List after");
        for(User2 u : list) {
            System.out.println(u);
        } */
    }
}
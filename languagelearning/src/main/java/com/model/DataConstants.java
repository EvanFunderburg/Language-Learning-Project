package com.model;

/**
 * Constants class containing all data field names and file paths used throughout the application
 * @author Will Thacher
 * @author Ritvik Neerattil  
 * @author Aidan Van Voorhis
 * @author Evan Funderburg
 */
public abstract class DataConstants {
    // data constants for user
    protected static final String USER_FILE_NAME = "languagelearning/src/main/java/com/data/User.json";
    protected static final String USER_FILE_NAME_JUNIT = "/data/User.json";
    protected static final String USER2_FILE_NAME = "languagelearning/src/main/java/com/data/User2.json";
	  protected static final String USER = "user";
    protected static final String USER_ID = "ID";
    protected static final String USER_FRIENDS = "friends";
	  protected static final String USER_FIRST_NAME = "firstName";
	  protected static final String USER_LAST_NAME = "lastName";
	  protected static final String USER_USERNAME = "username";
    protected static final String USER_PASSWORD = "password";
    protected static final String USER_QUESTIONS_CORRECT = "questionsCorrect";
    protected static final String USER_QUESTIONS_WRONG = "questionsWrong";
    protected static final String USER_CURRENCY = "currency";
    protected static final String USER_LANGUAGE_TRACKS = "LanguageTracks";
    protected static final String USER_STRUGGLE_LIST = "struggleList";

    protected static final String LANGUAGE_TRACK_LANGUAGE = "learningLanguage";
    protected static final String LANGUAGE_TRACK_STAGE_LEVEL = "stageLevel";
    protected static final String LANGUAGE_TRACK_CURRENT_STAGE = "currentStage";

    protected static final String STAGE_FILE_NAME = "languagelearning/src/main/java/com/data/Stage.json";
    protected static final String STAGE_FILE_NAME_JUNIT = "/data/Stage.json";
    protected static final String STAGE_LEVEL = "stageLevel";
    protected static final String STAGE_STORY = "story";
    protected static final String STAGE_LESSON = "lesson";

    protected static final String LESSON_CONTENT = "content";
    protected static final String LESSON_QUESTIONS = "questions";
    protected static final String LESSON_ANSWERED = "answeredQuestions";
    protected static final String LESSON_SCORE = "score";
    protected static final String LESSON_CURRENT_QUESTION = "currentQuestion";
    protected static final String LESSON_STAGE_LEVEL = "stageLevel";

    protected static final String WORD_FILE_NAME = "languagelearning/src/main/java/com/data/Word.json";
    protected static final String WORD_FILE_NAME_JUNIT = "/data/Word.json";
    protected static final String WORD_PHONETICS = "phonetics";
    protected static final String WORD_LEARNING_LANG = "learningLanguage";
    protected static final String WORD_BASE_LANG = "baseLanguage";
    protected static final String WORD_LEARNING_DEF = "learningDefinition";
    protected static final String WORD_BASE_DEF = "baseDefinition";
    protected static final String WORD_ID = "wordID";

    protected static final String STORY_ID = "storyID";
    protected static final String STORY_QUESTIONS = "questions";
    protected static final String STORY_BOOK = "book";
    protected static final String STORY_TOTAL_PAGES = "totalPages";
    protected static final String STORY_PAGE = "page";
    protected static final String STORY_STAGE_LEVEL = "stageLevel";
    protected static final String STORY_TITLE = "title";

    protected static final String QUESTION_FILE_NAME = "languagelearning/src/main/java/com/data/Questions.json";
    protected static final String QUESTION_QUESTION = "question";
    protected static final String QUESTION_ID = "questionID";
    protected static final String QUESTION_STAGE_LEVEL = "stageLevel";

    protected static final String QUESTION_NUM_WORDS = "numWords";

    protected static final String QUESTION_CHOICE_POT_ANSWERS = "potentialAnswers";
    protected static final String QUESTION_CHOICE_CORRECT_ANSWER = "correctAnswer";

    protected static final String QUESTION_TRUE_FALSE_ANSWER = "answer";

    protected static final String PHRASE_FILE_NAME = "languagelearning/src/main/java/com/data/Phrase.json";
    protected static final String PHRASE_FILE_NAME_JUNIT = "/data/Phrase.json";
    protected static final String PHRASE_PHRASE = "phrase";
    protected static final String PHRASE_ID = "phraseID";
    protected static final String PHRASE_DEFINITION = "definition";
    protected static final String PHRASE_STAGE_LEVEL = "stageLevel";

    protected static final String STORYPAGE_PAGE_NUM = "pageNum";
    protected static final String STORYPAGE_IMAGE = "image";
    protected static final String STORYPAGE_SENTENCES = "sentences";

    public static boolean isJUnitTest() {  
		for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
		  if (element.getClassName().startsWith("org.junit.")) {
			return true;
		  }           
		}
		return false;
	  }



}

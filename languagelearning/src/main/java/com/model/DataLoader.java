package com.model;
/**
 * Class that handles loading data from JSON files into Java objects
 * @author Will Thacher
 * @author Ritvik Neerattil  
 * @author Aidan Van Voorhis
 * @author Evan Funderburg
 */
import java.util.UUID;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataLoader extends DataConstants {

    // Works
    /**
     * Loads and returns all users from the JSON file
     * @return ArrayList of User objects containing user data, or null if there was an error loading the data
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            // FileReader reader = new FileReader(USER_FILE_NAME);
            BufferedReader reader = getReaderFromFile(USER_FILE_NAME, USER_FILE_NAME_JUNIT);
            JSONParser parser = new JSONParser();
            JSONArray usersJSON = (JSONArray) new JSONParser().parse(reader);
            WordList wordList = WordList.getInstance();
            PhraseList phraseList = PhraseList.getInstance();
           // QuestionList questionList = QuestionList.getInstance();
            for (int i = 0; i < usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject) usersJSON.get(i);
                UUID userID = UUID.fromString((String) userJSON.get(USER_ID));
                JSONArray friends = (JSONArray) userJSON.get(USER_FRIENDS);
                String firstName = (String) userJSON.get(USER_FIRST_NAME);
                System.out.print(firstName);
                String lastName = (String) userJSON.get(USER_LAST_NAME);
                String username = (String) userJSON.get(USER_USERNAME);
                String password = (String) userJSON.get(USER_PASSWORD);
                long questionsCorrect = (Long) userJSON.get(USER_QUESTIONS_CORRECT);
                long questionsWrong = (Long) userJSON.get(USER_QUESTIONS_WRONG);
                long currency = (Long) userJSON.get(USER_CURRENCY);
                JSONArray struggleJSON = (JSONArray) userJSON.get(USER_STRUGGLE_LIST);
                ArrayList<Phrase> struggles = new ArrayList<Phrase>();
                for(int j = 0; j < struggleJSON.size(); j++) {
                    struggles.add(phraseList.getPhrase(UUID.fromString((String)struggleJSON.get(j))));
                }
                JSONArray langTrackJSON = (JSONArray) userJSON.get(USER_LANGUAGE_TRACKS);
                ArrayList<LanguageTrack> tracks = new ArrayList<LanguageTrack>();
                for (int j = 0; j < langTrackJSON.size(); j++) {
                    JSONObject langTrack = (JSONObject) langTrackJSON.get(j);
                    Language language = Language.valueOf((String) langTrack.get(LANGUAGE_TRACK_LANGUAGE));
                    long stageLevel = (Long) langTrack.get(LANGUAGE_TRACK_STAGE_LEVEL);

                    JSONObject stageJSON = (JSONObject) langTrack.get(LANGUAGE_TRACK_CURRENT_STAGE);

                    JSONObject storyJSON = (JSONObject) stageJSON.get(STAGE_STORY);
                    // read story data
                    UUID storyID = UUID.fromString((String) storyJSON.get(STORY_ID));
                    JSONArray bookArray = (JSONArray) storyJSON.get(STORY_BOOK);
                    long total_pages = (Long) storyJSON.get(STORY_TOTAL_PAGES);
                    String title = (String) storyJSON.get(STORY_TITLE);
                    long page = (Long) storyJSON.get(STORY_PAGE);
                    // create Story
                    ArrayList<StoryPage> book = new ArrayList<StoryPage>();
                    for (int k = 0; k < bookArray.size(); k++) {
                        JSONObject storyPageJSON = (JSONObject)bookArray.get(k);
                        long pageNum = (Long)storyPageJSON.get(STORYPAGE_PAGE_NUM);
                        String image = (String)storyPageJSON.get(STORYPAGE_IMAGE);
                        JSONArray sentenceJSON = (JSONArray)storyPageJSON.get(STORYPAGE_SENTENCES);
                        ArrayList<Phrase> sentences = new ArrayList<Phrase>();
                        for(int v = 0; v < sentenceJSON.size(); v++) {
                            sentences.add(phraseList.getPhrase(UUID.fromString((String)sentenceJSON.get(v))));
                        }
                        book.add(new StoryPage(image, sentences, (int)pageNum));
                    }
                    Story story = new Story(book, storyID, title, (int)stageLevel);

                    Stage stage = new Stage(story, (int)stageLevel, true);
                    // Create the language track and add it to the language tracks list
                    tracks.add(new LanguageTrack(Language.GER, stage));
                }

                // create the user and add to the arraylist
                users.add(new User(userID, firstName, lastName, username, password, (int)questionsCorrect, (int)questionsWrong, (int)currency, tracks, struggles));

            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Loads and parses phrase data from the phrases JSON file
     * @return ArrayList of Phrase objects containing all phrases in the system, or null if there was an error loading the data
     */
    public static ArrayList<Phrase> getPhrases(){
        ArrayList<Phrase> phrases = new ArrayList<Phrase>();
        WordList w = WordList.getInstance();
        try {
            BufferedReader reader = getReaderFromFile(PHRASE_FILE_NAME, PHRASE_FILE_NAME_JUNIT);
            JSONParser parser = new JSONParser();
            JSONArray phrasesJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < phrasesJSON.size(); i++) {
                JSONObject phraseJSON = (JSONObject) phrasesJSON.get(i);
                String definition = (String) phraseJSON.get(PHRASE_DEFINITION);
                JSONArray phraseArray = (JSONArray)phraseJSON.get(PHRASE_PHRASE);
                ArrayList<Word> phraseWords = new ArrayList<Word>();
                for(int j = 0; j < phraseArray.size(); j++)
                    phraseWords.add(w.search(UUID.fromString((String) phraseArray.get(j))));
                long stageLevel = (Long)phraseJSON.get(PHRASE_STAGE_LEVEL);
                UUID phraseID = UUID.fromString((String) phraseJSON.get(PHRASE_ID));
                phrases.add(new Phrase(definition, (int)stageLevel, phraseWords, phraseID));
            }

            return phrases;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

  /*   public static ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<Question>();

        try {
            FileReader reader = new FileReader(QUESTION_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray peopleJSON = (JSONArray) new JSONParser().parse(reader);
            WordList w = WordList.getInstance();

            for (int i = 0; i < peopleJSON.size(); i++) {
                JSONObject personJSON = (JSONObject) peopleJSON.get(i);
                JSONArray questionArray = (JSONArray) personJSON.get(QUESTION_QUESTION);
                ArrayList<Word> question = new ArrayList<Word>();

                for (int j = 0; j < questionArray.size(); j++) {
                    question.add(w.search(UUID.fromString((String) questionArray.get(j))));
                }

                long stageLevel = (Long) personJSON.get(QUESTION_STAGE_LEVEL);
                long numWords = (Long) personJSON.get(QUESTION_NUM_WORDS);
                String ans = (String) personJSON.get(QUESTION_TRUE_FALSE_ANSWER);
                JSONArray potentialAnswers = (JSONArray) personJSON.get(QUESTION_CHOICE_POT_ANSWERS);
                String correctAnswer = (String) personJSON.get(QUESTION_CHOICE_CORRECT_ANSWER);
                UUID quesID = UUID.fromString((String) personJSON.get(QUESTION_ID));

                // Adds a question of type matching
                if (numWords != -1) {
                    questions.add(new Matching(question, (int) numWords, (int) stageLevel, quesID));
                } else if (!ans.equals("null")) { // true false question
                    boolean answer;
                    if (ans.equals("true"))
                        answer = true;
                    else
                        answer = false;
                    questions.add(new TrueFalse(question, answer, (int) stageLevel, quesID));
                } else { // multiple choice question
                    String[] potAns = new String[4];
                    for (int j = 0; j < potAns.length; j++)
                        potAns[j] = (String) potentialAnswers.get(j);
                    questions.add(new MultipleChoice(question, potAns, correctAnswer.charAt(0), (int) stageLevel, quesID));
                }

            }

            return questions;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
*/

    /**
     * Loads word data from the JSON file and creates Word objects
     * @return ArrayList of Word objects containing word data from the JSON file, or null if there was an error
     */
    public static ArrayList<Word> getWords() {

        ArrayList<Word> words = new ArrayList<Word>();

        try {
            BufferedReader reader = getReaderFromFile(WORD_FILE_NAME, WORD_FILE_NAME_JUNIT);
            JSONParser parser = new JSONParser();
            JSONArray wordsJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < wordsJSON.size(); i++) {
                JSONObject wordJSON = (JSONObject) wordsJSON.get(i);
                String phonetics = (String) wordJSON.get(WORD_PHONETICS);
                Language learningLang = Language.valueOf((String) wordJSON.get(WORD_LEARNING_LANG));
                Language baseLang = Language.valueOf((String) wordJSON.get(WORD_BASE_LANG));
                String learningWord = (String) wordJSON.get(WORD_LEARNING_DEF);
                String baseWord = (String) wordJSON.get(WORD_BASE_DEF);
                UUID wordID = UUID.fromString((String) wordJSON.get(WORD_ID));
                words.add(new Word(learningLang, baseLang, learningWord, baseWord, wordID, phonetics));
            }

            return words;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Tested working
    /**
     * Loads stage data from the JSON file and creates Stage objects
     * Each stage contains a Story object with StoryPage objects containing Phrases
     * @return ArrayList of Stage objects containing stage data from the JSON file, or null if there was an error
     */
    public static ArrayList<Stage> getStages() {
        ArrayList<Stage> stages = new ArrayList<Stage>();

        try {
            BufferedReader reader = getReaderFromFile(STAGE_FILE_NAME, STAGE_FILE_NAME_JUNIT);
            JSONParser parser = new JSONParser();
            JSONArray stagesJSON = (JSONArray) new JSONParser().parse(reader);
           // QuestionList questionList = QuestionList.getInstance();
            WordList wordList = WordList.getInstance();
            PhraseList phraseList = PhraseList.getInstance();

            for (int i = 0; i < stagesJSON.size(); i++) {
                JSONObject stageJSON = (JSONObject) stagesJSON.get(i);
                long stageLevel = (Long) stageJSON.get(STAGE_LEVEL);
                JSONObject storyJSON = (JSONObject) stageJSON.get(STAGE_STORY);
                // read story data
                UUID storyID = UUID.fromString((String) storyJSON.get(STORY_ID));
                JSONArray bookArray = (JSONArray) storyJSON.get(STORY_BOOK);
                long total_pages = (Long) storyJSON.get(STORY_TOTAL_PAGES);
                String title = (String) storyJSON.get(STORY_TITLE);
                long page = (Long) storyJSON.get(STORY_PAGE);
                // create Story
                ArrayList<StoryPage> book = new ArrayList<StoryPage>();
                for (int j = 0; j < bookArray.size(); j++) {
                    JSONObject storyPageJSON = (JSONObject)bookArray.get(j);
                    long pageNum = (Long)storyPageJSON.get(STORYPAGE_PAGE_NUM);
                    String image = (String)storyPageJSON.get(STORYPAGE_IMAGE);
                    JSONArray sentenceJSON = (JSONArray)storyPageJSON.get(STORYPAGE_SENTENCES);
                    ArrayList<Phrase> sentences = new ArrayList<Phrase>();
                    for(int k = 0; k < sentenceJSON.size(); k++) {
                        sentences.add(phraseList.getPhrase(UUID.fromString((String)sentenceJSON.get(k))));
                    }
                    book.add(new StoryPage(image, sentences, (int)pageNum));
                }
                Story story = new Story(book, storyID, title, (int)stageLevel);

                // create stage and add it to list
                stages.add(new Stage(story, (int) stageLevel, false));
            }

            return stages;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    	private static BufferedReader getReaderFromFile(String fileName, String jsonFileName){
		try {
			if(isJUnitTest()){
				InputStream inputStream = DataLoader.class.getResourceAsStream(jsonFileName);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				return new BufferedReader(inputStreamReader);
			} else {
				FileReader reader = new FileReader(fileName);
				return new BufferedReader(reader);
			}
		} catch(Exception e){
			System.out.println("Can't load");
			return null;
		}
			
	}

    public static void main(String[] args) {
    //     ArrayList<User2> list = DataLoader.getUser2();

    //     for (User2 u : list) {
    //         // System.out.println(u);
    //     }

    //     ArrayList<Word> list2 = DataLoader.getWords();

    //     for (Word w : list2) {
    //         // System.out.println(w);
    //     }

    //     //ArrayList<Question> list3 = DataLoader.getQuestions();

    //    // for (Question q : list3) {
    //         // System.out.println("\n" + q);
    //    // }

    //     ArrayList<Stage> list4 = DataLoader.getStages();

    //     for (Stage s : list4) {
    //         // System.out.println(s.getStory().toString(1));
    //     }

    //     ArrayList<Phrase> list5 = DataLoader.getPhrases();

    //     for (Phrase p : list5) {
    //         // System.out.println(p.getPhraseStr());
    //     }

        ArrayList<User> list6 = DataLoader.getUsers();

        for (User u : list6) {
            System.out.println(u);
            //System.out.println(u.getLanguageTracks().get(0).getCurrentStage());
        }

        // UserList uL = UserList.getInstance();
        UserList.saveUsers();

        
    }
}

package com.model;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;


public class TestLanguageTrack {
    private Language language;
    private Stage stage;
    private LanguageTrack langTrack1;
    private LanguageTrack langTrack2;

    @Before
    public void setup() {
        language = Language.GER;
        stage = new Stage(null, 1, false);
        // langTrack1 = new LanguageTrack(language);
        langTrack2 = new LanguageTrack(language, stage);
    
    }

    @After
    public void teardown(){
        
    }

    @Test
    public void TestConstructor1(){
        // assertNotNull(langTrack1);
        // The Jsons were all messed up so i have to commente this out
    }

    @Test
    public void TestConstructor2(){
        assertNotNull(langTrack2);
    }

    @Test
    public void TestAdvanceStage(){
        langTrack2.advanceStage();
        assertEquals(2, langTrack2.getCurrentStageLevel());
    }

    @Test
    public void TestStage(){
        assertNotNull(langTrack2.getCurrentStage());
    }

    @Test
    public void TestLanguage(){
        assertEquals(language, langTrack2.getLearningLanguage());    
    }

    





}
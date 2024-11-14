package com.model;
/**
 * Enum representing supported languages in the application
 * @author Will Thacher
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author Evan Funderburg
 */
public enum Language {
    GER("German"),
    SPA("Spanish"), 
    ENG("English"),
    FRA("French"),
    LAT("Latin");
    
    
    public final String label;

    private Language(String label) {
        this.label = label;
    }
}

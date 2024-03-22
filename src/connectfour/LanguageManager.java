package connectfour;

import java.util.HashMap;

public class LanguageManager {
    private HashMap<String, HashMap<String, HashMap<String, String>>> languages;

    public LanguageManager() {
        languages = new HashMap<>();
        initializeLanguages();
    }

    private void initializeLanguages() {
        // English language
        HashMap<String, HashMap<String, String>> english = new HashMap<>();
        HashMap<String, String> englishPhrases = new HashMap<>();
        englishPhrases.put("gameLabel", "Game");
        englishPhrases.put("networkLabel", "Network");
        englishPhrases.put("helpLabel", "Help");
        englishPhrases.put("languageLabel", "Language");
        englishPhrases.put("restartLabel", "Restart Game");
        englishPhrases.put("quitLabel", "Quit Game");
        englishPhrases.put("cLanguageLabel", "Change Language");
        englishPhrases.put("HTPLabel", "How To Play");
        englishPhrases.put("P1Wins", "Player 1 Wins");
        englishPhrases.put("P2Wins", "Player 2 Wins");
        english.put("phrases", englishPhrases);
        languages.put("English", english);

        // French language
        HashMap<String, HashMap<String, String>> french = new HashMap<>();
        HashMap<String, String> frenchPhrases = new HashMap<>();
        frenchPhrases.put("gameLabel", "Jeu");
        frenchPhrases.put("networkLabel", "Réseau");
        frenchPhrases.put("helpLabel", "Aide");
        frenchPhrases.put("languageLabel", "Langue");
        frenchPhrases.put("restartLabel", "Recommencer Le Jeu");
        frenchPhrases.put("quitLabel", "Quitter Le Jeu");
        frenchPhrases.put("cLanguageLabel", "Changer La Langue");
        frenchPhrases.put("HTPLabel", "Comment Jouer");
        frenchPhrases.put("P1Wins", "Joueuer 1 Gagne");
        frenchPhrases.put("P2Wins", "Joueuer 2 Gagne");
        french.put("phrases", frenchPhrases);
        languages.put("French", french);
    }

    public HashMap<String, String> getPhrases(String language) {
        HashMap<String, HashMap<String, String>> languageMap = languages.getOrDefault(language, new HashMap<>());
        return languageMap.getOrDefault("phrases", new HashMap<>());
    }
}

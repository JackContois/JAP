package connectfour;

import java.util.HashMap;

/**
 * The LanguageManager class manages the language localization for the Connect Four game.
 * It provides methods to retrieve phrases and labels in different languages.
 */
public class LanguageManager {
	
	/** A map containing language data organized by language and phrase categories. */
    private HashMap<String, HashMap<String, HashMap<String, String>>> languages;

    /**
     * Constructs a new LanguageManager object and initializes the language data.
     */
    public LanguageManager() {
        languages = new HashMap<>();
        initializeLanguages();
    }

    /**
     * Initializes the language data for English and French.
     * Each language contains mappings for different phrase categories.
     */
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
        englishPhrases.put("cLanguageLabelEnglish", "English");
        englishPhrases.put("cLanguageLabelFrench", "French");
        englishPhrases.put("HTPLabel", "How To Play");
        englishPhrases.put("P1Wins", " Wins");
        englishPhrases.put("P2Wins", " Wins");
        englishPhrases.put("chipsPlayed", "Chips Played");
        englishPhrases.put("playerTurn", "Player's Turn");
        englishPhrases.put("timer", "Timer");
        englishPhrases.put("turnTimer", "Turn Timer");
        englishPhrases.put("player1Plays", " Plays In Column: ");
        englishPhrases.put("player2Plays", " Plays In Column: ");
        englishPhrases.put("send", "Send");
        englishPhrases.put("draw", "Tie Game");
        englishPhrases.put("rules", "Instructions:\n1. Each player takes turns dropping a chip into one of the columns.\n2. The chip falls to the lowest empty slot in the selected column.\n3. The first player to connect four chips in a row wins.\n4. The connection can be horizontal, vertical, or diagonal.");
        englishPhrases.put("close", "Close");
        english.put("phrases", englishPhrases);
        languages.put("English", english);

        // French language
        HashMap<String, HashMap<String, String>> french = new HashMap<>();
        HashMap<String, String> frenchPhrases = new HashMap<>();
        frenchPhrases.put("gameLabel", "Jeu");
        frenchPhrases.put("networkLabel", "R\u00E9seau");
        frenchPhrases.put("helpLabel", "Aide");
        frenchPhrases.put("languageLabel", "Langue");
        frenchPhrases.put("restartLabel", "Recommencer Le Jeu");
        frenchPhrases.put("quitLabel", "Quitter Le Jeu");
        frenchPhrases.put("cLanguageLabelEnglish", "Anglais");
        frenchPhrases.put("cLanguageLabelFrench", "Fran\u00E7ais");
        frenchPhrases.put("HTPLabel", "Comment Jouer");
        frenchPhrases.put("P1Wins", " Gagne");
        frenchPhrases.put("P2Wins", " Gagne");
        frenchPhrases.put("chipsPlayed", "Jetons Jou\u00E9s");
        frenchPhrases.put("playerTurn", "Le Tour De Joueuer");
        frenchPhrases.put("timer", "Minuteur");
        frenchPhrases.put("turnTimer", "Minuteur Du Tour");
        frenchPhrases.put("player1Plays", " Joue Dans La Colonne: ");
        frenchPhrases.put("player2Plays", " Joue Dans La Colonne: ");
        frenchPhrases.put("send", "Envoyer");
        frenchPhrases.put("draw", "Match Nul");
        frenchPhrases.put("rules", "Instructions:\n1. Chaque joueur d\u00E9pose \u00E0 tour de r\u00F4le un jeton dans l'une des colonnes.\n2. Le jeton tombe dans l'emplacement vide le plus bas de la colonne s\u00E9lectionn\u00E9e.\n3. Le premier joueur \u00E0 connecter quatre jetons dans une rang\u00E9e gagne.\n4. La connexion peut \u00EAtre horizontale, verticale ou diagonale.");
        frenchPhrases.put("close", "Fermer");
        french.put("phrases", frenchPhrases);
        languages.put("French", french);
    }

    /**
     * Retrieves the phrases for a specific language.
     * @param language The language for which to retrieve phrases (e.g., "English", "French").
     * @return A map containing the phrases for the specified language.
     */
    public HashMap<String, String> getPhrases(String language) {
        HashMap<String, HashMap<String, String>> languageMap = languages.getOrDefault(language, new HashMap<>());
        return languageMap.getOrDefault("phrases", new HashMap<>());
    }
}

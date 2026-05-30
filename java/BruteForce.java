import java.io.IOException;

public class BruteForce {

    public String decryptByBruteForce(String inputFile, String sampleFile) throws IOException {
        FileManager fileManager = new FileManager();
        CaesarCipher cipher = new CaesarCipher();

        String encryptedText = fileManager.readFile(inputFile);

        String bestDecryption = "";
        int bestScore = -1;
        int bestKey = 0;

        for (int key = 0; key < cipher.getAlphabetSize(); key++) {
            String decrypted = cipher.decrypt(encryptedText, key);
            int score = evaluateText(decrypted);

            if (score > bestScore) {
                bestScore = score;
                bestDecryption = decrypted;
                bestKey = key;
            }
        }

        System.out.println("Найден ключ: " + bestKey + " (оценка: " + bestScore + ")");
        return bestDecryption;
    }

    private int evaluateText(String text) {
        int score = 0;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                score++;
            }
        }

        char[] vowels = {'а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я'};
        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));
            for (char vowel : vowels) {
                if (c == vowel) {
                    score++;
                    break;
                }
            }
        }

        char[] punctuation = {'.', ',', '!', '?', ':', '"', '«', '»'};
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            for (char punct : punctuation) {
                if (c == punct) {
                    score = score + 2;
                    break;
                }
            }
        }

        return score;
    }
}
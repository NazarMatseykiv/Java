import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FileTokenizer {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Потрібно вказати назву файлу для зчитування.");
            return;
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(args[0]));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Введений рядок: " + line);
                StringTokenizer tokenizer = new StringTokenizer(line);
                System.out.println("Рядок складається з:");
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    try {
                        double num = Double.parseDouble(token);
                        System.out.println(num + " - це число = " + num);
                    } catch (NumberFormatException e) {
                        System.out.println(token + " - не є числом");
                    }
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + args[0]);
        } catch (IOException e) {
            System.out.println("Помилка вводу-виводу: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Помилка закриття файлу: " + e.getMessage());
                }
            }
        }
    }
}

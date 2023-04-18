import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class StringTokenTest {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        if (args.length == 0) {
            System.out.println("Не задано ім'я файлу");
            return;
        }
        BufferedReader fin = null;
        try {
            fin = new BufferedReader(new FileReader(args[0]));
            String line;
            while ((line = fin.readLine()) != null) {
                System.out.println("Введений рядок: " + line);
                StringTokenizer st = new StringTokenizer(line);
                System.out.println("Рядок складається з:");
                while (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    System.out.print(token + " - ");
                    try {
                        double num = Double.parseDouble(token);
                        System.out.println("це число = " + num);
                    } catch (NumberFormatException e) {
                        System.out.println("це не число");
                    }
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + args[0]);
        } catch (IOException e) {
            System.out.println("Помилка введення-виведення: " + e);
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    System.out.println("Помилка закриття файлу: " + e);
                }
            }
        }
    }
}

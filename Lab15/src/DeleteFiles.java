import java.io.File;
import java.util.Scanner;

public class DeleteFiles {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Не вказано ім'я каталогу для видалення файлів.");
            return;
        }

        String directoryName = args[0];
        File directory = new File(directoryName);

        if (!directory.isDirectory()) {
            System.out.println("Каталогу \"" + directoryName + "\" не існує.");
            return;
        }

        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("Каталог \"" + directoryName + "\" порожній.");
            return;
        }

        System.out.println("Каталог \"" + directoryName + "\" містить наступні файли:");

        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ви дійсно бажаєте видалити ці файли? (Y/N) ");
        String answer = scanner.nextLine();

        if (!answer.equalsIgnoreCase("Y")) {
            return;
        }

        int numDeleted = 0;

        for (File file : files) {
            if (file.isFile()) {
                if (file.delete()) {
                    numDeleted++;
                    System.out.println("Файл \"" + file.getName() + "\" успішно видалено.");
                } else {
                    System.out.println("Помилка видалення файлу \"" + file.getName() + "\".");
                }
            }
        }

        System.out.println("Видалено " + numDeleted + " файлів з каталогу \"" + directoryName + "\".");
    }
}

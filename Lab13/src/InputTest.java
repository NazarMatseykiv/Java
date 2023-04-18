import java.util.*;
import java.io.*;

public class InputTest {
    public static void main(String args[]) {
        if(args.length < 2) {
            System.out.println("Потріб два параметри виклику: ім'я файлу та рядок для пошуку");
            return;
        }
        String thisLine;
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader fin = null;
        try {
            fin = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
            while ((thisLine = fin.readLine()) != null) {
                System.out.println("==Введена строка:"+thisLine);
                list.add(thisLine);
            }
            Collections.sort(list);
            System.out.println("Відсортований список строк:");
            Iterator<String> iter = list.iterator();
            while(iter.hasNext()) {
                String str = iter.next();
                System.out.println(str);
            }
            String searchStr = args[1];
            int index = Collections.binarySearch(list, searchStr);
            if(index >= 0) {
                System.out.println("Рядок '" + searchStr + "' знайдено в позиції " + index);
            } else {
                System.out.println("Рядок '" + searchStr + "' не знайдено у списку");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + args[0]);
            System.out.println("Error: " + e);
        } catch (IOException e) {
            System.out.println("Помилка введення/виведення. Файл " + args[0]);
            System.out.println("Error: " + e);
        } finally {
            if (fin != null) {
                try {
                    fin.close(); // !!! Закрити файл
                } catch (IOException ex) {
                    System.out.println("Помилка закриття файлу " + args[0]);
                    System.out.println("Error: " + ex);
                }
            }
            fin = null;
        }
    }
}

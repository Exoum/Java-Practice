import java.util.Scanner;
import java.io.*;
import java.util.Random;

// Определение класса App
public class App {
    
    // Объявление статического массива целых чисел размером 4
    public static int[] arr = new int[4];

    // Объявление и инициализация строки со всеми символами, которые могут быть использованы при генерации случайной строки
    private static final String all_chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" +
    "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ!@#$%^&*()-_=+[{]};:'\",<.>/? ";

    public static void main(String[] args) throws UnsupportedEncodingException {

        int punMark = 0;
        String string = "";

        Scanner menu = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        Scanner text = new Scanner(System.in);

        int menu_count = 1;

        // Бесконечный цикл для отображения меню и обработки выбора пользователя
        while (true){             

            System.out.println("1. Сгенерировать строку.");
            System.out.println("2. Записать строку в ручную.");
            System.out.println("3. Произвести расчеты символов");
            System.out.println("4. Вывести результаты расчетов.");
            System.out.println("5. Выйти из программы.");
            
            // Обработка ввода пользователя
            try{
                menu_count = menu.nextInt();

                switch (menu_count) {
                    case 1:
                        // Очистка консоли и запрос длины строки для генерации
                        clearConsole();
                        System.out.println("Введите длину строки:");
                        int len = input.nextInt();
                        // Генерация случайной строки и сохранение ее в переменной string
                        String strRand = generateRandomString(len);
                        string = strRand;
                        break;
                    case 2:
                        // Очистка консоли и запрос строки от пользователя
                        clearConsole();
                        System.out.println("Введите строку:");
                        String str = text.nextLine();
                        string = str;
                        break;
                    case 3:
                        // Проверка, была ли сгенерирована строка, и выполнение расчетов
                        if (!string.equals("")){
                            clearConsole();
                            calcNumberOfChar(string);
                        }
                        else{
                            // Если строка не была сгенерирована, вывод сообщения об ошибке
                            clearConsole();
                            System.out.println("Вы не сгенерировали строку, выберите пункт 1 или 2!");
                        }
                        break;
                    case 4:
                        // Очистка консоли и вывод результатов расчетов
                        clearConsole();
                        if (arr[0] == 0 & arr[1] == 0 & arr[2] == 0 & arr[3] ==0){
                            System.out.println("Вы не произвели рассчеты, для выполнения расчетов выберите пункт 3!\n");
                        }
                        else{
                            // Расчет количества знаков препинания и пробелов и вывод статистики
                            punMark = string.length() - (arr[0] + arr[1] + arr[2] + arr[3]);
                            System.out.printf("Кол-во прописных букв кириллицы: %d\n", arr[0]);
                            System.out.printf("Кол-во строчных букв кириллицы: %d\n", arr[1]);
                            System.out.printf("Кол-во прописных букв латиница: %d\n", arr[2]);
                            System.out.printf("Кол-во строчных букв латиница: %d\n", arr[3]);
                            System.out.printf("Кол-во знаков препинания и пробелов: %d\n\n\n", punMark);
                        }
                        break;
                    case 5:
                        // Закрытие сканеров и выход из программы
                        input.close();
                        text.close();
                        menu.close();
                        System.exit(0);
                        break;
                }
            }
            catch (Exception ex){
                // Обработка исключений и вывод сообщения об ошибке
                System.out.println("Пожалуйста введите пункт меню от 1 до 5!");
                menu.next();
            }
            
        }
    }

    // Метод для генерации случайной строки заданной длины
    public static String generateRandomString(int len){

        Random random = new Random();

        StringBuilder stringBuilder = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(all_chars.length());
            char randomChar = all_chars.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    // Метод для подсчета количества различных типов символов в строке
    public static void calcNumberOfChar (String string){
        int upCase = 0, 
        lowCase = 0,
        upCaseEn = 0, 
        lowCaseEn = 0;

        int len = string.length();

        for(int i=0; i<len;i++) {
            if(Character.UnicodeScript.of(string.charAt(i)).equals(Character.UnicodeScript.CYRILLIC)) {
                if(Character.isUpperCase(string.charAt(i))){
                    upCase++ ;
                }
                else if(Character.isLowerCase(string.charAt(i))){
                    lowCase++;
                }
            }
            else if(Character.UnicodeScript.of(string.charAt(i)).equals(Character.UnicodeScript.LATIN)){
                if(Character.isUpperCase(string.charAt(i))){
                    upCaseEn++ ;
                }
                else if(Character.isLowerCase(string.charAt(i))){
                    lowCaseEn++;
                }
            }
        }
        arr[0] = upCase;
        arr[1] = lowCase;
        arr[2] = upCaseEn;
        arr[3] = lowCaseEn;
    }

    // Метод для очистки консоли
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

}


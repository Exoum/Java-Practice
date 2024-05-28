import java.util.Scanner;
import java.io.*;
import java.util.Random;


public class App {

    private static final String all_chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" +
    "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ!@#$%^&*()-_=+[{]};:'\",<.>/? ";
    public static void main(String[] args) throws UnsupportedEncodingException {

        String string = "";
    
        Scanner input = new Scanner(System.in);
        Scanner text = new Scanner(System.in);

        int menu = 1;

        while (true){
            System.out.println("1. Сгенерировать строку.\n2. Записать строку в ручную.\n" +
            "3. Произвести расчеты символов\n4. Выйти из программы.");
            try{
                menu = input.nextInt();

                switch (menu) {
                    case 1:
                        clearConsole();
                        System.out.println("Введите длину строки:");
                        int len = input.nextInt();
                        String strRand = generateRandomString(len);
                        string = strRand;
                        break;
                    case 2:
                        clearConsole();
                        System.out.println("Введите строку:");
                        String str = text.nextLine();
                        string = str;
                        break;
                    case 3:
                        if (string != ""){
                            clearConsole();
                            calcNumberOfChar(string);
                            break;
                        }
                        else{
                            clearConsole();
                            System.out.println("Вы не сгенерировали строку, выберите пункт 1 или 2!");
                            break;
                        }
                    case 4:
                        input.close();
                        text.close();
                        System.exit(0);
                }
            }
            catch (Exception ex){
                System.out.println("Пожалуйста введите пункт меню от 1 до 4!");
                input.next();
            }
            
        }
    }

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

    public static void calcNumberOfChar (String string){
        
        int upCase = 0, 
        lowCase = 0,
        upCaseEn = 0, 
        lowCaseEn = 0,
        punMark = 0;

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
        punMark = string.length() - (upCase + upCaseEn + lowCase + lowCaseEn);
        System.out.printf("Строка: %s\n\n", string);

        System.out.printf("Кол-во строчных символов кириллица: %d\nКол-во прописных символов кириллица: %d\nКол-во строчных символов латиница: %d\nКол-во прописных символов латиница: %d\nКол-во знаков препинания и пробелов: %d\n\n",upCase, lowCase, upCaseEn, lowCaseEn, punMark);
    }

    public static void clearConsole() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

}


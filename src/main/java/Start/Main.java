package Start;

import FirstCycle.ClosedLoop;
import SecondCycle.SemiClosedLoop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.util.*;


public class Main {
    static Scanner in = new Scanner(System.in);
    static String table = "src/main/java/Table/For_friend.xlsx";

    public static void main(String[] args) throws IOException {
        boolean exit = true;
        do {
            System.out.println("Выберите задачу:");
            System.out.println("1 - Замкнутый цикл");
            System.out.println("2 - Полу-замкнутый цикл");
            System.out.println("3 - Обратная функция для полу-замкнутого цикла");
            System.out.println("4 - Подбор ДГС по заданным параметрам глубины и времени работы на грунте");
            System.out.println("5 - Выход");
            int type = checkCorrectNumber(1, 5);
            switch (type) {
                case 1 -> {
                    int difficulty = selectDifficulty3();
                    System.out.println("Введите объем НДГС (1 или 2 литра)");//1 или 2 литра
                    int balloonVolume = checkCorrectNumber(1, 2);
                    System.out.println("Укажите давление ДГС (от 200 до 300 паскалей)");//200;250;300
                    int initialPressure = checkCorrectNumber(200, 300);
                    System.out.println("Укажите запас");//200;250;300
                    double zapas = checkCorrectZapas();
                    ClosedLoop closedLoop = new ClosedLoop();
                    closedLoop.printTime(balloonVolume, initialPressure, difficulty, zapas);
                }
                case 2 -> {
                    Object data[][];
                    double maxDecom[] = new double[]{0};
                    boolean flag = true;
                    SemiClosedLoop semiClosedLoop = new SemiClosedLoop();
                    System.out.println("Введите объем ОДНОГО БАЛОНА (их будет два)  НДГС (7,10,12,14,18,20,24)");//от 5 до 10 литров
                    int[] array = {7, 10, 12, 18, 14, 20, 24};
                    int value = checkCorrectNumberInArr(array);
                    System.out.println("Укажите содержание кислорода (от 30 до 60%)");//от 40% до 60%
                    int percent = checkCorrectNumber(30, 60);
                    System.out.println("Укажите давление ДГС (от 200 до 300 паскалей)");//200;250;300
                    int pressure = checkCorrectNumber(200, 300);
                    System.out.println("Укажите запас");//200;250;300
                    double zapas = checkCorrectZapas();
                    double[] timePlusDepth = semiClosedLoop.printDepthAndTime(value, percent, pressure, zapas);
                    // Загрузить файл Excel
                    data = convertToMatrix(table);
                    int vodorod = 100 - percent;
                    double normalDepth = 0;
                    for (Object[] datum : data) {
                        if (((timePlusDepth[1] - (Double) datum[0] < 3) && (timePlusDepth[1] - (Double) datum[0] >= 0))) {
                            normalDepth = (Double) datum[0];
                        }
                        if (((timePlusDepth[1] * vodorod / 80 - (Double) datum[0] < 3) && (timePlusDepth[1] * vodorod / 80 - (Double) datum[0] >= 0))) {
                            if ((Double) datum[1] + (Double) datum[2] < timePlusDepth[0]) {
                                if (flag) {
                                    maxDecom = new double[]{(Double) datum[0], (Double) datum[1], (Double) datum[2]};
                                    flag = false;
                                } else if ((Double) datum[1] + (Double) datum[2] > maxDecom[1] + maxDecom[2]) {
                                    maxDecom = new double[]{(Double) datum[0], (Double) datum[1], (Double) datum[2]};
                                }
                            }
                        }
                    }
                    System.out.println("Учитывая декомпрессионные обязательсва: ");
                    if (maxDecom[0] == 0) {
                        System.out.println("Для заданной системы декомпрессионные обязательсва не нужны.");
                    } else {
                        System.out.print("Глубина спуска: " + normalDepth + " м., " + "эквивалент глубины: " + maxDecom[0] + " м.(воздух).\n" + "Экспозиция на грунте: " +
                                maxDecom[1] + " мин.\n" + "Общее время декомпрессии: " + maxDecom[2] + " мин.\n");
                    }
                }
                case 3 -> {
                    SemiClosedLoop semiClosedLoop = new SemiClosedLoop();
                    System.out.println("Укажите длительность погружения в минутах (от 30 до 420) ");
                    int time = checkCorrectNumber(30, 420);
                    System.out.println("Укажите глубину погружения в метрах (от 2 до 50)");
                    int depth = checkCorrectNumber(2, 50);
                    System.out.println("Укажите запас");//200;250;300
                    double zapas = checkCorrectZapas();
                    double[] oborydovanie = semiClosedLoop.printValuePercentPressure(depth, time, zapas, 0);
                    if (oborydovanie[0] == -1)
                        System.out.println("нет подходящего оборудования");
                }
                case 4 -> {
                    Object data[][];
                    double maxDecom[] = new double[]{0};
                    boolean flag = true;
                    int difficulty;
                    ClosedLoop closedLoop = new ClosedLoop();
                    SemiClosedLoop semiClosedLoop = new SemiClosedLoop();
                    System.out.println("Укажите рабочую глубину (от 12 до 60 метров)");
                    int depth = checkCorrectNumber(12, 60);
                    System.out.println("Укажите время работы на грунте (от 30 до 360 минут)");
                    int time = checkCorrectNumber(30, 360);
                    System.out.println("Укажите запас");//200;250;300
                    double zapas = checkCorrectZapas();
                    //System.out.println("Выберите тип работы нормальная/тяжелая");
                    difficulty = selectDifficulty2();
                    double weatherConditions = checkWeatherConditions();
                    System.out.println("погодные условаия добавили процент сложности равный  " + weatherConditions * 100);
                    double[] percentPressure = semiClosedLoop.printValuePercentPressure(depth, time, zapas, weatherConditions);
                    //System.out.println(percentPressure[0]+" "+percentPressure[1]+" "+percentPressure[2]);

                    if (!Arrays.equals(percentPressure, new double[]{-1, -1})) {
                        // Загрузить файл Excel
                        data = convertToMatrix(table);
                        int vodorod = (int) (100 - percentPressure[0]);
                        double normalDepth = 0;
                        for (Object[] datum : data) {
                            if (((depth - (Double) datum[0] < 3) && (depth - (Double) datum[0] >= 0))) {
                                normalDepth = (Double) datum[0];
                            }
                            if (((depth * vodorod / 80 - (Double) datum[0] < 3) && (depth * vodorod / 80 - (Double) datum[0] >= 0))) {
                                if ((Double) datum[1] + (Double) datum[2] < time) {
                                    if (flag) {
                                        maxDecom = new double[]{(Double) datum[0], (Double) datum[1], (Double) datum[2]};
                                        flag = false;
                                    } else if ((Double) datum[1] + (Double) datum[2] > maxDecom[1] + maxDecom[2]) {
                                        maxDecom = new double[]{(Double) datum[0], (Double) datum[1], (Double) datum[2]};
                                    }
                                }
                            }
                        }

                        System.out.println("Учитывая декомпрессионные обязательсва: ");
                        if (maxDecom[0] == 0) {
                            System.out.println("Для заданной системы декомпрессионные обязательсва не нужны.");
                        } else {
                            System.out.print("Глубина спуска: " + normalDepth + " м., " + "эквивалент глубины: " + maxDecom[0] + " м.(воздух).\n" + "Экспозиция на грунте: " +
                                    maxDecom[1] + " мин.\n" + "Общее время декомпрессии: " + maxDecom[2] + " мин.\n");
                        }


                        System.out.printf("Процент содержания кислорода смеси %.2f \n", percentPressure[0] * 62, 5);
                    } else {
                        System.out.println("Нет подходящего снаряжения.");
                    }
                    System.out.println("");
                    System.out.println("Для замкнутого цикла");
                    closedLoop.printVolumePlusPressure(time, difficulty, zapas);
                }
                default -> {
                    System.out.println("Плыви Рыбка");
                    exit = false;
                }
            }
            System.out.println();
        } while (exit);
    }

    private static double checkWeatherConditions() {
        System.out.println("Вода холодная?");
        System.out.println("1 - да");
        System.out.println("2 - нет");
        boolean cold = true;
        int type = checkCorrectNumber(1, 2);
        switch (type) {
            case 1 -> {
                cold = true;
            }
            case 2 -> {
                cold = false;
            }
        }
        System.out.println("В воде есть опасные предметы?");
        System.out.println("1 - да");
        System.out.println("2 - нет");
        boolean danger = true;
        type = checkCorrectNumber(1, 2);
        switch (type) {
            case 1 -> {
                danger = true;
            }
            case 2 -> {
                danger = false;
            }
        }
        System.out.println("Есть химические загрязнения?");
        System.out.println("1 - да");
        System.out.println("2 - нет");
        boolean radiation = true;
        type = checkCorrectNumber(1, 2);
        switch (type) {
            case 1 -> {
                radiation = true;
            }
            case 2 -> {
                radiation = false;
            }
        }
        System.out.println("Есть течение?");
        System.out.println("1 - да");
        System.out.println("2 - нет");
        boolean course = true;
        type = checkCorrectNumber(1, 2);
        switch (type) {
            case 1 -> {
                course = true;
            }
            case 2 -> {
                course = false;
            }
        }
        if (course || danger) {
            return 0.15;
        } else if (radiation || cold) {
            return 0.1;
        } else {
            return 0;
        }
    }

    public static int selectDifficulty3() {
        System.out.println("Выберите сложность погружения:");
        System.out.println("1 - легкое");
        System.out.println("2 - среднее");
        System.out.println("3 - тяжелое");
        return checkCorrectNumber(1, 3);
    }

    public static int selectDifficulty2() {
        System.out.println("Выберите сложность погружения:");
        System.out.println("1 - нормальное");
        System.out.println("2 - тяжелое");
        return checkCorrectNumber(1, 2);
    }


    public static int checkCorrectNumber(int min, int max) {
        while (true) {
            String volume = in.nextLine();
            if (checkInt(volume)) {
                int number = Integer.parseInt(volume);
                if (number >= min && number <= max)
                    return number;
            }
            System.out.println("Введено неправильное значение!");
            System.out.println("Введите число в диапазоне от " + min + " до " + max);
        }
    }

    public static int checkCorrectNumberInArr(int[] arr) {
        List<Integer> intList = new ArrayList<>();
        for (int num : arr) {
            intList.add(num);
        }
        while (true) {
            String volume = in.nextLine();
            if (checkInt(volume)) {
                int number = Integer.parseInt(volume);
                if (intList.contains(number))
                    return number;
            }
            System.out.println("Введено неправильное значение!");
        }
    }

    public static boolean checkInt(String string) {
        boolean flag = true;
        if (string.length() > 0) {
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) >= 48 && string.charAt(i) <= 57) {
                } else flag = false;
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public static Object[][] convertToMatrix(String table) {
        int sheetIndex = 0; // индекс листа в файле Excel
        int numRows; // количество строк в таблице
        int numCols; // количество столбцов в таблице
        Object[][] data; // двумерный массив для хранения данных таблицы

        // загрузка файла Excel
        try (FileInputStream file = new FileInputStream(new File(table))) {
            Workbook workbook = WorkbookFactory.create(file);

            // получение листа
            Sheet sheet = workbook.getSheetAt(sheetIndex);

            // получение размеров таблицы
            numRows = sheet.getPhysicalNumberOfRows();
            numCols = sheet.getRow(0).getPhysicalNumberOfCells();

            // создание двумерного массива
            data = new Object[numRows][numCols];

            // заполнение массива данными из таблицы
            for (int i = 0; i < numRows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < numCols; j++) {
                    Cell cell = row.getCell(j);
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i][j] = cell.getNumericCellValue();
                            break;
                        case BOOLEAN:
                            data[i][j] = cell.getBooleanCellValue();
                            break;
                        default:
                            data[i][j] = null;
                    }
                }
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static double checkCorrectZapas() {
        System.out.println("выберите ваш вариант:");
        System.out.println("1 - 30%");
        System.out.println("2 - 20%");
        int type = checkCorrectNumber(1, 2);
        switch (type) {
            case 1 -> {
                return 0.7;
            }
            case 2 -> {
                return 0.8;
            }
        }
        return 0.7;
    }
}


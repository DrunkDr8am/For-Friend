package Start;

import FirstCucle.ClosedLoop;
import SeconCucle.SemiClosedLoop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.util.*;


public class Main {
    static Scanner in = new Scanner(System.in);
    static String table = "C:\\Users\\Никита\\Desktop\\НИР\\2023\\Table.xlsx";

    public static void main(String[] args) throws IOException {
        boolean exit = true;
        do {
            System.out.println("Выберите тип погружения");
            System.out.println("1 - замкнутый цикл");
            System.out.println("2 - полу-замкнутый цикл");
            System.out.println("3 - обратная функция для полу-замкнутого цикла  ");
            System.out.println("4 - выход");
            int type = checkCorrectNumber(1, 4);
            switch (type) {
                case 1 -> {
                    int difficulty = selectDifficulty();
                    System.out.println("Введите объем НДГС (1 или 2 литра)");//1 или 2 литра
                    int balloonVolume = checkCorrectNumber(1, 2);
                    System.out.println("Укажите давление ДГС (от 200 до 300 паскалей)");//200;250;300
                    int initialPressure = checkCorrectNumber(200, 300);
                    ClosedLoop closedLoop = new ClosedLoop();
                    closedLoop.printTime(balloonVolume, initialPressure, difficulty);
                }
                case 2 -> {
                    Object data[][];
                    double maxDecom[] = new double[]{0};
                    boolean flag = true;
                    SemiClosedLoop semiClosedLoop = new SemiClosedLoop();
                    System.out.println("Введите объем НДГС (от 5 до 10 литров)");//от 5 до 10 литров
                    int value = checkCorrectNumber(5, 10);
                    System.out.println("Укажите содержание кислорода (от 30 до 60 процентов)");//от 40% до 60%
                    int percent = checkCorrectNumber(30, 60);
                    System.out.println("Укажите давление ДГС (от 200 до 300 паскалей)");//200;250;300
                    int pressure = checkCorrectNumber(200, 300);
                    double[] timePlusDepth = semiClosedLoop.printDepthAndTime(value, percent, pressure);
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
                    semiClosedLoop.printValuePercentPressure(depth, time);
                }
                default -> {
                    System.out.println("Плыви Рыбка");
                    exit = false;
                }
            }
            System.out.println();
        } while (exit);
    }

    public static int selectDifficulty () {
        System.out.println("Выберите сложность погружения");
        System.out.println("1 - легкое");
        System.out.println("2 - среднее");
        System.out.println("3 - сложное");
        return checkCorrectNumber(1, 3);
    }

    public static int checkCorrectNumber ( int min, int max){
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

    public static boolean checkInt (String string){
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
}
package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        dc.addNewDishes(dishType, dishName);
    }

    private static void generateDishCombo() {
        try {
            System.out.println("Начинаем конструировать обед...");

            System.out.println("Введите количество наборов, которые нужно сгенерировать:");
            int numberOfCombos = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
            String nextItem = scanner.nextLine();

            List<String> dishTypes = new ArrayList<>();
            while (!nextItem.isEmpty()) {
                if (dc.isValidDishType(nextItem)) {
                    if (dishTypes.contains(nextItem)) {
                        System.out.println("Данная категория уже выбрана");
                    } else {
                        dishTypes.add(nextItem);
                    }
                } else {
                    if (dc.dishMenuIsEmpty()) {
                        System.out.println("Список меню пуст!");
                        return;
                    }
                    System.out.printf("Категории %s нет в меню, попробуйте еще раз\n", nextItem);
                }
                nextItem = scanner.nextLine();
            }

            dc.getCombinationDishes(numberOfCombos, dishTypes);
        } catch (InputMismatchException e) {
            System.out.println("Для наборов, которые необходимо сконфигурировать, допустимо вводить только цифры");
            scanner = new Scanner(System.in);
        }
    }
}

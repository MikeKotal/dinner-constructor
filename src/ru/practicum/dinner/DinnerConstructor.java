package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.random.RandomGenerator;

public class DinnerConstructor {

    private final HashMap<String, List<String>> dishes = new HashMap<>();

    public void addNewDishes(String dishType, String dishName) {
        if (dishes.containsKey(dishType)) {
            dishes.get(dishType).add(dishName);
        } else {
            List<String> dishNames = new ArrayList<>();
            dishNames.add(dishName);
            dishes.put(dishType, dishNames);
        }
    }

    public void getCombinationDishes(int numberOfCombos, List<String> dishTypes) {
        for (int i = 0; i < numberOfCombos; i++) {
            StringBuilder builder = new StringBuilder();
            dishTypes.forEach(dish -> builder.append(dishes.get(dish)
                    .get(RandomGenerator.getDefault().nextInt(dishes.get(dish).size()))).append(","));
            builder.deleteCharAt(builder.length() - 1);
            System.out.printf("Комбинация №%d: %s.\n", i + 1, builder);
        }
    }

    public Boolean isValidDishType(String dish) {
        return dishes.containsKey(dish);
    }

    public Boolean dishMenuIsEmpty() {
        return dishes.isEmpty();
    }
}

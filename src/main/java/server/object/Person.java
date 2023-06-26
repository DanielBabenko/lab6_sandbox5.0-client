package server.object;


import server.object.enums.Color;

import java.io.Serializable;

public class Person implements Serializable {
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final String birthday; //Поле не может быть null
    private double height; //Значение поля должно быть больше 0
    private final Color eyeColor; //Поле не может быть null

    public Person(String name, Color eyeColor, double height, String birthday) {
        this.birthday = birthday;
        this.name = name;
        this.height = height;
        this.eyeColor = eyeColor;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return name;
    }
}

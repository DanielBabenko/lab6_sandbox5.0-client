package client;

import client.exceptions.*;
import server.object.Coordinates;
import server.object.LabWork;
import server.object.Person;
import server.object.enums.Color;
import server.object.enums.Difficulty;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

public class SendObject {

    private LinkedList<LabWork> labs;

    private LabWork labWork;

    public SendObject(LinkedList<LabWork> labWorks) {
        this.labs = labWorks;
    }

    public void setLabWork(LabWork labWork) {
        this.labWork = labWork;
    }

    public LabWork getLabWork() {
        return labWork;
    }

    public void start(BufferedReader b) {
        try {
            this.labWork = adder(b);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    LabWork adder(BufferedReader b) throws IOException {
        System.out.println("Введите название Лабораторной работы: ");
        String name = null;
        while (name == null) {
            try {
                name = b.readLine().trim();
                if (name == null || name.isEmpty()) {
                    throw new RuntimeException("Пустая строка не может именем лабораторной работы. Попробуй ещё раз.");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                name = null;
            }
        }
        Coordinates coordinates = addCoordinates(b);
        Person author = addPerson(b);
        int minimalPoint = addMinimalPoint(b);
        Long tunedInWorks = addTunedInWorks(b);
        Difficulty difficulty = addDifficulty(b);
        LabWork e = new LabWork(generateId(), name, minimalPoint, tunedInWorks, difficulty, coordinates, author);
        return e;
    }

    /**
     * Доп метод для {@link#addElement(String)}: добавить сложность
     *
     * @return
     * @throws IOException
     */
    private Difficulty addDifficulty(BufferedReader b) {
        System.out.println("Введите сложность работы (VERY_EASY, EASY, VERY_HARD, IMPOSSIBLE, HOPELESS:");
        String difficulty = checkOnEnum(Difficulty.class,b);
        return Difficulty.valueOf(difficulty);
    }

    /**
     * Метод генерирует id нового объекта
     *
     * @return
     */
    public int generateId() {
        Map<Integer, LabWork> labs = new HashMap<>();

        if (this.labs != null) {
            for (LabWork lab : this.labs)
                labs.put((int) lab.getId(), lab);
            labs = sortByKeys(labs);
            Integer size = labs.size();
            for (Map.Entry<Integer, LabWork> entry : labs.entrySet()) {
                if (size.equals(entry.getKey())) {
                    size += 1;
                }
            }
            return size;
        }


        return 0;
    }

    /**
     * Сортирует коллекцию объектов по ключу.
     *
     * @param unsortedMap
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> Map<K, V> sortByKeys(Map<K, V> unsortedMap) {
        return new TreeMap<>(unsortedMap);
    }


    /**
     * Метод обрабатывает поле
     *
     * @return
     */
    private Long addTunedInWorks(BufferedReader b) throws IOException {
        Long tunedInWorks = null;
        boolean flag = false;
        System.out.println("Введите tunedInWorks(1-1000):");
        String commandValue = b.readLine().trim();
        if (!commandValue.trim().isEmpty())
            while (!flag) {
                try {
                    if (commandValue != null) {
                        String num = commandValue;
                        commandValue = null;
                        tunedInWorks = Long.parseLong(num);
                    } else {
                        System.out.println("Введите tunedInWorks(1-1000):");
                        tunedInWorks = checkOnLong(b);
                    }
                    if (tunedInWorks > 0 && tunedInWorks < 1001) {
                        flag = true;
                    }
                    //commandValue = null;
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage().trim());
                }
            }


        return tunedInWorks;
    }

    private int addMinimalPoint(BufferedReader b) throws IOException {
        int minimalPoint = 0;
        boolean flag = false;
        while (!flag) {
            System.out.println("Введите minimalPoint(1-1000):");
            minimalPoint = checkOnInt(b);
            if (minimalPoint > 0 && minimalPoint < 1001)
                flag = true;
            else
                System.out.println("Вы ввели неккоректное число! Число не может быть отрицательным, или равно нулю.");
        }

        return minimalPoint;
    }

    private Person addPerson(BufferedReader b) throws IOException {
        boolean flag = false;
        String name = null;
        while (!flag) {
            System.out.println("Введите имя автора: ");
            name = b.readLine().trim();
            if (!name.isEmpty())
                flag = true;
            else
                System.out.println("Поле имя автора не может быть пустым");
        }

        flag = false;
        float height = 0;
        while (!flag) {
            System.out.println("Введите рост автора: ");
            Float h = checkOnFloat(b);
            if (h.isInfinite())
                throw new IllegalArgumentException("Некорректный ввод. Повторите попытку.(height)");
            if (h < 272 && h > 0) {
                flag = true;
                height = h;
            } else {
                System.out.println("Вы ввели неправильный рост! Доступно в интервале от 0 до 272.");
            }
        }


        String date = null;
        LocalDate birthday = null;
        while (date == null) {
            try {
                System.out.println("Введите дату рождения автора (гггг-мм-дд): ");
                birthday = LocalDate.parse(b.readLine().trim());
                String[] dateSplit = birthday.toString().split("-");
                if (Integer.parseInt(dateSplit[0]) >= 1907 && Integer.parseInt(dateSplit[0]) < 2015)
                    date = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0];
                else
                    System.out.println("Ты не мог родиться в такой год. Самый старый человек родился в 1907 году.Мария Браньяс Морера");
            } catch (DateTimeException e) {
                System.out.println("Невалидный ввод данных, повторите попытку.(time)");
            }
        }

        System.out.println("Введите цвет глаз автора (GREEN, RED, ORANGE, WHITE, BLACK): ");

        String color = checkOnEnum(Color.class,b);

        return new Person(name, Color.valueOf(color), height, date);
    }

    private Coordinates addCoordinates(BufferedReader b) throws IOException {
        boolean flag = false;
        System.out.println("Введите координату x: ");
        int x = checkOnInt(b);
        double y = 0;
        while (!flag) {
            try {
                System.out.println("Введите координату y: ");
                y = checkOnDouble(b);

                if (y < -184) {
                    throw new InvalidFieldY("Field Y must be > -184 and can not be NULL");
                }
                flag = true;
            } catch (InvalidFieldY e) {
                System.out.println(e.getMessage());
            }
        }

        return new Coordinates(x, y);
    }

    private double checkOnDouble(BufferedReader b) throws IOException {
        double y = 0;
        boolean flag = false;
        while (!flag) {
            try {
                y = Double.parseDouble(b.readLine().trim());
                flag = true;
            } catch (NumberFormatException | IOException e) {
                flag = false;
                System.out.println("Невалидный ввод данных, повторите попытку.");
            }
        }

        return y;
    }

    private Long checkOnLong(BufferedReader b) throws IOException {
        long y = 0;
        boolean flag = false;
        while (!flag)
            try {
                y = Long.parseLong(b.readLine().trim());
                flag = true;
            } catch (NumberFormatException e) {
                flag = false;
            } catch (IOException e) {
                System.out.println("Невалидный ввод данных, повторите попытку.");
            }
        return y;
    }

    /**
     * Метод проверяет является ли число типом {@link Integer}
     *
     * @return
     */
    private int checkOnInt(BufferedReader b) throws IOException {
        int y = 0;
        boolean flag = false;
        while (!flag)
            try {
                y = Integer.parseInt(b.readLine().trim());
                flag = true;
            } catch (NumberFormatException | IOException e) {
                flag = false;
                System.out.println("Невалидный ввод данных, повторите попытку.");
            }
        return y;
    }

    /**
     * Метод проверяет является ли число типом {@link Enum}
     *
     * @return
     */
    private String checkOnEnum(Class className, BufferedReader b) {
        boolean flag = false;
        String enumValue = null;
        while (!flag) {
            try {
                enumValue = b.readLine().toUpperCase().trim();
                Enum.valueOf(className, enumValue);
                flag = true;
            } catch (IllegalArgumentException | IOException e) {
                flag = false;
                System.out.println("Невалидный ввод данных, повторите попытку.(enum)");
            }
        }

        return enumValue;
    }

    /**
     * Метод проверяет является ли число типом {@link Float}
     *
     * @return
     */
    private float checkOnFloat(BufferedReader b) throws IOException {
        float y = 0;
        boolean flag = false;
        while (!flag)
            try {
                String cmd = b.readLine().trim();
                if (cmd != null) {
                    y = Float.parseFloat(cmd);
                    flag = true;
                }
            } catch (NumberFormatException | IOException e) {
                flag = false;
                System.out.println("Невалидный ввод данных, повторите попытку.(float)");
            }
        return y;
    }

}

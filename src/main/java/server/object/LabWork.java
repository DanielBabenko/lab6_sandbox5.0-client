package server.object;



import server.object.enums.Difficulty;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

/**
 * The object.LabWork is main object for console App
 */

public class LabWork implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates = new Coordinates(); //Поле не может быть null
    //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int minimalPoint; //Значение поля должно быть больше 0
    private Integer tunedInWorks;
    private Difficulty difficulty; //Поле может быть null
    private Person author; //Поле не может быть null

    private String creationDateString;  //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    public LabWork(int id, String name, int minimalPoint, Long tunedInWorks, Difficulty difficulty, Coordinates coordinates, Person author, String creationDateString) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.coordinates = Objects.requireNonNull(coordinates);
        this.minimalPoint = minimalPoint;
        this.author = Objects.requireNonNull(author);
        this.difficulty = Objects.requireNonNull(difficulty);
        if (tunedInWorks != null)
            this.tunedInWorks = tunedInWorks.intValue();

        if (creationDateString.isEmpty()) {
            ZonedDateTime date = ZonedDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.
                    ofPattern(DateTimeFormatter.
                            ofPattern("MM/dd/yyyy - HH:mm:ss Z").
                            format(date));
            this.creationDateString = date.format(formatter);
        } else {
            this.creationDateString = creationDateString;
        }
    }

    public LabWork(int id, String name, int minimalPoint,Long tunedInWorks, Difficulty difficulty, Coordinates coordinates, Person author) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.coordinates = Objects.requireNonNull(coordinates);
        this.minimalPoint = minimalPoint;
        this.author = Objects.requireNonNull(author);
        this.difficulty = Objects.requireNonNull(difficulty);

        if (tunedInWorks != null)
            this.tunedInWorks = tunedInWorks.intValue();

        ZonedDateTime date = ZonedDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.
                ofPattern(DateTimeFormatter.
                        ofPattern("MM/dd/yyyy - HH:mm:ss Z").
                        format(date));
        this.creationDateString = date.format(formatter);
    }

    public LabWork(String name, int minimalPoint, Long tunedInWorks, Difficulty difficulty, Coordinates coordinates, Person author) {
        this.name = Objects.requireNonNull(name);
        this.coordinates = Objects.requireNonNull(coordinates);
        this.minimalPoint = minimalPoint;
        this.author = Objects.requireNonNull(author);
        this.difficulty = Objects.requireNonNull(difficulty);
        ZonedDateTime date = ZonedDateTime.now();

        if (tunedInWorks != null)
            this.tunedInWorks = tunedInWorks.intValue();

        DateTimeFormatter formatter = DateTimeFormatter.
                ofPattern(DateTimeFormatter.
                        ofPattern("MM/dd/yyyy - HH:mm:ss Z").
                        format(date));
        this.creationDateString = date.format(formatter);
    }

    public ZonedDateTime getCreationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
        formatter = formatter.withLocale(Locale.US);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        ZonedDateTime date = ZonedDateTime.parse(this.creationDateString, formatter);
        return date;
    }


    public String getCreationDateString() {
        return creationDateString;
    }

    public void setDifficulty(Difficulty diff) {
        this.difficulty = diff;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public Person getAuthor() {
        return this.author;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public void setMinimalPoint(int minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public int getMinimalPoint() {
        return this.minimalPoint;
    }

    public void setTunedInWorks(Integer tunedInWorks) {
        this.tunedInWorks = tunedInWorks;
    }

    public Integer getTunedInWorks() {
        return this.tunedInWorks;
    }

    @Override
    public String toString() {
        return "id:" + id + "\n" +
                "name:" + name + "\n" +
                "creationDate:" + creationDateString + "\n" +
                "coordinates:" + "\n" +
                "[x=" + getCoordinates().getX() + "\n" +
                "y=" + getCoordinates().getY() + "]" + "\n" +
                "minimalPoint=" + minimalPoint + "\n" +
                "tunedInWorks=" + tunedInWorks + "\n" +
                "difficulty=" + difficulty + "\n" +
                "author:" + "\n" +
                "[name=" + author.getName() + "\n" +
                "birthday=" + author.getBirthday() + "\n" +
                "height=" + author.getHeight() + "\n" +
                "eyeColor=" + author.getEyeColor() + "]\n" +
                "---------------------" + "\n";
    }
}


package server.object.enums;

public enum Difficulty {
    VERY_EASY("очень легко",1),
    EASY("легко",2),
    VERY_HARD("очень трудно",3),
    IMPOSSIBLE("невозможно",4),
    HOPELESS("безнадёжно",5);

    String name;
    int level;

    Difficulty(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}

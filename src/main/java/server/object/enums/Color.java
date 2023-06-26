package server.object.enums;

public enum Color {
    GREEN("зелёный"),
    RED("красный"),
    BLACK("чёрный"),
    ORANGE("оранжевый"),
    WHITE("белый");

    String name;

    Color(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
package ru.javawebinar.basejava.model;

public class TextSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

    private String text;

    public TextSection(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection textSection = (TextSection) o;

        return text.equals(textSection.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public String toString() {
        return text;
    }
}

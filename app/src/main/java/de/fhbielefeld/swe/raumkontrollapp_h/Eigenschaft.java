package de.fhbielefeld.swe.raumkontrollapp_h;

public class Eigenschaft {

    private String name;
    private int anzahl;
    private String zustand;

    public Eigenschaft (String name, int anzahl, String zustand) {
        setName(name);
        setAnzahl(anzahl);
        setZustand(zustand);
    }

    public String getZustand() {
        return zustand;
    }

    public void setZustand(String zustand) {
        this.zustand = zustand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
}

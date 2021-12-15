package de.fhbielefeld.swe.raumkontrollapp_h;

public class Eigenschaft {

    private int Anzahl;
    private String RaumName;
    private String zustand;

    public String getZustand() {
        return zustand;
    }

    public void setZustand(String zustand) {
        this.zustand = zustand;
    }

    public String getRaumName() {
        return RaumName;
    }

    public void setRaumName(String raumName) {
        RaumName = raumName;
    }

    public int getAnzahl() {
        return Anzahl;
    }

    public void setAnzahl(int anzahl) {
        Anzahl = anzahl;
    }
}

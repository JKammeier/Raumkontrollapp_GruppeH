package de.fhbielefeld.swe.raumkontrollapp_h;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Raum implements Parcelable {

    private String raumNr;
    private ArrayList<Eigenschaft> eigenschaftListe;

    // Funktionen, die benötigt werden, damit Raum ein Parcelable ist
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(raumNr);
    }

    public static final Parcelable.Creator<Raum> CREATOR = new Parcelable.Creator<Raum>() {
        @Override
        public Raum createFromParcel(Parcel in) {
            return new Raum(in);
        }

        @Override
        public Raum[] newArray(int size) {
            return new Raum[size];
        }
    };

    private Raum(Parcel in) {
        raumNr = in.readString();
    }
    //Ende des Parcelable Setups

    public Raum() {
        //setRaumNr("default");
    }

    public Raum(String raumNr) {
        setRaumNr(raumNr);
        eigenschaftListe = new ArrayList<Eigenschaft>();
    }

    public String getRaumNr() {
        return raumNr;
    }

    public void setRaumNr(String raumNr) {
        this.raumNr = raumNr;
    }

    @Override
    public String toString() {
        return getRaumNr();
    }
}

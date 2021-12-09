package de.fhbielefeld.swe.raumkontrollapp_h;

import android.os.Parcel;
import android.os.Parcelable;

public class Raum implements Parcelable {
    String nr;

    // Funktionen, die benötigt werden, damit Raum ein Parcelable ist
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nr);
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
        nr = in.readString();
    }
    //Ende des Parcelable Setups

    public Raum(String nr) {
        this.nr = nr;
    }
}

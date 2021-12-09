package de.fhbielefeld.swe.raumkontrollapp_h;

import android.os.Parcel;
import android.os.Parcelable;

public class Raum implements Parcelable {
    String nr;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nr);
    }
}

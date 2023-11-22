package com.example.proyecto_integrador.data

import android.os.Parcel
import android.os.Parcelable

data class Detalle(val picPath: Int?, val name: String?, val other: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        if (picPath != null) {
            parcel.writeInt(picPath)
        }
        parcel.writeString(name)
        parcel.writeString(other)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Detalle> {
        override fun createFromParcel(parcel: Parcel): Detalle {
            return Detalle(parcel)
        }

        override fun newArray(size: Int): Array<Detalle?> {
            return arrayOfNulls(size)
        }
    }
}

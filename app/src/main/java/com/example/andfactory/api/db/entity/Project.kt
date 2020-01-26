package com.example.andfactory.api.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String?,
    val url: String?
)

//@Entity(tableName = "project")
//class Project (
//    var name: String?,
//    var url: String?
//): Parcelable{
//    @PrimaryKey(autoGenerate = true)
//    var id: Long? = null
//
//    constructor(parcel: Parcel) : this(
//        parcel.readString(),
//        parcel.readString()
//    ) {
//        id = parcel.readValue(Long::class.java.classLoader) as? Long
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(name)
//        parcel.writeString(url)
//        parcel.writeValue(id)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Project> {
//        override fun createFromParcel(parcel: Parcel): Project {
//            return Project(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Project?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
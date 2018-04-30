package com.smoo182.wguplanner.data.datatypes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Note {

    @PrimaryKey (autoGenerate = true)
    @NonNull
    Integer id;

    @NonNull
    @ColumnInfo(name = "name")
    String name;
    @NonNull
    @ColumnInfo(name = "content")
    String content;

    @NonNull
    @ColumnInfo(name = "courseId")
    String courseId;
}





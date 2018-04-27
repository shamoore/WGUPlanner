package com.smoo182.wguplanner.data.datatypes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Note {
    @PrimaryKey
    @NonNull
    Integer id;
    @NonNull
    String name;
    @NonNull
    String content;
}

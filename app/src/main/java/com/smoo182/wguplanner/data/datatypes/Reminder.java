package com.smoo182.wguplanner.data.datatypes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(primaryKeys = { "content", "type"})
public class Reminder {
    @NonNull
    private String content;
    private String date;
    @NonNull
    private String type;


    public Reminder(@NonNull String content, String date, String type) {
        this.content = content;
        this.date = date;
        this.type = type;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}







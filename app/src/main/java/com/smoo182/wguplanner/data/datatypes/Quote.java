package com.smoo182.wguplanner.data.datatypes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Quote {
    @PrimaryKey
    @NonNull
    private int quoteId;
    private String content;
    private String author;

    public Quote(String content, String author, int id) {
        this.quoteId = id;
        this.content = content;
        this.author = author;
    }

    public int getQuoteId(){ return quoteId; }

    public void setQuoteId(int id) { this.quoteId = id; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

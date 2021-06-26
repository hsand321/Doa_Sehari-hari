package com.example.doasehari_hari;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "doa_table")
public class Doa {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String arabic;
    private String latin;
    private String translation;

    public Doa(int id, String title, String arabic, String latin, String translation) {
        this.id = id;
        this.title = title;
        this.arabic = arabic;
        this.latin = latin;
        this.translation = translation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getLatin() {
        return latin;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}

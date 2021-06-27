package com.example.doasehari_hari.database;

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

    public Doa( String title, String arabic, String latin, String translation) {
        this.title = title;
        this.arabic = arabic;
        this.latin = latin;
        this.translation = translation;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArabic() {
        return arabic;
    }

    public String getLatin() {
        return latin;
    }

    public String getTranslation() {
        return translation;
    }




}

package com.example.doasehari_hari.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Doa.class}, version = 2)
public abstract class DoaDatabase extends RoomDatabase {

    private static DoaDatabase instance;

    public abstract DoaDao doaDao();

    public static synchronized  DoaDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DoaDatabase.class, "doa_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new DailyDbAsyncTask(instance).execute();
        }
    };
    private static class DailyDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private DoaDao doaDao;
        private DailyDbAsyncTask(DoaDatabase db){
            doaDao = db.doaDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            doaDao.insert(new Doa("Doa Mau Makan","اَللّٰهُمَّ بَارِكْ لَنَا فِيْمَا رَزَقْتَنَا وَقِنَا عَذَابَ النَّارِ","Alloohumma barik lanaa fiimaa razatanaa waqinaa 'adzaa bannar","Ya Allah, berkahilah kami dalam rezeki yang telah Engkau berikan kepada kami dan peliharalah kami dari siksa api neraka"));
            doaDao.insert(new Doa("Doa Sesudah Makan","اَلْحَمْدُ ِللهِ الَّذِىْ اَطْعَمَنَا وَسَقَانَا وَجَعَلَنَا مُسْلِمِيْنَِ","Alhamdu lillaahil ladzii ath'amanaa wa saqoonaa wa ja'alnaa muslimiin","Segala puji bagi Allah yang telah memberi makan kami dan minuman kami, serta menjadikan kami sebagai orang-orang islam"));
            return null;
        }
    }
}

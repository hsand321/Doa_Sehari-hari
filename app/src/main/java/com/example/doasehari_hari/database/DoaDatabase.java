package com.example.doasehari_hari.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.doasehari_hari.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Database(entities = {Doa.class}, version = 2)
public abstract class DoaDatabase extends RoomDatabase {

    private static DoaDatabase instance;

    private static Context activity;

    public abstract DoaDao doaDao();

    public static synchronized  DoaDatabase getInstance(Context context){

        activity = context.getApplicationContext();

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
            doaDao.insert(new Doa("Doa Selamat Dunia Akhirat","اَللّٰهُمَّ اِنَّا نَسْأَلُكَ سَلاَمَةً فِى الدِّيْنِ وَعَافِيَةً فِى الْجَسَدِ وَزِيَادَةً فِى الْعِلْمِ وَبَرَكَةً فِى الرِّزْقِ وَتَوْبَةً قَبْلَ الْمَوْتِ وَرَحْمَةً عِنْدَ الْمَوْتِ وَمَغْفِرَةً بَعْدَ الْمَوْتِِ","Allohumma inna nas-aluka salaamatan fid diini wa 'aafiyatan fil jasadi wa ziyaadatan fil 'ilmi wa barokatan fir rizqi wa taubatan qoblal mauti wa rohmatan 'indal mauti wa maghfirotan ba'dal mauti","Ya Allah kami memohon kepadaMu keselamatan dalam agama, dan kesejahteraan/kesegaran pada tubuh dan penambahan ilmu, dan keberkahan rizqi, serta taubat sebelum mati dan rahmat di waktu mati, dan keampunan sesudah mati"));
            doaDao.insert(new Doa("Doa Ketika Mengunjungi Orang Sakit","اللَّهُمَّ رَبَّ النَّاسِ مُذْهِبَ الْبَاسِ اشْفِ أَنْتَ الشَّافِى لاَ شَافِىَ إِلاَّ أَنْتَ ، شِفَاءً لاَ يُغَادِرُ سَقَمًا","allahumma rabban naas mudzhibal ba’si isyfi antasy-syaafii laa syafiya illaa anta syifaa’an laa yughaadiru saqoman","Ya Allah Wahai Tuhan segala manusia, hilangkanlah penyakitnya, sembukanlah ia. (Hanya) Engkaulah yang dapat menyembuhkannya, tidak ada kesembuhan melainkan kesembuhan dariMu, kesembuhan yang tidak kambuh lagi"));
            fillWithStartingData(activity);
            return null;
        }
    }
    private static void fillWithStartingData(Context context){
        DoaDao dao = getInstance(context).doaDao();
        JSONArray doas = loadJSONArray(context);
        try {
            for (int i=0;i<doas.length();i++){
                JSONObject doa = doas.getJSONObject(i);
                String judul = doa.getString("title");
                String arab = doa.getString("arabic");
                String baca = doa.getString("latin");
                String terjemahan = doa.getString("translation");

                dao.insert(new Doa(judul, arab, baca, terjemahan));

            }
        }catch (JSONException e){

        }
    }
    private static JSONArray loadJSONArray(Context context){
        StringBuilder builder = new StringBuilder();
        InputStream in = context.getResources().openRawResource(R.raw.doaseharihari);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
         String line;
         try {
             while ((line = reader.readLine()) !=null){
                 builder.append(line);
             }
             JSONObject json = new JSONObject(builder.toString());
             return json.getJSONArray("data");
         } catch (IOException| JSONException exception){
             exception.printStackTrace();
         }
         return null;
    }
}

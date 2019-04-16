package com.example.yun.myapplication.testClasses;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yun.myapplication.DaoAccess;
import com.example.yun.myapplication.Entities.Favourites;
import com.example.yun.myapplication.Entities.Medic;
import com.example.yun.myapplication.Retrofit.NetworkService;
import com.example.yun.myapplication.R;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestAPITestActivity extends AppCompatActivity {



    private static final String DATABASE_NAME = "favourites_db";
    private FavouritesDatabase favouritesDatabase;

    @Database(entities = {Medic.class}, version = 1, exportSchema = false)
    public static abstract class FavouritesDatabase extends RoomDatabase {
        public abstract DaoAccess daoAccess() ;
    }

    Button button;

    /**
     * Testing
     *
     * @param savedInstanceState
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        favouritesDatabase = Room.databaseBuilder(getApplicationContext(),
                FavouritesDatabase.class, DATABASE_NAME)
                .build();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);
        button.setOnClickListener(v -> {
            NetworkService.getInstance()
                    .getJSONApi()
                    .getAllPosts()
                    .enqueue(new Callback<List<Medic>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<Medic>> call, @NonNull Response<List<Medic>> response) {
                            List<Medic> medics = response.body();
                            textView.append(medics.get(0).getLastName());
                            //Favourites favourite = new Favourites(medics.get(0));
                            //favouritesDatabase.daoAccess().insertFavourite(medics.get(0));
                            //textView.append(favouritesDatabase.daoAccess().getAll().getValue().get(0).getLastName());
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Medic>> call, @NonNull Throwable t) {
                            textView.append("Error occurred while getting request!");
                            t.printStackTrace();
                        }
                    });
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        new HttpRequestTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Заполнение меню; добавляются пункты меню в action bar, если он присутствует.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Это обработчик нажатия на пукт меню action bar. Аction bar будет
        // автоматически обрабатывать нажатия Home/Up кнопки, до тех пор
        // пока вы вы определите их действия в родительском activity в AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            new HttpRequestTask().execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Фрагмент, содержащий простое представление.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, Medic> {
        @Override
        protected Medic doInBackground(Void... params) {
            try {
                final String url = "https://pocket-medic.herokuapp.com/medic?id=1";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Medic medic = restTemplate.getForObject(url, Medic.class);
                return medic;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Medic medic) {
            TextView greetingIdText = findViewById(R.id.id_value);
            TextView greetingContentText = findViewById(R.id.content_value);
            greetingIdText.setText(medic.getAddress());
            greetingContentText.setText(String.valueOf(medic.getId()));
        }
    }

}
package com.example.yun.myapplication.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yun.myapplication.Entities.Greeting;
import com.example.yun.myapplication.Entities.Medic;
import com.example.yun.myapplication.R;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class Activity extends AppCompatActivity {

    /**
     * Testing
     *
     * @param savedInstanceState
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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
            greetingIdText.setText(medic.getCategory());
            greetingContentText.setText(medic.getFirstName());
        }

    }

}
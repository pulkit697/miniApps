package com.example.networking_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button btGetData;
    RecyclerView rvUsers;
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGetData = findViewById(R.id.btGetData);
        rvUsers = findViewById(R.id.rvUsers);
        etName = findViewById(R.id.etName);
    }

    public void getDataOKHttp(View v)
    {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "https://api.github.com/search/users?q=pulkit";
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("pulkit","request failed due to " + e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseBody = response.body().string();
                ArrayList<GitHubUser> users = parseJsonFromString(responseBody);
                Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
                ApiResult result = gson.fromJson(responseBody,ApiResult.class);
//                Log.d("pulkit",""+users.size());
                UsersListAdapter customAdapter = new UsersListAdapter(result.getItems());
                MainActivity.this.runOnUiThread(() -> {
                    rvUsers.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    rvUsers.setAdapter(customAdapter);
                });
            }
        });
    }

    public void getData(View v) {
        String name = etName.getText().toString();
        MyNetworkTask networkTask = new MyNetworkTask();
        networkTask.execute("https://api.github.com/search/users?q="+name);

    }

    class MyNetworkTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... executed_urls) {
            String fetched_content = "Failed to load content....";
            String string_url = executed_urls[0];
            try {
                URL url = new URL(string_url);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                scanner.useDelimiter("\\A");
                if(scanner.hasNext()) {
                    fetched_content = scanner.next();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return fetched_content;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<GitHubUser> users = parseJsonFromString(s);
            Log.d("pulkit",""+users.size());
            UsersListAdapter customAdapter = new UsersListAdapter(users);
            rvUsers.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            rvUsers.setAdapter(customAdapter);
        }
    }

    ArrayList<GitHubUser> parseJsonFromString(String s) {
        ArrayList<GitHubUser> users = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray users_list = jsonObject.getJSONArray("items");
            for(int i=0;i<users_list.length();i++)
            {
                JSONObject user = users_list.getJSONObject(i);
                String login = user.getString("login");
                String url = user.getString("url");
                int score = user.getInt("score");
                int id = user.getInt("id");
                users.add(new GitHubUser(login,url,score,id));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Please enter a name to search", Toast.LENGTH_SHORT).show();
        }
        return users;
    }
}
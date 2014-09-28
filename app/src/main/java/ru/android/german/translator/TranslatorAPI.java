package ru.android.german.translator;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by creed on 28.09.14.
 */

public class TranslatorAPI {
    public Context ctx = null;
    public void exec(Context ctx, String q) {
        this.ctx = ctx;
        LoadTask task = new LoadTask();
        task.execute(q);
    }

    public class LoadTask extends AsyncTask<String, Void, String> {
        private final String USER_AGENT = "Mozilla/5.0";
        private final String key = "trnsl.1.1.20140927T191415Z.85ed08b03e3d84ae.c9561739ed7a76ef198ecb09eef98cceb9d102be";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... query) {
            System.out.println("Send Http GET request");
            String res = "Empty response";
            try {
                URL translateUrl = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?lang=en-ru&key="+key+"&text="+query[0]);
                URL imagesUrl = new URL("http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=penis&rsz=2&start=8);
                HttpURLConnection con = (HttpURLConnection)translateUrl.openConnection();
                con.setRequestMethod("GET");// optional default is GET
                //con.setRequestProperty("User-Agent", USER_AGENT);//add request header

                //System.out.println("Sending 'GET' request to URL : " + url);
                System.out.println("Translation Response Code: "+con.getResponseCode());

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String translation = in.readLine();
                in.close();

                JSONObject r = new JSONObject(response);
                res = r.getString("text");

            } catch (IOException io) {
                System.out.println("IOException occured");
            } catch (JSONException e) {
                System.out.println("JSONException occured");
                e.printStackTrace();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("onPostExecute: " + result);
        }
    }
}

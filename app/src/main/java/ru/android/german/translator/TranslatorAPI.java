package ru.android.german.translator;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

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
                String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?lang=en-ru&key="+key+"&text="
                        + Arrays.toString(query);
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");// optional default is GET
                con.setRequestProperty("User-Agent", USER_AGENT);//add request header

                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'GET' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                res = response.toString();
            } catch (IOException io) {
                System.err.println("IOException occured");
            } finally {
                return res;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //ctx.startNewActivity(result);
            Intent intent = new Intent(ctx, TranslateActivity.class);
            intent.putExtra("translate", result);
            ctx.startActivity(intent);
            System.out.println("onPostExecute: " + result);
        }
    }
}

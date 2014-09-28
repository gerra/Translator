package ru.android.german.translator;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by creed on 28.09.14.
 */

public class TranslatorAPI {
    public TranslatorAPI() {

    }

    public void exec(String q) {
        LoadTask task = new LoadTask();
        task.execute(q);
    }

    public class LoadTask extends AsyncTask<String, Void, String> {
        private final String USER_AGENT = "Mozilla/5.0";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... query) {
            System.out.println("Send Http GET request");
            try {
                String url = "http://www.google.com/search?q=" + query;
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

                //print result
                //System.out.println(response.toString());
                return response.toString();
            } catch (IOException io) {
                System.err.println("IOException occured");
            } finally {
                return "Empty response";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("onPostExecute: " + result);
        }
    }
}

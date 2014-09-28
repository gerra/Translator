package ru.android.german.translator;

import android.os.AsyncTask;

import java.util.concurrent.TimeUnit;

/**
 * Created by creed on 28.09.14.
 */
public class TranslatorAPI {

    public class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void doInBackground(Void ) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}

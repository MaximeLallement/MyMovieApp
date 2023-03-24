package com.example.mymusicapplication.outils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mymusicapplication.controller.Control;
import com.example.mymusicapplication.modele.LocalAccess;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class API extends AsyncTask<String, String, String> {

    private static LocalAccess localAccess;

    @Override
    protected String doInBackground(String... params) {
        Log.d("TAG", "doInBackground: Here");
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            Log.d("TAG", "doInBackground.try: Here");

            // Pass in a String and convert to URL
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            // for reading data line by line
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer strBuffer = new StringBuffer();


            String line = "";
            while ((line = reader.readLine()) != null) {
                strBuffer.append(line);
            }

            // If we are able to get the data

            String retrievedJson = strBuffer.toString();
            JSONObject parentObject = new JSONObject(retrievedJson);

            try {

                Control.currentFilm.setFilmName(parentObject.getString("Title"));
                Control.currentFilm.setGrade(-1);
                Control.currentFilm.setComment("");
                Control.type =  parentObject.getString("Type");

            }catch (JSONException jsonException){
                Log.e("Error",jsonException.getMessage());
            }

            return retrievedJson;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            //cant close null

            if (connection != null) {
                // close both connection and the reader
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;

    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("TAG", "onPostExecute: Here");

        super.onPostExecute(result);
        /* for JSONObject data*/
        if(result!=null && !result.isEmpty())
            formatJSONObjectData(result);

        //tvData.setText(result);
    }

    public void formatJSONObjectData(String results){
        Log.d("TAG", "formatJSONObjectData: Here");


    }
}



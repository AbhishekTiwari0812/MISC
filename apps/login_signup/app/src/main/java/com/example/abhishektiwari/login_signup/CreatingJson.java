package com.example.abhishektiwari.login_signup;


import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;

public class CreatingJson extends AsyncTask<String, Integer, Integer> {

    @Override
    protected Integer doInBackground(String... params) {

        String data = null;
        String email = params[0];
        String password = params[1];

        String UrlToSendTo = params[2];
        String contentType = "application/x-www-form-urlencoded; charset=UTF-8";
        try {
            data = "&" + URLEncoder.encode("email", "UTF-8") + "="
                    + URLEncoder.encode(email, "UTF-8");
            data += "&" + URLEncoder.encode("pass", "UTF-8")
                    + "=" + URLEncoder.encode(password, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            Log.d("Error in encoding", "There's error while encoding the string");
        }

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httppostreq = new HttpPost(UrlToSendTo);
        StringEntity se = null;
        try {

            se = new StringEntity(data);
        } catch (UnsupportedEncodingException e) {
            Log.d("StringEntity", "Error here");
        }
        se.setContentType(contentType);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, contentType));
        httppostreq.setEntity(se);
        HttpResponse httpresponse = null;
        try {
            httpresponse = httpclient.execute(httppostreq);
        } catch (IOException e) {
            Log.d("Error in response", "The fuck happened now");
        }
        if (httpresponse == null) {
            Log.d("HttpResponse", "Error in response");
        }
        HttpEntity resultentity = httpresponse.getEntity();
        InputStream inputstream = null;
        try {
            inputstream = resultentity.getContent();
        } catch (IOException e) {
            Log.d("Input stream", "Error in input strtema");
        }
        //Here try importing the other option!
        Header contentencoding = httpresponse.getFirstHeader("Content-Encoding");
        if (contentencoding != null && contentencoding.getValue().equalsIgnoreCase("gzip")) {
            try {
                //doesn't go in here!
                inputstream = new GZIPInputStream(inputstream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }
        String resultstring = convertStreamToString(inputstream);
        try {
            inputstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            resultstring = resultstring.substring(1, resultstring.length() - 1);

        } catch (StringIndexOutOfBoundsException e) {
            Log.d("StringIndexOut", "Error here");
        }
        Log.d("Output123", (resultstring + "\n\n" + httppostreq.toString().getBytes()));

        try {
            if (resultstring.equals("1")) {
            } else if (resultstring.equals("\"error\":\"register\",\"message\":\"Database error.\"")) {
            }
        } catch (NullPointerException e) {
            Log.d("NullPOinter", "What the damn hell");
        }
        try {
            JSONObject recvdjson = new JSONObject(resultstring);
            Log.d("Output456", recvdjson.toString(2));
        } catch (JSONException e) {
            Log.d("Output error", "The fuck happened now");
        }
        return null;
    }

    private String convertStreamToString(InputStream is) {

        String line = "";
        StringBuilder total = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (Exception e) {
            Log.d("Error in input", "error while converting");
        }
        return total.toString();
    }


    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }
}

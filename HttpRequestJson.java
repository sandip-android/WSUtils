package com.webzino.runmile.ws;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import com.webzino.runmile.interfaces.AsyncInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpRequestJson extends AsyncTask<String, Void, String> {
    Context context;
    String method;
    ArrayList<RequestHeader> requestHeaders;
    String reqBody;
    String WSName;

    AsyncInterface asyncInterface;

    HttpURLConnection httpURLConnection;
    ProgressDialog pDialog;

    public static String Type_GET = "GET";
    public static String Type_POST = "POST";
    public static String Type_PUT = "PUT";
    public static String Type_DELETE = "DELETE";

    public HttpRequestJson(Context context, String method, ArrayList<RequestHeader> requestHeaders, String reqBody, String WSName) {
        this.context = context;
        this.method = method;
        this.requestHeaders = requestHeaders;
        this.reqBody = reqBody;
        this.WSName = WSName;
        this.asyncInterface = (AsyncInterface) context;
    }

    public HttpRequestJson(Context context, Fragment fragment, String method, ArrayList<RequestHeader> requestHeaders, String reqBody, String WSName) {
        this.context = context;
        this.method = method;
        this.requestHeaders = requestHeaders;
        this.reqBody = reqBody;
        this.WSName = WSName;
        this.asyncInterface = (AsyncInterface) fragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setTitle("Loading");
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        try {
            URL url = new URL(strings[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            for (int i = 0; i < requestHeaders.size(); i++) {
                httpURLConnection.setRequestProperty(requestHeaders.get(i).getKey(), requestHeaders.get(i).getValue());
            }
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod(method);
            httpURLConnection.setDoInput(true);
            if (method.equals(HttpRequestJson.Type_GET)) {
                httpURLConnection.setDoOutput(false);
            } else {
                httpURLConnection.setDoOutput(true);
            }

            if (!method.equals(HttpRequestJson.Type_GET)) {
                Writer writer = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
                writer.write(reqBody);
                writer.flush();
                writer.close();
            }

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream iStream = httpURLConnection.getInputStream();
                InputStreamReader isReader = new InputStreamReader(iStream);
                BufferedReader br = new BufferedReader(isReader);
                String line;
                while ((line = br.readLine()) != null) {
                    result += line;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pDialog.dismiss();
        asyncInterface.onWSResponse(s, WSName);
    }
}

package com.weatherbug.demo.network;

import com.weatherbug.demo.main_activity.MainContract;
import com.weatherbug.demo.model.Notice;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetNoticeIntractorImpl implements MainContract.GetNoticeIntractor {

    private List<Notice> noticeList;
    private OkHttpClient mClient = null;
    private Request mRequest = null;

    private static final String url = "https://s3.amazonaws.com/sc.va.util.weatherbug" +
            ".com/interviewdata/mobilecodingchallenge/sampledata.json";

    @Override
    public void getNoticeArrayList(final OnFinishedListener onFinishedListener) {

        noticeList = new ArrayList<>();
        mClient = new OkHttpClient();
        mRequest = new Request.Builder()
//                .header("Authorization", "your token")
                .url(url)
                .build();

        mClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
                onFinishedListener.onFailure();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    String body = response.body().string();
                    syncData(body);
                    onFinishedListener.onFinished(noticeList);
                }
            }

        });
    }

    private void syncData(String body) {
        try {
//            JSONObject photosObj = new JSONObject(body);
//        JSONArray photo1 = photosObj.names(); //[photos, stat]
//            JSONObject p2 = photosObj.getJSONObject("photos");
//            JSONArray photo = p2.getJSONArray("photo");
            JSONArray photo = new JSONArray(body);
            for (int i = 0; i < photo.length(); i++) {
                JSONObject p = photo.getJSONObject(i);
                String title = p.getString("title");
                String description = p.getString("description");
                String filename = p.getString("filename");
                Notice notice = new Notice(title, description, filename);
                noticeList.add(notice);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

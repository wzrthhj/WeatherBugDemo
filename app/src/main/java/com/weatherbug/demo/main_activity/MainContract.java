package com.weatherbug.demo.main_activity;

import com.weatherbug.demo.model.Notice;

import java.util.List;

public interface MainContract {

    /**
     * Call when user interact with the view and other when view OnDestroy()
     * */
    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();

    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetNoticeInteractorImpl class
     **/
    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Notice> noticeArrayList);

//        void onResponseFailure(Throwable throwable);
        void onResponseFailure();
    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/

    interface GetNoticeIntractor {

        interface OnFinishedListener {
            void onFinished(List<Notice> noticeArrayList);
//            void onFailure(Throwable t);
            void onFailure();
        }

        void getNoticeArrayList(OnFinishedListener onFinishedListener);
    }
}

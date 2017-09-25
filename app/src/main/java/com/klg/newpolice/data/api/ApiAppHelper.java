package com.klg.newpolice.data.api;

import com.klg.newpolice.data.api.model.CommentResponce;
import com.klg.newpolice.data.api.model.MissChildResponce;
import com.klg.newpolice.util.rx.AppSchedulerProvider;
import com.klg.newpolice.util.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sergejkozin on 9/23/17.
 */

public class ApiAppHelper implements ApiHelper {

    private ApiManager mManager;

    public ApiAppHelper() {
        mManager = ApiManager.getInstance();
    }

    @Override
    public Flowable<List<CommentResponce>> getComments(String authorisation, int id, int offset, int limit) {
        return mManager.getService()
                .getComments(authorisation, id, offset, limit);
    }

    @Override
    public Flowable<List<MissChildResponce>> getMissedChildren(String authorisation, int offset, int limit) {
        return mManager.getService()
                .getChilds(authorisation, offset, limit);
    }
}

package com.klg.newpolice.data.api;

import com.klg.newpolice.data.api.model.CommentResponce;
import com.klg.newpolice.data.api.model.MissChildResponce;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by sergejkozin on 9/23/17.
 */

public interface ApiHelper {

    Flowable<List<CommentResponce>> getComments(String authorisation, int id, int offset, int limit);

    Flowable<List<MissChildResponce>> getMissedChildren(String authorisation, int offset, int limit);
}

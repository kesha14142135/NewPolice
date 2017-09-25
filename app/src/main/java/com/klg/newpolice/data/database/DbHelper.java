package com.klg.newpolice.data.database;

import com.klg.newpolice.data.database.model.MissChild;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;

/**
 * Created by sergejkozin on 9/23/17.
 */

public interface DbHelper {

    List<MissChild> getMissChildren();

    MissChild getMissChild(int id);

    void setMissChildren(@NonNull List<MissChild> missChildren);

    void deleteAllMissChildren();

    boolean tableMissingChildrenIsEmpty();
}

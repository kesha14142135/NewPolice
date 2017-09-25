package com.klg.newpolice.data;

import com.klg.newpolice.data.database.model.Comment;
import com.klg.newpolice.data.database.model.MissChild;

import java.util.List;

/**
 * Created by sergejkozin on 9/23/17.
 */

public interface DataManager {

    void isUserValid(String login, String password, CallBackUserValid callBack);

    interface CallBackUserValid {
        void onSuccess();

        void onFailure();
    }

    boolean isUserAuthorisation();

    void receiveCommentFromServer(int id, CallBackComment callBack);

    void updateCommentFromServer(int id, CallBackComment callBack);

    interface CallBackComment {
        void onSuccess(List<Comment> comments);

        void onFailure();
    }

    void receiveMissChildrenFromServerToWriteDb(CallBackMissChildren callBack);

    void updateMissChildrenFromServerToWriteDb(CallBackMissChildren callBack);

    List<MissChild> getMissChildrenInDb();

    MissChild getMissChildById(int id);

    interface CallBackMissChildren {
        void onSuccess(List<MissChild> children);

        void onFailure();
    }

}

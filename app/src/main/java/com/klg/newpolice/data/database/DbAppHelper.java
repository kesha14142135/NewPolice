package com.klg.newpolice.data.database;

import com.klg.newpolice.data.database.model.MissChild;

import java.util.List;

import io.realm.Realm;

/**
 * Created by sergejkozin on 9/23/17.
 */

public class DbAppHelper implements DbHelper {
    @Override
    public List<MissChild> getMissChildren() {
        Realm realm = Realm.getDefaultInstance();
        List<MissChild> missChildren = realm.copyFromRealm(realm.where(MissChild.class).findAll());
        realm.close();
        return missChildren;
    }

    @Override
    public MissChild getMissChild(int id) {
        Realm realm = Realm.getDefaultInstance();
        //Todo use resource and mContext.getString(R.string.id
        MissChild missChild = realm.copyFromRealm(realm.where(MissChild.class).equalTo("mId", id).findFirst());
        realm.close();
        return missChild;
    }

    @Override
    public void setMissChildren(final List<MissChild> missChildren) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(missChildren));
        realm.close();
    }

    @Override
    public void deleteAllMissChildren() {
        Realm realm = Realm.getDefaultInstance();
        realm.addChangeListener(realm1 -> realm1.deleteAll());
        realm.close();
    }

    @Override
    public boolean tableMissingChildrenIsEmpty() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(MissChild.class).findAll().isEmpty();
    }
}

package com.kampen.riks.app.rikskampen.DI;

import com.kampen.riks.app.rikskampen.user.module.DB_User_Module;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LocalApiService implements Service{


    public  static final   String  NAME="Realm_db";
    public  static final   String  PACKAGE_NAME="com.kampen.riks.app.rikskampen";




     private  Realm mRealm;


    public String getName() {
        return NAME;
    }




    public   LocalApiService getApiObject() {


       /* Realm  mRealm;
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(PACKAGE_NAME + ".realm")
                .schemaVersion(2)
                .modules(new DB_User_Module())
                .build();

         LocalApiService localApiService=new LocalApiService();
        localApiService.setmRealm(Realm.getInstance(config));*/

        return null;



    }


    public Realm getmRealm() {
        return mRealm;
    }

    public void setmRealm(Realm mRealm) {
        this.mRealm = mRealm;
    }
}

package com.kampen.riks.app.rikskampen.DI;

import com.kampen.riks.app.rikskampen.api.remote_api.API_Provider;
import com.kampen.riks.app.rikskampen.user.module.DB_User_Module;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.kampen.riks.app.rikskampen.DI.LocalApiService.PACKAGE_NAME;

public class InitialContext {

    public Object lookup(String jndiName){

        if(jndiName.equalsIgnoreCase(RemoteApiService.NAME)){

            RemoteApiService remoteApiService= new RemoteApiService();

            remoteApiService.setApiService(API_Provider.provideApi());

            return remoteApiService;

        }
        else if (jndiName.equalsIgnoreCase(LocalApiService.NAME)){



            RealmConfiguration config = new RealmConfiguration.Builder()
                    .name(PACKAGE_NAME + ".realm")
                    .schemaVersion(2)
                    .modules(new DB_User_Module())
                    .build();


            LocalApiService localApiService=new LocalApiService();
            localApiService.setmRealm(Realm.getInstance(config));

            return localApiService;
        }
        return null;
    }

}

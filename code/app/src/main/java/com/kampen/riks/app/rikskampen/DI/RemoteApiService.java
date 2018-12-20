package com.kampen.riks.app.rikskampen.DI;

import com.kampen.riks.app.rikskampen.api.remote_api.APIService;
import com.kampen.riks.app.rikskampen.api.remote_api.API_Provider;

import io.realm.Realm;

public class RemoteApiService implements Service {


    public  static final   String  NAME="Network";


    private APIService  apiService;


    public String getName() {
        return NAME;
    }



    public RemoteApiService getApiObject() {

        /*RemoteApiService remoteApiService= new RemoteApiService();
        remoteApiService.setApiService(API_Provider.provideApi());*/
       return  null;

    }


    public APIService getApiService() {
        return apiService;
    }

    public void setApiService(APIService apiService) {
        this.apiService = apiService;
    }
}

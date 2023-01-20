package com.example.fragments.server;

public class UtilsAPI {

    public static final String BASE_ROOT_URL = "https://www.omdbapi.com/";

    public static BaseApiService getApiService() {
        return RetrofitClient.getClient(BASE_ROOT_URL).create(BaseApiService.class);
    }
}

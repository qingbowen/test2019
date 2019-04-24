package com.zowoyoo.kintetsu.tools;


import com.google.gson.Gson;
import com.zowoyoo.kintetsu.exception.AppException;

public class AppJsonUtils {

    public static <T> T fromJson(String response, Class<T> bean) {

        try {
            return new Gson().fromJson(response, bean);
        } catch (Exception e) {
            throw new AppException(response);
        }

    }
}

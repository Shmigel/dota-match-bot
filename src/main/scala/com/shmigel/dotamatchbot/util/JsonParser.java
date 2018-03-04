package com.shmigel.dotamatchbot.util;

import com.google.gson.Gson;
import com.shmigel.dotamatchbot.service.telegram.TelegramResponse;

public class JsonParser {

    private static Gson parser = new Gson();

    public static TelegramResponse parse(String json) {
        return parser.fromJson(json, TelegramResponse.class);
    }

}

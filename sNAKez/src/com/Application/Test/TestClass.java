package com.Application.Test;

import com.company.Main;
import com.google.gson.*;

public class TestClass {
    String jsonString = "{\"width\":40,\"height\":40}";
    JsonObject jobj = new Gson().fromJson(jsonString, JsonObject.class);

    public static void main(String[] args) {
        TestClass test = new TestClass();
        test.getWidth();
    }

    private void getWidth(){
        System.out.println(jobj.get("width").getAsDouble());
    }

}

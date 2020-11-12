package com.Application.Test;

import com.google.gson.annotations.SerializedName;

public class JsonString {
    @SerializedName("width")
    private width data;

    private static class width{
        public double width;
    }

    public double getWidth(){
        return data.width;
    }

}

package com.xomorod.utility.business;

import android.content.Intent;

import com.xomorod.utility.logic.C;

/**
 * Created by Ahmad on 2/3/2016.
 */
public class MessageEvent {
    public final Intent message;

    public MessageEvent(Intent message) {
        this.message = message;
    }

    public  boolean CheckEvent(String action)
    {
        return  message.getAction().equals(C.Action.RefreshActivity);
    }
}
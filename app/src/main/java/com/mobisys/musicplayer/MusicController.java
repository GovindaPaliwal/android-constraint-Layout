package com.mobisys.musicplayer;

import android.content.Context;
import android.media.session.MediaController;
import android.util.AttributeSet;

/**
 * Created by Govinda P on 6/4/2016.
 */

public class MusicController extends android.widget.MediaController{

    public MusicController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MusicController(Context c){
        super(c);
    }

    public void hide(){}
}

package com.mobisys.musicplayer.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.TextView;

import com.mobisys.musicplayer.MusicController;
import com.mobisys.musicplayer.R;
import com.mobisys.musicplayer.model.MusicFile;

/**
 * Created by Govinda P on 6/3/2016.
 */

public class MusicPlayerActivity extends AppCompatActivity implements MediaController.MediaPlayerControl{

    private MusicController controller;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_player);
        MusicFile song = getIntent().getParcelableExtra("song");
        ((TextView)findViewById(R.id.txt_title)).setText(song.getTitle());
        ((TextView)findViewById(R.id.txt_singer)).setText(song.getAlbum());
    }

    public void setController(){
        controller=new MusicController(this);
    }
    @Override
    public void start() {

    }

    @Override
    public void pause() {

    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }

    @Override
    public void seekTo(int pos) {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return false;
    }

    @Override
    public boolean canSeekBackward() {
        return false;
    }

    @Override
    public boolean canSeekForward() {
        return false;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}

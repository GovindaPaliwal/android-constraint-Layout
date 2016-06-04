package com.mobisys.musicplayer.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.mobisys.musicplayer.model.MusicFile;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Govinda P on 6/3/2016.
 */

public class AppUtil {

    // SDCard Path
    public static String MEDIA_PATH = new String("/");

    public static ArrayList<MusicFile> getFiles(Context context) {
        ArrayList<MusicFile> list = new ArrayList<MusicFile>();
        final Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        final String[] cursor_cols = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
        };
        final String where = MediaStore.Audio.Media.IS_MUSIC +"=1";
        final Cursor cursor = context.getContentResolver().query(uri, cursor_cols, where, null, null);
        //cursor.moveToNext();


        while (cursor.moveToNext()) {
            String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
            String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
            String track = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            String data = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
            list.add(new MusicFile(track,album,data,artist,""));
        }

        return list;
    }

    /**
     * Function to read all mp3 files from sdcard
     * and store the details in ArrayList
     * */
    public static ArrayList<MusicFile> getPlayList(){
        File home = new File(MEDIA_PATH);
        ArrayList<MusicFile> list = new ArrayList<MusicFile>();
        if (home.listFiles(new FileExtensionFilter()).length > 0) {
            for (File file : home.listFiles(new FileExtensionFilter())) {
                list.add(new MusicFile(file.getName(),"1",file.getName(),file.getPath(),file.getPath()));
            }
        }
        // return songs list array
        return list;
    }

    /**
     * Class to filter files which are having .mp3 extension
     * */
    public static class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3"));
        }
    }
}

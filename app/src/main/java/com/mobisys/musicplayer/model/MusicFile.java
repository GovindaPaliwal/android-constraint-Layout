package com.mobisys.musicplayer.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Govinda P on 6/3/2016.
 */

public class MusicFile implements Parcelable {
    private String title;
    private String album;
    private String id;
    private String singer;
    private String path;

    public MusicFile() {
    }

    public MusicFile(String title, String album, String id, String singer, String path) {
        this.title = title;
        this.album = album;
        this.id = id;
        this.singer = singer;
        this.path = path;
    }

    protected MusicFile(Parcel in) {
        title = in.readString();
        album = in.readString();
        id = in.readString();
        singer = in.readString();
        path=in.readString();
    }

    public static final Creator<MusicFile> CREATOR = new Creator<MusicFile>() {
        @Override
        public MusicFile createFromParcel(Parcel in) {
            return new MusicFile(in);
        }

        @Override
        public MusicFile[] newArray(int size) {
            return new MusicFile[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getId() {
        return id;
    }

    public String getSinger() {
        return singer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "MusicFile{" +
                "title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", id='" + id + '\'' +
                ", singer='" + singer + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(album);
        dest.writeString(id);
        dest.writeString(singer);
        dest.writeString(path);
    }
}

package com.mobisys.musicplayer.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobisys.musicplayer.BR;
import com.mobisys.musicplayer.R;
import com.mobisys.musicplayer.databinding.MusicItemBinding;
import com.mobisys.musicplayer.model.MusicFile;
import com.mobisys.musicplayer.util.AppUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<MusicFile> musicFiles;
    RecyclerView mRecyclerView;
    MusicAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicFiles = new ArrayList<MusicFile>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                musicFiles = AppUtil.getFiles(MainActivity.this);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.d("Size", "" + musicFiles.size());
                mAdapter = new MusicAdapter(musicFiles);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
        ArrayList<MusicFile> music = new ArrayList<MusicFile>();
        public RecycleClickLisner lisener;

        public MusicAdapter(ArrayList<MusicFile> musicFiles) {
            this.music = musicFiles;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.music_item, parent, false);

            ViewHolder dataObjectHolder = new ViewHolder(view);
            return dataObjectHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final MusicFile item = getItem(position);
            /*holder.title.setText(item.getTitle());
            holder.singer.setText(item.getAlbum());*/

            holder.getBinding().setVariable(BR.file,item);
            holder.getBinding().executePendingBindings();

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent player = new Intent(getApplicationContext(), MusicPlayerActivity.class);
                    player.putExtra("song", item);
                    startActivity(player);
                }
            });

        }

        @Override
        public int getItemCount() {
            return this.music.size();
        }

        public MusicFile getItem(int position) {
            return this.music.get(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView title;
            private TextView singer;
            private View view;
MusicItemBinding binding;
            public ViewHolder(View itemView) {
                super(itemView);
                binding= DataBindingUtil.bind(itemView);
                view = itemView;
               /* title = (TextView) itemView.findViewById(R.id.txt_row_title);
                singer = (TextView) itemView.findViewById(R.id.txt_row_singer);*/
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
            public MusicItemBinding getBinding() {
                return binding;
            }
        }
    }

    interface RecycleClickLisner {
        public void setOnClickList(int position);
    }
}

package com.example.hotify;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by User_1_Benjamin_Rosenthal on 3/2/16.
 */
public class HotifyService extends Service {

    private static final String TAG = HotifyService.class.getSimpleName();

    MediaPlayer mPlayer;
    String mUrl;



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = new MediaPlayer();
        mUrl = "http://download.lisztonian.com/music/download/Clair+de+Lune-113.mp3";
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        })


        //Log.d(TAG, intent.getAction());
        if (intent.getAction().equals("PLAY")) {
            Toast.makeText(HotifyService.this, "Service Started", Toast.LENGTH_SHORT).show();
            playMyJam();
        } else if (intent.getAction().equals("PAUSE")) {
            pauseMyJam();
        } else if (intent.getAction().equals("STOP")) {
            stopMyJam();
       }
        return super.onStartCommand(intent, flags, startId);

        //new Thread(runnable).start();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void playMyJam() {
        try {
            String url = "http://download.lisztonian.com/music/download/Clair+de+Lune-113.mp3";
            final MediaPlayer player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(url);
            player.prepareAsync();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                }
            });
        } catch (Throwable thr){

        }

    }



    public void pauseMyJam(){
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            Toast.makeText(HotifyService.this, "The music should be paused", Toast.LENGTH_SHORT).show();
        }
    }

    public void stopMyJam(){
        mPlayer.reset();
        if (!mPlayer.isPlaying()) {
            mPlayer.release();
        }
    }
}


package com.rainysky.r_m_unt.mydearladyalarm.alarm;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;


public class SoundController {

    private static final String TAG = "SoundController";
//    private static SoundController instance;
    //private SoundController instance;
    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;
    private Context context;
    private int alarmSoundId;
    private int messageSoundId;
    private AudioAttributes audioAttributes;
    private static final int POOL_SIZE = 2;

    public SoundController(Context context, int alarmUri, int massageUri) {
        this.context = context;

        int currentApiVersion = Build.VERSION.SDK_INT;

        // APIレベルが21未満の場合
        if (currentApiVersion < Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool(POOL_SIZE, AudioManager.STREAM_ALARM, 0);

            // APIレベルが21以上の場合
        } else {
            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(POOL_SIZE)
                    .build();
        }
        // 音のロード
        alarmSoundId = soundPool.load(context, alarmUri, 1);
        messageSoundId = soundPool.load(context, massageUri, 1);

        // アラーム音のロード完了後に再生(ループ再生)
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (sampleId == alarmSoundId && 0 == status) {
                    //soundPool.play(sampleId,1.0F, 1.0F, 0, 0, 1.0F);
                    soundPool.play(alarmSoundId,1f, 1f, 0, -1, 1f);
                }
            }
        });
    }

//    public static SoundController getInstance() {
//        if (instance == null) {
//            instance = new SoundController();
//        }
//        return instance;
//    }

//  //  public void setMediaPlayer(Context context) {
//    public void setMediaPlayer(Context context, int uri) {
//        //Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
////        Uri uri = Uri.parse("/res/raw/effect.wav");
//
//        // 音声を設定
//        //mediaPlayer = MediaPlayer.create(context, uri);
//        mediaPlayer = MediaPlayer.create(context, uri);
//        mediaPlayer = new MediaPlayer();
//    //    mediaPlayer.setDataSource(context, uri);
//
//        // アラームのボリュームで再生
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
//
//        Log.i(TAG, "SoundController mediaPlayer SET");
//    }
//    public void setPlaySound(int uri) {
//        int currentApiVersion = Build.VERSION.SDK_INT;
//
//        // APIレベルが21未満の場合
//        if (currentApiVersion < Build.VERSION_CODES.LOLLIPOP) {
//            soundPool = new SoundPool(POOL_SIZE, AudioManager.STREAM_ALARM, 0);
//
//            // APIレベルが21以上の場合
//        } else {
//            audioAttributes = new AudioAttributes.Builder()
//                    .setUsage(AudioAttributes.USAGE_ALARM)
//                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
//                    .build();
//
//            soundPool = new SoundPool.Builder()
//                    .setAudioAttributes(audioAttributes)
//                    .setMaxStreams(POOL_SIZE)
//                    .build();
//        }
//        // 音を読み込む
//        soundId = soundPool.load(context, uri, 1);
//
//    }

//    public void play(boolean isLoopPlay) {
//        mediaPlayer.setLooping(isLoopPlay);
//        mediaPlayer.start();
//        Log.i(TAG, "SoundController mediaPlayer START");

//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            public void onCompletion(MediaPlayer mp) {
//                Log.i(TAG, "再生終了");
//            }
//        });
//        soundPool.play(soundId, 1f, 1f, 0, isLoopPlay ? 1 : 0, 1);
//    }

    /**
     *
     */
    public void messagePlay() {
        // アラーム音を停止後にメッセージを再生
        soundPool.stop(alarmSoundId);
        soundPool.play(messageSoundId, 1f, 1f, 0, 0, 1);
    }
//    public void play(SoundType soundType) {
//        switch (soundType) {
//            case ALARM:
//                // ループ再生
//                soundPool.play(alarmSoundId, 1f, 1f, 0, -1, 1);
//                break;
//            case MESSAGE:
//                // ノーマル再生
//                soundPool.stop(alarmSoundId);
//                soundPool.play(messageSoundId, 1f, 1f, 0, 0, 1);
//                break;
//            default:
//        }
//    }

    public void stop() {
//        Log.i(TAG, "変更済");
//        Log.i(TAG, "mediaPlayer:"+ mediaPlayer);
//        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//            mediaPlayer.stop();
//            Log.i(TAG, "SoundController mediaPlayer STOP");
//            mediaPlayer.reset();
//            Log.i(TAG, "SoundController mediaPlayer RESET");
//            mediaPlayer.release();
//            Log.i(TAG, "SoundController mediaPlayer RELEASE");
//        }

        soundPool.stop(alarmSoundId);
        soundPool.stop(messageSoundId);
        soundPool.unload(alarmSoundId);
        soundPool.unload(messageSoundId);
        soundPool.release();

        //}
    }
}

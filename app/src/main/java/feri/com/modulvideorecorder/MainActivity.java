package feri.com.modulvideorecorder;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private int VIDEO_REQ = 1001;
    private Uri VideoUri;

    Button btn_playvid, btn_recvid;
    VideoView vidview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_playvid = (Button) findViewById(R.id.play_btn);
        btn_recvid = (Button) findViewById(R.id.rec_btn);
        vidview = (VideoView) findViewById(R.id.vid_view);
    }

    public void playVideo(View view) {
        vidview.setVideoURI(VideoUri);
        vidview.start();
    }

    public void stopVideo(View view) {
        vidview.setVideoURI(VideoUri);
        vidview.stopPlayback();
    }

    public void recVideo(View view) {
        Intent VideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        if (VideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(VideoIntent, VIDEO_REQ);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == VIDEO_REQ && resultCode == RESULT_OK) {
            VideoUri = data.getData();
        }
    }


}

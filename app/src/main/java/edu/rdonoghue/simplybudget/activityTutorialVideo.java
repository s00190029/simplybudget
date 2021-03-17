package edu.rdonoghue.simplybudget;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class activityTutorialVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_video);

        //for back button on action bar

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //for the video to display and have controls
        VideoView videoView = (VideoView) findViewById(R.id.VVTurtorialVideo);
        String VideoPath = ("android.resource://" + getPackageName() + "/" + R.raw.tutorialvideo);
        Uri uri = Uri.parse(VideoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);








    }

    //public void DoBack(View view) {
       // finish();
    //}
}
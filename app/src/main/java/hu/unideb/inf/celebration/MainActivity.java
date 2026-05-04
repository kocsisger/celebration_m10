package hu.unideb.inf.celebration;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import hu.unideb.inf.celebration.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.downloadButton.setOnClickListener(view -> startDownload());
        binding.playButton.setOnClickListener(view -> playSong());
        path = getFilesDir().getParent();
    }

    private void startDownload() {
        new DownloadAsyncTask(
                binding.downloadButton,
                binding.progressBar,
                binding.downloadTextView,
                path
        ).execute();
    }

    private MediaPlayer mPlayer;
    public void playSong() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(path + "/files/song.mp3");
                    mPlayer.prepare();
            // Start playing the Music file
            mPlayer.start();
        } catch (Exception e) {
            Log.e("IOEX", e.getMessage());
        }
    }
}
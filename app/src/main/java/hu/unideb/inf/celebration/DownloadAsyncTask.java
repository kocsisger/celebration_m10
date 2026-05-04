package hu.unideb.inf.celebration;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class DownloadAsyncTask extends AsyncTask<Void, Integer, String> {
    WeakReference<Button> weakButton;
    WeakReference<ProgressBar> weakProgressBar;
    WeakReference<TextView> weakTextView;

    public DownloadAsyncTask(Button b, ProgressBar pb, TextView tv) {
        this.weakButton = new WeakReference<>(b);
        this.weakProgressBar = new WeakReference<>(pb);
        this.weakTextView = new WeakReference<>(tv);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        weakButton.get().setEnabled(false);
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(10);
                publishProgress(i+1);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        weakProgressBar.get().setProgress(values[0]);
        weakTextView.get().setText(values[0] + "%");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        weakButton.get().setEnabled(true);
    }
}

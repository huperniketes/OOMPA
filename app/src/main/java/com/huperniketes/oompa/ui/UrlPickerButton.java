package com.huperniketes.oompa.ui;

import com.huperniketes.oompa.MusicService;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;

public class UrlPickerButton extends Button implements OnClickListener {

	/**
     * The URL we suggest as default when adding by URL. This is just so that the user doesn't
     * have to find an URL to test this sample.
     */
    final String SUGGESTED_URL = "https://archive.org/13/items/kahvi037/kahvi037_epoq-lepidoptera.mp3";

	public UrlPickerButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnClickListener((OnClickListener) this);
	}

	@Override
	public void onClick(View v) {
		showUrlDialog();
	}

    /** 
     * Shows an alert dialog where the user can input a URL. After showing the dialog, if the user
     * confirms, sends the appropriate intent to the {@link MusicService} to cause that URL to be
     * played.
     */
    void showUrlDialog() {
    	final Context		context = getContext();
    	AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle("Manual Input");
        alertBuilder.setMessage("Enter a URL (must be http://)");
        final EditText input = new EditText(context);
        alertBuilder.setView(input);

        input.setText(SUGGESTED_URL);

        alertBuilder.setPositiveButton("Play!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dlg, int whichButton) {
                // Send an intent with the URL of the song to play. This is expected by
                // MusicService.
                Intent i = new Intent(MusicService.ACTION_URL).setClass(getContext(), MusicService.class);
                Uri uri = Uri.parse(input.getText().toString());
                i.setData(uri);
                context.startService(i);
            }
        });
        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dlg, int whichButton) {}
        });

        alertBuilder.show();
    }
}

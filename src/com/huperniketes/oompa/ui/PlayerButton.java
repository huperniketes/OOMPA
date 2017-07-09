package com.huperniketes.oompa.ui;

import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.widget.Button;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

public class PlayerButton extends Button implements OnClickListener {

	public PlayerButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
    	String		action;

    	action = (String)getTag();
        ((Activity)getContext()).startService(new Intent(action));
	}
}

package cn.edu.nju.delicacy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AddDelicacyStepOneActivity extends Activity implements
		OnClickListener {

	private CameraView mPreview;
	private Button btnsavepic;
	private Button btnnext;

	private static int click = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_delicacy_step_one);

		mPreview = (CameraView) findViewById(R.id.camera_preview);
		btnsavepic = (Button) findViewById(R.id.btnsavepic);
		btnsavepic.setOnClickListener(this);
		btnnext = (Button) findViewById(R.id.btnnext);
		btnnext.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnnext: {
			startActivity(new Intent(this, AddDelicacyStepTwoActivity.class));
			break;
		}
		case R.id.btnsavepic: {
			if (click % 2 == 0) {
				mPreview.takePicture();
				btnsavepic.setText("жьед");
			} else {
				btnsavepic.setText("едуу");
				mPreview.cleanPicture();
			}
			btnnext.setTextColor(Color.BLACK);
			btnnext.setEnabled(true);
			click++;
			break;
		}
		}
	}

}

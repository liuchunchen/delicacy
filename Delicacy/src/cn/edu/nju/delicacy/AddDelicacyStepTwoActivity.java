package cn.edu.nju.delicacy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AddDelicacyStepTwoActivity extends Activity implements OnClickListener {

	private Button btnDone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_delicacy_step_two);
		btnDone = (Button) findViewById(R.id.btndone);
		btnDone.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btndone: {
			startActivity(new Intent(this, GalleryActivity.class));
			Toast.makeText(this, "Ìí¼Ó³É¹¦", Toast.LENGTH_SHORT).show();
			break;
		}
		}
	}

}

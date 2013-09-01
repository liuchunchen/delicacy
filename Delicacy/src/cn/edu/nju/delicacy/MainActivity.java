package cn.edu.nju.delicacy;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//import android.widget.Toast;
import android.app.Activity;
//import android.view.Menu;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button btngallery;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btngallery=(Button)findViewById(R.id.btngallery);
        btngallery.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		if (v.getId()==R.id.btngallery) {
			startActivity(new Intent(this,GalleryActivity.class));
			//startActivity(new Intent(this,CameraActivity.class));
			return;
		}
	}
}
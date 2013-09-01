package cn.edu.nju.delicacy;

import java.util.ArrayList;
import java.util.Random;

import cn.edu.nju.controller.ShakeListener;
import cn.edu.nju.controller.ShakeListener.OnShakeListener;
import cn.edu.nju.model.DelicacyBrief;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

@SuppressLint("HandlerLeak")
public class GalleryActivity extends Activity implements Runnable, OnClickListener {
	private Gallery gallery;
	private Button btnAdd;
	private Button btnChoose;

	private ArrayList<DelicacyBrief> list;
	private ImageAdapter adapter;

	private ShakeListener mShakeListener = null;

	private Context context = this;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 2:
				adapter = new ImageAdapter(context, list);
				gallery.setAdapter(adapter);
				gallery.setSelection(list.size() / 2);
				break;
			}
		}
	};

	public GalleryActivity() {
		list = new ArrayList<DelicacyBrief>();
		for (int i = 0; i < 8; i++)
			list.add(null);
		new Thread(this).start();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		gallery = (Gallery) findViewById(R.id.gallery);
		btnAdd = (Button) findViewById(R.id.btnadd);
		btnChoose = (Button) findViewById(R.id.btnchoose);
		btnAdd.setOnClickListener(this);
		btnChoose.setOnClickListener(this);

		adapter = new ImageAdapter(this, list);
		gallery.setAdapter(adapter);
		gallery.setSelection(list.size() / 2);

		registerForContextMenu(gallery);

		mShakeListener = new ShakeListener(this);
		mShakeListener.setOnShakeListener(new OnShakeListener() {
			long pattern[] = { 50, 50, 100, 50, 100 };

			@Override
			public void onShake() {
				vibrate(GalleryActivity.this, pattern, false);
				randomChoose();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mShakeListener != null) {
			mShakeListener.stop();
		}
	}

	public void vibrate(final Activity activity, long milliseconds) {
		Vibrator vib = (Vibrator) activity
				.getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(milliseconds);
	}

	public void vibrate(final Activity activity, long[] pattern,
			boolean isRepeat) {
		Vibrator vib = (Vibrator) activity
				.getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(pattern, isRepeat ? 1 : -1);
	}

	public void randomChoose() {
		int random = new Random().nextInt(list.size());
		random = (new Random().nextInt(2) == 1) ? random : -random;
        gallery.onFling(null, null, -1000 * random, 0);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("Action");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		Toast.makeText(this, "Longpress: " + info.position, Toast.LENGTH_SHORT)
				.show();
		return true;
	}

	@Override
	public void run() {
		//SearchManager manager = new SearchManager();
		//list = manager.getDelicacyList();
		Message msg = new Message();
		msg.what = 2;
		handler.sendMessage(msg);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnadd: {
			startActivity(new Intent(this, AddDelicacyStepOneActivity.class));
			break;
		}
		case R.id.btnchoose: {
			randomChoose();
			break;
		}
		}
	}

}

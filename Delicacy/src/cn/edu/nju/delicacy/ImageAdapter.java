package cn.edu.nju.delicacy;

import java.util.ArrayList;

import cn.edu.nju.model.DelicacyBrief;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private int mGalleryItemBackground;
	private Context mContext;
	private ArrayList<DelicacyBrief> list;
	private int defaultImgIds[] = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g,
			R.drawable.h };

	public ImageAdapter(Context context, ArrayList<DelicacyBrief> list) {
		mContext = context;
		this.list = list;

		TypedArray a = mContext.obtainStyledAttributes(R.styleable.Gallery1);
		mGalleryItemBackground = a.getResourceId(
				R.styleable.Gallery1_android_galleryItemBackground, 0);
		a.recycle();
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		return position % list.size();
	}

	@Override
	public long getItemId(int position) {
		return position % list.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView i = new ImageView(mContext);
		position = position % list.size();

		if (list.get(position) != null) {
			i.setImageBitmap(list.get(position).getImage());
		} else {
			i.setImageResource(defaultImgIds[position]);
		}

		i.setScaleType(ImageView.ScaleType.FIT_XY);
		i.setLayoutParams(new Gallery.LayoutParams(400, 540));
		i.setBackgroundResource(mGalleryItemBackground);
		return i;
	}

}

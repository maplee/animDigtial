package com.jia.animnumber.ui.effect;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author Mars
 *
 */
public class EffectImageView extends EffectView {
	
	public EffectImageView(Context context) {
		super(context);
	}
	
	public EffectImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setResources(int[] resIds) {
		Context ctx = this.getContext();
		for (int resId : resIds) {
			// add image view
			ImageView iv = new ImageView(ctx);
			LayoutParams tvParams = new LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
//			tvParams.bottomMargin = 5;
			iv.setLayoutParams(tvParams);
			iv.setImageResource(resId);
			this.addView(iv);
		}
	}
}

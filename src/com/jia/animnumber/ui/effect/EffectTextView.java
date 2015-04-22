package com.jia.animnumber.ui.effect;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * @author Mars
 *
 */
public class EffectTextView extends EffectView {
	// default text size
		private float ts = 24.0f;
		private int mTextColor = Color.BLACK;
		
		public EffectTextView(Context context) {
			super(context);
		}
		
		public EffectTextView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}
		
		public void setTextSize(int size) {
			ts = size;
		}
		
		public void setTextColor(int color) {
	        this.mTextColor = color;
	    }
		
		public void setTexts(String[] texts) {
			Context ctx = this.getContext();
			for (String text : texts) {
				// add text view
				TextView tv = new TextView(ctx);
				LayoutParams tvParams = new LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.FILL_PARENT);
				tvParams.gravity = Gravity.CENTER;
				tv.setLayoutParams(tvParams);
				tv.setTextSize(ts);
				tv.setTextColor(Color.RED);
				tv.setGravity(Gravity.CENTER_HORIZONTAL);
				tv.setText(text);
				this.addView(tv);
			}
		}
}

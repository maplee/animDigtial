package com.jia.animnumber.ui.effect;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * @author Mars
 *
 */
public class EffectUtil {
	public static EffectView fillTexts(Context context, int ani,int color, int size, String[] texts) {
		EffectTextView etv = new EffectTextView(context);
		etv.setAnimation(ani);
		etv.setTextSize(size);
		etv.setTextColor(color);
		etv.setTexts(texts);
		return etv;
	}
	
	public static EffectView fillImages(Context context, int ani, int[] resIds) {
		EffectImageView eiv = new EffectImageView(context);
		eiv.setAnimation(ani);
		eiv.setResources(resIds);
		return eiv;	
	}
	
	public static EffectView fillTexts(Context context, Animation inAni,Animation outAni,AnimationListener listener, int size, String[] texts) {
		EffectTextView etv = new EffectTextView(context);
		inAni.setAnimationListener(listener);
		etv.setInAnimation(inAni);
		etv.setOutAnimation(outAni);
		etv.setTextSize(size);
		etv.setTexts(texts);
		return etv;
	}
}

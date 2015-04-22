package com.jia.animnumber.ui.effect;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * @author Mars
 *
 */
public class EffectAnimations {
	// default animation type 
		private static int rtp = Animation.RELATIVE_TO_PARENT;
		private static int rts = Animation.RELATIVE_TO_SELF;
		
		// default duration time
		private static int ti = 300;
		
		public static void setDuration(int ms) {
			ti = ms;
		}
		
		public static AnimationSet getUpIn() {
			AnimationSet set = new AnimationSet(true);
			Animation ta = new TranslateAnimation(rtp, 0f, rtp, 0f, rtp, 1.0f, rtp, 0f);
			Animation aa = new AlphaAnimation(0f, 1.0f);
			set.addAnimation(ta);
			set.addAnimation(aa);
			set.setDuration(ti);
			return set;
		}
		
		public static AnimationSet getUpOut() {
			AnimationSet set = new AnimationSet(true);
			Animation ta = new TranslateAnimation(rtp, 0f, rtp, 0f, rtp, 0f, rtp, -1.0f);
			Animation aa = new AlphaAnimation(1.0f, 0f);
			set.addAnimation(ta);
			set.addAnimation(aa);
			set.setDuration(ti);
			return set;
		}
		
		public static AnimationSet getDownIn() {
			AnimationSet set = new AnimationSet(true);
			Animation ta = new TranslateAnimation(rtp, 0f, rtp, 0f, rtp, -1.0f, rtp, 0f);
			Animation aa = new AlphaAnimation(0f, 1.0f);
			set.addAnimation(ta);
			set.addAnimation(aa);
			set.setDuration(ti);
			return set;
		}
		
		public static AnimationSet getDownOut() {
			AnimationSet set = new AnimationSet(true);
			Animation ta = new TranslateAnimation(rtp, 0f, rtp, 0f, rtp, 0f, rtp, 1.0f);
			Animation aa = new AlphaAnimation(1.0f, 0f);
			set.addAnimation(ta);
			set.addAnimation(aa);
			set.setDuration(ti);
			return set;
		}
		
		public static AnimationSet getLeftIn() {
			AnimationSet set = new AnimationSet(true);
			Animation ta = new TranslateAnimation(rtp, 1.0f, rtp, 0f, rtp, 0f, rtp, 0f);
			Animation aa = new AlphaAnimation(0f, 1.0f);
			set.addAnimation(ta);
			set.addAnimation(aa);
			set.setDuration(ti);
			return set;
		}
		
		public static AnimationSet getLeftOut() {
			AnimationSet set = new AnimationSet(true);
			Animation ta = new TranslateAnimation(rtp, 0f, rtp, -1.0f, rtp, 0f, rtp, 0f);
			Animation aa = new AlphaAnimation(1.0f, 0f);
			set.addAnimation(ta);
			set.addAnimation(aa);
			set.setDuration(ti);
			return set;
		}
		
		public static AnimationSet getRightIn() {
			AnimationSet set = new AnimationSet(true);
			Animation ta = new TranslateAnimation(rtp, -1.0f, rtp, 0f, rtp, 0f, rtp, 0f);
			Animation aa = new AlphaAnimation(0f, 1.0f);
			set.addAnimation(ta);
			set.addAnimation(aa);
			set.setDuration(ti);
			return set;
		}
		
		public static AnimationSet getRightOut() {
			AnimationSet set = new AnimationSet(true);
			Animation ta = new TranslateAnimation(rtp, 0f, rtp, 1.0f, rtp, 0f, rtp, 0f);
			Animation aa = new AlphaAnimation(1.0f, 0f);
			set.addAnimation(ta);
			set.addAnimation(aa);
			set.setDuration(ti);
			return set;
		}
		
		public static AnimationSet getRotateIn() {
			AnimationSet set = new AnimationSet(true);
			RotateAnimation ra = new RotateAnimation(180f, 360f, rts, 0.5f, rts, 0.5f);
			Animation aa = new AlphaAnimation(0f, 1.0f);
			set.addAnimation(ra);
			set.addAnimation(aa);
			set.setDuration(ti);
			return set;
		}
		
		public static AnimationSet getRotateOut() {
			AnimationSet set = new AnimationSet(true);
			RotateAnimation ra = new RotateAnimation(0f, 180f, rts, 0.5f, rts, 0.5f);
			Animation aa = new AlphaAnimation(1.0f, 0f);
			set.addAnimation(ra);
			set.addAnimation(aa);
			set.setDuration(ti);
			return set;
		}
		
		public static AnimationSet getScaleIn() {
			AnimationSet set = new AnimationSet(true);
			ScaleAnimation sa = new ScaleAnimation(0f, 1f, 0f, 1f, rts, 0.5f, rts, 0.5f);
			Animation aa = new AlphaAnimation(0f, 1.0f);
			set.addAnimation(sa);
			set.addAnimation(aa);
			set.setDuration(ti);
			return set;
		}
		
		public static AnimationSet getScaleOut() {
			AnimationSet set = new AnimationSet(true);
			ScaleAnimation sa = new ScaleAnimation(1f, 0f, 1f, 0f, rts, 0.5f, rts, 0.5f);
			Animation aa = new AlphaAnimation(1.0f, 0f);
			set.addAnimation(sa);
			set.addAnimation(aa);
			set.setDuration(ti);
			return set;
		}
		
		public static AnimationSet getFadeIn() {
			AnimationSet set = new AnimationSet(true);
			Animation aa = new AlphaAnimation(0f, 1.0f);
			set.addAnimation(aa);
			set.setDuration(ti);
			return set;
		}
		
		public static AnimationSet getFadeOut() {
			AnimationSet set = new AnimationSet(true);
			Animation aa = new AlphaAnimation(1.0f, 0f);
			set.addAnimation(aa);
			set.setDuration(ti);
			return set;
		}
}

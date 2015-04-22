package com.jia.animnumber.ui;

import java.util.Arrays;
import java.util.Collections;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jia.animnumber.R;
import com.jia.animnumber.ui.effect.EffectUtil;
import com.jia.animnumber.ui.effect.EffectView;
import com.jia.animnumber.util.CommonUtil;

/**
 *  @author Mars
 *
 */
@SuppressLint("NewApi")
public class AnimNumberView extends RelativeLayout{
	
	private Context context;
	
	private LinearLayout root;
	private EffectView hundredTv;
	private EffectView tenTv;
	private EffectView bitTv;
	private EffectView pointTv;
	private EffectView unitTv;
	
	private AnimationListener animationListener;
	private final int HUNDRED=1;
	private final int TEN=2;
	private final int BIT=3;
	private final int UNIT=4;
	
	private int hundredTime,tenTime,bitTime,unitTime;
	private int anim = EffectView.ANI_UP;
	
	
	// 文字资源（用于展示渐变切换）
	private String[] mTexts = { "0","1", "2", "3" ,"4","5","6","7","8","9","0","1", "2", "3" ,"4","5","6","7","8" };
	private String[] mPoints = { "." };
	
	private String[] hundredArrays;
	private String[] tenArrays;
	private String[] bitArrays;
	private String[] unitArrays;
	
	private double prevValue; // 原先值
	private double currentValue; // 当前值
	private int topPadding;
	private int bottomPadding;
	private int textSize;
	private int mTextColor;
	private int hundred,ten,bit,unit; // 当前值
	private int prevHundred,prevTen,prevBit,prevUnit; // 初始值
	/**
	 * @param context
	 */
	public AnimNumberView(Context context) {
		super(context);
		this.context = context;
		init();
	}
	
	public AnimNumberView(Context context,double value,int textSize,int topPadding,int bottomPadding) {
		super(context);
		this.context = context;
		this.currentValue = value;
		this.textSize =textSize;
		this.topPadding = topPadding;
		this.bottomPadding = bottomPadding;
		init();
	}
	
	/**
	 * @param context
	 * @param attrs
	 */
	public AnimNumberView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.animView);
		this.textSize = a.getDimensionPixelOffset(R.styleable.animView_tSize, 36);
		this.topPadding = a.getDimensionPixelOffset(R.styleable.animView_top, 36);
		this.bottomPadding = a.getDimensionPixelOffset(R.styleable.animView_bottom, 36);;
		init();
	}
	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public AnimNumberView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}
	
	/**
	 * 
	 */
	private void init() {
		if(root!=null){
			root.removeAllViews();
		}else{
			root = new LinearLayout(context);
			root.setOrientation(LinearLayout.HORIZONTAL);
			addView(root, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			root.setGravity(Gravity.CENTER_HORIZONTAL);
			root.setPadding(10, topPadding, 10, bottomPadding);
		}
		
		dealData();
		initData();
	}

	/**
	 * 
	 */
	private void dealData() {
		if(currentValue!=0d && prevValue!=0d && CommonUtil.compareInt(prevValue,currentValue)>0){
			anim =EffectView.ANI_DOWN;
		}
		if(currentValue!=0d){
			String temp = String.valueOf(currentValue);
			int intValue =0;
			String unitValue ="";
			if(temp.contains(".")){
				intValue = Integer.parseInt(temp.substring(0, temp.indexOf(".")));
				hundred = intValue/100;
				ten = (intValue%100) / 10;
				bit = intValue%10;
				unitValue = temp.substring(temp.indexOf("."));
				if(unitValue.length()>1){
					
					unit = Integer.parseInt(unitValue.substring(1,2));
				}
			}else{
				intValue = Integer.parseInt(temp);
				hundred = intValue/100;
				ten = (intValue%100) / 10;
				bit = intValue%10;
				unit = 0;
			}
			if(hundred>9){
				hundred = 9;
			}
		}
		
		if(prevValue!=0d){
			String temp = String.valueOf(prevValue);
			int intValue =0;
			String unitValue ="";
			if(temp.contains(".")){
				intValue = Integer.parseInt(temp.substring(0, temp.indexOf(".")));
				prevHundred = intValue/100;
				prevTen = (intValue%100) / 10;
				prevBit = intValue%10;
				unitValue = temp.substring(temp.indexOf("."));
				if(unitValue.length()>1){
					prevUnit = Integer.parseInt(unitValue.substring(1,2));
				}
			}else{
				intValue = Integer.parseInt(temp);
				prevHundred = intValue/100;
				prevTen = (intValue%100) / 10;
				prevBit = intValue%10;
				prevUnit = 0;
			}
			if(prevHundred>9){
				prevHundred = 9;
			}
		}
		
	}

	/**
	 * 
	 */
	private void initData() {
		if(prevHundred == hundred){
			hundredArrays = Arrays.copyOfRange(mTexts,prevHundred, prevHundred+1);
		}else if(hundred>prevHundred){
			hundredArrays = Arrays.copyOfRange(mTexts,prevHundred, hundred+1);
		}else{
			if(anim==0){
				hundredArrays = Arrays.copyOfRange(mTexts,prevHundred, hundred+11);
			}else{
				hundredArrays = Arrays.copyOfRange(mTexts,hundred, prevHundred+1);
				Arrays.sort(hundredArrays,Collections.reverseOrder());//对数组进行 倒序
			}
			
		}
		
		hundredTv = EffectUtil.fillTexts(context,anim,mTextColor,textSize, hundredArrays);
		if(prevTen == ten ){
			tenArrays = Arrays.copyOfRange(mTexts,prevTen, prevTen+1);
		}else if(prevTen < ten ){
			tenArrays = Arrays.copyOfRange(mTexts,prevTen, ten+1);
		}else{
			if(anim==0){
				tenArrays = Arrays.copyOfRange(mTexts,prevTen, ten+11);
			}else{
				tenArrays = Arrays.copyOfRange(mTexts,ten, prevTen+1);
				Arrays.sort(tenArrays,Collections.reverseOrder());//对数组进行 倒序
			}
			
		}
		tenTv = EffectUtil.fillTexts(context,anim,mTextColor, textSize, tenArrays);
		if(prevBit == bit ){
			bitArrays = Arrays.copyOfRange(mTexts,prevBit, prevBit+1);
		}else if(prevBit < bit ){
			bitArrays = Arrays.copyOfRange(mTexts,prevBit, bit+1);
		}else{
			
			if(anim==0){
				bitArrays = Arrays.copyOfRange(mTexts,prevBit, bit+11);
			}else{
				bitArrays = Arrays.copyOfRange(mTexts,bit, prevBit+1);
				Arrays.sort(bitArrays,Collections.reverseOrder());//对数组进行 倒序
			}
			
		}
		bitTv = EffectUtil.fillTexts(context, anim,mTextColor, textSize, bitArrays);
		pointTv = EffectUtil.fillTexts(context,anim,mTextColor, textSize, mPoints);
		if(prevUnit == unit ){
			unitArrays = Arrays.copyOfRange(mTexts,prevUnit, prevUnit+1);
		}else if(prevUnit < unit ){
			unitArrays = Arrays.copyOfRange(mTexts,prevUnit, unit+1);
		}else{
			if(anim==0){
				unitArrays = Arrays.copyOfRange(mTexts,prevUnit, unit+11);
			}else{
				unitArrays = Arrays.copyOfRange(mTexts,unit, prevUnit+1);
				Arrays.sort(unitArrays,Collections.reverseOrder());//对数组进行 倒序
			}
			
		}
		unitTv = EffectUtil.fillTexts(context,anim,mTextColor, textSize, unitArrays);
		
		
		
		if(hundred!=0){
			root.addView(hundredTv);
		}
		if((hundred!=0 && ten==0) || ten!=0){
			root.addView(tenTv);
		}
		if((hundred!=0 && ten==0 && bit==0) || (ten!=0 && bit==0) || bit!=0){
			root.addView(bitTv);
		}
		if((hundred!=0 && ten==0 && bit==0) || (ten!=0 && bit==0) || bit!=0){
			root.addView(pointTv);
			root.addView(unitTv);
		}
		
		
		initAnim();
		
		
		
	}

	/**
	 * @param flag
	 * @param size
	 * @return
	 */
	private AnimationListener initListener(final int flag, final int size) {
		animationListener = new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				int index =0;
				switch (flag) {
				case HUNDRED:
					index = hundredTv.getDisplayedChild();
					Log.e("anim", "hundredTv:index:"+index);
					if(index==size){
						hundredTv.stopFlipping();
					}
					index =0;
					break;
				case TEN:
					index = tenTv.getDisplayedChild();
					Log.e("anim", "tenTv:index:"+index);
					if(index==size){
						tenTv.stopFlipping();
					}
					index =0;
					break;
				case BIT:
					index = bitTv.getDisplayedChild();
					Log.e("anim", "bitTv:index:"+index+",size:"+size);
					if(index==size){
						bitTv.stopFlipping();
					}
					index =0;
					break;
				case UNIT:
					index = unitTv.getDisplayedChild();
					Log.e("anim", "unitTv:index:"+index);
					if(index==size){
						unitTv.stopFlipping();
					}
					index =0;
					break;
				default:
					stop();
					break;
				}
				
			}
		};
		return animationListener;
	}

	/**
	 * 
	 */
	private void initAnim() {
		if(hundredArrays.length!=0){
			hundredTv.setInAnimation(switchAnimIn(1200/hundredArrays.length,initListener(HUNDRED, hundredArrays.length-1)));
			hundredTv.setOutAnimation(switchAnimOut(1200/hundredArrays.length));
			if(hundredArrays.length>2)
				hundredTv.setInterval(1200);
			hundredTv.start();
		}
		if(tenArrays.length!=0){
			tenTv.setInAnimation(switchAnimIn(1200/tenArrays.length,initListener(TEN, tenArrays.length-1)));
			tenTv.setOutAnimation(switchAnimOut(1200/tenArrays.length));
			if(tenArrays.length>2)
				tenTv.setInterval(1000);
			tenTv.start();
		}
		if(bitArrays.length!=0){
			bitTv.setInAnimation(switchAnimIn(1200/bitArrays.length,initListener(BIT, bitArrays.length-1)));
			bitTv.setOutAnimation(switchAnimOut(1200/bitArrays.length));
			if(bitArrays.length>2)
				bitTv.setInterval(800);
			bitTv.start();
			
		}
		if(unitArrays.length!=0){
			unitTv.setInAnimation(switchAnimIn(1200/unitArrays.length,initListener(UNIT, unitArrays.length-1)));
			unitTv.setOutAnimation(switchAnimOut(1200/unitArrays.length));
			if(unitArrays.length>2)
				unitTv.setInterval(600);
			unitTv.start();
		}
		
		
	}
	
	
	

	/**
	 * @param ms
	 * @param AnimationListener
	 * @return
	 */
	private Animation switchAnimIn(int ms,AnimationListener listener) {
		Animation animation = null;
		switch (anim) {
		case 0:
			animation = animIn(ms,listener);
			break;
		case 1:
			animation = getDownIn(ms,listener);
			break;

		default:
			animation = animIn(ms,listener);
			break;
		}
		return animation;
	}
	
	/**
	 * @param ms
	 * @return
	 */
	private Animation switchAnimOut(int ms) {
		Animation animation = null;
		switch (anim) {
		case 0:
			animation = animOut(ms);
			break;
		case 1:
			animation = getDownOut(ms);
			break;

		default:
			animation = animOut(ms);
			break;
		}
		return animation;
	}

	public double getPrevValue() {
		return prevValue;
	}

	public void setPrevValue(double prevValue) {
		this.prevValue = prevValue;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double prevValue,double currentValue) {
		this.prevValue = prevValue;
		this.currentValue = currentValue;
		this.invalidate();
		init();
	}

	
	
	/**
	 * 
	 */
	public void stop() {
		if(hundredTv.isFlipping()){
			hundredTv.stopFlipping();
			if(hundredArrays.length>0)
				hundredTv.setDisplayedChild(hundredArrays.length-1);
		}
		if(tenTv.isFlipping()){
			tenTv.stopFlipping();
			if(tenArrays.length>0)
				tenTv.setDisplayedChild(tenArrays.length-1);
		}
		
		if(bitTv.isFlipping()){
			bitTv.stopFlipping();
			if(bitArrays.length>0)
				bitTv.setDisplayedChild(bitArrays.length-1);
		}
		
		if(unitTv.isFlipping()){
			unitTv.stopFlipping();
			if(unitArrays.length>0)
				unitTv.setDisplayedChild(unitArrays.length-1);
		}

	}
	
	
	private static int rtp = Animation.RELATIVE_TO_PARENT;
	private static int rts = Animation.RELATIVE_TO_SELF;
	
	public  AnimationSet animIn(int ms,AnimationListener listener) {
		AnimationSet set = new AnimationSet(true);
		Animation ta = new TranslateAnimation(rtp, 0f, rtp, 0f, rtp, 1.0f, rtp, 0f);
		Animation aa = new AlphaAnimation(0f, 1.0f);
		ta.setInterpolator(new OvershootInterpolator());
		set.addAnimation(ta);
		set.addAnimation(aa);
		set.setDuration(ms);
		set.setAnimationListener(listener);
		return set;
	}
	
	public  AnimationSet animOut(int ms) {
		AnimationSet set = new AnimationSet(true);
		Animation ta = new TranslateAnimation(rtp, 0f, rtp, 0f, rtp, 0f, rtp, -1.0f);
		Animation aa = new AlphaAnimation(1.0f, 0f);
		set.addAnimation(ta);
		set.addAnimation(aa);
		set.setDuration(ms);
		return set;
	}
	
	public static AnimationSet getDownIn(int ms,AnimationListener listener) {
		AnimationSet set = new AnimationSet(true);
		Animation ta = new TranslateAnimation(rtp, 0f, rtp, 0f, rtp, -1.0f, rtp, 0f);
		Animation aa = new AlphaAnimation(0f, 1.0f);
		set.addAnimation(ta);
		set.addAnimation(aa);
		set.setDuration(ms);
		set.setAnimationListener(listener);
		return set;
	}
	
	public static AnimationSet getDownOut(int ms) {
		AnimationSet set = new AnimationSet(true);
		Animation ta = new TranslateAnimation(rtp, 0f, rtp, 0f, rtp, 0f, rtp, 1.0f);
		Animation aa = new AlphaAnimation(1.0f, 0f);
		set.addAnimation(ta);
		set.addAnimation(aa);
		set.setDuration(ms);
		return set;
	}

	public int getTopPadding() {
		return topPadding;
	}

	public void setTopPadding(int topPadding) {
		this.topPadding = topPadding;
	}

	public int getBottomPadding() {
		return bottomPadding;
	}

	public void setBottomPadding(int bottomPadding) {
		this.bottomPadding = bottomPadding;
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}

	public int getmTextColor() {
		return mTextColor;
	}

	public void setmTextColor(int mTextColor) {
		this.mTextColor = mTextColor;
	}

	
	
	
	
}

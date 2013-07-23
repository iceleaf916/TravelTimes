package com.deepin.traveltimes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements OnGestureListener {
	private ViewFlipper viewFlipper;
	private GestureDetector gestureDetector;
	private ImageView startImage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
		gestureDetector = new GestureDetector(this); // 声明检测手势事件
		startImage = (ImageView) findViewById(R.id.start);

		AlphaAnimation aa = new AlphaAnimation(0, 1);
		aa.setDuration(1500);
		aa.setInterpolator(new AccelerateDecelerateInterpolator());
		aa.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				viewFlipper.showNext();
			}
		});
		startImage.setAnimation(aa);
		aa.startNow();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		viewFlipper.stopFlipping(); // 点击事件后，停止自动播放
		viewFlipper.setAutoStart(false);
		return gestureDetector.onTouchEvent(event); // 注册手势事件
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e2.getX() - e1.getX() > 120) {
			if (viewFlipper.getCurrentView().getId() != R.id.start) {
				Animation rInAnim = AnimationUtils.loadAnimation(this,
						R.anim.push_right_in); // 向右滑动左侧进入的渐变效果（alpha 0.1 ->
												// 1.0）
				Animation rOutAnim = AnimationUtils.loadAnimation(this,
						R.anim.push_right_out); // 向右滑动右侧滑出的渐变效果（alpha 1.0 ->
												// 0.1）

				viewFlipper.setInAnimation(rInAnim);
				viewFlipper.setOutAnimation(rOutAnim);// 从左向右滑动（左进右出）
				viewFlipper.showPrevious();
			}

			return true;
		} else if (e2.getX() - e1.getX() < -120) {
			if (viewFlipper.getCurrentView().getId() != R.id.end) {
				Animation lInAnim = AnimationUtils.loadAnimation(this,
						R.anim.push_left_in); // 向左滑动左侧进入的渐变效果（alpha 0.1 -> 1.0）
				Animation lOutAnim = AnimationUtils.loadAnimation(this,
						R.anim.push_left_out); // 向左滑动右侧滑出的渐变效果（alpha 1.0 ->
												// 0.1）

				viewFlipper.setInAnimation(lInAnim);
				viewFlipper.setOutAnimation(lOutAnim); // 从右向左滑动（右进左出）
				viewFlipper.showNext();
			} else {
				Intent i = new Intent(this, LoginActivity.class);
				startActivity(i);
				this.finish();
			}

			return true;
		}
		return true;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}
}

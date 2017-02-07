package com.oceansoft.osc.mvp.view.lce.impl.animator;

import android.view.View;

/**
 * 策略模式
 * 
 * @author Jackie
 *
 */
public interface ILceAnimator {
	public void showLoading(View loadingView, View contentView, View errorView);

	public void showErrorView(View loadingView, View contentView, View errorView);

	public void showContent(View loadingView, View contentView, View errorView);
}

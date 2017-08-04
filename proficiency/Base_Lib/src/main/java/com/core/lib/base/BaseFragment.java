package com.core.lib.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.core.lib.base.mvp.BasePresenter;
import com.core.lib.base.mvp.BaseView;
import com.core.lib.helper.DelayTaskHelper;
import com.core.lib.helper.Helper;
import com.core.lib.helper.ResourceHelper;
import com.core.lib.wiget.AsynTask;

import java.lang.reflect.Field;

/**
 * BaseFragment 项目基类
 *
 * @author linhuan 2015年7月11日 上午9:08:09
 */
public abstract class BaseFragment<V extends BaseView, P extends BasePresenter> extends Fragment {

	private static final String TAG = BaseFragment.class.getSimpleName();

	private View contentView;											// 内容
	protected Activity act;

	private P basePresenter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		contentView = new View(act);

		basePresenter = setPresenter();
		if (Helper.isNotNull(basePresenter)) {
			basePresenter.attachView(getMvpView());
		}
	}

	public V getMvpView() {
		return (V) this;
	}

	protected abstract P setPresenter();

	public P getBasePresenter() {
		return basePresenter;
	}

	public void showViewStub(int rootId) {
		ViewStub stub = (ViewStub) findViewById(rootId);
		if (Helper.isNotNull(stub)) {
			stub.inflate();
		}
	}

	public View getViewStub(int rootId, int childId) {
		ViewStub stub = (ViewStub) findViewById(rootId);
		return stub.inflate().findViewById(childId);
	}

	/**
	 * setContentView 设置界面
	 *
	 * @param id 界面ID
	 *
	 * @author linhuan 2015年7月11日 上午9:17:29
	 */
	public void setContentView(int id) {
		setContentView(ResourceHelper.loadLayout(getActivity(), id));
	}

	public View getContentView() {
		return contentView;
	}

	/**
	 * setContentView 设置界面
	 *
	 * @param view 界面
	 *
	 * @author linhuan 2015年7月11日 上午9:17:51
	 */
	public void setContentView(View view) {
		contentView = view;
	}

	@Override
	public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		onCreateView(savedInstanceState);
		if (null == contentView)
			return super.onCreateView(inflater, container, savedInstanceState);
		return contentView;
	}

	protected void onCreateView(Bundle savedInstanceState) {}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		act = activity;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();

		if (Helper.isNotNull(contentView)) {
			((ViewGroup) contentView.getParent()).removeView(contentView);
			contentView = null;
		}
		act = null;
	}

	@Override
	public void onDestroy() {
		if (Helper.isNotNull(basePresenter)) {
			basePresenter.detachView();
		}
		super.onDestroy();
	}

	/**
	 * findView 读取布局
	 *
	 * @param id 控件ID
	 * @return
	 *
	 * @author linhuan 2015年7月11日 上午9:20:38
	 */
	public <T extends View> T findView(int id) {
		return (T) contentView.findViewById(id);
	}

	/**
	 * findViewById 读取布局
	 *
	 * @param id 控件ID
	 * @return
	 *
	 * @author linhuan 2015年7月11日 上午9:21:28
	 */
	public View findViewById(int id) {
		return contentView.findViewById(id);
	}

	/**
	 * function: 设置界面
	 *
	 * @author:linhuan 2015-1-14 上午9:05:01
	 */
	protected void initView() {}

	/**
	 * function: 设置数据
	 *
	 * @author:linhuan 2015-1-14 上午9:05:01
	 */
	protected void initData() {}

	@Override
	public void onDetach() {
		super.onDetach();
		try {
			Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);

		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加异步请求
	 *
	 * @param type
	 */
	protected final void addAsynTask(int type) {
		DelayTaskHelper.doAsynTask(new BaseOnAsynExecuteListener(type));
	}

	/**
	 * 异步读取数据
	 */
	class BaseOnAsynExecuteListener implements AsynTask.OnAsynExecuteListener {

		private int type;

		public BaseOnAsynExecuteListener(int type) {
			this.type = type;
		}

		@Override
		public void onStartBackground() {
			startBackground(type);
		}

		@Override
		public void onProgressUpdate() {
			progressUpdate(type);
		}

		@Override
		public void onPreExecute() {
			preExecute(type);
		}

		@Override
		public void onPostExecute() {
			postExecute(type);
		}
	}

	protected void startBackground(int type) {}

	protected void progressUpdate(int type) {}

	protected void preExecute(int type) {}

	protected void postExecute(int type) {}

}

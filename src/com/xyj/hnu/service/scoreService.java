package com.xyj.hnu.service;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xyj.hnu.R;
import com.xyj.hnu.adapter.ScoreAdapter;
import com.xyj.hnu.domain.O_SocreBeans;
import com.xyj.hnu.domain.Valid_ScoreBeans;
import com.xyj.hnu.tools.Metrics;


public class scoreService extends Activity implements OnClickListener {
	private RelativeLayout rl;
	private ImageView down, up, downtype, cursor1, cursor2;
	private TextView textView1, textView2, textView3, textView4, textView5,
			textView6, textView7, textView8, textView9, scoretype,
			original_score;
	private TextView tvs[] = { textView1, textView2, textView3, textView4,
			textView5, textView6, textView7, textView8, textView9, scoretype };
	public TextView current;
	View view;
	private float endX;
	private float startX;
	private FrameLayout fl1, fl2;
	private PopupWindow popw;
	private ListView score_list;
	private List<O_SocreBeans> o_Scores;
	private List<Valid_ScoreBeans> valid_Scores;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score);
		ready();

	}

	/**
	 * 改变下标位置
	 * 
	 * @param endT
	 */
	public void changeCursor(TextView endT) {
		if (endT == textView1 || endT == textView2 || endT == textView3
				|| endT == textView4) {
			fl2.removeView(cursor2);
			if (fl1.getChildCount() == 1)
				fl1.addView(cursor1);
			startX = current.getLeft();
			endX = endT.getLeft();
			// 构造动画
			TranslateAnimation move = new TranslateAnimation(startX, endX, 0, 0);
			move.setDuration(100);
			move.setFillAfter(true);
			cursor1.startAnimation(move);
			cursor1.setVisibility(View.VISIBLE);

		} else {
			fl1.removeView(cursor1);
			if (fl2.getChildCount() == 1)
				fl2.addView(cursor2);
			startX = current.getLeft();
			endX = endT.getLeft();
			// 构造动画
			TranslateAnimation move = new TranslateAnimation(startX, endX, 0, 0);
			move.setDuration(100);
			move.setFillAfter(true);

			cursor2.startAnimation(move);
			cursor2.setVisibility(View.VISIBLE);

		}

		current = endT;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.down:
			rl.setVisibility(View.VISIBLE);
			down.setVisibility(View.INVISIBLE);
			break;
		case R.id.up:
			rl.setVisibility(View.GONE);
			down.setVisibility(View.VISIBLE);
			break;
		case R.id.downtype:
			if (null != popw && popw.isShowing()) {
				popw.dismiss();
			} else {
				view = getLayoutInflater().inflate(R.layout.original_score,
						null);
				popw = new PopupWindow(view, scoretype.getWidth(),
						LayoutParams.WRAP_CONTENT);

				original_score = (TextView) view
						.findViewById(R.id.original_score);
				original_score.setOnClickListener(this);
				if (scoretype.getText().equals("原始成绩")) {
					original_score.setText("有效成绩");
				}

				popw.showAsDropDown(scoretype);
			}
			break;
		case R.id.scoretype:
			if (null != popw && popw.isShowing()) {
				popw.dismiss();
			} else {
				view = getLayoutInflater().inflate(R.layout.original_score,
						null);
				popw = new PopupWindow(view, scoretype.getWidth(),
						LayoutParams.WRAP_CONTENT);

				original_score = (TextView) view
						.findViewById(R.id.original_score);
				original_score.setOnClickListener(this);
				if (scoretype.getText().equals("原始成绩")) {
					original_score.setText("有效成绩");
				}

				popw.showAsDropDown(scoretype);
			}
			break;
		case R.id.textView1:
			changeCursor(textView1);
			break;
		case R.id.textView2:
			changeCursor(textView2);
			break;
		case R.id.textView3:
			changeCursor(textView3);
			break;
		case R.id.textView4:
			changeCursor(textView4);
			break;
		case R.id.textView5:
			changeCursor(textView5);
			break;
		case R.id.textView6:
			changeCursor(textView6);
			break;
		case R.id.textView7:
			changeCursor(textView7);
			break;
		case R.id.textView8:
			changeCursor(textView8);
			break;
		case R.id.original_score:
			if (original_score.getText().equals("原始成绩")) {
				scoretype.setText("原始成绩");
				popw.dismiss();
			} else {
				scoretype.setText("有效成绩");
				popw.dismiss();
			}

		default:
			break;
		}

	}

	/**
	 * onCreate时组件的加载
	 */
	public void ready() {
		/* 获取相应组件------------------------ */
		down = (ImageView) findViewById(R.id.down);
		up = (ImageView) findViewById(R.id.up);
		downtype = (ImageView) findViewById(R.id.downtype);
		cursor1 = (ImageView) findViewById(R.id.line_cursor1);
		cursor2 = (ImageView) findViewById(R.id.line_cursor2);
		rl = (RelativeLayout) findViewById(R.id.RelativeLayout2);

		score_list = (ListView) findViewById(R.id.score_list);

		textView1 = tvs[0] = (TextView) findViewById(R.id.textView1);
		textView2 = tvs[1] = (TextView) findViewById(R.id.textView2);
		textView3 = tvs[2] = (TextView) findViewById(R.id.textView3);
		textView4 = tvs[3] = (TextView) findViewById(R.id.textView4);
		textView5 = tvs[4] = (TextView) findViewById(R.id.textView5);
		textView6 = tvs[5] = (TextView) findViewById(R.id.textView6);
		textView7 = tvs[6] = (TextView) findViewById(R.id.textView7);
		textView8 = tvs[7] = (TextView) findViewById(R.id.textView8);
		textView9 = tvs[8] = (TextView) findViewById(R.id.textView9);
		scoretype = tvs[9] = (TextView) findViewById(R.id.scoretype);
		fl1 = (FrameLayout) findViewById(R.id.framelayout1);
		fl2 = (FrameLayout) findViewById(R.id.framelayout2);
		Metrics.setSreenInfo(this);
		current = textView1;
		/* 调整组件的大小------------------------------------- */
		cursor1.getLayoutParams().width = (Metrics.getWidthPixels() - dip2px(
				this, 55) * 2) / 4;
		cursor2.getLayoutParams().width = (Metrics.getWidthPixels() - dip2px(
				this, 55) * 2) / 4;

		/* 添加监听------------------------------------------- */
		for (int i = 0; i < tvs.length; i++) {
			tvs[i].setOnClickListener(this);
		}
		down.setOnClickListener(this);
		up.setOnClickListener(this);
		downtype.setOnClickListener(this);

		score_list.setAdapter(new ScoreAdapter(scoretype, o_Scores,
				valid_Scores, this));
	}

	/**
	 * px转dip
	 * */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * dip转px
	 * */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

}

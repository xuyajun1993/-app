package com.xyj.hnu.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xyj.hnu.R;
import com.xyj.hnu.domain.O_SocreBeans;
import com.xyj.hnu.domain.Valid_ScoreBeans;
import com.xyj.hnu.service.scoreService;

public class ScoreAdapter extends BaseAdapter{
private TextView scoreType;
private List<O_SocreBeans> o_Scores;
private List<Valid_ScoreBeans> valid_Scores; 
private scoreService ma;
	
	public ScoreAdapter(TextView scoreType, List<O_SocreBeans> o_Scores,
		List<Valid_ScoreBeans> valid_Scores,scoreService ma) {
	super();
	this.scoreType = scoreType;
//	this.o_Scores = o_Scores;
//	this.valid_Scores = valid_Scores;
	this.ma = ma;
}

	public int getCount() {
//		if(scoreType.getText().equals("Ô­Ê¼³É¼¨"))
//		return o_Scores.size();
//		else return valid_Scores.size();
		return 15;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(ma, R.layout.scorelist,null);
		
		
		return view;
	}

}

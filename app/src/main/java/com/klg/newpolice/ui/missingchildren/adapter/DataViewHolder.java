package com.klg.newpolice.ui.missingchildren.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.klg.newpolice.R;
import com.klg.newpolice.data.database.model.MissChild;
import com.klg.newpolice.ui.missingchildren.interfase.OnItemClickListener;

import java.text.ParseException;

public class DataViewHolder extends RecyclerView.ViewHolder {
    //ui
    private TextView mTextViewName;
    private TextView mTextViewGender;
    private TextView mTextViewYearOld;
    private TextView mTextViewDistrictLoss;
    private LinearLayout mCardLayout;
    //content
    private Context mContext;

    public DataViewHolder(View itemView) {
        super(itemView);
        mTextViewName = itemView.findViewById(R.id.text_view_name);
        mTextViewGender = itemView.findViewById(R.id.text_view_gender);
        mTextViewYearOld = itemView.findViewById(R.id.text_view_age);
        mTextViewDistrictLoss = itemView.findViewById(R.id.text_view_district);
        mCardLayout = itemView.findViewById(R.id.card_view_miss_child);
        mContext = itemView.getContext();
    }

    public void bindWith(MissChild child) {
        mTextViewName.setText(child.getName());
        mTextViewGender.setText(child.getGender().equals(mContext.getString(R.string.male)) ?
                mContext.getString(R.string.male_rus) :
                mContext.getString(R.string.women_rus));
        mTextViewDistrictLoss.setText(child.getRegion());
        try {
            int age = Integer.parseInt(child.getAge());
            mTextViewYearOld.setText(mContext.getResources().getQuantityString(
                    R.plurals.plurals_years, age, age));
        } catch (ParseException e) {
            e.printStackTrace();
            mTextViewYearOld.setText(child.getDateOfBirth());
        }

        switch (child.getStatus()) {
            case "FOUND":
                mCardLayout.setBackground(mCardLayout.getContext().getResources().getDrawable(R.drawable.card_border_green));
                break;
            case "REJECTED":
                mCardLayout.setBackground(mCardLayout.getContext().getResources().getDrawable(R.drawable.card_border_red));
                break;
            case "SEARCHING":
                mCardLayout.setBackground(mCardLayout.getContext().getResources().getDrawable(R.drawable.card_border_blue));
                break;
        }
    }

    void setItemClickListener(final int position, final OnItemClickListener listener) {
        itemView.setOnClickListener(view -> listener.onItemClick(position));
    }

}

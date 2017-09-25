package com.klg.newpolice.ui.comment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.klg.newpolice.R;
import com.klg.newpolice.data.database.model.Comment;

public class CommentsViewHolder extends RecyclerView.ViewHolder{

    private TextView mSenderNameTV;
    private TextView mSendTimeTV;
    private TextView mCommentTV;

    public CommentsViewHolder(View itemView) {
        super(itemView);
        mSenderNameTV = itemView.findViewById(R.id.text_view_sender_name);
        mSendTimeTV = itemView.findViewById(R.id.text_view_send_time);
        mCommentTV = itemView.findViewById(R.id.text_view_comment);
    }

    public void bindWith(Comment comment){
        mSenderNameTV.setText(comment.getSenderName());
        mSendTimeTV.setText(comment.getTimeOfCreate());
        mCommentTV.setText(comment.getText());
    }
}

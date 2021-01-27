package com.example.quizbay.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizbay.R;
import com.example.quizbay.activity.DynamicSubscribe;
import com.example.quizbay.activity.LeaderboardActivity;
import com.example.quizbay.activity.QuizStartActivity;
import com.example.quizbay.model.DynamicQuizNamesListModel;
import com.example.quizbay.model.QuizbayProfileModel;

import java.util.ArrayList;

public class DynamicQuizListAdapter extends RecyclerView.Adapter<DynamicQuizListAdapter.PostHolder> {

    private final ArrayList<DynamicQuizNamesListModel> mList;
    private final Context mContext;

    public DynamicQuizListAdapter(ArrayList<DynamicQuizNamesListModel> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_activity_dynamic_quiz_each_item, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {


        //get user postion
        DynamicQuizNamesListModel dynamicQuizNamesListModel = mList.get(position);

        //set id\
        holder.tv_dynamic_quiz1.setText(dynamicQuizNamesListModel.getQuizName());

        holder.viewHolder.setOnClickListener(v -> {
            Intent intent=new Intent(holder.viewHolder.getContext(), DynamicSubscribe.class);
            intent.putExtra("QuizId",String.valueOf(dynamicQuizNamesListModel.getQuizId()));
            holder.viewHolder.getContext().startActivity(intent);

        });
         }


    @Override
    public int getItemCount() {
        return (mList == null) ? 0 : mList.size();
    }


    public static class PostHolder extends RecyclerView.ViewHolder{
        private final TextView tv_dynamic_quiz1;
        private final View viewHolder;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            viewHolder =itemView;
            tv_dynamic_quiz1 = itemView.findViewById(R.id.tv_dynamic_quiz_name);

        }
    }

}

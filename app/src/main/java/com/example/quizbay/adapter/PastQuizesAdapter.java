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
import com.example.quizbay.activity.LeaderboardActivity;
import com.example.quizbay.model.QuizbayProfileModel;

import java.util.ArrayList;

public class PastQuizesAdapter extends RecyclerView.Adapter<PastQuizesAdapter.PostHolder> {

    private final ArrayList<QuizbayProfileModel> mList;
    private final Context mContext;

    public PastQuizesAdapter(ArrayList<QuizbayProfileModel> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public PastQuizesAdapter.PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.past_quiz_list_item, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PastQuizesAdapter.PostHolder holder, int position) {


        //get user postion
        QuizbayProfileModel quizbayProfileModel = mList.get(position);

        //set id

        holder.tv_quiz.setText(quizbayProfileModel.getQuizName());
        holder.tv_quiz_score.setText(String.valueOf(quizbayProfileModel.getScore()));

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(mContext, LeaderboardActivity.class);
            intent.putExtra("quizId", quizbayProfileModel.getQuizId());
            mContext.startActivity(intent);
        });

    }


    @Override
    public int getItemCount() {
        return (mList == null) ? 0 : mList.size();
    }


    public static class PostHolder extends RecyclerView.ViewHolder{
        private final TextView tv_quiz;
        private final TextView tv_quiz_score;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            tv_quiz = itemView.findViewById(R.id.tv_pastquizes_quizname);
            tv_quiz_score = itemView.findViewById(R.id.tv_pastquizes_score);
        }
    }
}

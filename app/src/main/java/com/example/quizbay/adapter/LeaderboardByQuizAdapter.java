package com.example.quizbay.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizbay.R;
import com.example.quizbay.model.LeaderboardByQuizModel;

import java.util.ArrayList;

public class LeaderboardByQuizAdapter extends RecyclerView.Adapter<LeaderboardByQuizAdapter.PostHolder> {



    private final ArrayList<LeaderboardByQuizModel> mList;
    private final Context mContext;

    public LeaderboardByQuizAdapter(ArrayList<LeaderboardByQuizModel> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.overall_leaderboard_item, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {


        //get user postion
        LeaderboardByQuizModel quizbay_leaderboard_class = mList.get(position);

        //set id
        holder.tv_rank.setText(String.valueOf(quizbay_leaderboard_class.getRank()));
        //set user name
        holder.tv_user_name.setText(quizbay_leaderboard_class.getUserName());
        //set user score
        holder.tv_user_score.setText(String.valueOf(quizbay_leaderboard_class.getScore()));

        if(quizbay_leaderboard_class.getScore()>=200)
            holder.iv_emoji.setBackgroundResource(R.drawable.beeremoji);
        else if(quizbay_leaderboard_class.getScore()>=100 && quizbay_leaderboard_class.getScore()<200)
            holder.iv_emoji.setBackgroundResource(R.drawable.sademoji);
        else if(quizbay_leaderboard_class.getScore()<100)
            holder.iv_emoji.setBackgroundResource(R.drawable.surprised_emoji);

        if(position==0) {
            holder.cv_leader.setCardBackgroundColor(Color.parseColor("#ffd700"));
        }

        if(position==1)
            holder.cv_leader.setCardBackgroundColor(Color.parseColor("#C0C0C0"));

        if(position==2)
            holder.cv_leader.setCardBackgroundColor(Color.parseColor("#cd7f32"));


    }
    @Override
    public int getItemCount() {
        return (mList == null) ? 0 : mList.size();
    }




    public static class PostHolder extends RecyclerView.ViewHolder{
        private final TextView tv_rank;
        private final TextView tv_user_name;
        private final TextView tv_user_score;
        private final ImageView iv_emoji;
        private final CardView cv_leader;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            tv_rank = itemView.findViewById(R.id.tv_lb_rank);
            tv_user_name = itemView.findViewById(R.id.tv_lb_username);
            tv_user_score = itemView.findViewById(R.id.tv_lb_score);
            iv_emoji = itemView.findViewById(R.id.iv_leaderboard_eachuser_emoji);
            cv_leader = itemView.findViewById(R.id.cv_leaderboard_eachuser);
        }
    }
}

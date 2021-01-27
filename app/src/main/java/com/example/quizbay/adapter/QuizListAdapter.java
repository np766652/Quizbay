package com.example.quizbay.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizbay.R;
import com.example.quizbay.activity.QuizStartActivity;
import com.example.quizbay.activity.QuizbayHomeActivity;
import com.example.quizbay.model.QuizListNewModel;

import java.util.List;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuizViewHolder> {

    private final List<QuizListNewModel> mquizLists;

    public QuizListAdapter(List<QuizListNewModel> mquizLists) {
        this.mquizLists = mquizLists;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.active_quiz_list_item,parent,false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {

        QuizListNewModel quizList=mquizLists.get(position);
        holder.setQuizName(quizList.getQuizName());
        holder.setStartTime(quizList.getStartTime());
        holder.setEndTime(quizList.getEndTime());
        holder.quizListView.setOnClickListener(v -> {
            Intent intent=new Intent(holder.quizListView.getContext(), QuizStartActivity.class);
            intent.putExtra("QuizId",String.valueOf(quizList.getQuizId()));
            holder.quizListView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mquizLists.size();
    }



    public static class QuizViewHolder extends RecyclerView.ViewHolder{

        private final TextView quizName;
        private final TextView startTime;
        private final TextView endTime;
        private final View quizListView;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);

            quizListView=itemView;
            quizName=itemView.findViewById(R.id.tv_quizbay_quizlist_quiz_name);
            startTime=itemView.findViewById(R.id.tv_quizbay_quizlist_start_time);
            endTime=itemView.findViewById(R.id.tv_quizbay_quizlist_end_time);
        }

        public void setQuizName(String quizName){
            this.quizName.setText(quizName);
        }

        public void setStartTime(String startTime) {
            this.startTime.setText(startTime);
        }

        public void setEndTime(String endTime) {
            this.endTime.setText(endTime);
        }
    }
}

package com.example.quizbay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizbay.R;

import java.util.ArrayList;

public class PastQuizesQuotesAdapter extends RecyclerView.Adapter<PastQuizesQuotesAdapter.PostHolder> {

    private final ArrayList<String> sList;
    private Context mContext;

    public PastQuizesQuotesAdapter(ArrayList<String> sList, Context mContext) {
        this.sList = sList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public PastQuizesQuotesAdapter.PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.past_quizes_quotes_list, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PastQuizesQuotesAdapter.PostHolder holder, int position) {


        //get user postion
        String str = sList.get(position);

        //set id
        holder.tv_past_quotes.setText(str);


    }


    @Override
    public int getItemCount() {
        return (sList == null) ? 0 : sList.size();
    }



    public static class PostHolder extends RecyclerView.ViewHolder{
        private final TextView tv_past_quotes;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            tv_past_quotes = itemView.findViewById(R.id.tv_pastquizes_quotes);

        }
    }
}

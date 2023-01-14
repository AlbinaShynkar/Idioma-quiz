package com.example.idioma_quiz.quizsecond;

import static com.example.idioma_quiz.quizsecond.App.getContext;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idioma_quiz.R;
import com.example.idioma_quiz.quizsecond.models.Word;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardView> {
    private List<Word> users;

    public CardAdapter(List<Word> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public CardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CardView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardView holder, int position) {
        holder.userNameTextView.setText(users.get(position).getOriginal());
        holder.userInfoTextView.setText(users.get(position).getTranslation());
        String imgName = users.get(position).getImageName();
        int resID = getContext().getResources().getIdentifier(imgName , "drawable", getContext().getPackageName());
        holder.imageView.setImageResource(resID);
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }

    static class CardView extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView userNameTextView;
        private final TextView userInfoTextView;

        public CardView(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.user_image);
            userNameTextView = itemView.findViewById(R.id.user_name);
            userInfoTextView = itemView.findViewById(R.id.user_info);
        }
    }
}

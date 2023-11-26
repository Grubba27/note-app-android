package com.example.noteapp;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteapp.databinding.NotaItemBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Nota}.
 * TODO: Replace the implementation with code for your data type.
 */
public class NotaRecyclerViewAdapter extends RecyclerView.Adapter<NotaRecyclerViewAdapter.ViewHolder> {
    public static final String TXT = "com.example.noteapp.TEXT";
    public static final String ID = "com.example.noteapp.ID";
    private final List<Nota> mValues;

    public NotaRecyclerViewAdapter(List<Nota> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(NotaItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(Long.toString(mValues.get(position).getId()));
        holder.mContentView.setText(mValues.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface OnNoteListener{
        void onEdit(int id);
        void onDelete(int id);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Nota mItem;

        public ViewHolder(NotaItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;

            binding.editBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), EditaNota.class);
                    intent.putExtra(TXT, mContentView.getText().toString());
                    intent.putExtra(ID, mIdView.getText().toString());
                    v.getContext().startActivity(intent);
                }
            });

            binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ConfirmaDeletaNota.class);
                    intent.putExtra(TXT, mContentView.getText().toString());
                    intent.putExtra(ID, mIdView.getText().toString());
                    v.getContext().startActivity(intent);
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
package edu.jsu.mcis.cs408.lab5a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.jsu.mcis.cs408.lab5a.databinding.MemoBinding;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private MemoBinding binding;
    private List<Memo> data;

    private MainActivity activity;


    public RecyclerViewAdapter(MainActivity activity, List<Memo> data) {

        super();
        this.data = data;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = MemoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        ViewHolder holder = new ViewHolder(binding.getRoot());
        binding.getRoot().setOnClickListener(activity.getItemClick()); // the click handler
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setMemo(data.get(position));
        holder.bindData();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public Memo getMemo(int position){
        return this.data.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private Memo memo;
        private TextView textLabel;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public Memo getMemo() {
            return memo;
        }

        public void setMemo(Memo memo) {
            this.memo = memo;
        }

        public void bindData() {

            if (textLabel == null) {
                textLabel = (TextView) itemView.findViewById(R.id.textLabel);
            }

            textLabel.setText(memo.toString());


        }

    }
}

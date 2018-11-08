package org.fbluemle.testcoinlist.ui;

import org.fbluemle.testcoinlist.R;
import org.fbluemle.testcoinlist.data.TickerRow;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TickerAdapter extends RecyclerView.Adapter<TickerAdapter.ViewHolder> {
    private final List<TickerRow> mItems = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_ticker, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TickerRow item = mItems.get(position);
        holder.textViewRank.setText(String.format(Locale.US, "%d.", item.rank));
        holder.textViewName.setText(item.name);
        holder.textViewPrice.setText(String.format(Locale.US, "$%.2f", item.price));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(List<TickerRow> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewRank;
        public TextView textViewName;
        public TextView textViewPrice;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRank = itemView.findViewById(R.id.textViewRank);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }
}

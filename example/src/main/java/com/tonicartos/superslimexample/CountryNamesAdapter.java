package com.tonicartos.superslimexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tonicartos.superslim.GridSLM;

import java.util.ArrayList;

/**
 *
 */
public class CountryNamesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0x01;

    private static final int VIEW_TYPE_CONTENT = 0x00;

    private static final int LINEAR = 0;

    private final ArrayList<LineItem> mItems;

    private int mHeaderDisplay;


    private final Context mContext;

    public CountryNamesAdapter(Context context, int headerMode) {
        mContext = context;

        final String[] countryNames = context.getResources().getStringArray(R.array.country_names);
        mHeaderDisplay = headerMode;

        mItems = new ArrayList<>();

        //Insert headers into list of items.
        String lastHeader = "";
        int sectionManager = -1;
        int headerCount = 0;
        int sectionFirstPosition = 0;
        for (int i = 0; i < countryNames.length; i++) {
            String header = countryNames[i].substring(0, 1);
            if (!TextUtils.equals(lastHeader, header)) {
                // Insert new header view and update section data.
                sectionManager = (sectionManager + 1) % 2;
                sectionFirstPosition = i + headerCount;
                lastHeader = header;
                headerCount += 1;
                mItems.add(new LineItem(header, true, sectionManager, sectionFirstPosition));
            }
            mItems.add(new LineItem(countryNames[i], false, sectionManager, sectionFirstPosition));
        }


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_item, parent, false);
            return new CountryViewHolder1(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.text_line_item, parent, false);
            return new CountryViewHolder2(view);
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final LineItem item = mItems.get(position);
        final View itemView = holder.itemView;
        if (getItemViewType(position) == VIEW_TYPE_HEADER){
            ((CountryViewHolder1) holder).bindItem(item.text);
        }else{
            ((CountryViewHolder2) holder).bindItem(item.text);
        }

        final GridSLM.LayoutParams lp = GridSLM.LayoutParams.from(itemView.getLayoutParams());
        // Overrides xml attrs, could use different layouts too.

        lp.setSlm(GridSLM.ID);
        lp.setNumColumns(2);
        lp.setFirstPosition(item.sectionFirstPosition);
        itemView.setLayoutParams(lp);
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).isHeader ? VIEW_TYPE_HEADER : VIEW_TYPE_CONTENT;
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }


    private static class LineItem {
        public int sectionManager;
        public int sectionFirstPosition;
        public boolean isHeader;
        public String text;
        public LineItem(String text, boolean isHeader, int sectionManager,
                        int sectionFirstPosition) {
            this.isHeader = isHeader;
            this.text = text;
            this.sectionManager = sectionManager;
            this.sectionFirstPosition = sectionFirstPosition;
        }
    }

    class CountryViewHolder1 extends RecyclerView.ViewHolder {

        private TextView mTextView;
        CountryViewHolder1(View view) {
            super(view);

            mTextView = (TextView) view.findViewById(R.id.text);
        }

        public void bindItem(String text) {
            mTextView.setText(text);
        }

        @Override
        public String toString() {
            return mTextView.getText().toString();
        }
    }

    class CountryViewHolder2 extends RecyclerView.ViewHolder {

        private TextView mTextView;

        CountryViewHolder2(View view) {
            super(view);

            mTextView = (TextView) view.findViewById(R.id.text);
        }

        public void bindItem(String text) {
            mTextView.setText(text);
        }

        @Override
        public String toString() {
            return mTextView.getText().toString();
        }
    }
}

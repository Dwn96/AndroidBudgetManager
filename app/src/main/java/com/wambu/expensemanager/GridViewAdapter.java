package com.wambu.expensemanager;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wambu.expensemanager.R;

public class GridViewAdapter extends BaseAdapter {

    Context context;
    private final String [] values;
    private  final int [] images;
    View view;
    LayoutInflater layoutInflater;

    public GridViewAdapter(Context context, String[] values, int[] images) {
        this.context = context;
        this.values = values;
        this.images = images;
    }


    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView==null){
            view=new View(context);
            view=layoutInflater.inflate(R.layout.single_item,null);
            ImageView imageView=(ImageView) view.findViewById(R.id.imageview);
            TextView textView=(TextView) view.findViewById(R.id.textview);
            imageView.setImageResource(images[position]);
            textView.setText(values[position]);


        }

        return view;
    }
}

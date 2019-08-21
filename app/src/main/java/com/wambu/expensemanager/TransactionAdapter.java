package com.wambu.expensemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wambu.expensemanager.R;
import com.wambu.expensemanager.Transaction;

import java.util.ArrayList;

class TransactionAdapter extends BaseAdapter {

    private ArrayList<Transaction> transactionList;
    private Context context;

    public TransactionAdapter(ArrayList<Transaction> list, Context cont){
        this.transactionList = list;
        this.context = cont;
    }

    @Override
    public int getCount() {
        return this.transactionList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.transactionList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if(convertView == null){
            LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.display_transactions, parent,false);

            holder = new ViewHolder();
            holder.amount = convertView.findViewById(R.id.t_amount);
            holder.description = convertView.findViewById(R.id.t_desc);
            holder.date = convertView.findViewById(R.id.t_date);
            holder.type = convertView.findViewById(R.id.t_type);


            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        Transaction transaction = transactionList.get(position);
        holder.amount.setText(String.valueOf(transaction.getAmount()));
        holder.description.setText(transaction.getDescription());
        holder.date.setText(transaction.getDate());
        holder.type.setText(transaction.getIncomeType());


        return convertView;
    }


    private static class ViewHolder{
        private TextView amount;
        private TextView description;
        private TextView date;
        private TextView type;

    }

}
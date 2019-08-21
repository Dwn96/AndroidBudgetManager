package com.wambu.expensemanager;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayTransaction extends Activity {

    ListView listView,listView2;
    DatabaseHelper databaseHelper;
    TransactionAdapter transcationAdapter;
    ArrayList<Transaction> arrayList;
    ArrayList<Transaction> arrayList2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_transaction_layout);



        listView = findViewById(R.id.display_listview);
        listView2=findViewById(R.id.display_listviewExp);
        databaseHelper=new DatabaseHelper(this);
        arrayList=new ArrayList<>();
        arrayList2=new ArrayList<>();




        fillListview();
        fillListviewExpense();

    }
    public void fillListview() {

        arrayList=databaseHelper.getAllData();
        transcationAdapter=new TransactionAdapter(arrayList,this);
        listView.setAdapter(transcationAdapter);


    }

    public void fillListviewExpense(){
        ExpenseDatabaseHelper expenseDatabaseHelper=new ExpenseDatabaseHelper(this);
        arrayList2=expenseDatabaseHelper.getAllData();
        transcationAdapter=new TransactionAdapter(arrayList2,this);
        listView2.setAdapter(transcationAdapter);

    }


}

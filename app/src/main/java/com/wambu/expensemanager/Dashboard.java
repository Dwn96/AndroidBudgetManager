package com.wambu.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class Dashboard extends Activity {
    GridView gridView;

    String [] values={"Add Income","Add Expense","Generate Reports","All transactions"};
    int [] images={R.mipmap.ic_addincome,R.mipmap.ic_addexpense,R.mipmap.ic_reports,R.mipmap.ic_alltransactions};




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);


        gridView=(GridView) findViewById(R.id.gridView);

       GridViewAdapter gridViewAdapter=new GridViewAdapter(this,values,images);
       gridView.setAdapter(gridViewAdapter);



       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //type new O to autogen
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               switch (position){
                   case 0:
                       Intent intent=new Intent(getApplicationContext(),AddIncome.class);
                       startActivity(intent);
                       break;


                   case 1:
                       Intent intent1=new Intent(getApplicationContext(),AddExpense.class);
                       startActivity(intent1);
                       break;

                   case 3:
                       Intent intent2=new Intent(getApplicationContext(),DisplayTransaction.class);
                       startActivity(intent2);
                       break;

                   case 2:
                       Intent intent3 = new Intent(getApplicationContext(),GenerateReports.class);
                       startActivity(intent3);
                       break;
               }




           }
       });

    }
}

package com.kampen.riks.app.rikskampen.leader.activity.fragments.plans;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.MainLeaderActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PlanDetailActivity extends AppCompatActivity {



    private LinearLayout containerLayout;

    private TextView prizeTitle,planType,planRange,planSinglePrice,planDoublePrice;

    boolean isPlanSelected=false;

    RadioGroup   group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);



        containerLayout=findViewById(R.id.containerLayout);

        prizeTitle=findViewById(R.id.prizeTitle);
        planRange=findViewById(R.id.planRange);
        planType=findViewById(R.id.planType);
        planRange=findViewById(R.id.planRange);
        planSinglePrice=findViewById(R.id.planSinglePrice);
        planDoublePrice=findViewById(R.id.planDoublePrice);

        group=findViewById(R.id.radioGroup);



      int   selectedPlan  =getIntent().getIntExtra("value",1);


        setLayout(selectedPlan);


        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                isPlanSelected=true;

            }
        });



    }



     private void setLayout(int plan)
     {
        // int drawable=plan

         switch(plan)
         {
             case  1:
             {

                 containerLayout.setBackgroundResource(R.drawable.plan_item_1_bg);

                 prizeTitle.setText("1st prize 25000 kr.");
                 planRange.setText("13-16 yrs");
                 planType.setText("Junior");
                 planSinglePrice.setText("2495 kr 110/month Single");
                 planDoublePrice.setText("3495 kr 155/month Double");


                 break;
             }
             case  2:
             {
                 containerLayout.setBackgroundResource(R.drawable.plan_item_2_bg);

                 prizeTitle.setText("1st prize 50000 kr.");
                 planRange.setText("16-45 yrs");
                 planType.setText("Vuxen");
                 planSinglePrice.setText("6995 kr 295/month Single");
                 planDoublePrice.setText("8995 kr 195/month Double");

                 break;
             }
             case  3:
             {
                 containerLayout.setBackgroundResource(R.drawable.plan_item_3_bg);

                 prizeTitle.setText("1st prize 50000 kr.");
                 planRange.setText("45-65 yrs");
                 planType.setText("Senior");
                 planSinglePrice.setText("6995 kr 295/month Single");
                 planDoublePrice.setText("8995 kr 195/month Double");

                 break;
             }
             case  4:
             {
                 containerLayout.setBackgroundResource(R.drawable.plan_item_4_bg);

                 prizeTitle.setText("1st prize 50000 kr.");
                 planRange.setText("65-100 yrs");
                 planType.setText("Veteran");
                 planSinglePrice.setText("4995 kr 215/month Single");
                 planDoublePrice.setText("6995 kr 145/month Double");


                 break;
             }
             case  5:
             {

                 prizeTitle.setText("1st prize 100000 kr.");
                 planRange.setText("13-100 yrs");
                 planType.setText("ELIT");
                 planSinglePrice.setText("7995 kr 336/month Single");
                 planDoublePrice.setText("9995 kr 215/month Double");

                 containerLayout.setBackgroundResource(R.drawable.plan_item_5_bg);
                 break;
             }
         }

     }


    public void onNextClick(View view) {


        Intent intent = new Intent(getApplicationContext(), MainLeaderActivity.class);
        startActivity(intent);
        finish();


    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void onTopClick(View view) {


        group.check(R.id.top);

    }

    public void onBottomClick(View view) {


        group.check(R.id.down);
    }
}

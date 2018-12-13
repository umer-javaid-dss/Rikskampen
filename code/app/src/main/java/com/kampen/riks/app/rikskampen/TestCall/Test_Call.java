package com.kampen.riks.app.rikskampen.TestCall;

public class Test_Call {


    private   void  performLoginOrsignUpForRider()
    {



        /*APIServiceWithSession service= API_Provider.provideApiWithSession();



        String  riderId = AppConstants.getRiderId(etPhoneNo.getText().toString(),myCountryCode.replace("+","00"));

        HashMap hm=new HashMap();

        hm.put("email_or_id",riderId);



        Call<Generic_Result<R_Rider>> call = service.isRiderAlreadyExist(hm);

        //calling the api
        call.enqueue(new Callback<Generic_Result<R_Rider>>() {
            @Override
            public void onResponse(Call<Generic_Result<R_Rider>> call, Response<Generic_Result<R_Rider>> response) {

                Generic_Result<R_Rider>  obj=null;

                obj=response.body();

                if(obj.getStatus()==1)
                {

                    SharedPreferenceHelper.setDriverLive(mContext,true);
                    fabProgressCircle.hide();
                    rootFrame.setAlpha(1f);


                    saveData(obj.getDataObject());

                    Intent riderIntent = new Intent(mContext, EnterSecurityCodeActivity.class);
                    riderIntent.putExtra(RIDER_COUNTRY_CODE_TAG,myCountryCode);
                    riderIntent.putExtra(RIDER_COUNTRY_CODE_TEXT_TAG,myCountryCodeTextValue);
                    riderIntent.putExtra(RIDER_CONTACT_TAG,etPhoneNo.getText().toString());
                    startActivity(riderIntent);


                    return;
                }
                else
                {
                    SharedPreferenceHelper.setRiderID(mContext,"");

                    Intent riderIntent = new Intent(mContext, EnterSecurityCodeActivity.class);
                    riderIntent.putExtra(RIDER_COUNTRY_CODE_TAG,myCountryCode);
                    riderIntent.putExtra(RIDER_COUNTRY_CODE_TEXT_TAG,myCountryCodeTextValue);
                    riderIntent.putExtra(RIDER_CONTACT_TAG,etPhoneNo.getText().toString());
                    startActivity(riderIntent);

                }

                fabProgressCircle.hide();
                rootFrame.setAlpha(1f);
                //MyApplication.showSimpleSnackBar(mContext,obj.getMsg());

            }

            @Override
            public void onFailure(Call<Generic_Result<R_Rider>> call, Throwable t) {

                t.toString();


            }
        });*/

    }




}

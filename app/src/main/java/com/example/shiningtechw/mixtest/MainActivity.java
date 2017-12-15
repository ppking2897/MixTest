package com.example.shiningtechw.mixtest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MySQLiteHelper helper;
    private SQLiteDatabase database;
    private Bitmap bitmap;
    private ImageView testImage ;
    private MyHandler myHandler = new MyHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testImage = findViewById(R.id.testImage);

        helper = new MySQLiteHelper(this , "Infomation" , null , 1);

        database = helper.getWritableDatabase();


    }


    public void showAllData(){
        Cursor mCursor = helper.getAllData(database);
        List<String> listID = new ArrayList<>();
        List<String> listBrand = new ArrayList<>();
        List<String> listInfo = new ArrayList<>();

        if (mCursor!=null){
            mCursor.moveToFirst();
            for (int i = 0 ; i <mCursor.getCount() ; i++){

                listID.add(mCursor.getString(0));
                listBrand.add(mCursor.getString(1));
                listInfo.add(mCursor.getString(2));
                mCursor.moveToNext();
            }
        }
        Log.v("ppking" , " All Data ID : " + listID.toString());
        Log.v("ppking" , " All Data Brand : " + listBrand.toString());
        Log.v("ppking" , " All Data Location : " + listInfo.toString());
    }

    public void getData(View view) {

        List<String> YAMAHAList = new ArrayList<>();
        YAMAHAList.add("https://lh3.googleusercontent.com/52XE65082F4Uqq8-gj_Kp3QjjyUHp6bN7ZKEK0e2xGzmdwTz8WsL_LflBC7BfpVwpzlCuONuy1N6F_Pl05z7qD093g3cLFNQ3oM_eZFZx4z8jv5xyKdAr9ZPAZBBNmuWJkeg7Xr7IVfPO0AlOQDxQqumP6Sd-ciBcBu-X1Nss3v7MOMLzM-6uP3Ow56SXcTeSAjuFX1N0Qb64FlKsQ2PWCIbmaZqt4XqX3BRk0djIzFWzN4k-k_4oVSOnbRhhMb4JuMNQx7MDAGw1_B0df87UCL4NDfR3A1ug05mAg6MCUa4UqnCD4PRbQKSmnPPzFV0kriNbfLBMj-ZzXY2ufBzxOiVnh6WgUxrYD34bU-A5Q1E3-wTnluIgyaKqFwkLf3ya0LlbBjl13un7hXBg3Ur-mNstXSRLZ8T6Wywho9oOrK-0XbpXuuiDm9hXk4GmyXr9F7CzFKLV_iPpNoCGADv-9oL-53lhwDHCgiuHAO2g3JqpBQLCs3l2hWddnUfEDH-MvWGuVhoBrtUqt9OSUJ-aXYqJX7AH4iLySKQ9bPnh5w9KjVuwVrp53hO4z00mMFSvPy4PztFMntPNuDpiVxddVBaEGQ8az6TzPLqcUMU0nE=w1223-h949-no");
        YAMAHAList.add("https://lh3.googleusercontent.com/LHEipEu0xpQN6AZmjH5eZBSjqSTbmf5f38TGE9mbE5dAKQLzr4b7L60ao8lhcD4HMoRXc32r9zWICHB3iQINo2YRfjW3ZfZKb4H2cR09grsit78O0GmqoKVlR3DvzLBqxF4zndO_vf7MbmTklvQh2jxGxzuksINjHyvxpteFpih297h8UWPV1Q99e4bZ4uh4eJs7-ee_jW5YtchVRSXSetfQHWf2LDTduTwGM3rZ94ilVJ5KXUJolMzwicBW8rVthpoNV9bNZdJEztBrbR9q-L1fYGH0WTNu9uSyQh7A1wvDK52sb04yv9CXtM8mo0LWL1gw4pT6DOW7Pyehr0X3D-ajP5peX7yXypKOJ1RV_xSLvzgNN-uyAlJQRqjlrP1ftAoHC2ZpdUV7ol6tgl4Ew3TWHAenhfE1DnAqzKeQUOa2WQrCj3S6KnEyaTy7Lya0SPfwQgUp3agllb9qxjFZBE47p_b22K8PHOqQw5QX0AucY0kL1ATDdMEevhVnWeAlKJzBnWWNbuzidFk2O4MiT3WNCQg0NWE3JN3jmJjjg-r7id7tzKr4_4vXNn2pnGMFQuCFfmne5B5UzEsEp4SKmSumxMoHVmECeni9AeR2--Y=s300-no");

        List<String> SYMList = new ArrayList<>();
        SYMList.add("https://lh3.googleusercontent.com/JkFFnok6bosvwq-zgLHoDw3bLl6nmJ2UMdQC_86UZAsMy2dI8jHVt3f9pTSD7EVxRwihZfAag5pUzd-MfVW4v0boAgIGQa_RG5VU-BoTnKlRZry8fARgv5oWiyeUobELt6VndWzO25wczD4UMYRPuuRmGuyOrHnJN5GJUK9sNCxDtm6QyZG7op4t7e43CSiVf7FK3PrKHo1aaOsIWsPBj7aVISp9JwhvXUt1O8bo3pgXVPTDLOraIryWRTVFBN_Hsra04Acpw7tduGSr98O6YxyD5pjojIaIbaDaF6WRUNv6AHw5Rk68r06lWPOH3Uyh2WVjm-HlKjh8N7GbafJgI27i-3THyi9McYKIVbK-1_24yGEWUcoZwNgCG0SDvE3ntqB1gpUCyi7S9RNziWE7-zZ4bSxBgfN1PGi_Kky-CbmKtrtYX9DTrcHC9yyv-Ef7_z5_TIl-H7OLMdBISL1uXhEILlTUhWY1tKKly1QY3lI4knmuOHAvk9hcEUPPSyZZN987tIi2TbDeFoGWcFUSv4NBSQ-jJjZsnrDxonvlleYm1HbutyuJ7QWCL7JVhnYTf5bPxX87vgTz-fyuEtcDnQKWAW61T4ZMEWVQ7hrguYA=w300-h256-no");
        SYMList.add("https://lh3.googleusercontent.com/tuyu7V1_B2YnTlhc0cQOetc3qV5P_7Eh3HBXhHvkpeysfkGv7_T0QbUcMAXKejDNsX6x66l58b2ckPjWV6PWqP7QWl4-FAjinvYIAaACMmP8qYUkp7CNCp-MYTBbg4KJzUH22oMqQsIXhCi6WMQfgeSDmb3-PFqqhiJ161-0aItChoy231oYylj1YmuXD2_bT8KbwzftidhA28hQyzdlvY2-M2RMRwVbZwjmEGr4D_Rt7cvoOpYFoebd8-FUD84MYaa0tjH1oC-2VrcfX1Kb8XvwFK1qX6wpwFvZjroYbmPE50XJouxRx8mthtPI65lVvSoeTzkYKmUOhmmlriPSwno3-bfthXRuBGpAjxeqcWltvKGGGc3UmWFPGecYN9jEOLhalsaWRy8-8hpT73u78IqYvFtEJSz3SqWzOBl_0iDAQkfFwkZFKVot2FMC3tNSVVYjO58C_a8FLogJYmhxqWpPSJO4GhJo9oGt0Nd-9Ynurl9G6BZH1B0fqO6p3qzmuhYa-nCLwbKma_tEawiybmYjwhVdAU6cbez3BpZpaYKVxWyu-rLWGEYNGHA1RlwaHraK8scdGr0XNmIHxWrMef8VS_dL9B_j91Jw7lktD3s=w300-h256-no");


        HashMap<String , List<String>> hashMap = new HashMap<>();
        hashMap.put("YAMAHA"  , YAMAHAList);
        hashMap.put("SYM" , SYMList);


        List<String> list = hashMap.get("YAMAHA");

        for (int i = 0 ; i<list.size(); i++){
            //helper.insertInfo(database , "YAMAHA" , list.get(i));
        }

        MyRunnable myRunnable = new MyRunnable(list.get(0));
        new Thread(myRunnable).start();
    }

    public void showPicture(View view) {

    }


    public class MyRunnable implements Runnable{
        private String dataInfo;
        public MyRunnable(String dataInfo){
            this.dataInfo =dataInfo;
        }
        @Override
        public void run() {

            try {
                Bitmap bitmap =connectTheUrl(dataInfo);
                saveTheData(bitmap);
                //myHandler.sendEmptyMessage(0);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Bitmap connectTheUrl(String dataInfo) throws Exception{
        URL url = new URL(dataInfo);

        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        InputStream inputStream  = connection.getInputStream();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        int len;

        while ((len = inputStream.read(buffer))!=-1){
            outputStream.write(buffer , 0 , len);
        }

        inputStream.close();
        outputStream.close();


        byte[] data = outputStream.toByteArray();
        bitmap = BitmapFactory.decodeByteArray(data , 0 , data.length);

        return bitmap;
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            testImage.setImageBitmap(bitmap);
        }
    }

    public void saveTheData(Bitmap bm){
        //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/date.jpg");
        File dir = buildDir();

        File file = new File(dir , "picture.jpg");
        try {
            Log.v("ppking" , "Success !  "+file.getPath() );
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

            Bitmap returnBitmap = Bitmap.createBitmap(bm ,0 , 0 , bm.getWidth() , bm.getHeight() , null , true);
            returnBitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);

            bos.flush();
            bos.close();
        } catch (Exception e) {
            Log.v("ppking" , "Exception :  " +e.toString());
            e.printStackTrace();
        }
    }

    public File buildDir(){
        //建立資料夾,並檢查是否有建立成功
        File fileParent = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "ALBUM");
        if (!fileParent.mkdirs()){
            Log.e("", "Directory not created or exist");
        }
        return fileParent;
    }

}

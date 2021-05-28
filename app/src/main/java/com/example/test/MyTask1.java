package com.example.test;

import android.util.Base64;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MyTask1 {
    public void executeMyTask(){
        System.out.println("мы тут");
        Thread thread = new Thread() {
            @Override
            public void run() {
                String SOAP_ACTION = "https://www.w3schools.com/xml/FahrenheitToCelsius";
                String METHOD_NAME = "IsUserLoginFree"; //метод веб-сервиса
                String NAMESPACE = "https://www.w3schools.com/xml/";
                String URL = "http://255.255.255.255:777/"; //  адрес веб-сервиса
                String username = "log";
                String password = "pass";

                //Initialize soap request
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                //Use this to add parameters
                request.addProperty("Login", "emega2@mail.ru"); // веб-сервис принимает один параметр text в виде строки

                //Declare the version of the SOAP request
                try {
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.dotNet = true;
                    envelope.setOutputSoapObject(request);

                    //Needed to make the internet call
                    HttpTransportSE transport = new HttpTransportSE(URL);
                   // List<HeaderProperty> headerList = new ArrayList<HeaderProperty>();
                    //headerList.add(new HeaderProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes())));

                    transport.call(SOAP_ACTION, envelope);
                    //Get the Response from the envelope body.
                    //SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
                }catch (Exception e){
                    Log.e("TESTS", "KSOAP2", e);
                }

            }
        };

        thread.start();
    }
}

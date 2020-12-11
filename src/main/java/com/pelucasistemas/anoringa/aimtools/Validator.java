package com.pelucasistemas.anoringa.aimtools;



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.IOException;

public class Validator {


    public static Boolean sendToValitation(String token) throws IOException, NullPointerException {

        String SECRET_KEY = "0x653D78Bfd96255a7d25d2e7BA8724901fAF9c818";
        String VERIFY_URL = "https://hcaptcha.com/siteverify";



        /*
        JsonObject jsonObject = new JsonObject();


        jsonObject.addProperty("secret", SECRET_KEY);
        jsonObject.addProperty("response", token);



        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        */

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("secret", "0x653D78Bfd96255a7d25d2e7BA8724901fAF9c818")
                .addFormDataPart("response", token)
                .build();
        Request request = new Request.Builder()
                .url("https://hcaptcha.com/siteverify")
                .method("POST", body)
                //.addHeader("Cookie", "__cfduid=d144c55b11455474dad7e963613e281bd1607105427; INGRESSCOOKIE=1607107786.253.1136.201600")
                .build();
        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();

        System.out.println(responseBody);
        JsonObject jsonObject2 = new Gson().fromJson(responseBody, JsonObject.class);

        //return jsonObject.get("success"); // returns a JsonElement for that name

        //return jsonObject.getAsJsonObject("success").getAsBoolean();

        boolean success = jsonObject2.get("success").getAsBoolean();
        System.out.println("success");
        System.out.println(success);


        /*
            RequestBody body = RequestBody.create(jsonObject.getAsString(), JSON); // new
            // RequestBody body = RequestBody.create(JSON, json); // old
            Request request = new Request.Builder()
                    .url(VERIFY_URL)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            System.out.println(responseBody);

            JsonObject jsonObject2 = new Gson().fromJson(responseBody, JsonObject.class);

            //return jsonObject.get("success"); // returns a JsonElement for that name

            //return jsonObject.getAsJsonObject("success").getAsBoolean();

            boolean success = jsonObject2.get("success").getAsBoolean();
            System.out.println("success");
            System.out.println(success);
        */
            return success;
    }
    public static Boolean sendToValitationOld(String token) throws IOException, NullPointerException {

        String SECRET_KEY = "0x653D78Bfd96255a7d25d2e7BA8724901fAF9c818";
        String VERIFY_URL = "https://hcaptcha.com/siteverify";

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("hcaptcha.com")
                .addPathSegment("siteverify")
                .addQueryParameter("secret", SECRET_KEY)
                .addQueryParameter("response", token)
                // Each addPathSegment separated add a / symbol to the final url
                // finally my Full URL is:
                // https://subdomain.apiweb.com/api/v1/students/8873?auth_token=71x23768234hgjwqguygqew
                .build();

        System.out.println(httpUrl.toString());

        Request requesthttp = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl) // <- Finally put httpUrl in here
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(requesthttp).execute();
        //return response.body().string();

        String stringContent =  response.body().string();
        System.out.println("stringContent");
        System.out.println(stringContent);

        //JsonObject jsonObject = Gson().fromJson( stringContent, JsonObject.class);

        JsonObject jsonObject = new Gson().fromJson(stringContent, JsonObject.class);

        //return jsonObject.get("success"); // returns a JsonElement for that name

        //return jsonObject.getAsJsonObject("success").getAsBoolean();

        boolean success = jsonObject.get("success").getAsBoolean();
        System.out.println("success");
        System.out.println(success);
        return success;
    }


}
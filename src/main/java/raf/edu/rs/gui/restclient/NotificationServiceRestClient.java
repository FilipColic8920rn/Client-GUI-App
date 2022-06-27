package raf.edu.rs.gui.restclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import raf.edu.rs.gui.MainFrame;
import raf.edu.rs.gui.restclient.dto.*;
import java.io.IOException;

public class NotificationServiceRestClient {
    public static final String URL = "http://localhost:8083/api";
    //public static final String URL = "http://localhost:8084/email-service/api";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public SentEmailListDto getAllNotifications() throws IOException {
        Request request = new Request.Builder()
                .url(URL + "/email/allSentAdmin")
                .addHeader("Authorization", "Bearer " + MainFrame.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();
            SentEmailListDto dto = objectMapper.readValue(json, SentEmailListDto.class);

            return dto;
        }

        throw new RuntimeException("Error getting all sent notifications");
    }
    public SentEmailListDto getAllNotificationsByEmail(String email) throws IOException {
        Request request = new Request.Builder()
                .url(URL + "/email/allSent/"+email)
                .addHeader("Authorization", "Bearer " + MainFrame.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();
            SentEmailListDto dto = objectMapper.readValue(json, SentEmailListDto.class);

            return dto;
        }

        throw new RuntimeException("Error getting all sent notifications");
    }

    public SentEmailListDto getAllNotificationsFiltered(String email, String type, String date) throws IOException{
        if (email.equals(""))
            email = "-1";
        if (type.equals(""))
            type = "-1";
        if (date.equals(""))
            date = "-1";

        Request request = new Request.Builder()
                .url(URL + "/email/filter/"+email+"/"+type+"/"+date)
                .addHeader("Authorization", "Bearer " + MainFrame.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();
            SentEmailListDto dto = objectMapper.readValue(json, SentEmailListDto.class);

            return dto;
        }

        throw new RuntimeException("Error getting all sent notifications");
    }


    public EmailNotificationListDto getAllNotificationTypes() throws IOException{
        Request request = new Request.Builder()
                .url(URL + "/email/type")
                .addHeader("Authorization", "Bearer " + MainFrame.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();
            EmailNotificationListDto dto = objectMapper.readValue(json, EmailNotificationListDto.class);

            return dto;
        }

        throw new RuntimeException("Error getting all sent notifications");
    }

    public void changeNotiftype(Long id, String text) throws IOException {
        EmailNotificationDto dto = new EmailNotificationDto();
        dto.setId(id);
        dto.setText(text);

        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(dto));

        Request request = new Request.Builder()
                .url(URL + "/email/type")
                .addHeader("Authorization", "Bearer " + MainFrame.getInstance().getToken())
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.code() == 200){
            System.out.println("here");
        }
        else
            throw new RuntimeException("Error while changing notification type");
    }

    public void deleteNotificationType(Long id) throws IOException {
        Request request = new Request.Builder()
                .url(URL + "/email/type/"+id)
                .addHeader("Authorization", "Bearer " + MainFrame.getInstance().getToken())
                .delete()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.code() == 200){
            System.out.println("here");
        }
        else
            throw new RuntimeException("Error while deleting notification type");
    }
}
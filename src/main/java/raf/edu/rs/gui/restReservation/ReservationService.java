package raf.edu.rs.gui.restReservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import raf.edu.rs.gui.MainFrame;
import raf.edu.rs.gui.restReservation.dto.Hotel;
import raf.edu.rs.gui.restReservation.dto.Reservation;
import raf.edu.rs.gui.restReservation.dto.Review;
import raf.edu.rs.gui.restReservation.dto.Room;
import raf.edu.rs.gui.restclient.dto.UserCreateDto;
import raf.edu.rs.gui.restclient.dto.UserDto;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    public static final String URL = "http://localhost:8082/api";
    //public static final String URL = "http://localhost:8084/reservation-service/api";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public void addNewHotel(String city, String name, String description, Integer numberOfRooms) throws IOException {
        String token = MainFrame.getInstance().getToken();
        Long managerId = TokenDecoder.getId(token);

        Hotel hotel = new Hotel(managerId, city, name, description, numberOfRooms);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(hotel));

        Request request = new Request.Builder()
                .url(URL + "/hotel")
                .addHeader("Authorization", "Bearer " + token)
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful()) {
            UserDto userDto = MainFrame.getInstance().getCurrentUser();
            UserCreateDto userCreateDto = new UserCreateDto(userDto.getId(), null, null,
                    null, null, null, null,
                    null, hotel.getName(), null, null,
                    null);

            MainFrame.getInstance().getUserServiceRestClient().changeUser(userCreateDto);
            JOptionPane.showMessageDialog(null, "Hotel added successfully!");
        }
        else
            throw new RuntimeException("Cannot add a new hotel!");
    }

    public void editHotel(String city, String name, String description, Integer numberOfRooms) throws IOException {
        String token = MainFrame.getInstance().getToken();
        Long managerId = TokenDecoder.getId(token);

        Hotel hotel = new Hotel(managerId, city, name, description, numberOfRooms);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(hotel));

        Request request = new Request.Builder()
                .url(URL + "/hotel")
                .addHeader("Authorization", "Bearer " + token)
                .put(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful()) {
            UserDto userDto = MainFrame.getInstance().getCurrentUser();
            UserCreateDto userCreateDto = new UserCreateDto(userDto.getId(), null, null,
                    null, null, null, null,
                    null, hotel.getName(), null, null,
                    null);

            MainFrame.getInstance().getUserServiceRestClient().changeUser(userCreateDto);
            JOptionPane.showMessageDialog(null, "Hotel updated successfully!");
        }
        else
            throw new RuntimeException("Cannot update a hotel!");
    }

    public void addRoom(Integer roomNumber, String type, Double pricePerDay) throws IOException {
        String token = MainFrame.getInstance().getToken();

        Room room = new Room(-1L, roomNumber, type, pricePerDay);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(room));

        Request request = new Request.Builder()
                .url(URL + "/room")
                .addHeader("Authorization", "Bearer " + token)
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful()) {
            JOptionPane.showMessageDialog(null, "Room added successfully!");
        }
        else
            throw new RuntimeException("Cannot add a new room!");
    }

    public void editRoom(Integer curNumber, Integer roomNumber, String type, Double pricePerDay) throws IOException {
        String token = MainFrame.getInstance().getToken();

        Room room = new Room(-1L, roomNumber, type, pricePerDay);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(room));

        Request request = new Request.Builder()
                .url(URL + "/room/" + curNumber)
                .addHeader("Authorization", "Bearer " + token)
                .put(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful()) {
            JOptionPane.showMessageDialog(null, "Room updated successfully!");
        }
        else
            throw new RuntimeException("Cannot update a room!");
    }

    public void editRooms(Double startPrice, Double endPrice, String type) throws IOException {
        String token = MainFrame.getInstance().getToken();

        HttpUrl.Builder httpBuilder = HttpUrl.parse(URL + "/room/rangeUpdate/").newBuilder();
        httpBuilder.addQueryParameter("startPrice", startPrice.toString());
        httpBuilder.addQueryParameter("endPrice", endPrice.toString());
        httpBuilder.addQueryParameter("type", type);

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader("Authorization", "Bearer " + token)
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful())
            JOptionPane.showMessageDialog(null, "Successfully updated!");
        else
            throw new RuntimeException("Cannot add a new room!");
    }

    public List getAvailableRooms(String city, String hotelName, String roomType, String startDate, String endDate,
                                                                                    String sort) throws IOException {
        // lista slobodnih termina
        List availableRooms;
        String token = MainFrame.getInstance().getToken();

        HttpUrl.Builder httpBuilder = HttpUrl.parse(URL + "/room/list").newBuilder();
        httpBuilder.addQueryParameter("city", city);
        httpBuilder.addQueryParameter("hotelName", hotelName);
        httpBuilder.addQueryParameter("roomType", roomType);
        httpBuilder.addQueryParameter("startDate", startDate);
        httpBuilder.addQueryParameter("endDate", endDate);
        httpBuilder.addQueryParameter("sort", sort);

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader("Authorization", "Bearer " + token)
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful())
            availableRooms = objectMapper.readValue(response.body().string(), List.class);
        else
            throw new RuntimeException("Cannot get rooms!");

        return availableRooms;
    }

    public void createReservation(Long hotelId, Long roomId, String startDate,
                                  String endDate) throws IOException {
        String token = MainFrame.getInstance().getToken();
        Reservation reservation = new Reservation(0L, TokenDecoder.getId(token), "", hotelId, roomId,
                startDate, endDate, 0.0);

        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(reservation));

        Request request = new Request.Builder()
                .url(URL + "/reservation")
                .addHeader("Authorization", "Bearer " + token)
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful())
            JOptionPane.showMessageDialog(null, "Room added successfully!");
        else
            throw new RuntimeException("Cannot add a new room!");
    }

    public List findRelatedReservations() throws IOException {
        String token = MainFrame.getInstance().getToken();

        Request request = new Request.Builder()
                .url(URL + "/reservation/manager")
                .addHeader("Authorization", "Bearer " + token)
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful()) {
            List reservations = objectMapper.readValue(response.body().string(), List.class);
            System.out.println(reservations);
            return reservations;
        }
        else return new ArrayList<>();
    }

    public void cancelReservation(Long reservationId) throws IOException {
        String token = MainFrame.getInstance().getToken();
        RequestBody body = RequestBody.create(JSON, reservationId.toString());

        Request request = new Request.Builder()
                .url(URL + "/reservation/" + reservationId)
                .addHeader("Authorization", "Bearer " + token)
                .delete(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful())
            JOptionPane.showMessageDialog(null, "Reservation canceled!");
        else
            throw new RuntimeException("ERROR");
    }

    public void newReview(Review review) throws IOException {
        String token = MainFrame.getInstance().getToken();
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(review));

        Request request = new Request.Builder()
                .url(URL + "/review")
                .addHeader("Authorization", "Bearer " + token)
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful())
            JOptionPane.showMessageDialog(null, "Review added successfully!");
        else
            throw new RuntimeException("Error while adding review");
    }

    public void updateReview(Long id, Review review) throws IOException {
        String token = MainFrame.getInstance().getToken();
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(review));

        Request request = new Request.Builder()
                .url(URL + "/review/" + id)
                .addHeader("Authorization", "Bearer " + token)
                .put(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful())
            JOptionPane.showMessageDialog(null, "Review added successfully!");
        else
            throw new RuntimeException("Error while adding review");
    }

    public void deleteReview(Long reviewId) throws IOException {
        String token = MainFrame.getInstance().getToken();
        RequestBody body = RequestBody.create(JSON, reviewId.toString());
        System.out.println("Brisem review" + reviewId);

        Request request = new Request.Builder()
                .url(URL + "/review")
                .addHeader("Authorization", "Bearer " + token)
                .delete(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful())
            JOptionPane.showMessageDialog(null, "Review removed!");
        else
            throw new RuntimeException("Cannot remove review!");
    }

    public List getAllReviews(String hotelName, String city) throws IOException {
        List reviews;
        String token = MainFrame.getInstance().getToken();

        HttpUrl.Builder httpBuilder = HttpUrl.parse(URL + "/review/all").newBuilder();
        httpBuilder.addQueryParameter("hotelName", hotelName);
        httpBuilder.addQueryParameter("city", city);

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader("Authorization", "Bearer " + token)
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful())
            reviews = objectMapper.readValue(response.body().string(), List.class);
        else
            throw new RuntimeException("ERROR");

        return reviews;
    }

    public List getTopRatedHotels() throws IOException {
        List reviews;
        String token = MainFrame.getInstance().getToken();

        Request request = new Request.Builder()
                .url(URL + "/review")
                .addHeader("Authorization", "Bearer " + token)
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful())
            reviews = objectMapper.readValue(response.body().string(), List.class);
        else
            throw new RuntimeException("ERROR");

        return reviews;
    }
}
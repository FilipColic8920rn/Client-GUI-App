package raf.edu.rs.gui.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import raf.edu.rs.gui.MainFrame;
import raf.edu.rs.gui.restclient.dto.*;

import java.io.IOException;

public class UserServiceRestClient {
	//public static final String URL = "http://localhost:8084/user-service/api";
	public static final String URL = "http://localhost:8081/api";
	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

	OkHttpClient client = new OkHttpClient();
	ObjectMapper objectMapper = new ObjectMapper();

	public String login(String email, String password) throws IOException {
		TokenRequestDto tokenRequestDto = new TokenRequestDto(email, password);
		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(tokenRequestDto));

		Request request = new Request.Builder()
			.url(URL + "/user/login")
			.post(body)
			.build();

		Call call = client.newCall(request);

		Response response = call.execute();
		System.out.println(request.toString());
		if (response.code() == 200) {
			String json = response.body().string();
			TokenResponseDto dto = objectMapper.readValue(json, TokenResponseDto.class);

			return dto.getToken();
		}

		throw new RuntimeException("Invalid username or password");
	}

	public void registerClient(String name, String lastname, String username, String password, String email,
				String dateOfBirth, String phoneNumber, String passportNumber) throws RuntimeException, IOException {
		UserCreateDto dto = new UserCreateDto();
		dto.setFirstName(name);
		dto.setLastName(lastname);
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setEmail(email);
		dto.setDateOfBirth(dateOfBirth);
		dto.setPhoneNumber(phoneNumber);
		dto.setPassportNumber(passportNumber);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(dto));

		Request request = new Request.Builder()
				.url(URL + "/user/register/client")
				//.addHeader("Authorization", "Bearer " + MainFrame.getInstance().getToken())
				.post(body)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();

		if (response.isSuccessful())
			System.out.println("Registration successful!");
		else
			throw new RuntimeException("Username or email already in use");
	}
	public void registerManager(String name, String lastname, String username, String password, String email,
							   String dateOfBirth, String phoneNumber, String dateOfEmp) throws RuntimeException, IOException {
		UserCreateDto dto = new UserCreateDto();
		dto.setFirstName(name);
		dto.setLastName(lastname);
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setEmail(email);
		dto.setDateOfBirth(dateOfBirth);
		dto.setPhoneNumber(phoneNumber);
		dto.setDateOfEmployment(dateOfEmp);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(dto));

		Request request = new Request.Builder()
				.url(URL + "/user/register/manager")
				//.addHeader("Authorization", "Bearer " + MainFrame.getInstance().getToken())
				.post(body)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();

		if (response.isSuccessful())
			System.out.println("Registration successful!");
		else
			throw new RuntimeException("Username or email already in use");
	}

	public void sendChangePasswordRequest(String email) throws IOException {

		Request request = new Request.Builder()
				.url(URL + "/user/passwordChange/request/"+email)
				.get()
				.build();

		System.out.println(request.toString());
		Call call = client.newCall(request);
		Response response = call.execute();
		System.out.println(response.code()+"");

		if (response.code() == 200){
			System.out.println("here");
			//System.out.println(response.body().string());
			//return objectMapper.readValue(response.body().string(), UserDto.class);
		}
		else
			throw new RuntimeException("User with email not found");

	}

	public void changePassword(String email, String code, String newPassword) throws IOException{
		ChangePasswordRequestDto dto = new ChangePasswordRequestDto();
		dto.setEmail(email);
		dto.setToken(code);
		dto.setNewPassword(newPassword);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(dto));
		Request request = new Request.Builder()
				.url(URL + "/user/passwordChange")
				.post(body)
				.build();

		System.out.println(request.toString());
		Call call = client.newCall(request);
		Response response = call.execute();
		System.out.println(response.code()+"");

		if (response.code() == 200){
			System.out.println("here");
			//System.out.println(response.body().string());
			//return objectMapper.readValue(response.body().string(), UserDto.class);
		}
		else
			throw new RuntimeException("User with email not found");
	}

	public AllUsersDto getAllUsers() throws IOException {
		Request request = new Request.Builder()
				.url(URL + "/user")
				.addHeader("Authorization", "Bearer " + MainFrame.getInstance().getToken())
				.get()
				.build();
		Call call = client.newCall(request);
		Response response = call.execute();
		if (response.code() == 200){
			System.out.println("here");
			return objectMapper.readValue(response.body().string(), AllUsersDto.class);
		}
		else
			throw new RuntimeException("Error while getting all users");
	}

	public UserDto getCurrentUser(Long id) throws IOException {
		Request request = new Request.Builder()
				.url(URL + "/user/get/"+id)
				.addHeader("Authorization", "Bearer " + MainFrame.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();
		if (response.code() == 200){
			System.out.println("here");
			return objectMapper.readValue(response.body().string(), UserDto.class);
		}
		else
			throw new RuntimeException("Error while getting user");
	}

	public void blockUser(Long id) throws IOException {

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(id));

		Request request = new Request.Builder()
				.url(URL + "/user/block")
				.addHeader("Authorization", "Bearer " + MainFrame.getInstance().getToken())
				.post(body)
				.build();
		Call call = client.newCall(request);
		Response response = call.execute();
		if (response.code() == 200){
			System.out.println("here");
		}
		else
			throw new RuntimeException("Error while blocking user");
	}

	public AllRanksDto getAllRanks() throws IOException {
		Request request = new Request.Builder()
				.url(URL + "/user/rank")
				.addHeader("Authorization", "Bearer " + MainFrame.getInstance().getToken())
				.get()
				.build();
		Call call = client.newCall(request);
		Response response = call.execute();
		if (response.code() == 200){
			System.out.println("here");
			return objectMapper.readValue(response.body().string(), AllRanksDto.class);
		}
		else
			throw new RuntimeException("Error while getting all ranks");
	}

	public void changeRank(RankDto rankDto) throws IOException{
		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(rankDto));

		Request request = new Request.Builder()
				.url(URL + "/user/changeRank")
				.addHeader("Authorization", "Bearer " + MainFrame.getInstance().getToken())
				.post(body)
				.build();
		Call call = client.newCall(request);
		Response response = call.execute();
		if (response.code() == 200){
			System.out.println("here");
		}
		else
			throw new RuntimeException("Error while changing rank");
	}

	public void changeUser(UserCreateDto userCreateDto) throws IOException {
		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(userCreateDto));

		Request request = new Request.Builder()
				.url(URL + "/user/change")
				.addHeader("Authorization", "Bearer " + MainFrame.getInstance().getToken())
				.post(body)
				.build();
		Call call = client.newCall(request);
		Response response = call.execute();
		System.out.println(response.code());
		if (response.code() == 200){
			System.out.println("here");
		}
		else
			throw new RuntimeException("Error while changing user");
	}
}
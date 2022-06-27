package raf.edu.rs.gui.restReservation;

import raf.edu.rs.gui.restclient.dto.UserDto;

import java.util.Base64;

public class TokenDecoder {
    public static Long getId(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String header = new String(decoder.decode(chunks[0]));
        final String payload = new String(decoder.decode(chunks[1]));
        String id = "";

        for (int i = 6; i < payload.length(); ++i) {
            if (payload.startsWith("id", i-4)) {
                while (payload.charAt(i) >= '0' && payload.charAt(i) <= '9')
                    id += payload.charAt(i++);

                break;
            }
        }

        return Long.parseLong(id);
    }

    public static UserDto decodeToken(String token){
        String[] split = token.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(split[1]));
        payload = payload.replace("}","");
        payload = payload.replace("{","");
        payload = payload.replace(","," ");
        payload = payload.replace("\"", "");
        String[] claims = payload.split(" ");

        UserDto userDto = new UserDto();
        for (String claim: claims){
            System.out.println(claim);
            if (claim.contains("id:"))
                userDto.setId(Long.parseLong(claim.split(":")[1]));
            else if (claim.contains("email:"))
                userDto.setEmail(claim.split(":")[1]);
            else if (claim.contains("role:"))
                userDto.setRole(claim.split(":")[1]);
        }
        return userDto;
    }
}
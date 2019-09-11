package ak.inzynierka.core.utility;

import ak.inzynierka.core.MainActivity;
import ak.inzynierka.model.AuthenticationRequest;
import ak.inzynierka.model.RegisterRequest;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.springframework.http.*;

import java.util.Collections;

public class EntityUtil {

    public static HttpEntity getAuthorizationEntity(){
        HttpAuthentication httpAuthorization = new HttpBasicAuthentication("test", "test");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAuthorization(httpAuthorization);
        requestHeaders.add("Authorization", MainActivity.TOKEN);
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(requestHeaders);
    }

    public static HttpEntity getAuthenticationEntity(String username, String passowrd){
        AuthenticationRequest ar = new AuthenticationRequest();
        ar.setUsername(username);
        ar.setPassword(passowrd);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(ar,requestHeaders);
    }

    public static HttpEntity getAuthenticationEntityAndRegister(RegisterRequest registerRequest){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(registerRequest,requestHeaders);
    }

}

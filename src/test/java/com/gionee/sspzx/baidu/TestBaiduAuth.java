package com.gionee.sspzx.baidu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import com.baidu.ub.api.UbapiClient;

public class TestBaiduAuth {
	
    private static final String PRIVATE_KEY
    = "-----BEGIN DSA PRIVATE KEY-----\n"
		+ "MIIDPgIBAAKCAQEAzavh5nq85hFBLNBPdDTu3Xa6s9J0B7Cscn1HUZnjjB8S+E15\n"
		+ "26pyDWlWKGDMdXDfradl3Rn34pCYcYYawpmgih+hwNgQmJBYpQMPj0cPDkQy+QP8\n"
	    + "r2M3P15N1qR/JCbN4+Q3MyoN7Q6Wh3bSIE1dOKDTc4PGn1DZRqKj1ilLSe16uV7n\n"
	    + "AqwUgG1vf459F9RtRUwDQK3AyN7X++0LNDRtpwg2AgnFzUwVCqnFifVzoiicFcYt\n"
	    + "8J96BxU4lR64JBWNA0B3/07Tdqq5e9islA+ZgH3Kf94huR2/ysDhMDmk5xjKQ7Js\n"
	    + "VD6tIu2Xp/A6hC6uRVXaxIt6THl7p1nOR8RZQwIVAOUz6IkzKhhcM5rNvyoNhPQh\n"
	    + "0caZAoIBADchSLQ+DVwXXULCGUsfjfEyPiekvmPXqUSVggrOzJTBfZou7oQiOYEl\n"
	    + "Jq7vZze5HTqyDZ3Z31YGvd4r80PCVL5gcXEZsbv+5xALKzTBPpiUddIYgj+hP+K2\n"
	    + "U85FvcUPeFt8vFPoTGwm+Ae1YjFVXElJV+JVzjLWm5u/f6DRhYrFfz3W4zgTuUeX\n"
	    + "qh53amsX8Qczfj0dKEkosB/V0jrtvIEf4lD3GZMH8BM4puzkRBBFmEjqb/R5uYGT\n"
	    + "nVuUARlPnC5FoHlS7NSx20CBsfAWnRL7hJv5v0iKoPCGyCyt/J5nIMbA1tYPCPnM\n"
	    + "QsnGL0oPDvXmMv4t3xjbQlPAQrnEbnoCggEAJbjOvESpv4PIxgpEqH6ErIzyzK8n\n"
	    + "yRD10umABbq/oDdLw2OMxKcfTfk8He/l0VDyLkGpgh0KpXQVQDbTyc4Z6EUj/GP1\n"
	    + "Em9EHev7+kgSoUiNKUP76p6oz4tfbYmpBxnP3PIDFCWTcbd3Gx1DRoICjFqJ3HcZ\n"
	    + "4MhPWU0Sd52Y1J5j9E9PdzQdk4yF3CduWJOsIsTnMXrSu41rM7KA7uabvrTFd2DX\n"
	    + "m+8fMQt0AdR/vh8til1wQTamElCb7aRsjZv0q6mQ+93y/aIkhntsUiuRPvUQlgEb\n"
	    + "sgpOdBtZgr4yzh/2IAINcCVrIyYtsHBrre2zWvyPqHhB5d9YFNSvyYCkpQIVALkI\n"
	    + "X2shNkMxg8boUiMaC65ZuZuP\n"
	    + "-----END DSA PRIVATE KEY-----";

    private static final String ACCESS_KEY = "HWC0GSVPRIIQCV4Y";

    public static void main(String[] args) throws IOException {
    	UbapiClient client = new UbapiClient("https://ubapi.baidu.com", ACCESS_KEY,
                                                PRIVATE_KEY,1000,5000,5000);
        HttpResponse response;
        String uri;

        uri = "/union/1/getApps";
        response = client.get(uri);
        
        printResponse(uri, response);

//        uri = "/adm/11/test.jsp";
//        response = client.post(uri, "application/json", "{date: \"abc\"}".getBytes());
//        printResponse(uri, response);
    }

    private static void printResponse(String uri, HttpResponse response) throws IOException {
        System.out.println("=========== " + uri);
        System.out.println("status: " + response.getStatusLine());
        System.out.println("headers: ");
        for (Header header : response.getAllHeaders()) {
            System.out.println(" " + header.getName() + ": " + header.getValue());
        }

        System.out.println();

        System.out.println("body: ");

        InputStream inputStream = response.getEntity().getContent();

        byte[] buffer = new byte[4096];
        int length = 0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((length = inputStream.read(buffer)) > -1) {
            outputStream.write(buffer, 0, length);
        }
        System.out.println(outputStream.toString());
    }
}

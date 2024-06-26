package com.exemplo.sistemacarrousuario.api.controller.constants;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ApiPathConstants {

	private static final String api = "/api";
	public static String[] unauthenticatedApiResources = getUnauthenticatedApiResources();
	public static String[] unauthenticatedApiFilesPattern = getUnauthenticatedApiFilesPattern();
	
	public static class Users {
		public static final String path = "/users";
		public static final String apiPath = api + path;
	}
	
	public static class Cars {
		public static final String path = "/cars";
		public static final String apiPath = api + path;
	}
	
	public static class SignIn {
		public static final String path = "/signin";
		public static final String apiPath = api + path;
	}
	
	public static class Me {
		public static final String path = "/me";
		public static final String apiPath = api + path;
	}
	
	public static class Hello {
		public static final String path = "/hello";
	}

	private static String[] getUnauthenticatedApiResources() {
		return Stream.of(
				ApiPathConstants.Users.apiPath,
				ApiPathConstants.SignIn.apiPath
			).collect(
					Collectors.mapping(path -> path + "/**", Collectors.toList()))
			.toArray(size -> new String[size]);
	}
	
	private static String[] getUnauthenticatedApiFilesPattern() {
		return new String[] {
				"/",
				"/target/site/apidocs/**",
				"/swagger-ui/**",
				"/v3/api-docs/**",
				"/h2-console/**",
				"/index.html",
				"**.js",
				"**.css"
		};
	}

}

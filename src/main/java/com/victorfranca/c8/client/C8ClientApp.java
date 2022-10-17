package com.victorfranca.c8.client;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;

public class C8ClientApp {

	private static final String zeebeAPI = "zeebeEndpoint";
	private static final String clientId = "clientId";
	private static final String clientSecret = "clientSecret";

	private static final String PROCESS_ID = "genericCaseProcess";

	public static void main(String[] args) {
		final OAuthCredentialsProvider credentialsProvider = new OAuthCredentialsProviderBuilder().clientId(clientId)
				.clientSecret(clientSecret).audience(zeebeAPI).build();

		try (ZeebeClient client = ZeebeClient.newClientBuilder().gatewayAddress(zeebeAPI + ":443")
				.credentialsProvider(credentialsProvider).build()) {
			final ProcessInstanceEvent processInstanceEvent = client.newCreateInstanceCommand()
					.bpmnProcessId(PROCESS_ID).latestVersion().send().join();

			System.out.println(processInstanceEvent);
		}
	}

}

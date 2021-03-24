package com.qa.account.example.rest;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.account.example.persistence.domain.Account;
import com.qa.account.example.service.AccountService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private AccountService service;

	@MockBean
	private RestTemplate template;

	private final Account ACCOUNT = new Account("a", "b", "c", 44.94);

	private final ObjectMapper mapper = new ObjectMapper();

//	@Test
	public void testRegisterAccount() throws Exception {
		doReturn(ResponseEntity.ok(ACCOUNT)).when(service).addAccount(ACCOUNT);
		mockMVC.perform(post("/account/register").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(ACCOUNT)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print());
	}

}

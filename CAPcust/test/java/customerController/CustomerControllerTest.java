package customerController;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.controller.CustomerController;
import com.mindtree.dto.CustomerDto;
import com.mindtree.model.Customer;
import com.mindtree.service.CustomerService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@WebMvcTest(value= CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerservice;

	@Autowired
	private ObjectMapper objectMapper;

	
	String jwt="";
	private static final long EXPIRATIONTIME = 900000;
	@Before
	public void setup() {
		jwt = "Bearer "+Jwts.builder().setSubject("user").claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).compact();
	}




	@Test
	public void registercustomertest() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerName("ravi");
		customer.setEmail("raviteja@c");
		customer.setMobileNumber(45353535L);
		
        
		when(customerservice.registerUser(any(Customer.class))).thenReturn(new Customer());
		RequestBuilder request = MockMvcRequestBuilders.post(
				"/customer/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((customer)))
				.header(HttpHeaders.AUTHORIZATION,
						jwt);
		mockMvc.perform(request)
		.andExpect(status().is(200))
		.andExpect(content().string("Item Added successfully"))
		.andReturn();
	}
	
	


	@Test
	public void updateOrder() throws Exception{
	
		
		CustomerDto customerdto = new CustomerDto("ravi","raviteja@com",1L);
		when(customerservice.updateCustomer(1L, any(CustomerDto.class))).thenReturn(new Customer("ravi","raviteja",1l ));
		RequestBuilder request = MockMvcRequestBuilders.put(
				"/review/{id}")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(customerdto))
		        .header(HttpHeaders.AUTHORIZATION,
						jwt);
		mockMvc.perform(request)
		.andExpect(status().is(200))
		.andReturn();

	}



}
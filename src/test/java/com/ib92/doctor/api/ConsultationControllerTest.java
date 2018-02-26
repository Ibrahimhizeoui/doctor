package com.ib92.doctor.api;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.ib92.doctor.DoctorApplication;
import com.ib92.doctor.model.Consultation;
import com.ib92.doctor.model.User;
import com.ib92.doctor.repository.ConsultationRepository;
import com.ib92.doctor.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoctorApplication.class)
@WebAppConfiguration
public class ConsultationControllerTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ConsultationRepository consultationRepository;
    
    @Autowired
    private UserRepository userRepository;
    
   
    
    private User user;
    private Consultation consultation;
    private List<Consultation> consultationList = new ArrayList<>();
    
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
    
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

       
        }
    
    @Test
    public void userNotFound() throws Exception {
        mockMvc.perform(get("/xx/consultaion")
                .content(this.json(new Consultation(null, null, null)))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }
    @Test
	public void saveConsultationTest() throws Exception{
		this.consultation = new Consultation("xx","yy","zz");		
		this.user = this.userRepository.save(new User("ib92"));
		this.consultation.setUser(this.user);
		String consultationtest = json(this.consultation);
		this.mockMvc.perform(
				post("/consultation")
                .contentType(contentType)
                .content(consultationtest))
		        .andDo(print())
                .andExpect(status().isCreated());
		
	}
	
    @Test
	public void getListConsultationsByUserTest() throws Exception{
    	this.consultation = new Consultation("xx","yy","zz");		
		this.user = this.userRepository.save(new User("ib92"));
		this.consultation.setUser(this.user);
		this.mockMvc.perform(
		    	get("/1/consultation"))
		    	.andDo(print())
		    	.andExpect(status().isOk())
		        .andExpect(content().contentType(contentType))
		        .andExpect(jsonPath("$[0].id", is(1)))
		        .andExpect(jsonPath("$[0].firstanswer", is("xx")))
		        .andExpect(jsonPath("$[0].secondAnswer", is("yy")))
		        .andExpect(jsonPath("$[0].thirdAnswer", is("zz")));
	}
	
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}

package com.stressmanager;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.http.MediaType;


// import static org.hamcrest.Matchers.*;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.beans.factory.annotation.Autowired;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApplicationTests extends AbstractTestNGSpringContextTests {

	@Test
	public void contextLoads() {
	}

	// @Rule
	// public OutputCapture capture = new OutputCapture();

	// @Autowired
    // private MockMvc mockMvc;

	@Test
	public void isAuthenticated() throws Exception {
		// this.mockMvc.perform(get("/login/google").accept(MediaType.TEXT_PLAIN))
		// 	.andExpect(status().isOk());
		// assertThat(capture.toString(), containsString("authenticated!!!!"));
	}

}

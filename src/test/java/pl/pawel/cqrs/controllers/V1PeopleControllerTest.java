package pl.pawel.cqrs.controllers;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static java.util.TimeZone.getDefault;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.pawel.cqrs.controllers.form.PersonForm;
import pl.pawel.cqrs.controllers.view.PersonView;
import pl.pawel.cqrs.service.PersonService;

@TestInstance(PER_CLASS)
class V1PeopleControllerTest {

  private static final String API_PATH = "/api/v1/people";
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private MockMvc mockMvc;
  private PersonService personService;

  @BeforeAll
  void beforeAll(){
    OBJECT_MAPPER.registerModule(new JavaTimeModule());
    OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    personService = mock(PersonService.class, withSettings().stubOnly().verboseLogging());
    mockMvc = MockMvcBuilders.standaloneSetup(new V1PeopleController(personService))
        .alwaysDo(print())
        .build();
  }

  @Test
  void testAdd() throws Exception {
    // given
    String address = "address";
    LocalDate dateOfBirth = LocalDate.now().minusYears(1);
    PersonForm personForm = PersonForm.builder()
        .address(address)
        .dateOfBirth(dateOfBirth)
        .name("name")
        .salary(10)
        .surname("surname")
        .build();

    given(personService.createPerson(personForm)).willReturn(PersonView.builder().address(address).build());

    String contentAsString = OBJECT_MAPPER
        .writeValueAsString(personForm);
    // when
    mockMvc.perform(post(API_PATH)
    .content(contentAsString)
    .contentType(APPLICATION_JSON))
    // then
    .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.address").value(address));
  }

  @Test
  void testAddWhenFutureDateOfBirth() throws Exception {
    // given
    String address = "address";
    LocalDate dateOfBirth = LocalDate.now().plusYears(1);
    PersonForm personForm = PersonForm.builder()
        .address(address)
        .dateOfBirth(dateOfBirth)
        .name("name")
        .salary(10)
        .surname("surname")
        .build();

    given(personService.createPerson(personForm)).willReturn(PersonView.builder().address(address).build());

    String contentAsString = OBJECT_MAPPER
        .writeValueAsString(personForm);
    // when
    mockMvc.perform(post(API_PATH)
        .content(contentAsString)
        .contentType(APPLICATION_JSON))
        // then
        .andExpect(status().isBadRequest());
  }
}
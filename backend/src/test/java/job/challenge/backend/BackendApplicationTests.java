package job.challenge.backend;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import job.challenge.backend.configuration.ModelMapperConfig;
import job.challenge.backend.dto.UserDTO;
import job.challenge.backend.model.User;
import job.challenge.backend.repository.UserRepository;
import job.challenge.backend.service.UserService;

@SpringBootTest
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private ModelMapperConfig modelMapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testFindByIdUserExists() {
		User user = createSampleUser();

		when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

		UserDTO userDTO = userService.findById(1L);
	}

	@Test
    public void testFindByIdUserNotExists() {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> userService.findById(1L));
    }

	private User createSampleUser() {
		User user = new User();
		user.setId(1L);
		user.setName("John Doe");
		user.setBirthDay(LocalDate.of(2002, 6, 15));
		return user;

	}

}

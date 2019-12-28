package FacturadeDB.Facturade;

import FacturadeDB.Facturade.Client.client;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest

class FacturadeApplicationTests {

	/*private Repository userRepository;

	@Test
	public void whenCalledSave_thenCorrectNumberOfUsers() {
		List<client> users = (List<client>) userRepository.findAll();

		assertEquals(users.size(),1);
	}*/

}

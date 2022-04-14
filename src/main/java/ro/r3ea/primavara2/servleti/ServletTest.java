package ro.r3ea.primavara2.servleti;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class ServletTest {

	
	@GetMapping("/salutare")
	public String getSalut() {
		return "SALUTARE BOSS";
	}
	
}

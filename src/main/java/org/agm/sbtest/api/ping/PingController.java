package org.agm.sbtest.api.ping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

	@RequestMapping("/")
	public ResponseEntity<PingResult> ping() {
		return ResponseEntity.ok(new PingResult());
	}

}
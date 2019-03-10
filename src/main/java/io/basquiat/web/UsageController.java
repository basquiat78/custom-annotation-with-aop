package io.basquiat.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.basquiat.annotation.APIUsage;

/**
 * 
 * created by basquiat
 *
 */
@RestController
public class UsageController {

	@APIUsage(usage="pay")
    @RequestMapping("/api/1")
    public String one() {
        return "Hi! one!";
    }

	@APIUsage
    @RequestMapping("/api/2")
    public String two() {
        return "Hi! two!";
    }

	@RequestMapping("/api/3")
    public String three() {
        return "Hi! three!";
    }
	
}
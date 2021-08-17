package com.example.demo.controller.v1;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/caching")
public class CacheController {
	
	@CacheEvict(value = "empregados", allEntries = true)
	@GetMapping("/clearEmpregados")
    public void clearCacheEmpregados() {
       
    }
}

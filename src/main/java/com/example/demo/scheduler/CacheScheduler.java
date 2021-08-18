package com.example.demo.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.service.CryptoService;
import com.example.demo.service.EmpregadoService;

@Component
public class CacheScheduler {

	@Autowired
	EmpregadoService empregadoService;
	
	@Autowired
	CryptoService cryptoService;
	
	@Scheduled(fixedRate = 86400000)
	public void evictAllcachesAtIntervals() {
		empregadoService.clearCacheEmpregados();
		//cryptoService.getBitCoinPrice();
	}
}

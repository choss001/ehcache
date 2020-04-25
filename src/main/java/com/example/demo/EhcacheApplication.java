package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@EnableCaching
@Controller
public class EhcacheApplication {
  
    private static Logger logger = LoggerFactory.getLogger(EhcacheApplication.class);
        
    @Autowired
    MemberRepository memberRepository;
    
    
    @GetMapping("/member/nocache/{name}")
    @ResponseBody
    public Member getNocacheMember(@PathVariable String name) {
      
      long start = System.currentTimeMillis();
      Member member = memberRepository.findByNameNoCache(name);
      long end = System.currentTimeMillis();
      
      logger.info(name+ "의 NoCache 수행시간 : "+ Long.toString(end-start));
      
      return member;
      
    }
    
    @GetMapping("/member/cache/{name}")
    @ResponseBody
    public Member getCacheMember(@PathVariable String name) {
      long start = System.currentTimeMillis();
      Member member = memberRepository.findByNameCache(name);
      long end = System.currentTimeMillis();
      
      logger.info(name+ "의 Cache 수행시간 : " + Long.toString(end-start));
      
      return member;
    }
    
    @GetMapping("/member/refresh/{name}")
    @ResponseBody
    public String refresh(@PathVariable String name) {
      memberRepository.refresh(name);
      return "cache clear!";
    }

	public static void main(String[] args) {
		SpringApplication.run(EhcacheApplication.class, args);
	}
	
	@GetMapping("/")
	@ResponseBody
	public String index() {
	  return "HelloWorld";
	}
}

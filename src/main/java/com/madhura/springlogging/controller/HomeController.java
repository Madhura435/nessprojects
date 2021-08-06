package com.madhura.springlogging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
public class HomeController {
	Logger logger=LoggerFactory.getLogger(HomeController.class);
  @GetMapping("/measgge")
  public String getMeassage1()
  {
	  logger.info("info log level");
	  logger.warn("warn log level");
	  logger.error("error log level");
	  logger.debug("debug log level");
	  logger.trace("trace log level");
	  return "you can check logs of this appiliction either in console or log file";
  }
}

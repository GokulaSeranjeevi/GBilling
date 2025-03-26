package com.skywings.gbilling.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Import({ ConnectionConfig.class })
@ComponentScan("com.skywings.*")
public class ApplicationConfig {

}

package com.vsouza.managerproducts;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:.env.test")
public class EnvConfig {
}

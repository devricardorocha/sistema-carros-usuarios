package com.exemplo.sistemacarrousuario;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.exemplo.sistemacarrousuario.config.AppConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class AppConfigTest {

	@Autowired
	AppConfig appConfig;

	@Test
	void modelMapperTest() {
		ModelMapper mapper = appConfig.modelMapper();
		Assertions.assertThat(mapper).isInstanceOf(ModelMapper.class);
	}

}

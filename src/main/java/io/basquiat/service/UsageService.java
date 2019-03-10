package io.basquiat.service;

import org.springframework.stereotype.Service;

import io.basquiat.model.Usage;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * created by basquiat
 * 
 * UsageService
 *
 */
@Slf4j
@Service("usageService")
public class UsageService {

	/**
	 * 사용량 측정 서비스
	 * @param apiName
	 * @param usage
	 * @return Usage
	 */
	public Usage measureUsage(String apiName, String usage) {
		log.info("call UsageService");
		Usage usageModel = Usage.builder().apiName(apiName).usage(usage).build();
		log.info(usageModel.toString());
		// Do Something
		// 예를 들면 호출된 api의 정보를 db에 insert하는 로직
		// e.g. UsageRepository.measureUsage(usageModel);
		return usageModel;
	}
	
}

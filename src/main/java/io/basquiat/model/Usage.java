package io.basquiat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 
 * created by baquiat
 *
 */
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Usage {

	/*
	 * apiName
	 */
	String apiName;

	/*
	 * annotaion usage value
	 */
	String usage;
	
}

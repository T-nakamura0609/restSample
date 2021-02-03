package myexample.zipcodeclient.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 郵便番号検索結果
 *
 */
@Getter
@Setter
public class ZipcodeResponseResource {

	int status;

	String message;

	List<ZipcodeDto> results = new ArrayList<>();
}

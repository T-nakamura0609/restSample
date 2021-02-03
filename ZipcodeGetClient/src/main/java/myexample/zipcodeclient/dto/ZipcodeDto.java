package myexample.zipcodeclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZipcodeDto {
	// 郵便番号
	String zipcode;
	//都道府県コード
	String prefcode;
	//都道府県名
	String address1;
	// 市区町村名
	String address2;
	// 町域名
	String address3;
	// 都道府県カナ
	String kana1;
	// 市区町村名カナ
	String kana2;
	// 町域名カナ
	String kana3;
}

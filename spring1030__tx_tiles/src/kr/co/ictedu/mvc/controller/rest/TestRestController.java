package kr.co.ictedu.mvc.controller.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ictedu.mvc.dto.JsonDTO;

@RestController
public class TestRestController {
	
	@GetMapping(value = "/restHello", produces = "application/json; charset=euc-kr")
	public String viewMessagee() {
		return "안녕하세요";
	}
	
	//VO를 반환하는 JSON -> {"key1": val1, "key2": val2}
	@GetMapping(value = "/restJsonDTO", produces = "application/json; charset=utf-8")
	public JsonDTO viewJsonDto() {
		JsonDTO vo = new JsonDTO("바다", 1000);
		return vo;
	}
	//
	@GetMapping(value="/restJsonDTOList",produces = "application/json; charset=utf-8")
	public List<JsonDTO> viewJsonDtoList(){
		List<JsonDTO> list = new ArrayList<JsonDTO>();
		String[] data = {"산","바다","강"};
		int[] cnt = {1000, 300, 500};
		for(int i= 0; i <data.length; i++) {
			JsonDTO vo = new JsonDTO(data[i], cnt[i]);
			list.add(vo);
		}
		return list;
	}
	
	@GetMapping(value="/jsonTest1",produces = "application/json; charset=utf-8")
	public String surveyDetail_title(){
		List<JsonDTO> list = new ArrayList<JsonDTO>();
		String[] data = {"산","바다","강","영화관", "집"};
		int[] cnt = {1000, 300, 500, 250, 100};
		for(int i= 0; i <data.length; i++) {
			JsonDTO vo = new JsonDTO(data[i], cnt[i]);
			list.add(vo);
		}
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(JsonDTO e : list) {
			map.put(e.getTitle(), e.getCnt());
		}
		
		System.out.println("size: " + list.size());
		String result = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writeValueAsString(map);
			System.out.println(result);
			result = "[{\"sub\":\"좋아하는 장소는?\"}," + result +"]";
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}

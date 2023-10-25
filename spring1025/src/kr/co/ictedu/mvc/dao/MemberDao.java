package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ictedu.mvc.dto.MemberVO;

@Repository
public class MemberDao implements MemberDaoInter {
	
	@Autowired
	private SqlSessionTemplate ss;

	@Override
	public void add(MemberVO vo) {
		ss.insert("mem.add", vo);
	}

	@Override
	public int idCheck(String id) {
		return ss.selectOne("mem.idcheck", id);
	}

	@Override
	public MemberVO loginCheck(MemberVO vo) {
		return ss.selectOne("mem.logincheck", vo);
	}

	@Override
	public MemberVO myPage(String id) {
		return ss.selectOne("mem.mypage", id);
	}

	@Override
	public List<MemberVO> memList(Map<String, String> map) {
		return null;
	}

	@Override
	public int getCnt() {
		return 0;
	}

}

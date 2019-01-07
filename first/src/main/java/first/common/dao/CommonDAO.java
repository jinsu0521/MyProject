package first.common.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("commonDAO")
public class CommonDAO extends AbstractDAO{
								// 반드시 AbstractDAO 를 상속받아야 한다.
	
	// 파일정보 가지고 온다
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return (Map<String,Object>) selectOne("common.selectFileInfo", map);
	}

}

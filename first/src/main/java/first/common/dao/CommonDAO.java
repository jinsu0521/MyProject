package first.common.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("commonDAO")
public class CommonDAO extends AbstractDAO{
								// �ݵ�� AbstractDAO �� ��ӹ޾ƾ� �Ѵ�.
	
	// �������� ������ �´�
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return (Map<String,Object>) selectOne("common.selectFileInfo", map);
	}

}

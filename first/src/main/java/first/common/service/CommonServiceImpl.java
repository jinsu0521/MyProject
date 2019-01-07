package first.common.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import first.common.dao.CommonDAO;

// 내가 만든 클래스는 commonService 아이디로 스프링 컨테이너에 등록이 된다
@Service("commonService")
public class CommonServiceImpl implements CommonService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="commonDAO")
	private CommonDAO commonDAO;
	
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception{
		return commonDAO.selectFileInfo(map);
	}

}

package first.common.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import first.common.common.CommandMap;
import first.common.service.CommonService;

@Controller
public class CommonController {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@RequestMapping(value="/common/downloadFile.do")
	public void downloadFile(CommandMap commandMap, HttpServletResponse response) throws Exception{
		// 첨부파일의 정보를 가져옴
		Map<String, Object> map = commonService.selectFileInfo(commandMap.getMap());
		String storedFileName = (String)map.get("STORED_FILE_NAME");
		String originalFileName = (String)map.get("ORIGINAL_FILE_NAME");
		
		// 실제로 파일이 저장된 위치에서 해당 첨부파일을 읽어서 byte[] 형태로 변환
		// FileUtils 는 우리가 만든 클래스가 아닌 org.apache.commons.io 패키지의 FileUtils 클래스이다. 이 클래스로 인해 byte 형식으로 변환을 손쉽게 한다
		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\Java\\upload\\"+storedFileName));
		
		// 읽어드린 파일정보를 화면에서 다운로드 할 수 있도록 변환하는 부분
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		// Content-Disposition 를 통해서 해당 패킷이 더떤 형식의 데이터인지 알 수 있다.
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}

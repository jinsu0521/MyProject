package first.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtils")
public class FileUtils {

	private static final String filePath = "C:\\Java\\upload\\";
	
	public List<Map<String,Object>> parseInsertFileInfo(Map<String,Object> map, HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		// 클라이언트에서 전송된 파일 정보를 담아서 반환해줄 list
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;
		
		// 게시글 번호를 받아옴
		String boardIdx = (String)map.get("IDX");
		
		// 파일을 저장할 경로에 해당 폴더가 없으면 폴더를 생성한다
		File file = new File(filePath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		// 파일의 정보를 받아서 새로운 이름으로 변경
		while(iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = CommonUtils.getRandomString() + originalFileExtension;
				
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				
				listMap = new HashMap<String, Object>();
				listMap.put("BOARD_IDX", boardIdx);
				listMap.put("ORIGINAL_FILE_NAME", originalFileName);
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				list.add(listMap);
			}
		}
		return list;
	}
	
	public List<Map<String,Object>> parseUpdateFileInfo(Map<String,Object> map, HttpServletRequest request) throws Exception{
	MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
	Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
	
	MultipartFile multipartFile = null;
	String originalFileName = null;
	String originalFileExtension = null;
	String storedFileName = null;
	
	// 클라이언트에서 전송된 파일 정보를 담아서 반환해줄 list
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Map<String, Object> listMap = null;
	
	// 게시글 번호를 받아옴
	String boardIdx = (String)map.get("IDX");
	String requestName = null;
	String idx = null;
	
	// 파일을 저장할 경로에 해당 폴더가 없으면 폴더를 생성한다
	File file = new File(filePath);
	if(file.exists() == false) {
		file.mkdirs();
	}
	
	// 파일의 정보를 받아서 새로운 이름으로 변경
	while(iterator.hasNext()) {
		multipartFile = multipartHttpServletRequest.getFile(iterator.next());
		if(multipartFile.isEmpty() == false) {
			originalFileName = multipartFile.getOriginalFilename();
			originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
			storedFileName = CommonUtils.getRandomString() + originalFileExtension;
			
			multipartFile.transferTo(new File(filePath + storedFileName));
			
			listMap = new HashMap<String, Object>();
			listMap.put("IS_NEW", "Y");
			listMap.put("BOARD_IDX", boardIdx);
			listMap.put("ORIGINAL_FILE_NAME", originalFileName);
			listMap.put("STORED_FILE_NAME", storedFileName);
			listMap.put("FILE_SIZE", multipartFile.getSize());
			list.add(listMap);
		} else {
			requestName = multipartFile.getName();
			
			// 파일 정보가 없는 경우 해당 파일정보가 기존에 저장되어 있던 내용인지 아니면 단순히 빈 파일인지 구분하기 위한 코드
			idx = "IDX_"+requestName.substring(requestName.indexOf("_")+1);
			if(map.containsKey(idx) == true && map.get(idx) != null) {
				listMap = new HashMap<String, Object>();
				listMap.put("IS_NEW", "N");
				listMap.put("FILE_IDX", map.get(idx));
				list.add(listMap);
			}
		}
	}
	return list;
	}
}

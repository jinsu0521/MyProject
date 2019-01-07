package first.common.util;

import java.util.UUID;

public class CommonUtils {

	// - 를 지우고 랜덤한 값을 리턴한다. 업로드할 때 랜덤한 이름으로 업로드 되도록
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}

package first.common.util;

import java.util.UUID;

public class CommonUtils {

	// - �� ����� ������ ���� �����Ѵ�. ���ε��� �� ������ �̸����� ���ε� �ǵ���
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}

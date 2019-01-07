package net.madvirus.spring4.chap02;

public class AuthFailLogger {

	private int threshold;		// 로그인 시도 횟수
	private int failCounts;		// 로그인 실패 횟수
	
	// 로그인 실패 횟수가 많으면 notifyTooManyFail 메서드로 처리
	public void insertBadPw(String userId, String inputPw) {
		System.out.printf("AuthFail [type=badpw, userid=%s, pw=%s]\n", userId, inputPw);
		failCounts++;
		if(threshold > 0 && failCounts > threshold) {
			notifyTooManyFail();
			failCounts = 0;
		}
	}
	
	private void notifyTooManyFail() {
		System.out.println("너무 많은 로그인 시도 실패");
	}
	
	public void setThreshold(int thresold) {
		this.threshold = thresold;
	}
}

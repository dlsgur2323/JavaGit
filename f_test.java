
public class f_test {
	public static void main(String[] args) {
		String str = "♚♚히어로즈 오브 더 스☆톰♚♚가입시"
				+ "$$전원 카드팩☜☜뒷면100%증정※ "
				+ "♜월드오브 워크래프트♜펫 무료증정￥ "
				+ "특정조건 §§디아블로3§§★공허의유산"
				+ "★초상화♜오버워치♜겐지스킨￥획득기회@"
				+ "@@ 즉시이동http://kr.bat"
				+ "tle.net/heroes/ko/";
		
		int i = 1;
		int l = 20;
	
	lineEnter(str, 20);
		
		
	}
	
	private static void lineEnter (String str ,int l) {
		int i = 1;
		while(true) {
			if(str.length() > l*i) {
				if(i > 1) {
					System.out.println(str.substring(l*(i-1), l*i));
				} else {
					System.out.println(str.substring(l*(i-1), l*i));
				}
			} else {
				if(i > 1) {
					System.out.println(str.substring(l*(i-1), str.length()));
					break;
				} else {
					System.out.println(str);
					break;
				}
			}
			i++;
		}
	}
	
	
	
}

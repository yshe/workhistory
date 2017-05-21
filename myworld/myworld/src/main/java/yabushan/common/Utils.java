package yabushan.common;



public class Utils {
	
	public static boolean IsNotEmpty(String str){
		if("null".equals(str) || "".equals(str) || str==null){
			return false; 
		}
		return true;
	}
	
	public static boolean IsEmpty(String str){
		if("null".equals(str) || "".equals(str) || str==null){
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		System.out.println(IsEmpty("null"));
	}

}

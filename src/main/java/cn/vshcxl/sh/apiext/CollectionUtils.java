package cn.vshcxl.sh.apiext;

public class CollectionUtils {

	
	
	
	
	//判空
	public static final boolean isEmpty(String[] strArr){
		if(strArr.length==0||strArr==null)
			return true;
		for (int i = 0; i < strArr.length; i++) {
			if(strArr[i]=="")
				return true;
		}
		return false;
	}
		
		
}

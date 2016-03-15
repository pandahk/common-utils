package cn.vshcxl.sh.apiext;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;

public class MapUtils {

	
	
	public static boolean isEmpty(Map map){
		return (map==null||map.isEmpty());
	}
	
	public static boolean isNotEmpty(Map map){
		return !MapUtils.isEmpty(map);
	}
	
	
	public static Properties toProperties(final Map map ){
		Properties answer=new Properties();
		if(MapUtils.isNotEmpty(map)){
			for (Iterator iter=map.entrySet().iterator();iter.hasNext();) {
				Map.Entry entry=(Entry) iter.next();
				Object key=entry.getKey();
				Object value=entry.getValue();
				answer.put(key, value);
			}
		}
		return answer;
		
		
	}
	
	public static Boolean getBoolean(final Map map,final Object key){
		if(map!=null){
			Object answer=map.get(key);
			if(answer!=null){
				if(answer instanceof Boolean){
					return (Boolean) answer;
				}else if(answer instanceof String){
					return new Boolean((String)answer);
				}else if(answer instanceof Number){
					Number n=(Number) answer;
					return (n.intValue()!=0)?Boolean.TRUE:Boolean.FALSE;
				}
				
			}
			
		}
		return null;
		
		
	}
	
	public static final Set EMPTY_SET = Collections.EMPTY_SET;
	public static final Map EMPTY_MAP = Collections.EMPTY_MAP;
	public static void main(String[] args) {
		//System.out.println(EMPTY_MAP.keySet().iterator().next().toString());
		System.out.println(NumberUtils.toInt("3"));
	}
	
}

package cn.vshcxl.sh.apiext;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.mvel2.MVEL;


public class CollectionUtil {

	/**
	 * 把List通过分隔符进行分隔
	 * 
	 * @param fromList
	 *            要连接的List
	 * @param joinStr
	 *            连接的字符串
	 * @return String 连接后字符串
	 */
	public static String listJoin(List<?> fromList,String joinStr){
		if (CollectionUtils.isEmpty(fromList)) {
			return null;
		}
        joinStr=StringUtils.isEmpty(joinStr)?",":joinStr;
        StringBuffer toList=new StringBuffer();
	    Iterator iterator=fromList.iterator();
        toList.append(String.valueOf(iterator.next()));
		while (iterator.hasNext()) {
			String str = String.valueOf(iterator.next());
			toList.append(joinStr);
			toList.append(str);
		}
		return toList.toString();
	}
	
	
	/**
	 * 把Array 通过分隔符进行分割
	 * @param fromArray
	 * @param joinStr
	 * @return
	 */
	public static String arrayJoin2(Object[] fromArray,String joinStr){
		if (ArrayUtils.isEmpty(fromArray)) {
			return null;
		}
		List<?> objList=Arrays.asList(fromArray);
		return listJoin(objList, joinStr);
	}
	
	
	/***
	 * 通过类型创建数组
	 * 
	 * @param clazz
	 *            类型
	 * @param length
	 *            数组长度
	 * @return 数组实例对象
	 */
	public static <T> T[] newArrayByArrayClass(Class<T[]> clazz,int length){
		return (T[]) Array.newInstance(clazz.getComponentType(), length);
	}
	
	/****
	 * 数组的合并<br>
	 * eg: CollectionUtil.arrayMerge(String[].class, ary1,ary2)
	 * 
	 * @param clazz
	 *            数组类型
	 * @param a
	 *            合并数组一
	 * @param b
	 *            合并数组二
	 * @return 
	 * @return 
	 * @return 全并后的数组
	 */
	
	public static <T>  T[] arrayMerge(Class<T[]> clazz,T[] a,T[] b){
		
		if(ArrayUtils.isEmpty(a))
			return b;
		if(ArrayUtils.isEmpty(b))
			return a;
		T[] newArray=newArrayByArrayClass(clazz, a.length+b.length);
		System.arraycopy(a, 0, newArray, 0, a.length);
		System.arraycopy(b, 0, newArray, a.length, b.length);
		return newArray;
		
	}
	
	
	/**
	 * 通过List得到对象的单个列值
	 * 
	 * @param fromList
	 *            要操作的数据源
	 * @param colName
	 *            要提取的列名
	 * @return List 提取预定列的List
	 */
	public static List<?> getColFromObj(List<?> fromList,String colName){
		List<Object> retList=new ArrayList<>();
		if (CollectionUtils.isEmpty(retList)) {
			return retList;
		}
		for (Object object : fromList) {
			Object result=null;
			if (ReflectAssist.isInterface(object.getClass(), "java.util.Map")) {
				Map tempObjMap=(Map) object;
				result=tempObjMap.get(colName);
			}else{
				result=MVEL.eval(colName, object);
			}
			retList.add(result);
		}
		return retList;
	}
	
	/**
	 * @param inputCollection
	 *            要操作的字符
	 * @param predicate
	 *            规则
	 * @return 返回符合条件的集合并把这个集合从inputCollection删除（即inputCollection只剩余不合条件的数据）
	 */
	public static Collection selectFilter(Collection inputCollection, Predicate predicate) {
		Collection retCollection = (Collection)CollectionUtils.find(inputCollection, predicate);
		CollectionUtils.filter(retCollection, predicate);
		return retCollection;
	}
	
	public static void distinctFilter(Collection collection){
		Set<?> temp=new HashSet<>();
		temp.addAll(collection);
		collection.clear();
		collection.addAll(temp);
		
	}
	/**
	 * 过滤空值
	 * @param map
	 * @return
	 */
	public static void filterNull(Map<String, String> map) {
		if (MapUtils.isEmpty(map)) {
			return ;
		}
		for (String key : map.keySet()) {
			if (StringUtil.isNull(map.get(key))) {
				map.remove(key);
			}
		}
		return ;
	}
	
	
	
	
	
	
	
	
	
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
		
	
	public static void main(String[] args) {
		
		List<String> list=new ArrayList<>();
		list.add("11");
		list.add("22");
		
		List<Integer> list2=new ArrayList<>();
		list2.add(33);
		list2.add(55);
		
		String ss=listJoin(list, null);
		System.out.println(ss);
		
		String ss2=listJoin(list2, null);
		System.out.println(ss2);
		
		System.out.println("=============");
		int [] tset1 = new int []{2,3,4,5,6,7,8} ;

	     System.out.println(Arrays.toString(tset1));

	     int [] test2 = new int [tset1.length] ;

	  System.arraycopy(tset1, 0, test2, 0, tset1.length) ;  

	    System.out.println(Arrays.toString(test2));

	     test2[0] = 8;   test2[1] = 88;

	  System.out.println(Arrays.toString(test2));
	}
		
}

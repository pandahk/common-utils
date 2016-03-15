package cn.vshcxl.sh.apiext;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import cn.vshcxl.sh.constant.OrgType;


public class CacheUtils {

	
	//生成缓存key
		public static final String generateCacheKey(Class clazz, String methodName, Object... objects) {
			StringBuilder key = new StringBuilder();
			key.append(clazz.getClass().getPackage().getName());
			key.append(clazz.getClass().getName());
			key.append(methodName);
			for (Object obj : objects) {
				if (obj instanceof String)
					key.append(obj);
				else if(obj instanceof Integer)
					key.append((Integer)obj);
				else if (obj.getClass().isArray()) {
					if (obj instanceof String[]) {
						key.append(StringUtils.join((Object[]) obj, ""));
					} else if (obj instanceof boolean[]) {
						key.append(StringUtils.join(ArrayUtils.toObject((boolean[]) obj)));
					} else if (obj instanceof byte[]) {
						key.append(StringUtils.join(ArrayUtils.toObject((byte[]) obj)));
					} else if (obj instanceof char[]) {
						key.append(StringUtils.join(ArrayUtils.toObject((char[]) obj)));
					} else if (obj instanceof double[]) {
						key.append(StringUtils.join(ArrayUtils.toObject((double[]) obj)));
					} else if (obj instanceof float[]) {
						key.append(StringUtils.join(ArrayUtils.toObject((float[]) obj)));
					} else if (obj instanceof int[]) {
						key.append(StringUtils.join(ArrayUtils.toObject((int[]) obj)));
					} else if (obj instanceof long[]) {
						key.append(StringUtils.join(ArrayUtils.toObject((long[]) obj)));
					} else if (obj instanceof short[]) {
						key.append(StringUtils.join(ArrayUtils.toObject((short[]) obj)));
					} else {
						for (Object unknown : (Object[]) obj) {
							key.append(String.valueOf(unknown));
						}
					}
				} else if (obj instanceof List) {
					key.append(((List<?>) obj));
				} else {
					key.append(String.valueOf(obj));
				}
			}
			return encode(key.toString());
		}

		private static final  String encode(String str) {
			MessageDigest md = null;
			String dstr = null;
			try {
				md = MessageDigest.getInstance("MD5");
				md.update(str.getBytes());
				dstr = new BigInteger(1, md.digest()).toString(16);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			return dstr;
		}
	    
		public static void main(String[] args) {
			// TODO Auto-generated method stub

			String aa[] = { "1", "2", "3", "4" };
			System.out.println(Arrays.asList(aa).hashCode());
			String bb[] = { "1", "2", "3", "4" };
			System.out.println(Arrays.asList(bb).hashCode());

			List<Integer> list = new ArrayList<Integer>();
			list.add(1);
			list.add(2);
			list.add(3);
			list.add(4);
			System.out.println(list.hashCode());
			List<Integer> list1 = new ArrayList<Integer>();
			list1.add(1);
			list1.add(2);
			list1.add(3);
			list1.add(4);
			System.out.println(list1.hashCode());

			int[] array10 = new int[] { 1, 2 };
			Integer[] result10 = ArrayUtils.toObject(array10);

			String[] array = { "aaa", "bbb", "ccc" };
			String result1 = StringUtils.join(result10, "");
			OrgType aad[] = { OrgType.DEPT, OrgType.STORE };
			for(int i=0;i<100;i++)
			   System.out.println(generateCacheKey(OrgType.class, "find", 44, array, aad,"SDDDDDDDDDDDDDDDDDAsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"));
		     }
}

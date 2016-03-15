package cn.vshcxl.sh.constant;

import org.apache.commons.lang3.StringUtils;

/***
 * 是与否枚举
 * 
 * @author Administrator
 * 
 */
public enum OrgType  {
	ORG("组织"), DEPT("部门"),STORE("门店");

	private final String desc;

	private OrgType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public String getName() {
		return this.name();
	}

	public static OrgType find(String name) {
		if (StringUtils.isEmpty(name)) {
			return null;
		}
		for (OrgType ele : OrgType.values()) {
			if (name.equalsIgnoreCase(ele.name())) {
				return ele;
			}
		}
		return null;
	}
}

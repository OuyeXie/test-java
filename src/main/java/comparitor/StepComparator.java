package comparitor;

import java.util.Comparator;
import java.util.Date;

/**
 * 对Step类进行排序
 * 
 * @author Administrator
 *
 */
public class StepComparator implements Comparator<Step> {

	/**
	 * 如果o1小于o2,返回一个负数;如果o1大于o2，返回一个正数;如果他们相等，则返回0;
	 */
	@Override
	public int compare(Step o1, Step o2) {
//		Date acceptTime1 = UtilTool.strToDate(o1.getAcceptTime(), null);
//		Date acceptTime2 = UtilTool.strToDate(o2.getAcceptTime(), null);
//
//		// 对日期字段进行升序，如果欲降序可采用before方法
//		if (acceptTime1.after(acceptTime2))
//			return 1;
		return -1;
	}

}

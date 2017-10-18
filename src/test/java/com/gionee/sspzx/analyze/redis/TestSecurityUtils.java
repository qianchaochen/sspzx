package com.gionee.sspzx.analyze.redis;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.util.StringUtils;

/**
 * @author dingyw
 *
 * 2017年3月25日
 */
public class TestSecurityUtils {
	/**获取map各字段，并且排序
     * @param vo
     */
    public static List<Entry<String,String>> getAscList(Map<String,String> map){
    	
		Set<Entry<String,String>> mapEntries = map.entrySet();  
		List<Entry<String,String>> list = new LinkedList<Entry<String,String>>(mapEntries);  
		Collections.sort(list, new Comparator<Entry<String,String>>() {  
            @Override  
            public int compare(Entry<String, String> ele1,  
  
                    Entry<String, String> ele2) {  
                return ele1.getKey().compareTo(ele2.getKey());  
            }  
  
        });  
		return list;
    }
    /**根据list获取各字段，拼接成a=1&b=2等字符串
	 * @param list
	 * @return
	 */
	public static String listToStr(List<Entry<String,String>>  list){
		
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<list.size()-1;i++){
			Entry<String,String> temp=list.get(i);
			if(StringUtils.isEmpty(temp.getValue())){
				sb.append(temp.getKey()+"=&");
			}
			else{
				sb.append(temp.getKey()+"="+temp.getValue()+"&");
			}
		}
		//处理最后一个
		Entry<String,String> temp=list.get(list.size()-1);
		if(StringUtils.isEmpty(temp.getValue())){
			sb.append(temp.getKey()+"=");
		}
		else{
			sb.append(temp.getKey()+"="+temp.getValue());
		}
		
		return sb.toString();
	}
	

}

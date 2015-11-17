package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Gang Zhanhui
 * @since 11.14
 * @version 1.02 2015-11-17
 */
public class Main {
	public static int Len_F1 = 0;
	public static int Len_F2 = 0;
	public static boolean flag = false;//标志文件1是否读取完毕
	public static void main(String[] args) throws IOException {
		List<String> twoFilelist = new ArrayList<String>();
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> uniqueList1 = new ArrayList<String>();
		List<String> uniqueList2 = new ArrayList<String>();
		List<String> same = new ArrayList<String>();
		Map<String,Integer> map_file1 = new HashMap<String,Integer>();
		Map<String,Integer> map_file2 = new HashMap<String,Integer>();
		String string,str1="",str2="";
		FileReader fr1 = null;
		FileReader fr2 = null;
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		File directoryFile = new File("");
		//E:\workspace\LeetCode
		/*String pathString = directoryFile.getCanonicalPath();
		System.out.println(pathString);*/
		String pathString = System.getProperty("user.dir");//user.dir指定了当前的路径
		/*URL url = Main.class.getResource("");
		String pathString = url.toString();
		System.out.println(pathString);*/
		try{
			fr1 = new FileReader(pathString + "/src/file/test1.txt");
			fr2 = new FileReader(pathString + "/src/file/test2.txt");
			br1 = new BufferedReader(fr1);
			br2 = new BufferedReader(fr2);
			while((string = br1.readLine())!= null){
				str1 += string + " ";
			}
			System.out.print("文件一：" + str1.trim());
			//将每行数据转化为list
			uniqueList1 = toUniqueList(str1);
			/*System.out.print("词汇表1：");
			for(String list:list1){
				System.out.print(list + " ");
			}*/
			while((string = br2.readLine())!= null){
				str2 += string + " ";
			}
			System.out.print("\n文件二：" + str2.trim());
			uniqueList2 = toUniqueList(str2);
			/*System.out.print("\n词汇表2：");
			for(String list:list2){
				System.out.print(list + " ");
			}*/
			//1、读取两个不同的文本文件，输出两个文件总共的词汇表（即所有不重复的单词）
			System.out.print("\n两个文件中一共出现的单词： ");
			twoFilelist = Unionlist(uniqueList1,uniqueList2);
			for(String list:twoFilelist){
				System.out.print(list + " ");
			}
			//2、同时出现在两个文件中的单词
			System.out.print("\n同时出现在两个文件中的单词： ");
			same = TheSame(uniqueList1,uniqueList2);
			for(String list:same){
				System.out.print(list + " ");
			}
			//3.1  两个词汇表中包含的单词数
			System.out.println("\n两个词汇表包含的单词数分别为： " + uniqueList1.size() +" 和  " + uniqueList2.size());
			//3.2  连个词汇表中重叠的单词数
			System.out.print("重叠单词个数：" + same.size());
			//3.3 非重叠单词所占比例——先找到两个集合中各自独立的单词，在求比例
			list1 = toList(str1);
			list2 = toList(str2);
			//System.out.print("\n" + list1);
			int count = 2;
			for(String list:list1){
				if (!same.contains(list)) {
					//System.out.print("\n" + list + "\t" + map_file1.get(list));
					if(!map_file1.containsKey(list)){
						map_file1.put(list,1);
					}else
						map_file1.put(list, count++);
				}
			}
			//System.out.print("\nmap1" + map_file1);
			count = 2;
			for(String list:list2){
				if (!same.contains(list)) {
					//System.out.print("\n" + list + "\t" + map_file1.get(list));
					if(!map_file2.containsKey(list)){
						map_file2.put(list,1);
					}else
						map_file2.put(list, count++);
				}
			}
			Set<?> set = map_file1.entrySet();
			Iterator<?> i = set.iterator();
			System.out.print("\n文件1中独立的单词: ");
			while (i.hasNext()) {
				@SuppressWarnings("unchecked")
				Map.Entry<String, Integer> entry1 = (Map.Entry<String, Integer>) i.next();
				System.out.print(entry1.getKey() + "所占百分比为： " + entry1.getValue() + "/"
						+ Len_F1 + ": " + 100 * Float.valueOf(entry1.getValue()) / Len_F1 + "%\t");
			}
			//System.out.print("\nmap2" + map_file2);
			System.out.print("\n文件2中独立的单词");
			for(Entry<String, Integer> entry:map_file2.entrySet()){    
				System.out.print(entry.getKey() + "所占百分比为： " + map_file2.get(entry.getKey()) + "/"
						+ Len_F2 + ": " + 100 * Float.valueOf(map_file2.get(entry.getKey())) / Len_F2 + "%\t");
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	private static List<String> toList(String str1) {
		List<String> list = new ArrayList<String>();
		String s = str1.trim();
		if (s.length() == 0 || s.equals(" ")) {
			return list;
		} else {
			String[] str = s.split(" ");
			for (int i = 0; i < str.length; i++) {
				if (!str[i].equals("")){
					list.add(str[i]);
				}
			}
			flag = true;
			return list;
		}
	}
	//转化为无重复的list
	private static List<String> toUniqueList(String str1) {
		List<String> list = new ArrayList<String>();
		String s = str1.trim();
		if (s.length() == 0 || s.equals(" ")) {
			return list;
		} else {
			String[] str = s.split(" ");
			for (int i = 0; i < str.length; i++) {
				if (!str[i].equals("")){
					if(flag == false)
						Len_F1++;
					else
						Len_F2++;
					if(!list.contains(str[i]))
						list.add(str[i]);
				}
			}
			flag = true;
			return list;
		}
	}
	public static List<String> Unionlist(List<String> list1, List<String> list2) {
		List<String> union = new ArrayList<String>();
		union.addAll(list1);
		for(String list:list2)
			if (!union.contains(list)) {
				union.add(list);
			}
		return union;
	}
	public static List<String> TheSame(List<String> list1, List<String> list2) {
		List<String> same = new ArrayList<String>();
		for(String str1:list1){
			for(String str2:list2){
				if(str1.equals(str2))
					same.add(str1);
			}
		}
		return same;
	}
}

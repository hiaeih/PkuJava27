import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			// 读入第一个文件，并处理之
			BufferedReader br1 = new BufferedReader(new FileReader(
					"D:\\test1.txt"));
			String s1;
			StringBuffer sb1 = new StringBuffer();
			while ((s1 = br1.readLine()) != null) {
				sb1.append(s1);
			}
			StringTokenizer st1 = new StringTokenizer(sb1.toString(), ",.! \n");
			int number1 = st1.countTokens();
			System.out.println("第一个文件A含单词数为  " + number1);

			// 读入第二个文件，并处理之
			BufferedReader br2 = new BufferedReader(new FileReader(
					"D:\\test2.txt"));
			String s2;
			StringBuffer sb2 = new StringBuffer();
			while ((s2 = br2.readLine()) != null) {
				sb2.append(s2);
			}
			StringTokenizer st2 = new StringTokenizer(sb2.toString(), ",.! \n");
			int number2 = st2.countTokens();
			System.out.println("第二个文件B含单词数为  " + number2);

			// 将两个文件分别至于两个链表中
			ArrayList list1 = new ArrayList();
			while (st1.hasMoreTokens()) {
				String letter1 = st1.nextToken();
				list1.add(letter1);
			}
			ArrayList list2 = new ArrayList();
			while (st2.hasMoreTokens()) {
				String letter2 = st2.nextToken();
				list2.add(letter2);
			}

			// 求两个文件的交集（去掉了重复的单词）
			ArrayList list3 = new ArrayList();
			for (int i = 0; i < list1.size(); i++) {
				Object t1 = list1.get(i);
				for (int j = 0; j < list2.size(); j++) {
					Object t2 = list2.get(j);
					if (!list3.contains(t1) && t1.equals(t2)) {
						list3.add(t1);
					}
				}
			}
			System.out.println("两文件共有的单词数为  " + list3.size());
			System.out.println("\n");
			System.out.println("两文件单词的交集为 : " );
			for (int k = 0; k < list3.size(); k++) {
				System.out.print(list3.get(k) + " ");
			}
			System.out.print("\n");
			System.out.println("\n");

			// 求两个文件的并集（去掉了重复的单词）
			ArrayList list4 = new ArrayList();
			for (int i = 0; i < list1.size(); i++) {
				Object t1 = list1.get(i);
					if (!list4.contains(t1) ) {
						list4.add(t1);
					}
				}
			for (int i = 0; i < list2.size(); i++) {
				Object t2 = list2.get(i);
					if (!list4.contains(t2) ) {
						list4.add(t2);
					}
				}
			System.out.println("两文件不相重复的总有的单词数为  " + list4.size());
			System.out.println("两文件单词的并集为 : " );
			int count=0;
			for (int k = 0; k < list4.size(); k++) {
				System.out.print(list4.get(k) + " ");
				count++;
				if(count==8){
					System.out.println("\n");
					count=0;
				}
			}
			System.out.println("\n");
			System.out.println("\n");
			
			//wd∈A 且wd ∉B的单词总数占A文件的百分比
			ArrayList list5 = new ArrayList();
			list5=(ArrayList) list1.clone();
			for (int j = 0; j < list2.size(); j++) {
					Object t2 = list2.get(j);
					if (list5.contains(t2)) {
						list5.remove(t2);
					}
				}
			System.out.println("wd∈A 且wd ∉B的单词总数占A文件的百分比是：  " + 1.0*list5.size()/list1.size()*100+"%");
			System.out.println("\n");
			
			//wd∈A 且wd ∉B的每个单词占A文件的百分比
			 Map<String,Integer> map = new HashMap<String, Integer>();
			 for (int j = 0; j < list5.size(); j++) {
                 int count1;
                 if (map.get(list5.get(j)) == null) {
                     count1 = 1;
                 } else {
                     count1 = map.get(list5.get(j)).intValue() + 1;
                 }
                 map.put((String) list5.get(j),count1);
			 }
			 Set<WordEntity> set = new TreeSet<WordEntity>();
             for (String key : map.keySet()) {
                 set.add(new WordEntity(key,map.get(key)));
             }
             System.out.println("输出wd∈A 且wd ∉B的每个单词占A的百分比：");
             System.out.println("\n");
             for (Iterator<WordEntity> it = set.iterator(); it.hasNext(); ) {
                 WordEntity w = it.next();
                 System.out.println("单词:" + w.getKey() + " 出现的百分比为： " + 1.0*w.getCount()/list1.size()*100+"%");
             }
             System.out.println("\n");
			
			//wd∈B且 wd∉A的单词总数占B文件的百分比
			ArrayList list6 = new ArrayList();
			list6=(ArrayList) list2.clone();
			for (int j = 0; j < list1.size(); j++) {
					Object t1 = list1.get(j);
					if (list6.contains(t1)) {
						list6.remove(t1);
					}
				}
			System.out.println("wd∈B 且wd ∉A的单词总数占B文件的百分比是：  " + 1.0*list6.size()/list2.size()*100+"%");
			System.out.println("\n");
			//wd∈B 且wd ∉A的每个单词占B文件的百分比
			 Map<String,Integer> map1 = new HashMap<String, Integer>();
			 for (int j = 0; j < list6.size(); j++) {
                int count2;
                if (map1.get(list6.get(j)) == null) {
                    count2 = 1;
                } else {
                    count2 = map1.get(list6.get(j)).intValue() + 1;
                }
                map1.put((String) list6.get(j),count2);
			 }
			 Set<WordEntity> set1 = new TreeSet<WordEntity>();
            for (String key : map1.keySet()) {
                set1.add(new WordEntity(key,map1.get(key)));
            }
            System.out.println("输出wd∈B 且wd ∉A的每个单词占B的百分比：");
            System.out.println("\n");
            for (Iterator<WordEntity> it1 = set1.iterator(); it1.hasNext(); ) {
                WordEntity w1 = it1.next();
                System.out.println("单词:" + w1.getKey() + " 出现的百分比为： " + 1.0*w1.getCount()/list2.size()*100+"%");
            }
            System.out.println("\n");
            System.out.println("\n");
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println("文件未找到~！");
		} catch (IOException e) {
			System.out.println("文件读异常~！");
		}
	}
}
